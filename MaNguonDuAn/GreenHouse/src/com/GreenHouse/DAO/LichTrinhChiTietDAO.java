/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelDiemThamQuan;
import com.GreenHouse.Model.ModelLichTrinhChiTiet;
import com.GreenHouse.Swing.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thuy
 */
public class LichTrinhChiTietDAO extends JDBCHelper {

    public void selectbyTimKiem(JTable tbl, String key) {
        try {
            String sql = "select ROW_NUMBER() Over (Order by MaTour),* from LichTrinhChiTiet where MaLichTrinh like '%" + key + "%' or MaTour LIKE '%" + key + "%' or MocThoiGian like '%" + key + "%' or NoiThamQuan like N'%" + key + "%' or GhiChu like N'%" + key + "'";
            String[] header = new String[]{"STT", "ID", "Mã lịch trình", "Mã Tour", "Mốc thời gian", "Nơi tham gian", "Ghi chú"};
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel model = new DefaultTableModel(header, 0);

            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getInt(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                data.add(rs.getString(6));
                data.add(rs.getString(7));

                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Them(ModelLichTrinhChiTiet ltct) {

        try {
            String sql = "Insert into LichTrinhChiTiet values(?,?,?,?,?)";
            Object[] obj = new Object[]{ltct.getMaLt(), ltct.getMaTour(), ltct.getTgDiChuyen(), ltct.getNoiThamQuan(), ltct.getGhiChu()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex);
        }

    }

    public void CapNhat(ModelLichTrinhChiTiet ltct) {
        try {
            String sql = "Update LichTrinhChiTiet set MocThoiGian = ?,NoiThamQuan = ?, GhiChu = ? where ID = ?";
            Object[] obj = new Object[]{ltct.getTgDiChuyen(), ltct.getNoiThamQuan(), ltct.getGhiChu(), ltct.getId()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ModelDiemThamQuan layNoiThamQuan(String ma) throws SQLException {
        ModelDiemThamQuan tq = new ModelDiemThamQuan();
        PreparedStatement p = con.prepareStatement("select TenDiaDiem FROM dbo.DiemThamQuan a join SetDiemThamQuan b on a.MaDiaDiem = b.MaDiaDiem join Tour c on b.MaTour = c.MaTour where = '" + ma + "'");
        ResultSet r = p.executeQuery();
        if (r.next()) {

            tq.setTenDiaDiem(r.getString("TenDiaDiem"));

        }
        return tq;
    }

    public List<String> selectNoiThamQuan() throws SQLException {
        String sql = "select TenDiaDiem FROM dbo.DiemThamQuan";
        List<String> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.query(sql);
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        rs.getStatement().getConnection().close();
        // System.out.print(list);
        return list;

    }

    public ModelDiemThamQuan selectTinhThanhByTenDiaDiem(String id) throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from DiemThamQuan where TenDiaDiem LIKE N'%" + id + "%'");
        ResultSet r = p.executeQuery();
        ModelDiemThamQuan mt = new ModelDiemThamQuan();
        if (r.next()) {
            mt.setMaDiaDiem(r.getString("MaDiaDiem"));
            mt.setTenDiaDiem(r.getString("TenDiaDiem"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setMoTa(r.getString("MoTa"));
            mt.setTinh(r.getString("Tinh"));
            mt.setHinhAnh(r.getString("HinhAnh"));
        }
        p.close();
        r.close();
        return mt;
    }

    public ArrayList<ModelDiemThamQuan> selectDiemThamQuanByTinhThanh(String tinhThanh) throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from DiemThamQuan where Tinh = ?");
        p.setString(1, tinhThanh);
        ResultSet r = p.executeQuery();
        ArrayList<ModelDiemThamQuan> list = new ArrayList<>();
        while (r.next()) {
            ModelDiemThamQuan mt = new ModelDiemThamQuan();
            mt.setMaDiaDiem(r.getString("MaDiaDiem"));
            mt.setTenDiaDiem(r.getString("TenDiaDiem"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setMoTa(r.getString("MoTa"));
            mt.setTinh(r.getString("Tinh"));
            mt.setHinhAnh(r.getString("HinhAnh"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public ArrayList<ModelLichTrinhChiTiet> fillTableLTCT(String malt) throws SQLException {
        String sql = "select * FROM LichTrinhChiTiet WHERE MaLichTrinh = ?";
        PreparedStatement p = con.prepareStatement(sql);

        p.setString(1, malt);
        ResultSet r = p.executeQuery();
        ArrayList<ModelLichTrinhChiTiet> list = new ArrayList<>();
        while (r.next()) {
            ModelLichTrinhChiTiet mt = new ModelLichTrinhChiTiet();
            mt.setId(r.getInt("ID"));
            mt.setMaLt(r.getString("MaLichTrinh"));
            mt.setMaTour(r.getString("MaTour"));
            mt.setTgDiChuyen(r.getDate("MocThoiGian"));
            mt.setNoiThamQuan(r.getString("NoiThamQuan"));
            mt.setGhiChu(r.getString("GhiChu"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "ID", "Mã lịch trình", "Mã Tour", "Mốc thời gian", "Nơi tham gian", "Ghi chú"};
            String sql = "select ROW_NUMBER() Over (Order by MaTour),* FROM LichTrinhChiTiet";
            new Table().statement(sql, tbl, header);

        } catch (Exception ex) {
            System.out.print(ex);

        }
    }

    public void hienThi(JTable tbl, ModelLichTrinhChiTiet ltct, int i) {
        try {
            ltct.setMaTour(String.valueOf(tbl.getValueAt(i, 3)));
            ltct.setMaLt(String.valueOf(tbl.getValueAt(i, 2)));
            ltct.setTgDiChuyen((Date) tbl.getValueAt(i, 4));
            ltct.setNoiThamQuan(String.valueOf(tbl.getValueAt(i, 5)));
            ltct.setGhiChu(String.valueOf(tbl.getValueAt(i, 6)));
            ltct.setId(Integer.parseInt(tbl.getValueAt(i, 1).toString()));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public ArrayList<ModelDiemThamQuan> selectDiemThamQuanByTenTour(String maTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("select * FROM dbo.DiemThamQuan JOIN dbo.SetDiemThamQuan ON SetDiemThamQuan.MaDiaDiem = DiemThamQuan.MaDiaDiem WHERE MaTour = '" + maTour + "'");
        ResultSet r = p.executeQuery();
        ArrayList<ModelDiemThamQuan> list = new ArrayList<>();
        while (r.next()) {
            ModelDiemThamQuan mt = new ModelDiemThamQuan();
            mt.setMaDiaDiem(r.getString("MaDiaDiem"));
            mt.setTenDiaDiem(r.getString("TenDiaDiem"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setHinhAnh(r.getString("HinhAnh"));
            mt.setMoTa(r.getString("MoTa"));
            list.add(mt);
        }
        return list;
    }

    public boolean checkMocThoiGian_CT(String maLichTrinh, Date mocThoiGian) throws SQLException {
        boolean kt = true;
        PreparedStatement p = con.prepareStatement("select * from LichTrinh where MaLichTrinh = '" + maLichTrinh + "'");
        ResultSet r = p.executeQuery();
        if (r.next()) {
            if (r.getDate("NgayKhoiHanh").after(mocThoiGian) || r.getDate("NgayKetThuc").before(mocThoiGian)) {
                kt = false;
            }
        }
        return kt;//Ko co loi thi False
    }

}
