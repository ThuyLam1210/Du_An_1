/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelNhanVien;
import com.GreenHouse.Model.TaiKhoan;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import java.awt.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thuy
 */
public class TaiKhoanDAO extends JDBCHelper {

    public void ForgetPass(TaiKhoan tk) throws SQLException {
        
        String sql = "UPDATE TaiKhoan Set MatKhau = ? where Email = ?";
        PreparedStatement cauLenh = con.prepareStatement(sql);
        cauLenh.setString(1, tk.getMatKhau());
        cauLenh.setString(2, tk.getEmail());
        //   ResultSet ketQua = cauLenh.executeQuery();

        cauLenh.executeUpdate();
        //JOptionPane.showMessageDialog(this, "Tạo Password mới thành công!");
        con.close();

    }

    public ArrayList<ModelNhanVien> layDS_NV() {
        ArrayList<ModelNhanVien> arr = new ArrayList<>();
        try {
            String sql = "select MaNhanVien, TenNhanVien from NHANVIEN";
            //    Statement st = con.createStatement();
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                ModelNhanVien nv = new ModelNhanVien(rs.getString(1), rs.getString(2));
                arr.add(nv);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return arr;
    }

    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"ID", "Tên Đăng Nhập", "Mật Khẩu", "Email", "Quyền Truy Cập", "Mã Nhân Viên"};
            String sql = "SELECT * FROM TaiKhoan";
            new Table().statement(sql, tbl, header);

        } catch (Exception ex) {
            System.out.print(ex);

        }
    }

    public boolean checkEmail(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.TAIKHOAN WHERE EMAIL = ? ");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public TaiKhoan timHDToDen(String ma) {
        try {
            String sql = "select * from TaiKhoan where TenDangNhap = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoan sb = new TaiKhoan(rs.getString(1));
                return sb;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public TaiKhoan timmaNV(String ma) {
        try {
            String sql = "select * from NhanVien where MaNhanVien = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoan sb = new TaiKhoan(rs.getString(1));
                return sb;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public boolean checkTenDangNhap(String user) throws SQLException {
        boolean tim = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.TAIKHOAN WHERE TenDangNhap = ? ");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            tim = true;
        }
        r.close();
        p.close();
        return tim;
    }

    public void them(TaiKhoan tk) {
        try {
            String sql = "INSERT INTO TaiKhoan Values(?,?,?,?,?)";
            Object[] obj = new Object[]{tk.getTenDangNhap(), tk.getMatKhau(), tk.getEmail(), tk.getVaiTro(), tk.getMaNhanVien()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    public int sua(TaiKhoan tk) {
        try {
            String sql = "update TaiKhoan set TenDangNhap = ? , MatKhau = ?, Email = ?, QuyenTruyCap = ?, MaNhanVien = ? where ID = ?";
            Object[] obj = new Object[]{tk.getTenDangNhap(), tk.getMatKhau(), tk.getEmail(), tk.getVaiTro(), tk.getMaNhanVien(), tk.getID()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int xoa(TaiKhoan tk) {
        try {
            PreparedStatement p = con.prepareStatement("DELETE FROM dbo.TAIKHOAN WHERE ID = ?");

            p.setInt(1, tk.getID());
            p.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    //List<TaiKhoan> listTK = new ArrayList<>();
    public ArrayList<TaiKhoan> getAll() {
        ArrayList<TaiKhoan> list = new ArrayList<>(); //tạo list chứa đối tượng tài khoản
        try {

            Statement st = con.createStatement();
            String sql = "SELECT * FROM TaiKhoan";
            ResultSet rs = st.executeQuery(sql);
            list.clear();

            while (rs.next()) {
                int id = rs.getInt(1);
                String tentaikhoan = rs.getString(2);
                String matkhau = rs.getString(3);
                String email = rs.getString(4);
                String quyen = rs.getString(5);
                String ma = rs.getString(6);
                list.add(new TaiKhoan(id, tentaikhoan, matkhau, email, quyen, ma));//add vào list
                //listTK.add(new TaiKhoan(tentaikhoan, matkhau, email, quyen));
            }
            con.close();

        } catch (SQLException ex) {
            System.out.print(ex);
        }

        return list;
    }

    public String checkDangNhap(String ten,String mk) {
        try {
            String sql = "SELECT * FROM TaiKhoan where TenDangNhap = ? and MatKhau = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, mk);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("QuyenTruyCap");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return "";
    }

    public String getHoTen(String ma) {
        try {
            String sql = "select TenDangNhap from TaiKhoan where TenDangNhap = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getVaiTro(String ma) {
        try {
            String sql = "select TenDangNhap from TaiKhoan where TenDangNhap = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String checkDoiMatKhau(TaiKhoan mk) throws SQLException {
        try {
            String sql1 = "UPDATE TaiKhoan SET MatKhau = ? Where Email = ?";
            PreparedStatement ps = con.prepareStatement(sql1);
            ps.setString(1, mk.getMatKhau());
            ps.setString(2, mk.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.print(e);
        }
        return "";
    }

    public void hienThi(JTable tbl, TaiKhoan tk, int i) {
        tk.setID(Integer.parseInt(tbl.getValueAt(i, 0).toString()));
        //   tk.setID(tbl.getValueAt(i, 0).toString());
        tk.setTenDangNhap(String.valueOf(tbl.getValueAt(i, 1)));
        tk.setMatKhau(String.valueOf(tbl.getValueAt(i, 2)));
        tk.setEmail(String.valueOf(tbl.getValueAt(i, 3)));
        tk.setVaiTro(String.valueOf(tbl.getValueAt(i, 4)));
        tk.setMaNhanVien(String.valueOf(tbl.getValueAt(i, 5)));
    }

    public void timKiem(JTable tbl, String tim) {
        try {
            String sql = "SELECT * FROM TaiKhoan where " + "  TenDangNhap like '%" + tim + "%' or Email like '%" + tim + "%' ";
            String[] header = new String[]{"ID", "Tên Đăng Nhập", "Mật Khẩu", "Email", "Quyền Truy Cập", "Mã Nhân Viên"};
            // DefaultTableModel model = new DefaultTableModel(header, 0);
            //PreparedStatement ps = con.prepareStatement(sql);
            new Table().statement(sql, tbl, header);
            tbl.removeAll();

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public ModelNhanVien selectNV(String taiKhoan) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT b.* FROM dbo.TaiKhoan a JOIN dbo.NhanVien b ON b.MaNhanVien = a.MaNhanVien WHERE a.TenDangNhap = ?");
        p.setString(1, taiKhoan);
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

}
