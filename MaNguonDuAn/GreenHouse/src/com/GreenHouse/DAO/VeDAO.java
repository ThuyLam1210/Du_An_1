/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelKhachHang;
import com.GreenHouse.Model.ModelNhanVien;
import com.GreenHouse.Model.ModelTour;
import com.GreenHouse.Model.ModelVe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class VeDAO extends JDBCHelper {

    public List<Integer> selectNam() {
        try {
            String sql = "select distinct Year(NgayBan) as 'NAM' from [QuanLy_TourDuLichGreenHouse].[dbo].[Ve]";
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
            System.out.print(ex);
            throw new RuntimeException(ex);

        }
    }

    public ModelTour selectTourByMaLT(String maLichTrinh) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT b.* FROM dbo.LichTrinhChiTiet a JOIN dbo.Tour b ON b.MaTour = a.MaTour WHERE a.MaLichTrinh = ? GROUP BY a.MaLichTrinh, b.MaTour, b.TenTour, b.GiaTour, b.SoNgay, b.SoDem, b.SoLuongKhach, b.HinhAnh");
        p.setString(1, maLichTrinh);
        ResultSet r = p.executeQuery();
        ModelTour mt = new ModelTour();
        if (r.next()) {
            mt.setMaTour(r.getString("MaTour"));
            mt.setTenTour(r.getString("TenTour"));
            mt.setSoNgay(r.getInt("SoNgay"));
            mt.setSoDem(r.getInt("SoDem"));
            mt.setGiaTour(r.getFloat("GiaTour"));
            mt.setSoLuongKhach(r.getInt("SoLuongKhach"));
            mt.setHinhAnh(r.getString("HinhAnh"));
        }
        return mt;
    }
    String selectID = "select * from Ve where MaVe = ?";

    public ModelVe checkMaVe(String ma) throws SQLException {
        List< ModelVe> list = this.selectVeAll1(selectID, ma);
        return list.size() > 0 ? list.get(0) : null;

    }

    public ArrayList<ModelVe> selectVeAll1(String sql, Object... args) throws SQLException {

        ArrayList<ModelVe> list = new ArrayList<ModelVe>();
        ResultSet r = JDBCHelper.query(sql, args);
        while (r.next()) {
            ModelVe mt = new ModelVe();
            mt.setMaVe(r.getString("MaVe"));
            mt.setMaNhanVien(r.getString("MaNhanVien"));
            mt.setMaKhachHang(r.getString("MaKhachHang"));
            mt.setMaLichTrinh(r.getString("MaLichTrinh"));
            mt.setSoLuongNguoiLon(r.getInt("SoLuongVeNguoiLon"));
            mt.setSoLuongTreEm(r.getInt("SoLuongVeTreEm"));
            mt.setTongTien(r.getDouble("TongTien"));
            mt.setNgayBan(r.getDate("NgayBan"));
            list.add(mt);
        }
        r.getStatement().getConnection().close();
        return list;
    }

    public ArrayList<ModelVe> selectVeAll() throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from Ve");
        ResultSet r = p.executeQuery();
        ArrayList<ModelVe> list = new ArrayList<>();
        while (r.next()) {
            ModelVe mt = new ModelVe();
            mt.setMaVe(r.getString("MaVe"));
            mt.setMaNhanVien(r.getString("MaNhanVien"));
            mt.setMaKhachHang(r.getString("MaKhachHang"));
            mt.setMaLichTrinh(r.getString("MaLichTrinh"));
            mt.setSoLuongNguoiLon(r.getInt("SoLuongVeNguoiLon"));
            mt.setSoLuongTreEm(r.getInt("SoLuongVeTreEm"));
            mt.setTongTien(r.getDouble("TongTien"));
            mt.setNgayBan(r.getDate("NgayBan"));
            list.add(mt);
        }
        return list;
    }

    public boolean checkSoLuongVe(String maLT) throws SQLException {
        boolean kq = true;
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
        if (soLuongKhachCon <= 0) {
            kq = false;
        }
        return kq;// Đúng thì TRUE, sai thì FALSE
    }

    public void insertVe(ModelVe mt) throws SQLException {
        PreparedStatement p = con.prepareStatement("INSERT INTO dbo.Ve VALUES (?,?,?,?,?,?,?,?,?)");
        p.setString(1, mt.getMaVe());
        p.setString(2, mt.getMaTour());
        p.setString(3, mt.getMaNhanVien());
        p.setString(4, mt.getMaKhachHang());
        p.setString(5, mt.getMaLichTrinh());
        p.setInt(6, mt.getSoLuongNguoiLon());
        p.setInt(7, mt.getSoLuongTreEm());
        p.setDouble(8, mt.getTongTien());
        p.setObject(9, mt.getNgayBan());
        p.executeUpdate();
        p.close();
    }

    public boolean checkDuplicateMaVe(String maVe) throws SQLException {
        boolean kt = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.Ve WHERE MaVe = ?");
        p.setString(1, maVe);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            kt = true;
        }
        return kt;
    }

    public String selectMaLTByMaTour(String maTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT DISTINCT MaLichTrinh FROM dbo.LichTrinhChiTiet WHERE MaTour = '" + maTour + "'");
        ResultSet r = p.executeQuery();
        String maLichTrinh = "";
        if (r.next()) {
            maLichTrinh = r.getString(1);
        }
        return maLichTrinh;
    }

    public void selectVeByAnyThing(String key, JTable tbl) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        String sql = "SELECT * FROM dbo.Ve WHERE \n"
                + "MaVe LIKE '%" + key + "%' OR MaNhanVien LIKE '%" + key + "%' OR MaKhachHang LIKE '%" + key + "%' OR MaLichTrinh LIKE '%" + key + "%'";
        PreparedStatement p = con.prepareStatement(sql);

        if (key.matches("[0-9]")) {
            sql = "SELECT * FROM dbo.Ve WHERE \n"
                    + "MaVe LIKE '%" + key + "%' OR MaNhanVien LIKE '%" + key + "%' OR MaKhachHang LIKE '%" + key + "%' OR MaLichTrinh LIKE '%" + key + "%'\n"
                    + "OR SoLuongVeNguoiLon = ? OR SoLuongVeTreEm = ? OR TongTien = ?";
            p = con.prepareStatement(sql);
            int so = Integer.parseInt(key);
            p.setInt(1, so);
            p.setInt(2, so);
            p.setInt(3, so);
        }
        ResultSet r = p.executeQuery();
        model.setRowCount(0);
        int j = 0;
        DecimalFormat df = new DecimalFormat("#,###");
        while (r.next()) {
            Vector data = new Vector();
            data.add(++j);
            for (int i = 0; i < tbl.getColumnCount(); i++) {
                if (i + 1 == 8) {
                    data.add(df.format(r.getObject(i + 1)));
                } else {
                    data.add(r.getObject(i + 1));
                }
            }
            data.remove(2);
            model.addRow(data);
        }
    }

    public String generateMaVE() {
        DecimalFormat df = new DecimalFormat("0000");
        Random ran = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String numRandom = df.format(ran.nextInt(10000));  //  Random from 0 to 9999
        String date = dateFormat.format(new Date().getTime());
        date = date.replace("-", "");
        String maVE = "VE" + date + numRandom;

        while (checkDuplicateMaVE(maVE)) {
            numRandom = df.format(ran.nextInt(10000));
            maVE = "VE" + numRandom;
        }

        return maVE;

    }

    private boolean checkDuplicateMaVE(String maVE) {
        boolean duplicate = false;
        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.HopDong WHERE MaVe = '" + maVE + "'");
            ResultSet r = p.executeQuery();
            if (r.next()) {
                duplicate = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HopDongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return duplicate;

    }

    //=================
    public ModelKhachHang selectKhachHangByID(String x) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachHang WHERE MaKhachHang = ?");
        p.setString(1, x);
        ResultSet r = p.executeQuery();
        ModelKhachHang mt = new ModelKhachHang();
        if (r.next()) {
            mt.setId(r.getString("MaKhachHang"));
            mt.setName(r.getString("TenKhachHang"));
            mt.setSex(r.getBoolean("GioiTinh"));
            mt.setCccd(r.getString("CMND_CCCD"));
            mt.setNumberPhone(r.getString("SDT"));
            mt.setEmail(r.getString("Email"));
            mt.setAdress(r.getString("DiaChi"));
        }
        return mt;
    }

    public ModelNhanVien selectNhanVienByID(String x) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT * FROM NhanVien WHERE MaNhanVien = ?");
        p.setString(1, x);
        ResultSet r = p.executeQuery();
        ModelNhanVien mt = new ModelNhanVien();
        if (r.next()) {
            mt.setMaNV(r.getString("MaNhanVien"));
            mt.setMaCV(r.getString("MaChucVu"));
            mt.setTenNV(r.getString("TenNhanVien"));
            mt.setGtinh(r.getBoolean("GioiTinh"));
            mt.setTrangthai(r.getBoolean("TrangThai"));
            mt.setCMND(r.getString("CMND_CCCD"));
            mt.setSDT(r.getString("SDT"));
            mt.setEmail(r.getString("Email"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setHinhAnh(r.getString("HinhAnh"));
        }
        return mt;
    }

    public void fillBangKhachHang(JTable tbl) throws SQLException {
        String[] header = new String[]{"STT", "Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính ", "CMND-CCCD", "Số Điện Thoại", "Email", "Địa Chỉ"};
        DefaultTableModel model = new DefaultTableModel(header, 0);

        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachHang");
        ResultSet r = p.executeQuery();
        int j = 0;
        while (r.next()) {
            Vector data = new Vector();
            data.add(++j);
            for (int i = 0; i < header.length - 1; i++) {
                data.add(r.getObject(i + 1));
            }
            model.addRow(data);
        }
        tbl.setModel(model);
        for (int i = 0; i < tbl.getRowCount(); i++) {
            String str = String.valueOf(tbl.getValueAt(i, 3));
            if (str.equals("true")) {
                tbl.setValueAt("Nam", i, 3);
            } else {
                tbl.setValueAt("Nữ", i, 3);
            }
        }
    }

}
