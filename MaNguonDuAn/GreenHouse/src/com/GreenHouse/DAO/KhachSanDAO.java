/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelKhachSan;
import com.GreenHouse.Swing.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Van Anh
 */
public class KhachSanDAO extends JDBCHelper {
    
    DecimalFormat df = new DecimalFormat("#,###");
    
    public ModelKhachSan timMa(String ma) {
        try {
            String sql = "select * from KhachSan where MaKhachSan = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ModelKhachSan hk = new ModelKhachSan(rs.getString(1));
                return hk;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public boolean checkMaks(String ma) throws SQLException {
        boolean tim = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachSan WHERE MaKhachSan = ? ");
        p.setString(1, ma);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            tim = true;
        }
        r.close();
        p.close();
        return tim;
    }
    
    public ArrayList<ModelKhachSan> selectTinh() throws SQLException {
        PreparedStatement p = con.prepareStatement("select Tinh from KhachSan Group by Tinh");
        ResultSet r = p.executeQuery();
        ArrayList<ModelKhachSan> list = new ArrayList<>();
        while (r.next()) {
            ModelKhachSan mt = new ModelKhachSan();
            mt.setTinh(r.getString("Tinh"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
        
    }
    
    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "Mã Khách Sạn", "Tên Khách Sạn", "Giá", "Xếp Hạng", "Số Điện Thoại", "Địa Chỉ", "Tỉnh", "Mô Tả"};
            //String sql = "SELECT * FROM KhachSan";
            DefaultTableModel model = (DefaultTableModel) tbl.getModel();
            model.setRowCount(0);
            PreparedStatement p = con.prepareStatement("SELECT * FROM KhachSan");
            ResultSet rs = p.executeQuery();
            int j = 0;
            while (rs.next()) {
                Vector data = new Vector();
                data.add(++j);
                data.add(rs.getString("MaKhachSan"));
                data.add(rs.getString("TenKhachSan"));
                data.add(df.format(rs.getFloat("Gia")));
                data.add(rs.getString("XepHang"));
                data.add(rs.getString("SDT"));
                data.add(rs.getString("DiaChi"));
                data.add(rs.getString("Tinh"));
                data.add(rs.getString("MoTa"));
                model.addRow(data);
            }
            tbl.setModel(model);
            
        } catch (SQLException ex) {
            System.out.print(ex);
        }
        
    }
    
    public void insert(ModelKhachSan ks) {
        try {
            String sql = "INSERT INTO KhachSan(MaKhachSan,TenKhachSan,Gia,XepHang,SDT,DiaChi,Tinh,MoTa) VALUES (?,?,?,?,?,?,?,?)";
            Object[] obj = new Object[]{ks.getMaKhachSan(), ks.getTenKhachSan(), ks.getGiaKhachSan(), ks.getHang(), ks.getSdt(), ks.getDiaChi(), ks.getTinh(), ks.getMoTa()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        //  return 0;
    }
    
    public void timkiem(JTable tbl, String tim) {
        try {
            String sql = "SELECT * FROM KhachSan where " + "TenKhachSan like N'%" + tim + "%' or Gia like '%" + tim + "%'  or XepHang like N'%" + tim + "%' or SDT like '%" + tim + "%' or DiaChi like N'%" + tim + "%' or Tinh like N'%" + tim + "%'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel model = (DefaultTableModel) tbl.getModel();
            model.setRowCount(0);
            int j = 0;
            while (rs.next()) {
                Vector data = new Vector();
                data.add(++j);
                data.add(rs.getString("MaKhachSan"));
                data.add(rs.getString("TenKhachSan"));
                data.add(df.format(rs.getFloat("Gia")));
                data.add(rs.getString("XepHang"));
                data.add(rs.getString("SDT"));
                data.add(rs.getString("DiaChi"));
                data.add(rs.getString("Tinh"));
                data.add(rs.getString("MoTa"));
                model.addRow(data);
            }
            tbl.setModel(model);
            
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    
    public void HienThi(JTable tbl, ModelKhachSan ks, int i) {
        
        ks.setMaKhachSan(String.valueOf(tbl.getValueAt(i, 1)));
        ks.setTenKhachSan(String.valueOf(tbl.getValueAt(i, 2)));
        ks.setGiaKhachSan(Float.valueOf(tbl.getValueAt(i, 3).toString().replace(",", "")));
        ks.setSdt(String.valueOf(tbl.getValueAt(i, 5)));
        ks.setHang(String.valueOf(tbl.getValueAt(i, 4)));
        ks.setDiaChi(String.valueOf(tbl.getValueAt(i, 6)));
        ks.setMoTa(String.valueOf(tbl.getValueAt(i, 8)));
        ks.setTinh(String.valueOf(tbl.getValueAt(i, 7)));
    }
    
    public void updateKS(ModelKhachSan ks) throws SQLException {
        PreparedStatement p = con.prepareStatement("UPDATE dbo.KhachSan SET TenKhachSan=?, Gia=?,XepHang= ?, SDT=?, DiaChi=?, Tinh= ?, MoTa=? WHERE MaKhachSan=?");
        p.setString(8, ks.getMaKhachSan());
        p.setString(1, ks.getTenKhachSan());
        p.setFloat(2, ks.getGiaKhachSan());
        p.setString(3, ks.getHang());
        p.setString(4, ks.getSdt());
        p.setString(5, ks.getDiaChi());
        p.setString(6, ks.getTinh());
        p.setString(7, ks.getMoTa());
        p.executeUpdate();
        p.close();
    }
    
    public void update(ModelKhachSan ks) throws SQLException {
        try {
            String sql = "UPDATE dbo.KhachSan SET TenKhachSan=?, Gia=?,XepHang = ?, SDT=?, DiaChi=?,, Tinh= ?, MoTa=? WHERE MaKhachSan=?";
            Object[] obj = new Object[]{ks.getTenKhachSan(), ks.getGiaKhachSan(), ks.getHang(), ks.getDiaChi(), ks.getTinh(), ks.getMoTa(), ks.getMaKhachSan()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void delete(ModelKhachSan ks) throws SQLException {
        
        PreparedStatement p = con.prepareStatement("DELETE FROM KhachSan WHERE MaKhachSan=?");
        p.setString(1, ks.getMaKhachSan());
        p.executeUpdate();
        
        
        
    }
    
    public ArrayList<ModelKhachSan> selectTinh(String ks) throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from KhachSan where Tinh LIKE N'%" + ks + "%'");
        ResultSet r = p.executeQuery();
        //String[] header = new String[]{"STT", "Mã Khách Sạn", "Tên Khách Sạn", "Giá", "Xếp Hạng", "Số Điện Thoại", "Địa Chỉ", "Tỉnh", "Mô Tả"};

        ArrayList<ModelKhachSan> list = new ArrayList<>();
        while (r.next()) {
            ModelKhachSan mt = new ModelKhachSan();
            mt.setMaKhachSan(r.getString("MaKhachSan"));
            mt.setTenKhachSan(r.getString("TenKhachSan"));
            mt.setGiaKhachSan(r.getFloat("Gia"));
            mt.setHang(r.getString("XepHang"));
            mt.setSdt(r.getString("SDT"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setTinh(r.getString("Tinh"));
            mt.setMoTa(r.getString("MoTa"));
            list.add(mt);
        }
        //  p.close();
        // r.close();
        return list;
    }
    
}
