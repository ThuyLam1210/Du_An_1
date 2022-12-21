/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelKhachHang;
import com.GreenHouse.Swing.Table;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thuy
 */
public class KhachHangDAO extends JDBCHelper {

    public void tim(JTable tbl, String tim) throws SQLException {
        String sql = "Select * from KhachHang where " + " TenKhachHang like N'%" + tim + "%' or Email like '%" + tim + "%' or DiaChi like N'%" + tim + "%'";
        String[] header = new String[]{"STT", "Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính ", "CMND-CCCD", "Số Điện Thoại", "Email", "Địa Chỉ"};

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        DefaultTableModel model = new DefaultTableModel(header, 0);
        try {
            int j = 0;
            while (rs.next()) {
                Vector data = new Vector();
                data.add(++j);
                for (int i = 0; i < header.length - 1; i++) {
                    data.add(rs.getObject(i + 1));
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
        } catch (Exception e) {
            System.out.println("nó đâu rồi");
        }
    }

    public void timkiem(JTable tbl, String tim) {
        try {
            String sql = "SELECT  ROW_NUMBER() Over (Order by MaKhachHang),* FROM [dbo].KhachHang where TenKhachHang like N'%" + tim + "%' or Email like '%" + "%' or SDT like '%" + tim + "%' or DiaChi like N'%" + tim + "%' ";
            PreparedStatement ps = con.prepareStatement(sql);
            String[] header = new String[]{"STT", "Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính ", "CMND-CCCD", "Số Điện Thoại", "Email", "Địa Chỉ"};
            // new Table().statement(sql, tbl, header);

            DefaultTableModel model = new DefaultTableModel(header, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getBoolean(4));
                data.add(rs.getString(5));
                data.add(rs.getString(6));
                data.add(rs.getString(7));
                data.add(rs.getString(8));
                // data.add(rs.getString(9));
                //data.add(rs.getString(10));
                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);

            for (int i = 0; i < tbl.getRowCount(); i++) {
                String str = String.valueOf(tbl.getValueAt(i, 3));
                if (str.equals("true")) {
                    tbl.setValueAt("Nam", i, 3);
                } else {
                    tbl.setValueAt("Nữ", i, 3);
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void loadTable(JTable tbl) throws SQLException {
        try {
            String[] header = new String[]{"STT", "Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính ", "CMND-CCCD", "Số Điện Thoại", "Email", "Địa Chỉ"};
            String sql = "SELECT ROW_NUMBER() Over (Order by MaKhachHang),* FROM KhachHang";
            new Table().statement(sql, tbl, header);
            for (int i = 0; i < tbl.getRowCount(); i++) {
                String str = String.valueOf(tbl.getValueAt(i, 3));
                if (str.equals("true")) {
                    tbl.setValueAt("Nam", i, 3);
                } else {
                    tbl.setValueAt("Nữ", i, 3);
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }

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
            for (int i = 0; i < header.length-1; i++) {
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

    public boolean checkId(String user) throws SQLException {
        boolean Search = false;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM dbo.KhachHang WHERE MaKhachHang = ?");
        ps.setString(1, user);
        ResultSet r = ps.executeQuery();
        if (r.next()) {
            Search = true;
        }
        r.close();
        ps.close();
        return Search;
    }

    public void updateKH(ModelKhachHang ks) throws SQLException {
        PreparedStatement p = con.prepareStatement("update KhachHang set TenKhachHang = ? , GioiTinh = ?,CMND_CCCD = ?,SDT= ?,Email= ?,DiaChi= ? where MaKhachHang = ?");
        p.setString(7, ks.getId());
        p.setString(1, ks.getName());
        p.setBoolean(2, ks.isSex());
        p.setString(3, ks.getCccd());
        p.setString(5, ks.getEmail());
        p.setString(6, ks.getAdress());
        p.setString(4, ks.getNumberPhone());
        p.executeUpdate();
        p.close();
    }

    public ModelKhachHang TimMa(String ma) {
        try {
            String sql = "select * from KhachHang where MaKhachHang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ModelKhachHang hk = new ModelKhachHang(rs.getString(1));
                return hk;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;

    }

    public int add(ModelKhachHang kh) {
        try {
            String sql = "INSERT INTO KhachHang  VALUES(?,?,?,?,?,?,?)";
            Object[] obj = new Object[]{kh.getId(), kh.getName(), kh.isSex(), kh.getCccd(), kh.getNumberPhone(), kh.getEmail(), kh.getAdress()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.print(e);
        }
        return 0;
    }

    public int delete(ModelKhachHang kh) {
        try {
            PreparedStatement p = con.prepareStatement("DELETE FROM dbo.KhachHang WHERE MaKhachHang = ?");

            p.setString(1, kh.getId());
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean checkMaKH(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachHang WHERE MaKhachHang = ? ");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean checkEmail(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachHang WHERE EMAIL = ? ");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean checkCccd(String user) throws SQLException {
        boolean tim = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachHang WHERE CMND_CCCD = ? ");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            tim = true;
        }
        r.close();
        p.close();
        return tim;
    }

    public void hienThi(JTable tbl, ModelKhachHang kh, int i) {

        kh.setId(String.valueOf(tbl.getValueAt(i, 0)));
        kh.setName(String.valueOf(tbl.getValueAt(i, 1)));
        kh.setCccd(String.valueOf(tbl.getValueAt(i, 3)));
        kh.setNumberPhone(String.valueOf(tbl.getValueAt(i, 4)));
        kh.setEmail(String.valueOf(tbl.getValueAt(i, 5)));
        kh.setAdress(String.valueOf(tbl.getValueAt(i, 6)));
        if (String.valueOf(tbl.getValueAt(i, 2)).equalsIgnoreCase("Nam")) {
            kh.setSex(true);
        } else {
            kh.setSex(false);
        }

    }
}
