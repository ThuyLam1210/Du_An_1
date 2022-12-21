/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelHopDong;
import com.GreenHouse.Swing.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class HopDongDAO extends JDBCHelper {

    public void selectbyTimKiem(JTable tbl, String key) {
        try {
            String sql = "select ROW_NUMBER() Over (Order by MaVe),* from HopDong where MaHopDong like '%" + key + "%' or MaVe LIKE '%" + key + "%' or NgayLapHopDong like '%" + key + "%' or SoLuongKhach like '%" + key + "%' or NoiDungHopDong like N'%" + key + "'";
            String[] header = new String[]{"STT", "Mã Hợp Đồng", "Mã Vé", "Mã Tour", "Ngày Lập Hợp Đồng", "Số Lượng Khách", "Ghi Chú"};
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            DefaultTableModel model = new DefaultTableModel(header, 0);

            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(new HopDongDAO().selectMaTourByMaVe(rs.getString(3)));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                data.add(rs.getString(6));

                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean CheckMaHopDong(String mahd) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM HopDong where MaHopDong = ?");
        p.setString(1, mahd);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean CheckCMNDCCCD(String cccd, String mahd) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.ChiTietHopDong WHERE CMND_CCCD = ? and MaHopDong = ?");
        p.setString(1, cccd);
        p.setString(2, mahd);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean CheckSDT(String sdt, String mahd) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.ChiTietHopDong WHERE SDT = ? and MaHopDong = ?");
        p.setString(1, sdt);
        p.setString(2, mahd);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public ArrayList<ModelHopDong> selectHopDong() throws SQLException {
        ArrayList<ModelHopDong> list = new ArrayList<ModelHopDong>();
        PreparedStatement p = con.prepareStatement("select * from HopDong");
        ResultSet r = p.executeQuery();
        while (r.next()) {
            ModelHopDong hd = new ModelHopDong();
            hd.setMaHD(r.getString("MaHopDong"));
            hd.setMaVe(r.getString("MaVe"));
            hd.setNgayLap(r.getDate("NgayLapHopDong"));
            hd.setSoLuongKhach(r.getInt("SoLuongKhach"));
            hd.setNoiDungHD(r.getString("NoiDungHopDong"));
            list.add(hd);
        }
        return list;
    }
    public String selectMaTourByMaVe(String maVE) throws SQLException {
        PreparedStatement p = con.prepareStatement("select MaTour from Ve where MaVe = '"+maVE+"'");
        ResultSet r = p.executeQuery();
        String maTour = "";
        if (r.next()) {
            maTour = r.getString("MaTour");
        }
        return maTour;
    }
    ///Fill Bảng Hợp đồng

    public void lapHopDong(ModelHopDong hd) {
        try {
            String sql = "INSERT INTO dbo.HopDong(MaHopDong, MaVe, NgayLapHopDong, SoLuongKhach, NoiDungHopDong) VALUES(?,?,?,?,?)";
            Object[] obj = new Object[]{hd.getMaHD(), hd.getMaVe(), hd.getNgayLap(), hd.getSoLuongKhach(), hd.getNoiDungHD()};
            PreparedStatement ps = new Table().prepareStatement(sql, obj);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateMaHD() {
        DecimalFormat df = new DecimalFormat("000");
        Random ran = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String numRandom = df.format(ran.nextInt(1000));  //  Random from 0 to 999
        String date = dateFormat.format(new Date().getTime());
        date = date.replace("-", "");
        String maHD = "HD" + date + numRandom;

        while (checkDuplicateMaHD(maHD)) {
            numRandom = df.format(ran.nextInt(1000));
            maHD = "HD" + date + numRandom;
        }

        return maHD;

    }

    private boolean checkDuplicateMaHD(String maHD) {
        boolean duplicate = false;
        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM dbo.HopDong WHERE MaHopDong = '" + maHD + "'");
            ResultSet r = p.executeQuery();
            if (r.next()) {
                duplicate = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HopDongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return duplicate;

    }

}
