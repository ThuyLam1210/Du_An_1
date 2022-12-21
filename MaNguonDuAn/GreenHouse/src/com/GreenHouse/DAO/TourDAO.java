package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelDiemThamQuan;
import com.GreenHouse.Model.ModelKhachSan;
import com.GreenHouse.Model.ModelPhuongTien;
import com.GreenHouse.Model.ModelThemDTQ;
import com.GreenHouse.Model.ModelTour;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDAO extends JDBCHelper {

    public List<ModelTour> TourDAO() throws SQLException {
        return this.selectAll1(selectAll);
    }
  
    String selectAll = "select * from Tour";
    String sl = "select TenTour from Tour where MaTour = ?";
    String selectID = "select * from Tour where MaTour = ?";
    //   String selectMaTour = "Select * from Tour join LichTrinhChiTiet LTCT on LTCT.MaTour = Tour.MaTour join LichTrinh on LichTrinh.MaLichTrinh = LTCT.MaLichTrinh";

    public ModelTour checkMaTour(String ma) throws SQLException {
        List< ModelTour> list = this.selectAll1(selectID, ma);
        return list.size() > 0 ? list.get(0) : null;

    }

    public List<ModelTour> selectAll1(String sql, Object... args) throws SQLException {
        List<ModelTour> list = new ArrayList<ModelTour>();
        try {

            ResultSet r = JDBCHelper.query(sql, args);
            while (r.next()) {
                ModelTour mt = new ModelTour();
                mt.setMaTour(r.getString(1));
                mt.setTenTour(r.getString(2));
                mt.setSoNgay(r.getInt(3));
                mt.setSoDem(r.getInt(4));
                mt.setGiaTour(r.getFloat(5));
                mt.setSoLuongKhach(r.getInt(6));
                mt.setHinhAnh(r.getString(7));
                list.add(mt);
                //System.out.println(mt);
            }
            r.getStatement().getConnection().close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return list;
    }

    public ArrayList<ModelTour> selectAll() throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from Tour");
        ResultSet r = p.executeQuery();
        ArrayList<ModelTour> list = new ArrayList<>();
        while (r.next()) {
            ModelTour mt = new ModelTour();
            mt.setMaTour(r.getString("MaTour"));
            mt.setTenTour(r.getString("TenTour"));
            mt.setSoLuongKhach(r.getInt("SoLuongKhach"));
            mt.setSoNgay(r.getInt("SoNgay"));
            mt.setSoDem(r.getInt("SoDem"));
            mt.setGiaTour(r.getFloat("GiaTour"));
            mt.setHinhAnh(r.getString("HinhAnh"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public ModelTour selectByID(String id) throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from Tour where MaTour = ?");
        p.setString(1, id);
        ResultSet r = p.executeQuery();
        ModelTour mt = new ModelTour();
        while (r.next()) {
            mt.setMaTour(r.getString("MaTour"));
            mt.setTenTour(r.getString("TenTour"));
            mt.setSoLuongKhach(r.getInt("SoLuongKhach"));
            mt.setSoNgay(r.getInt("SoNgay"));
            mt.setSoDem(r.getInt("SoDem"));
            mt.setGiaTour(r.getFloat("GiaTour"));
            mt.setHinhAnh(r.getString("HinhAnh"));
        }
        p.close();
        r.close();
        return mt;
    }

    public ArrayList<ModelTour> selectByAnything(String key) throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from Tour where MaTour = '%" + key + "%' or TenTour LIKE N'%" + key + "%' or SoNgay like '%" + key + "%' or SoDem like '%" + key + "%' or SoLuongKhach like '%" + key + "' or GiaTour like '%" + key + "%'");
        ResultSet r = p.executeQuery();
        ArrayList<ModelTour> list = new ArrayList<>();
        while (r.next()) {
            ModelTour mt = new ModelTour();
            mt.setMaTour(r.getString("MaTour"));
            mt.setTenTour(r.getString("TenTour"));
            mt.setSoLuongKhach(r.getInt("SoLuongKhach"));
            mt.setSoNgay(r.getInt("SoNgay"));
            mt.setSoDem(r.getInt("SoDem"));
            mt.setGiaTour(r.getFloat("GiaTour"));
            mt.setHinhAnh(r.getString("HinhAnh"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public void insertTour(ModelTour mt) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into Tour values (?,?,?,?,?,?,?)");
        p.setString(1, mt.getMaTour());
        p.setString(2, mt.getTenTour());
        p.setInt(3, mt.getSoNgay());
        p.setInt(4, mt.getSoDem());
        p.setFloat(5, mt.getGiaTour());
        p.setInt(6, mt.getSoLuongKhach());
        p.setString(7, mt.getHinhAnh());
        p.executeUpdate();
        p.close();
    }

    public void updateTour(ModelTour mt) throws SQLException {
        PreparedStatement p = con.prepareStatement("update Tour set TenTour = ?, SoLuongKhach = ?, SoNgay = ?, SoDem = ?, GiaTour = ?, HinhAnh = ? where MaTour = ?");
        p.setString(1, mt.getTenTour());
        p.setInt(2, mt.getSoLuongKhach());
        p.setInt(3, mt.getSoNgay());
        p.setInt(4, mt.getSoDem());
        p.setFloat(5, mt.getGiaTour());
        p.setString(6, mt.getHinhAnh());
        p.setString(7, mt.getMaTour());
        p.executeUpdate();
        p.close();
    }

    public void updateGiaTour(String maTour, float tienTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("update Tour set GiaTour = ? where MaTour = ?");
        p.setFloat(1, tienTour);
        p.setString(2, maTour);
        p.executeUpdate();
        p.close();
    }

    public void deleteTour(String id) throws SQLException {
        PreparedStatement p = con.prepareStatement("delete from Tour where MaTour = ?");
        p.setString(1, id);
        p.executeUpdate();
        p.close();
    }

    public ModelTour timMa(String ma) {
        try {
            String sql = "select * from Tour where MaTour = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ModelTour sb = new ModelTour(rs.getString(1));
                return sb;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public boolean checkDuplicateTour(String id) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select * from Tour where MaTour = ?");
        p.setString(1, id);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        return duplicate;
    }

    public boolean checkLTTour(String id) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM LichTrinhChiTiet where MaTour = ?");
        p.setString(1, id);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        return duplicate;
    }

    // Them Diem Tham Quan panel
    public ArrayList<ModelDiemThamQuan> selectDiemThamQuanAll() throws SQLException {
        PreparedStatement p = con.prepareStatement("select * from DiemThamQuan");
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

    public ArrayList<ModelDiemThamQuan> selectTinhDiemThamQuan() throws SQLException {
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

    public ArrayList<ModelThemDTQ> selectSetThamQuanByMaTour(String maTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT a.MaTour, c.TenTour, a.MaDiaDiem, b.TenDiaDiem, b.DiaChi FROM dbo.SetDiemThamQuan a JOIN dbo.DiemThamQuan b ON b.MaDiaDiem = a.MaDiaDiem JOIN dbo.Tour c ON c.MaTour = a.MaTour WHERE a.MaTour = ?");
        p.setString(1, maTour);
        ResultSet r = p.executeQuery();
        ArrayList<ModelThemDTQ> list = new ArrayList<>();
        while (r.next()) {
            ModelThemDTQ mt = new ModelThemDTQ();
            mt.setMaTour(r.getString("MaTour"));
            mt.setTenTour(r.getString("TenTour"));
            mt.setMaDiemThamQuan(r.getString("MaDiaDiem"));
            mt.setTenDiemThamQuan(r.getString("TenDiaDiem"));
            mt.setDiaChi(r.getString("DiaChi"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public void insertSetDiemThamQuan(String maDiaDiem, String maTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("INSERT INTO dbo.SetDiemThamQuan VALUES (?,?)");
        p.setString(1, maDiaDiem);
        p.setString(2, maTour);
        p.executeUpdate();
        p.close();
    }

    public ModelDiemThamQuan selectDiemThamQuanByID(String maDiaDiem) throws SQLException {
        ModelDiemThamQuan mt = new ModelDiemThamQuan();
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.DiemThamQuan WHERE MaDiaDiem = '" + maDiaDiem + "'");
        ResultSet r = p.executeQuery();
        if (r.next()) {
            mt.setMaDiaDiem(r.getString("MaDiaDiem"));
            mt.setTenDiaDiem(r.getString("TenDiaDiem"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setMoTa(r.getString("MoTa"));
            mt.setHinhAnh(r.getString("HinhAnh"));
        }
        return mt;
    }

    public void deleteSetDiemThamQuan(String maTour, String maDiaDiem) throws SQLException {
        PreparedStatement p = con.prepareStatement("DELETE FROM dbo.SetDiemThamQuan WHERE MaTour = ? AND MaDiaDiem = ?");
        p.setString(1, maTour);
        p.setString(2, maDiaDiem);
        p.executeUpdate();
        p.close();
    }

    public boolean checkDuplicateSetDiemThamQuan(String maTour, String maDiaDiem) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select * from SetDiemThamQuan where MaDiaDiem = ? and MaTour = ?");
        p.setString(1, maDiaDiem);
        p.setString(2, maTour);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        return duplicate;
    }

    // Them Phuong Tien panel
    public ArrayList<ModelPhuongTien> selectSetPhuongTienAll() throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.PhuongTien");
        ResultSet r = p.executeQuery();
        ArrayList<ModelPhuongTien> list = new ArrayList<>();
        while (r.next()) {
            ModelPhuongTien mt = new ModelPhuongTien();
            mt.setMaPhuongTien(r.getString("MaPhuongTien"));
            mt.setMaLoaiPhuongTien(r.getString("MaLoaiPhuongTien"));
            mt.setTenPhuongTien(r.getString("TenPhuongTien"));
            mt.setCuocPhi(r.getFloat("CuocPhi"));
            mt.setBienSo(r.getString("BienSo"));
            mt.setSoLuongChua(r.getInt("SoLuongChua"));
            mt.setGhiChu(r.getString("GhiChu"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public String[] selectLoaiPhuongTien() throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT TenLoaiPhuongTien FROM dbo.LoaiPhuongTien");
        ResultSet r = p.executeQuery();
        String list[] = new String[10];
        int i = 0;
        while (r.next()) {
            list[i++] = r.getString(1);
        }
        p.close();
        r.close();
        return list;
    }

    public ArrayList<ModelPhuongTien> selectPhuongTienByLoai(String loaiPhuongTien) throws SQLException {
        PreparedStatement p = con.prepareStatement("select a.* from phuongtien a join loaiphuongtien b on a.MaLoaiPhuongTien = b.MaLoaiPT where TenLoaiPhuongTien LIKE N'%" + loaiPhuongTien + "%'");
        ResultSet r = p.executeQuery();
        ArrayList<ModelPhuongTien> list = new ArrayList<>();
        while (r.next()) {
            ModelPhuongTien mt = new ModelPhuongTien();
            mt.setMaPhuongTien(r.getString("MaPhuongTien"));
            mt.setMaLoaiPhuongTien(r.getString("MaLoaiPhuongTien"));
            mt.setTenPhuongTien(r.getString("TenPhuongTien"));
            mt.setCuocPhi(r.getFloat("CuocPhi"));
            mt.setBienSo(r.getString("BienSo"));
            mt.setSoLuongChua(r.getInt("SoLuongChua"));
            mt.setGhiChu(r.getString("GhiChu"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public ArrayList<ModelPhuongTien> selectSetPhuongTienByMaTour(String maTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT a.* FROM dbo.PhuongTien a JOIN dbo.SetPhuongTien b ON b.MaPhuongTien = a.MaPhuongTien JOIN dbo.Tour c ON c.MaTour = b.MaTour WHERE b.MaTour = ?");
        p.setString(1, maTour);
        ResultSet r = p.executeQuery();
        ArrayList<ModelPhuongTien> list = new ArrayList<>();
        while (r.next()) {
            ModelPhuongTien mt = new ModelPhuongTien();
            mt.setMaPhuongTien(r.getString("MaPhuongTien"));
            mt.setMaLoaiPhuongTien(r.getString("MaLoaiPhuongTien"));
            mt.setTenPhuongTien(r.getString("TenPhuongTien"));
            mt.setCuocPhi(r.getFloat("CuocPhi"));
            mt.setBienSo(r.getString("BienSo"));
            mt.setSoLuongChua(r.getInt("SoLuongChua"));
            mt.setGhiChu(r.getString("GhiChu"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public void insertSetPhuongTien(String maPhuongTien, String maTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("INSERT INTO dbo.SetPhuongTien VALUES (?,?)");
        p.setString(1, maPhuongTien);
        p.setString(2, maTour);
        p.executeUpdate();
        p.close();
    }

    public void deleteSetPhuongTien(String maTour, String maPhuongTien) throws SQLException {
        PreparedStatement p = con.prepareStatement("DELETE FROM dbo.SetPhuongTien WHERE MaTour = ? AND MaPhuongTien = ?");
        p.setString(1, maTour);
        p.setString(2, maPhuongTien);
        p.executeUpdate();
        p.close();
    }

    public boolean checkDuplicateSetPhuongTien(String maTour, String maPhuongTien) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select * from SetPhuongTien where MaPhuongTien = ? and MaTour = ?");
        p.setString(1, maPhuongTien);
        p.setString(2, maTour);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        return duplicate;
    }

    // Them Khach San panel
    public ArrayList<ModelKhachSan> selectKhachSanAll() throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachSan");
        ResultSet r = p.executeQuery();
        ArrayList<ModelKhachSan> list = new ArrayList<>();
        while (r.next()) {
            ModelKhachSan mt = new ModelKhachSan();
            mt.setMaKhachSan(r.getString("MaKhachSan"));
            mt.setTenKhachSan(r.getString("TenKhachSan"));
            mt.setGiaKhachSan(r.getFloat("Gia"));
            mt.setSdt(r.getString("SDT"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setTinh(r.getString("Tinh"));
            mt.setMoTa(r.getString("MoTa"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public ArrayList<ModelKhachSan> selectTinhKhachSan() throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachSan");
        ResultSet r = p.executeQuery();
        ArrayList<ModelKhachSan> list = new ArrayList<>();
        while (r.next()) {
            ModelKhachSan mt = new ModelKhachSan();
            mt.setMaKhachSan(r.getString("MaKhachSan"));
            mt.setTenKhachSan(r.getString("TenKhachSan"));
            mt.setGiaKhachSan(r.getFloat("Gia"));
            mt.setSdt(r.getString("SDT"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setTinh(r.getString("Tinh"));
            mt.setMoTa(r.getString("MoTa"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public ArrayList<ModelKhachSan> selectKhachSanByTinh(String tinh) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachSan WHERE Tinh LIKE N'%" + tinh + "%'");
        ResultSet r = p.executeQuery();
        ArrayList<ModelKhachSan> list = new ArrayList<>();
        while (r.next()) {
            ModelKhachSan mt = new ModelKhachSan();
            mt.setMaKhachSan(r.getString("MaKhachSan"));
            mt.setTenKhachSan(r.getString("TenKhachSan"));
            mt.setGiaKhachSan(r.getFloat("Gia"));
            mt.setSdt(r.getString("SDT"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setTinh(r.getString("Tinh"));
            mt.setMoTa(r.getString("MoTa"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public ModelKhachSan selectKhachSanByID(String maKhachSan) throws SQLException {
        ModelKhachSan mt = new ModelKhachSan();
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.KhachSan WHERE MaKhachSan = '" + maKhachSan + "'");
        ResultSet r = p.executeQuery();
        if (r.next()) {
            mt.setMaKhachSan(r.getString("MaKhachSan"));
            mt.setTenKhachSan(r.getString("TenKhachSan"));
            mt.setGiaKhachSan(r.getFloat("Gia"));
            mt.setSdt(r.getString("SDT"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setTinh(r.getString("Tinh"));
            mt.setMoTa(r.getString("MoTa"));
        }
        return mt;
    }

    public ArrayList<ModelKhachSan> selectSetKhachSanByMaTour(String maTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT a.* FROM dbo.KhachSan a JOIN dbo.SetKhachSan b ON b.MaKhachSan = a.MaKhachSan JOIN dbo.Tour c ON c.MaTour = b.MaTour WHERE b.MaTour = ?");
        p.setString(1, maTour);
        ResultSet r = p.executeQuery();
        ArrayList<ModelKhachSan> list = new ArrayList<>();
        while (r.next()) {
            ModelKhachSan mt = new ModelKhachSan();
            mt.setMaKhachSan(r.getString("MaKhachSan"));
            mt.setTenKhachSan(r.getString("TenKhachSan"));
            mt.setGiaKhachSan(r.getFloat("Gia"));
            mt.setSdt(r.getString("SDT"));
            mt.setDiaChi(r.getString("DiaChi"));
            mt.setTinh(r.getString("Tinh"));
            mt.setMoTa(r.getString("MoTa"));
            list.add(mt);
        }
        p.close();
        r.close();
        System.out.println(list.size());
        return list;
    }

    public void insertSetKhachSan(String maKhachSan, String maTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("INSERT INTO dbo.SetKhachSan VALUES (?,?)");
        p.setString(1, maKhachSan);
        p.setString(2, maTour);
        p.executeUpdate();
        p.close();
    }

    public void deleteSetKhachSan(String maTour, String maKhachSan) throws SQLException {
        PreparedStatement p = con.prepareStatement("DELETE FROM dbo.SetKhachSan WHERE MaTour = ? AND MaKhachSan = ?");
        p.setString(1, maTour);
        p.setString(2, maKhachSan);
        p.executeUpdate();
        p.close();
    }

    public boolean checkDuplicateSetKhachSan(String maTour, String maPhuongTien) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select * from SetKhachSan where MaKhachSan = ? and MaTour = ?");
        p.setString(1, maPhuongTien);
        p.setString(2, maTour);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        return duplicate;
    }
}
