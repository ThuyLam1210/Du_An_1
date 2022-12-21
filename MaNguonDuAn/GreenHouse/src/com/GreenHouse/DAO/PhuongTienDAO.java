/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelLoaiPhuongTien;
import com.GreenHouse.Model.ModelPhuongTien;
import com.GreenHouse.Swing.Table;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Van Anh
 */
public class PhuongTienDAO extends JDBCHelper {

    DecimalFormat df = new DecimalFormat("#,###");

    public ArrayList<ModelLoaiPhuongTien> layDS_PhuongTien() {
        ArrayList<ModelLoaiPhuongTien> arr = new ArrayList<>();
        try {
            String sql = "select MaLoaiPT,TenLoaiPhuongTien from LoaiPhuongTien";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                ModelLoaiPhuongTien hk = new ModelLoaiPhuongTien(rs.getString(1), rs.getString(2));
                arr.add(hk);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return arr;
    }

    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "Mã Phương Tiện", "Mã Loại Phương Tiện", "Tên Phương Tiện", "Cước Phí", "Biển Số", " Số Lượng Chứa", "Ghi Chú"};
            DefaultTableModel model = (DefaultTableModel) tbl.getModel();
            PreparedStatement p = con.prepareStatement("select * from PhuongTien");
            ResultSet rs = p.executeQuery();
            int j = 0;
            while (rs.next()) {
                Vector data = new Vector();
                data.add(++j);
                data.add(rs.getString("MaPhuongTien"));
                data.add(rs.getString("MaLoaiPhuongTien"));
                data.add(rs.getString("TenPhuongTien"));
                data.add(df.format(rs.getFloat("CuocPhi")));
                data.add(rs.getString("BienSo"));
                data.add(rs.getInt("SoLuongChua"));
                data.add(rs.getString("GhiChu"));

                model.addRow(data);
            }
            tbl.setModel(model);

        } catch (SQLException ex) {
            System.out.print(ex);
        }

    }

    public boolean checkTrungPhuongTien(String maPhuongTien, String bienSo) throws SQLException {
        boolean tim = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.PhuongTien WHERE MaPhuongTien = ? or BienSo = ? ");
        p.setString(1, maPhuongTien);
        p.setString(2, bienSo);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            tim = true;
        }
        r.close();
        p.close();
        System.out.println(tim);
        return tim;
    }

    public int xoa(ModelPhuongTien pt) {
        try {
            PreparedStatement p = con.prepareStatement("DELETE FROM dbo.PhuongTien WHERE MaPhuongTien = ?");

            p.setString(1, pt.getMaPhuongTien());
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ModelPhuongTien timMa(String ma) {
        try {
            String sql = "select * from PhuongTien where MaPhuongTien = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ModelPhuongTien hk = new ModelPhuongTien(rs.getString(1));
                return hk;
            }
        } catch (Exception e) {
        }
        return null;

    }

    public int sua(ModelPhuongTien pt) {
        try {

            String sql = "update PhuongTien set MaLoaiPhuongTien = ? , TenPhuongTien = ?,CuocPhi = ?,BienSo= ?,SoLuongChua = ?,GhiChu= ? where MaPhuongTien = ?";
            Object[] obj = new Object[]{pt.getMaLoaiPhuongTien(), pt.getTenPhuongTien(), pt.getCuocPhi(), pt.getBienSo(), pt.getSoLuongChua(), pt.getGhiChu(), pt.getMaPhuongTien()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int them(ModelPhuongTien pt) {
        try {
            String sql = "INSERT INTO [dbo].[PhuongTien]\n"
                    + "     VALUES(?,?,?,?,?,?,?)";
            Object[] obj = new Object[]{pt.getMaPhuongTien(), pt.getMaLoaiPhuongTien(), pt.getTenPhuongTien(), pt.getCuocPhi(), pt.getBienSo(), pt.getSoLuongChua(), pt.getGhiChu()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.print(e);
        }
        return 0;
    }

    public void timkiem(JTable tbl, String tim) {
        try {
            String sql = "SELECT * FROM PhuongTien where " + "  MaPhuongTien like N'%" + tim + "%' or MaLoaiPhuongTien like '%" + tim + "%' or TenPhuongTien like '%" + tim + "%' or BienSo like N'%" + tim + "%' or SoLuongChua like N'%" + tim + "%'";
            String[] header = new String[]{"Mã Phương Tiện", "Mã Loại Phương Tiện", "Tên Phương Tiện", "Cước Phí", "Biển Số", "Số Lượng Chứa", "Ghi Chú"};
            new Table().statement(sql, tbl, header);
            tbl.removeAll();

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void hienThi(JTable tbl, ModelPhuongTien pt, int i) {

        pt.setMaPhuongTien(String.valueOf(tbl.getValueAt(i, 1)));
        //   tk.setID(tbl.getValueAt(i, 0).toString());
        pt.setMaLoaiPhuongTien(String.valueOf(tbl.getValueAt(i, 2)));
        pt.setTenPhuongTien(String.valueOf(tbl.getValueAt(i, 3)));
        pt.setCuocPhi(Float.valueOf(tbl.getValueAt(i, 4).toString().replace(",", "")));
        pt.setBienSo(String.valueOf(tbl.getValueAt(i, 5)));
        pt.setSoLuongChua(Integer.valueOf(tbl.getValueAt(i, 6).toString()));
        pt.setGhiChu(String.valueOf(tbl.getValueAt(i, 7)));

    }
}
