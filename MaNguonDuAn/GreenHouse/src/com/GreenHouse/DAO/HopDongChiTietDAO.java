/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelHDCT;
import com.GreenHouse.Swing.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author Thuy
 */
public class HopDongChiTietDAO extends JDBCHelper {

    public ArrayList<ModelHDCT> fillBang(String maTour) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT * FROM ChiTietHopDong WHERE MaHopDong = ?");
        p.setString(1, maTour);
        ResultSet r = p.executeQuery();
        ArrayList<ModelHDCT> list = new ArrayList<>();
        while (r.next()) {
            ModelHDCT mt = new ModelHDCT();
            mt.setMaHD(r.getString("MaHopDong"));
            mt.setTenKhachHang(r.getString("TenKhachHang"));
            mt.setCmnd(r.getString("CMND_CCCD"));
            mt.setSdt(r.getString("SDT"));
            //      mt.setDiaChi(r.getString("DiaChi"));
            list.add(mt);
        }
        p.close();
        r.close();
        return list;
    }

    public void LapHopDongChiTiet(ModelHDCT hdct) throws SQLException {
        String sql = "INSERT INTO dbo.ChiTietHopDong( MaHopDong,TenKhachHang, CMND_CCCD,SDT )VALUES (?,?,?,?)";
        Object[] obj = new Object[]{hdct.getMaHD(), hdct.getTenKhachHang(), hdct.getCmnd(), hdct.getSdt()};
        PreparedStatement ps = new Table().prepareStatement(sql, obj);
        ps.executeUpdate();

    }
    
    public void updateHDCT(ModelHDCT hdct, String cccdWHERE) throws SQLException{
        PreparedStatement p = con.prepareStatement("UPDATE dbo.ChiTietHopDong SET TenKhachHang = ?, CMND_CCCD = ?, SDT = ? WHERE CMND_CCCD = ?");
        p.setObject(1, hdct.getTenKhachHang());
        p.setObject(2, hdct.getCmnd());
        p.setObject(3, hdct.getSdt());
        p.setObject(4, cccdWHERE);
    }

    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"Mã Hợp Đồng Chi Tiết", "Mã Hợp Đồng", "Tên Khách Hàng", "CMND/CCCD", "Số Điện Thoại"};
            String sql = "select * from ChiTietHopDong";
            new Table().statement(sql, tbl, header);

        } catch (Exception ex) {
            System.out.print(ex);

        }
    }
}
