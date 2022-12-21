/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ChucVu;
import com.GreenHouse.Model.ModelNhanVien;
import com.GreenHouse.Model.ModelVe;
import com.GreenHouse.Swing.Table;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Van Anh
 */
public class NhanVienDAO extends JDBCHelper {

    public void tim(JTable tbl, String tim) throws SQLException {
        String sql = "SELECT * FROM NhanVien where " + "  TenNhanVien like N'%" + tim + "%' or SDT like '%" + tim + "%' or DiaChi like N'%" + tim + "%'";
        String[] header = new String[]{"Mã Nhân Viên", "Mã Chức Vụ", "Tên Nhân Viên", "Giới Tính ", "Trạng Thái", "CMND-CCCD", "Số Điện Thoại", "Email", "Địa Chỉ", "Hình Ảnh"};
        Statement stmt = con.createStatement();
        ResultSet r = stmt.executeQuery(sql);
        DefaultTableModel model = new DefaultTableModel(header, 0);
        try {
            while (r.next()) {
                Object[] item = new Object[8];
                item[0] = r.getString("MaNhanVien");
                item[1] = r.getString("MaChucVu");
                item[2] = r.getString("MaNhanVien");
                item[3] = r.getBoolean("GioiTinh");
                item[4] = r.getBoolean("TrangThai");
                item[5] = r.getString("CMND_CCCD");
                item[6] = r.getString("SDT");
                item[7] = r.getString("Email");
                item[8] = r.getString("TongTien");
                item[9] = r.getString("NgayBan");
                model.addRow(item);
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
                String st = String.valueOf(tbl.getValueAt(i, 4));
                if (st.equals("false")) {
                    tbl.setValueAt("Đang Làm Việc", i, 4);
                } else {
                    tbl.setValueAt("Đã Thôi Việc", i, 4);
                }
            }

        } catch (Exception e) {
            System.out.println("nó đâu rồi");
        }
    }

