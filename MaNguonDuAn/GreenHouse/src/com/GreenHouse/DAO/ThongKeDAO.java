/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.DAO;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.Model.ModelDoanhThuTheoNam;
import com.GreenHouse.Swing.Table;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Thuy
 */
public class ThongKeDAO extends JDBCHelper {

    public void loadTable_DoanhThu(JTable tbl, int quy, int nam) {
        try {
            int[] so = checkQuy(quy);
            String sql = "SELECT ROW_NUMBER() OVER (ORDER BY MaVe),MaVe,NgayBan,(SoLuongVeNguoiLon+SoLuongVeTreEm) AS SoLuongVe,(FORMAT(GiaTour,'N','vi-VN') + ' VNĐ'),(FORMAT((GiaTour*(SoLuongVeNguoiLon)+(GiaTour*0.75*(SoLuongVeTreEm))),'N','vi-VN') + ' VNĐ')\n"
                    + "FROM dbo.Ve,Tour WHERE Tour.MaTour = Ve.MaTour    and Month(NgayBan) in (?,?,?)\n"
                    + " and Year(NgayBan) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, so[0]);
            ps.setInt(2, so[1]);
            ps.setInt(3, so[2]);
            ps.setInt(4, nam);
            String[] header = new String[]{"STT", "Mã Vé", "Ngày Bán", "Số Lượng Vé", "Giá Vé", "Thành tiền(triệu)"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            tbl.setDefaultEditor(Object.class, null);
            tbl.getTableHeader().setCursor(new Cursor(HAND_CURSOR));
            tbl.getTableHeader().setFont(new Font("Segoe UI", 1, 13));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getInt(4));
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

    public int getTien(int quy, int nam) {
        try {
            int tong = 0;
            String sql = "select MaVe, (GiaTour*(SoLuongVeNguoiLon)+(GiaTour*0.75*(SoLuongVeTreEm))) from Ve,Tour WHERE Tour.MaTour = Ve.MaTour and MONTH(NgayBan) = ? and YEAR(NgayBan) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quy);
            ps.setInt(2, nam);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tong += rs.getInt(2);
            }
            return tong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void loadChart(JPanel pnl, int quy, int nam) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int[] q = checkQuy(quy);
        //System.out.println(q);
        for (int i = 0; i < q.length; i++) {
            dataset.setValue("Tháng " + q[i], getTien(q[i], nam));
        System.out.println( getTien(q[i], nam));
        }
        
        JFreeChart chart = ChartFactory.createPieChart("DOANH THU QUÝ", dataset, false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        pnl.setLayout(new java.awt.BorderLayout());
        pnl.add(chartPanel, BorderLayout.CENTER);
        pnl.validate();
    }

    public int[] checkQuy(int quy) {
        int[] so = new int[3];
        switch (quy) {
            case 1:
                // 1, 2, 3
                so[0] = quy;
                so[1] = quy + 1;
                so[2] = quy + 2;
                break;
            case 2:
                //4, 5, 6
                so[0] = quy + 2;
                so[1] = quy + 3;
                so[2] = quy + 4;
                break;
            case 3:
                //7, 8, 9
                so[0] = quy + 4;
                so[1] = quy + 5;
                so[2] = quy + 6;
                break;
            case 4:
                //10, 11, 12
                so[0] = quy + 6;
                so[1] = quy + 7;
                so[2] = quy + 8;
                break;
        }
        return so;

    }

    public int getTongDuKhach() {
        try {
            String sql = "SELECT SUM(SoLuongVeNguoiLon + SoLuongVeTreEm) AS TongSoDuKhach FROM dbo.Ve ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1) ;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getTongSoNV() {
        try {
            String sql = "SELECT COUNT(MaNhanVien) AS TongSoNhanVien FROM dbo.NhanVien ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1) ;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getTongTour() {
        try {
            String sql = "SELECT COUNT(MaTour) AS SoTour FROM dbo.Tour ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Object[]> getListofArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getTour(Integer MAKH) {
        String sql = "{CALL sp_ThongKeTour(?)}";
        String[] cols = new String[]{"Tên Tour", "Giá Tour", "Tổng Số Du Khách", "Thời Gian Đi"};
        return this.getListofArray(sql, cols, MAKH);
    }

    public List<Object[]> getDoanhThu(Integer MAKH) {
        //tring[] header = new String[]{"ID", "Tên Đăng Nhập", "Mật Khẩu", "Email", "Quyền Truy Cập", "Mã Nhân Viên"};
        try {
            String sql = "{CALL sp_ThongKeDoanhThu(?)}";
            String[] cols = new String[]{"Tên Tour", "Tổng Số Lộ Trình", "Tổng Số Người Lớn", "Tổng Số Trẻ Em", "Doanh Thu", "Ngày Bán"};
            return this.getListofArray(sql, cols, MAKH);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // new Table().statement(sql, tbl, header);
    }

    public double getDoanhThuTheoNam(int n) {
        try {
            String sql = " SELECT  YEAR(NgayBan), (GiaTour*SoLuongVeNguoiLon)+(GiaTour*SoLuongVeTreEm*0.8) AS 'TongTien'\n"
                    + "                    FROM Tour,dbo.LichTrinh,dbo.LichTrinhChiTiet LTCT,dbo.Ve where  LTCT.MaTour = Tour.MaTour\n"
                    + "                    and Ve.MaLichTrinh = LichTrinh.MaLichTrinh and YEAR(NgayBan) = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();
            ArrayList<ModelDoanhThuTheoNam> ar = new ArrayList<>();
            while (rs.next()) {
                int nam = Integer.parseInt(String.valueOf(rs.getInt(1)));
                double tongTien = Double.parseDouble(String.valueOf(rs.getDouble(2)));

                ModelDoanhThuTheoNam dt = new ModelDoanhThuTheoNam(nam, tongTien);
                ar.add(dt);

            }

            double tong = 0.0;
            for (int i = 0; i < ar.size(); i++) {
                tong += ar.get(i).getTongTien();
            }
            return Math.round(tong);
        } catch (Exception e) {
            System.out.print(e);
        }
        return 0.0;
    }
}
