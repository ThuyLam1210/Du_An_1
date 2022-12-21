/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Form.DiemThamQuan;
import com.GreenHouse.Model.ModelDiemThamQuan;
import com.GreenHouse.Model.TaiKhoan;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Van Anh
 */
public class DiemThamQuanDAO extends JDBCHelper {

    public ArrayList<ModelDiemThamQuan> selectTinhDD(String ks) throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from DiemThamQuan where Tinh LIKE N'%" + ks + "%'");
        ResultSet r = p.executeQuery();
        //String[] header = new String[]{"STT", "Mã Khách Sạn", "Tên Khách Sạn", "Giá", "Xếp Hạng", "Số Điện Thoại", "Địa Chỉ", "Tỉnh", "Mô Tả"};

        ArrayList<ModelDiemThamQuan> list = new ArrayList<>();
        while (r.next()) {
            ModelDiemThamQuan mt = new ModelDiemThamQuan();
            mt.setMaDiaDiem(r.getString("MaDiaDiem"));
            mt.setTenDiaDiem(r.getString("TenDiaDiem"));

            mt.setDiaChi(r.getString("DiaChi"));
            mt.setTinh(r.getString("Tinh"));
            mt.setMoTa(r.getString("MoTa"));
            mt.setHinhAnh(r.getString("HinhAnh"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public ArrayList<ModelDiemThamQuan> selectTinh() throws SQLException {
        PreparedStatement p = con.prepareStatement("select Tinh from DiemThamQuan Group by Tinh");
        ResultSet r = p.executeQuery();
        ArrayList<ModelDiemThamQuan> list = new ArrayList<>();
        while (r.next()) {
            ModelDiemThamQuan mt = new ModelDiemThamQuan();
            mt.setTinh(r.getString("Tinh"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;

    }

    public ModelDiemThamQuan timMa(String ma) {
        try {
            String sql = "select * from DiemThamQuan where MaDiaDiem = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ModelDiemThamQuan sb = new ModelDiemThamQuan(rs.getString(1));
                return sb;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "Mã Địa Điểm", "Tên Địa Điểm", "Địa Chỉ", "Mô Tả", "Tỉnh", "Hình Ảnh"};
            DefaultTableModel model = new DefaultTableModel(header, 0);

            PreparedStatement p = con.prepareStatement("select * from DiemThamQuan");
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
        } catch (SQLException ex) {
            System.out.print(ex);
        }

    }

    public boolean checkma(String user) throws SQLException {
        boolean tim = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.DiemThamQuan WHERE MaDiaDiem = ? ");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            tim = true;
        }
        r.close();
        p.close();
        System.out.println(tim);
        return tim;
    }

    public int sua(ModelDiemThamQuan dtq) {
        try {

            String sql = "update DiemThamQuan set TenDiaDiem = ? , DiaChi = ?,MoTa= ? ,Tinh = ?, HinhAnh = ? where MaDiaDiem = ?";
            Object[] obj = new Object[]{dtq.getTenDiaDiem(), dtq.getDiaChi(),dtq.getMoTa(), dtq.getTinh(),  dtq.getHinhAnh(), dtq.getMaDiaDiem()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int them(ModelDiemThamQuan dtq) {
        try {
            String sql = "INSERT INTO [dbo].[DiemThamQuan]\n"
                    + "     VALUES(?,?,?,?,?,?)";
            Object[] obj = new Object[]{dtq.getMaDiaDiem(), dtq.getTenDiaDiem(), dtq.getDiaChi(),dtq.getMoTa(), dtq.getTinh(),  dtq.getHinhAnh()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.print(e);
        }
        return 0;
    }

    public int xoa(ModelDiemThamQuan dtq) {
        try {
            PreparedStatement p = con.prepareStatement("DELETE FROM dbo.DiemThamQuan WHERE MaDiaDiem = ?");

            p.setString(1, dtq.getMaDiaDiem());
            p.executeUpdate();
            MsgBox.alert(null, "Xóa điểm tham quan thành công !");

        } catch (Exception e) {
            MsgBox.alert(null, "Điểm tham quan đang được sử dụng");
        }
        return 0;
    }

    public void timkiem(JTable tbl, String tim) throws SQLException {
       
            String sql = "SELECT * FROM DiemThamQuan where " + "  MaDiaDiem like N'%" + tim + "%' or TenDiaDiem like '%" + tim + "%' or DiaChi like N'%" + tim + "%' or Tinh like N'%" + tim + "%'";
            String[] header = new String[]{"STT", "Mã Địa Điểm", "Tên Địa Điểm", "", "Địa Chỉ", "Mô Tả", "Tỉnh"};
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

            } catch (Exception e) {
                System.out.println("nó đâu rồi");
            }
        }

    

    public void hienThi(JTable tbl, ModelDiemThamQuan dtq, int i) {

        dtq.setMaDiaDiem(String.valueOf(tbl.getValueAt(i, 1)));
        //   tk.setID(tbl.getValueAt(i, 0).toString());
        dtq.setTenDiaDiem(String.valueOf(tbl.getValueAt(i, 2)));
        dtq.setDiaChi(String.valueOf(tbl.getValueAt(i, 3)));
        dtq.setTinh(String.valueOf(tbl.getValueAt(i, 5)));
        dtq.setMoTa(String.valueOf(tbl.getValueAt(i, 4)));
        dtq.setHinhAnh(String.valueOf(tbl.getValueAt(i, 6)));
    }
}