    public void fillTable(JTable tbl) {
        try {
            String[] header = new String[]{"Mã Nhân Viên", "Mã Chức Vụ", "Tên Nhân Viên", "Giới Tính ", "Trạng Thái", "CMND-CCCD", "Số Điện Thoại", "Email", "Địa Chỉ", "Hình Ảnh"};
            String sql = "SELECT * FROM NhanVien";
            new Table().statement(sql, tbl, header);
            for (int i = 0; i < tbl.getRowCount(); i++) {
                String str = String.valueOf(tbl.getValueAt(i, 3));
                if (str.equals("true")) {
                    tbl.setValueAt("Nam", i, 3);
                } else {
                    tbl.setValueAt("Nữ", i, 3);
                }
                String st = String.valueOf(tbl.getValueAt(i, 4));
                if (st.equals("false")) {
                    tbl.setValueAt("Đang Làm Việc", i, 4);
                } else {
                    tbl.setValueAt("Đã Thôi Việc", i, 4);
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }

    }

    public boolean checkId(String user) throws SQLException {
        boolean Search = false;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM dbo.NhanVien WHERE MaNhanVien = ?");
        ps.setString(1, user);
        ResultSet r = ps.executeQuery();
        if (r.next()) {
            Search = true;
        }
        r.close();
        ps.close();
        System.out.println(Search);
        return Search;
    }

    public int update(ModelNhanVien staff) {
        try {
            String sql = "update NhanVien set MaChucVu = ?, TenNhanVien = ? ,GioiTinh = ?, TrangThai= ?,CMND_CCCD= ?,SDT= ?,Email= ?,DiaChi = ?,HinhAnh= ? where MaNhanVien = ?";
            Object[] obj = new Object[]{staff.getMaCV(), staff.getTenNV(), staff.isGtinh(), staff.isTrangthai(), staff.getCMND(), staff.getSDT(), staff.getEmail(), staff.getDiaChi(), staff.getHinhAnh(), staff.getMaNV()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    public void fillBangNV(JTable tbl) throws SQLException {
        String[] header = new String[]{"STT", "Mã Nhân Viên", "Mã Chức Vụ", "Tên Nhân Viên", "Giới Tính ", "Trạng Thái", "CMND-CCCD", "Số Điện Thoại", "Email", "Địa Chỉ", "Hình Ảnh"};
        DefaultTableModel model = new DefaultTableModel(header, 0);

        PreparedStatement p = con.prepareStatement("select * from NhanVien");
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
            String str = String.valueOf(tbl.getValueAt(i, 4));
            if (str.equals("true")) {
                tbl.setValueAt("Nam", i, 4);
            } else {
                tbl.setValueAt("Nữ", i, 4);
            }
            String st = String.valueOf(tbl.getValueAt(i, 5));
            if (st.equals("false")) {
                tbl.setValueAt("Đang Làm Việc", i, 5);
            } else {
                tbl.setValueAt("Đã Thôi Việc", i, 5);
            }
        }
    }

    public int add(ModelNhanVien staff) {
        try {
            String sql = " INSERT INTO dbo.NhanVien\n"
                    + " ( MaNhanVien, MaChucVu,TenNhanVien,GioiTinh,TrangThai,CMND_CCCD,SDT,Email, DiaChi, HinhAnh)\n"
                    + "     VALUES(?,?,?,?,?,?,?,?,?,?)";
            Object[] obj = new Object[]{staff.getMaNV(), staff.getMaCV(), staff.getTenNV(), staff.isGtinh(), staff.isTrangthai(), staff.getCMND(), staff.getSDT(), staff.getEmail(), staff.getDiaChi(), staff.getHinhAnh()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.print(e);
        }
        return 0;
    }

    public int delete(ModelNhanVien staff) throws SQLException {
        try {
            PreparedStatement p = con.prepareStatement("DELETE FROM dbo.NhanVien WHERE MaNhanVien = ?");

            p.setString(1, staff.getMaNV());
            p.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void timkiem(JTable tbl, String tim) {
        try {
            String sql = "SELECT * FROM NhanVien where " + "  TenNhanVien like N'%" + tim + "%' or SDT like '%" + tim + "%' or DiaChi like N'%" + tim + "%'";
            String[] header = new String[]{"STT", "Mã Nhân Viên", "Mã Chức Vụ", "Tên Nhân Viên", "Giới Tính ", "Trạng Thái", "CMND-CCCD", "Số Điện Thoại", "Email", "Địa Chỉ", "Hình Ảnh"};
            PreparedStatement ps = con.prepareStatement(sql);
            DefaultTableModel model = new DefaultTableModel(header, 0);
            ResultSet rs = ps.executeQuery();
            int j = 0;
            while (rs.next()) {
                Vector data = new Vector();
                data.add(++j);
                for (int i = 0; i < header.length - 1; i++) {
                    data.add(rs.getObject(i + 1));
                }
                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);
            for (int i = 0; i < tbl.getRowCount(); i++) {
                String str = String.valueOf(tbl.getValueAt(i, 4));
                if (str.equals("true")) {
                    tbl.setValueAt("Nam", i, 4);
                } else {
                    tbl.setValueAt("Nữ", i, 4);
                }
                String st = String.valueOf(tbl.getValueAt(i, 5));
                if (st.equals("false")) {
                    tbl.setValueAt("Đang Làm Việc", i, 5);
                } else {
                    tbl.setValueAt("Đã Thôi Việc", i, 5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkMaNV(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.NhanVien WHERE MaNhanVien = ? ");
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
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.NhanVien WHERE EMAIL = ? ");
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
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.NhanVien WHERE CMND_CCCD = ? ");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            tim = true;
        }
        r.close();
        p.close();
        return tim;
    }

    public boolean checkSDT(String sdt) throws SQLException {
        boolean tim = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.NhanVien WHERE SDT = ? ");
        p.setString(1, sdt);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            tim = true;
        }
        r.close();
        p.close();
        return tim;
    }

    public void hienThi(JTable tbl, ModelNhanVien staff, int i) {

        staff.setMaNV(String.valueOf(tbl.getValueAt(i, 1)));
        staff.setMaCV(String.valueOf(tbl.getValueAt(i, 2)));
        staff.setTenNV(String.valueOf(tbl.getValueAt(i, 3)));
        staff.setEmail(String.valueOf(tbl.getValueAt(i, 8)));
        staff.setSDT(String.valueOf(tbl.getValueAt(i, 7)));
        staff.setDiaChi(String.valueOf(tbl.getValueAt(i, 9)));
        staff.setCMND(String.valueOf(tbl.getValueAt(i, 6)));
        if (String.valueOf(tbl.getValueAt(i, 4)).equalsIgnoreCase("Nam")) {
            staff.setGtinh(true);
        } else {
            staff.setGtinh(false);
        }
        if (String.valueOf(tbl.getValueAt(i, 5)).equalsIgnoreCase("Đang Làm Việc")) {
            staff.setTrangthai(true);
        } else {
            staff.setTrangthai(false);
        }
        staff.setHinhAnh(String.valueOf(tbl.getValueAt(i, 10)));

    }

    public ArrayList<ChucVu> layDS_ChucVu() {
        ArrayList<ChucVu> arr = new ArrayList<>();
        try {
            //   String sql = "";
            PreparedStatement p = con.prepareStatement("select MaChucVu,TenChucVu from ChucVu");

            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                ChucVu hk = new ChucVu(rs.getString(1), rs.getString(2));
                arr.add(hk);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return arr;
    }
 public ModelNhanVien TimMa(String ma) {
        try {
            String sql = "select * from NhanVien where MaNhanVien = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ModelNhanVien hk = new ModelNhanVien(rs.getString(1));
                return hk;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;

    }
}
