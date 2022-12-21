/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelLichTrinh;
import com.GreenHouse.Model.ModelTour;
import com.GreenHouse.Swing.Table;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thuy
 */
public class LichTrinhDAO extends JDBCHelper {
    public void CapNhatTrangThai() {
        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.LichTrinh");
            ResultSet r = p.executeQuery();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date now = df.parse(df.format(new Date().getTime()));
            while (r.next()) {
                if (r.getDate("NgayKhoiHanh").before(now)) {// Ngay khoi hanh trước ngay hien tai.
                    String sql = "UPDATE dbo.LichTrinh SET TrangThai = ? WHERE MaLichTrinh = ?";
                    p = con.prepareStatement(sql);
                    p.setString(1, "Kết thúc");
                    p.setString(2, r.getString("MaLichTrinh"));
                    p.executeUpdate();
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(LichTrinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
          ex.printStackTrace();
        }

    }
    public void CapNhat(ModelLichTrinh lt) {

        try {
            String sql = "Update LichTrinh set NoiXuatPhat = ?, NoiDen = ?, TrangThai = ? where MaLichTrinh = ?";
            Object[] obj = new Object[]{lt.getNoiXuatPhat(), lt.getNoiDen(), lt.getTrangThai(), lt.getMaLT()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void Them(ModelLichTrinh lt) throws SQLException {
        try {
            // SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
            String sql = "INSERT INTO dbo.LichTrinh Values(?,?,?,?,?,?)";
            Object[] obj = new Object[]{lt.getMaLT(), lt.getNgayBatDau(), lt.getNgayKetThuc(), lt.getNoiXuatPhat(), lt.getNoiDen(), lt.getTrangThai()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            ps.executeUpdate();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        //  return 0;
    }

    public int XoaLT(ModelLichTrinh lt) throws SQLException {

        String sql = "delete from LichTrinh where MaLichTrinh = ?";
        Object[] obj = new Object[]{lt.getMaLT()};
        PreparedStatement ps = new Table().prepareStatement(sql, obj);
        return ps.executeUpdate();

    }

    public ModelLichTrinh selectByMaLichTrinh(String ma) {
        String SQl = "Select * from dbo.LichTrinh where MaLichTrinh = ?";
        List<ModelLichTrinh> list = selectbySQL(SQl, ma);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    ///Hiển thị lên textfiel
    public void HienThi(JTable tbl, ModelLichTrinh lt, int i) throws ParseException {
        try {
            lt.setMaLT(String.valueOf(tbl.getValueAt(i, 0)));
            lt.setNgayBatDau((Date) tbl.getValueAt(i, 1));
            lt.setNgayKetThuc((Date) tbl.getValueAt(i, 2));
            lt.setNoiXuatPhat(String.valueOf(tbl.getValueAt(i, 3)));
            lt.setNoiDen(String.valueOf(tbl.getValueAt(i, 4)));
            lt.setTrangThai(String.valueOf(tbl.getValueAt(i, 5)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ModelLichTrinh timNVToDen(String ma) {
        try {
            String sql = "select * from LichTrinh where MaLichTrinh = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ModelLichTrinh sb = new ModelLichTrinh(rs.getString(1));
                return sb;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkMaLT(String ma) throws SQLException {

        boolean tim = false;
        PreparedStatement p = con.prepareStatement("Select * from dbo.LichTrinh where MaLichTrinh = ?");
        p.setString(1, ma);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            tim = true;

        }
        r.close();
        p.close();
        return tim;
    }
    // public void insert()

    public ModelTour selectTenTour(String ten) throws SQLException {
        ModelTour lt = new ModelTour();
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.Tour WHERE TenTour = N'" + ten + "'");
        ResultSet r = p.executeQuery();
        if (r.next()) {
            lt.setMaTour(r.getString("MaTour"));
            lt.setTenTour(r.getString("TenTour"));
            lt.setSoNgay(r.getInt("SoNgay"));
            lt.setSoDem(r.getInt("SoDem"));
            lt.setGiaTour(r.getFloat("GiaTour"));
            lt.setSoLuongKhach(r.getInt("SoLuongKhach"));
            lt.setHinhAnh(r.getString("HinhAnh"));

        }
        return lt;
    }

    public List<String> selectTGLichTrinh(String maTour) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "select DISTINCT N' Từ '+ CONVERT(varchar,NgayKhoiHanh,105)  + N' Đến ' + CONVERT(varchar,NgayKetThuc,105)  from LichTrinh a JOIN dbo.LichTrinhChiTiet b ON b.MaLichTrinh = a.MaLichTrinh WHERE MaTour = '" + maTour + "'";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            p.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public List<Integer> selectNam() {
        try {
            String sql = "SELECT DISTINCT YEAR(NgayKetThuc) AS 'NAM' FROM LichTrinh";
            List<Integer> list = new ArrayList<>();

            ResultSet rs = JDBCHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            // System.out.print(list);
            return list;

            //  return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);

        }
    }

    public List<String> selectTenTour() {
        try {
            String sql = "select TenTour from Tour ";
            List<String> list = new ArrayList<>();

            ResultSet rs = JDBCHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            rs.getStatement().getConnection().close();
            // System.out.print(list);
            return list;

            //  return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);

        }
    }
    //public List<ModelLichTrinh> select(String )

    public void loadTable(JTable tbl, String ngayKhoiHanh, String ngayKetThuc) {
        try {
            String[] header = new String[]{"Mã lịch trình", "Tên Tour", "Số Lượng Khách", "Ngày khởi hành", "Ngày kết thúc", "Nơi xuất phát", "Nơi đến", "Trạng thái"};
            String sql = "select LT.MaLichTrinh,TenTour,SoLuongKhach,NoiXuatPhat,NoiDen,TrangThai from LichTrinh LT join LichTrinhChiTiet LTCT on LT.MaLichTrinh = LTCT.MaLichTrinh Join  dbo.Tour on Tour.MaTour = LTCT.MaTour  WHERE LT.NgayKhoiHanh = '" + ngayKhoiHanh + "' AND LT.NgayKetThuc = '" + ngayKetThuc + "' group by LT.MaLichTrinh,TenTour,SoLuongKhach,NoiXuatPhat,NoiDen,TrangThai";
            new Table().statement(sql, tbl, header);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadTableLichTrinh(JTable tbl) {
        try {
            String[] header = new String[]{"Mã lịch trình", "Ngày khởi hành", "Ngày kết thúc", "Nơi xuất phát", "Nơi đến", "Trạng thái"};
            String sql = "select * from LichTrinh ";
            new Table().statement(sql, tbl, header);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void selectbyTimKiem(JTable tbl, String key) {
        try {
            String sql = "select * from LichTrinh where MaLichTrinh like '%" + key + "%' or NgayKhoiHanh LIKE '%" + key + "%' or NgayKetThuc like '%" + key + "%' or NoiXuatPhat like N'%" + key + "%' or NoiDen like N'%" + key + "' or TrangThai like N'%" + key + "%'";
            String[] header = new String[]{"Mã lịch trình", "Ngày khởi hành", "Ngày kết thúc", "Nơi xuất phát", "Nơi đến", "Trạng thái"};

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel model = new DefaultTableModel(header, 0);
            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                data.add(rs.getString(6));

                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<ModelLichTrinh> selectbySQL(String sql, Object... args) {
        List<ModelLichTrinh> list = new ArrayList<ModelLichTrinh>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                ModelLichTrinh lt = new ModelLichTrinh();
                lt.setMaLT(rs.getString("MaLichTrinh"));
                lt.setNgayBatDau(rs.getDate("NgayKhoiHanh"));
                lt.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                lt.setNoiXuatPhat(rs.getString("NoiXuatPhat"));
                lt.setNoiDen(rs.getString("NoiDen"));
                lt.setTrangThai(rs.getString("TrangThai"));
                list.add(lt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public ArrayList<ModelLichTrinh> selectLichTrinhByNgay(Date ngayKhoiHanh, Date ngayKetThuc) throws SQLException {
        PreparedStatement p = con.prepareStatement("select LT.MaLichTrinh,TenTour,SoLuongKhach,NoiXuatPhat,NoiDen,TrangThai from LichTrinh LT join LichTrinhChiTiet LTCT on LT.MaLichTrinh = LTCT.MaLichTrinh Join  dbo.Tour on Tour.MaTour = LTCT.MaTour  WHERE LT.NgayKhoiHanh = ? AND LT.NgayKetThuc = ? group by LT.MaLichTrinh,TenTour,SoLuongKhach,NoiXuatPhat,NoiDen,TrangThai");
        //SimpleDateFormat sdf = new SimpleDateFormat
        p.setObject(1, ngayKhoiHanh);
        p.setObject(2, ngayKetThuc);
        ResultSet r = p.executeQuery();
        ArrayList<ModelLichTrinh> list = new ArrayList<>();
        while (r.next()) {
            ModelLichTrinh mt = new ModelLichTrinh();
            mt.setMaLT(r.getString("MaLichTrinh"));
            mt.setNoiXuatPhat(r.getString("NoiXuatPhat"));
            mt.setNoiDen(r.getString("NoiDen"));
            mt.setTrangThai(r.getString("TrangThai"));
            list.add(mt);
        }
        return list;
    }

    public int getSoLuongKhachCon(String maLT) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT DISTINCT c.SoLuongKhach FROM dbo.LichTrinh a JOIN dbo.LichTrinhChiTiet b ON b.MaLichTrinh = a.MaLichTrinh JOIN dbo.Tour c ON c.MaTour = b.MaTour WHERE a.MaLichTrinh = '" + maLT + "'");
        ResultSet r = p.executeQuery();
        int soLuongKhach = 0;
        if (r.next()) {
            soLuongKhach = r.getInt("SoLuongKhach");
        }
        p = con.prepareStatement("SELECT SUM(SoLuongVeNguoiLon) + SUM(SoLuongVeTreEm) AS 'SoLuongVe' FROM dbo.Ve WHERE MaLichTrinh = '" + maLT + "'");
        r = p.executeQuery();
        int soLuongVe = 0;
        if (r.next()) {
            soLuongVe = r.getInt("SoLuongVe");
        }
        r.close();
        p.close();
        int soLuongKhachCon = soLuongKhach - soLuongVe;
        return soLuongKhachCon;
    }

    /*   public void LoadDateTable_HDCT() throws SQLException {
    
    String sql = "Select MaHDCT, HDCT.MaHD,TenSach,GiaBan,SoLuongMua,TongTien from ChiTietHoaDon HDCT JOIN Sach On Sach.MaSach = HDCT.MaSach JOIN HoaDon On HoaDon.MAHD = HDCT.MaHD ";
    ResultSet r = JDBCHelper.ExcuteQueryGetTable(sql);
    Object[] obj = new Object[]{"STT", "MAHDCT", "MAHOADON", "TÊN SÁCH", "GIÁ BÁN", "SỐ LƯỢNG MUA", "TỔNG TIỀN"};
    DefaultTableModel model = new DefaultTableModel(obj, 0);
    //DecimalFormat df = new DecimalFormat("#,## VND0");
    tblListHDCT.setModel(model);
    //   model.setRowCount(0);
    int c = 0;
    try {
    while (r.next()) {
    c++;
    Object[] item = new Object[7];
    item[0] = c;
    item[1] = r.getInt("MaHDCT");
    item[2] = r.getInt("MaHD");
    item[3] = r.getString("TenSach");
    item[4] = r.getDouble("GiaBan");
    item[5] = r.getInt("SoLuongMua");
    
    item[6] = r.getDouble("TongTien");
    
    model.addRow(item);
    }
    } catch (SQLException ex) {
    System.out.println(ex.toString());
    }
    
    }*/
}
