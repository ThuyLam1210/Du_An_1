/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Form;

import com.GreenHouse.DAO.LichTrinhDAO;
import com.GreenHouse.DAO.ThongKeDAO;
import com.GreenHouse.DAO.VeDAO;
import com.GreenHouse.Export.ExportExcel;
import com.GreenHouse.Export.ExportPDF;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import com.itextpdf.text.pdf.PdfName;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Thuy
 */
public class pnlThongKe extends javax.swing.JPanel {

    private ThongKeDAO tkDAO = new ThongKeDAO();
    private LichTrinhDAO ltDAO = new LichTrinhDAO();
    private VeDAO veDAO = new VeDAO();

    public pnlThongKe() {
        initComponents();
        fillNam();
        showTableTour();
        fillNamBieuDo();
        tblTour.fixTable(jScrollPane2);
        tblThongKeTheoQuy.fixTable(jScrollPane3);
        tblDoanhThu.fixTable(jScrollPane1);
        fillNamDoanhThu();
        showTableDoanhThu();
        fillBieuDo();
        loadChart();
        loadTongDuKhach();
        loadTongNhanVien();
        loadTongSoTour();
        LoadTable_DoanhThuQuy();
    }

    private void LoadTable_DoanhThuQuy() {
        try {
            int quy = 0;
            int nam = 0;

            quy = Integer.parseInt(String.valueOf(cboQuy.getSelectedItem()));

            nam = Integer.parseInt(String.valueOf(cboTheoNam.getSelectedItem()));
            tkDAO.loadTable_DoanhThu(tblThongKeTheoQuy, quy, nam);
            int[] col = new int[]{40, 110, 120, 100, 110, 140};
            new Table().editColumnWidth(col, tblThongKeTheoQuy);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTongDuKhach() {
        pnlTongSoDuKhach.setValues(String.valueOf(new ThongKeDAO().getTongDuKhach()));
    }

    private void loadTongNhanVien() {
        pnlTongSoNhanVien.setValues(String.valueOf(new ThongKeDAO().getTongSoNV()));
    }

    private void loadTongSoTour() {
        pnlTongSoTour.setValues(String.valueOf(new ThongKeDAO().getTongTour()));
    }

    private void loadCBOQuy() {

    }

    private void loadChart() {
        pnlBieuDo1.removeAll();
        int quy = Integer.parseInt(String.valueOf(cboQuy.getSelectedItem()));
        int nam = Integer.parseInt(String.valueOf(cboTheoNam.getSelectedItem()));
        new ThongKeDAO().loadChart(pnlBieuDo1, quy, nam);
    }

    private void fillBieuDo() {
        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            String str = "Doanh thu bán vé Tour Green House hằng năm";

            dataset.addValue(new ThongKeDAO().getDoanhThuTheoNam(2018), str, "2016");
            dataset.addValue(new ThongKeDAO().getDoanhThuTheoNam(2017), str, "2017");
            dataset.addValue(new ThongKeDAO().getDoanhThuTheoNam(2018), str, "2018");
            dataset.addValue(new ThongKeDAO().getDoanhThuTheoNam(2019), str, "2019");
            dataset.addValue(new ThongKeDAO().getDoanhThuTheoNam(2020), str, "2020");
            dataset.addValue(new ThongKeDAO().getDoanhThuTheoNam(2021), str, "2021");
            dataset.addValue(new ThongKeDAO().getDoanhThuTheoNam(2022), str, "2022");
            //System.out.println(dataset);
            JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ THỐNG KÊ DOANH THU THEO NĂM", "Năm", "Tiền (Triệu)", dataset, PlotOrientation.VERTICAL, true, true, true);
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setSize(new java.awt.Dimension(pnlBieuDo.getWidth() - 1000, pnlBieuDo.getHeight() - 10));
            pnlBieuDo.setLayout(new java.awt.BorderLayout());
            pnlBieuDo.add(chartPanel, BorderLayout.CENTER);
            pnlBieuDo.validate();
            //System.out.println(dataset);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void fillNamBieuDo() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboTheoNam.getModel();
            model.removeAllElements();
            List<Integer> list = veDAO.selectNam();
            //System.out.print(list);
            for (Integer year : list) {
                model.addElement(year);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void fillNamDoanhThu() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
            model.removeAllElements();
            List<Integer> list = veDAO.selectNam();
            //System.out.print(list);
            for (Integer year : list) {
                model.addElement(year);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void fillNam() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam_Tour.getModel();
            model.removeAllElements();
            List<Integer> list = ltDAO.selectNam();
            //System.out.print(list.size());
            for (Integer year : list) {
                model.addElement(year);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void showTableTour() {
        DefaultTableModel model = (DefaultTableModel) tblTour.getModel();
        try {
            model.setRowCount(0);
            int nam = (Integer) cboNam_Tour.getSelectedItem();
            // System.out.print(nam);
            List<Object[]> list = tkDAO.getTour(nam);
            //System.out.println(list.size());
            for (Object[] row : list) {
                //   model.addRow(new Object[]{row[0], row[1], new DecimalFormat("####0").format(row[2]), new DecimalFormat("####0").format(row[3])});
                //   System.out.println(row);
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void showTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();

        model.setRowCount(0);
        int nam = (Integer) cboNam.getSelectedItem();
       // System.out.print(nam);
        List<Object[]> list = tkDAO.getDoanhThu(nam);
        System.out.print(list);
        for (Object[] row : list) {
            model.addRow(row);
            //System.out.println(row);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        materialTabbed1 = new com.GreenHouse.Swing.MaterialTabbed();
        jPanel3 = new javax.swing.JPanel();
        pnlBieuDo = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        cboQuy = new com.GreenHouse.Swing.Combobox();
        jLabel12 = new javax.swing.JLabel();
        cboTheoNam = new com.GreenHouse.Swing.Combobox();
        jLabel13 = new javax.swing.JLabel();
        pnlBieuDo1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThongKeTheoQuy = new com.GreenHouse.Swing.Table_1();
        btnXuatExcel_Tour1 = new com.GreenHouse.Swing.KButton();
        btnXuatPDF_Tour1 = new com.GreenHouse.Swing.KButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTour = new com.GreenHouse.Swing.Table_1();
        cboNam_Tour = new com.GreenHouse.Swing.Combobox();
        jLabel3 = new javax.swing.JLabel();
        btnXuatPDF_Tour = new com.GreenHouse.Swing.KButton();
        btnXuatExcel_Tour = new com.GreenHouse.Swing.KButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new com.GreenHouse.Swing.Table_1();
        cboNam = new com.GreenHouse.Swing.Combobox();
        jLabel2 = new javax.swing.JLabel();
        pnlTongSoNhanVien = new com.GreenHouse.Swing.Card();
        pnlTongSoTour = new com.GreenHouse.Swing.Card();
        pnlTongSoDuKhach = new com.GreenHouse.Swing.Card();
        btnXuatExcel_DoanhThu = new com.GreenHouse.Swing.KButton();
        btnXuatPDF_DoanhThu = new com.GreenHouse.Swing.KButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1040, 680));
        setPreferredSize(new java.awt.Dimension(1100, 900));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("THỐNG KÊ");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(1200, 680));
        jPanel3.setPreferredSize(new java.awt.Dimension(1200, 722));

        javax.swing.GroupLayout pnlBieuDoLayout = new javax.swing.GroupLayout(pnlBieuDo);
        pnlBieuDo.setLayout(pnlBieuDoLayout);
        pnlBieuDoLayout.setHorizontalGroup(
            pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        pnlBieuDoLayout.setVerticalGroup(
            pnlBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(47, 47, 47))
        );

        materialTabbed1.addTab("Biểu Đồ Doanh Thu Năm", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1100, 722));

        cboQuy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        cboQuy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboQuyItemStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 102));
        jLabel12.setText("Năm");

        cboTheoNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTheoNamItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("Quý");

        pnlBieuDo1.setMaximumSize(new java.awt.Dimension(300, 285));
        pnlBieuDo1.setPreferredSize(new java.awt.Dimension(300, 285));

        javax.swing.GroupLayout pnlBieuDo1Layout = new javax.swing.GroupLayout(pnlBieuDo1);
        pnlBieuDo1.setLayout(pnlBieuDo1Layout);
        pnlBieuDo1Layout.setHorizontalGroup(
            pnlBieuDo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        pnlBieuDo1Layout.setVerticalGroup(
            pnlBieuDo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblThongKeTheoQuy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblThongKeTheoQuy);

        btnXuatExcel_Tour1.setText("Xuất File Excel");
        btnXuatExcel_Tour1.setkEndColor(new java.awt.Color(255, 51, 153));
        btnXuatExcel_Tour1.setkStartColor(new java.awt.Color(204, 204, 255));
        btnXuatExcel_Tour1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcel_Tour1ActionPerformed(evt);
            }
        });

        btnXuatPDF_Tour1.setText("Xuất File PDF");
        btnXuatPDF_Tour1.setkEndColor(new java.awt.Color(255, 51, 153));
        btnXuatPDF_Tour1.setkStartColor(new java.awt.Color(204, 204, 255));
        btnXuatPDF_Tour1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPDF_Tour1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(cboTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pnlBieuDo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnXuatExcel_Tour1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnXuatPDF_Tour1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(75, 75, 75)
                    .addComponent(jLabel13)
                    .addContainerGap(992, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(cboQuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(158, 158, 158)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(pnlBieuDo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatExcel_Tour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatPDF_Tour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jLabel13)
                    .addContainerGap(660, Short.MAX_VALUE)))
        );

        materialTabbed1.addTab("Thống Kê Doanh Thu Theo Quý", jPanel4);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblTour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên Tour", "Giá Tour", "Tổng Số Du Khách", "Thời Gian Đi"
            }
        ));
        jScrollPane2.setViewportView(tblTour);
        if (tblTour.getColumnModel().getColumnCount() > 0) {
            tblTour.getColumnModel().getColumn(0).setMinWidth(300);
            tblTour.getColumnModel().getColumn(0).setMaxWidth(300);
            tblTour.getColumnModel().getColumn(1).setMinWidth(170);
            tblTour.getColumnModel().getColumn(1).setMaxWidth(170);
        }

        cboNam_Tour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNam_TourActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Năm");

        btnXuatPDF_Tour.setText("Xuất File PDF");
        btnXuatPDF_Tour.setkEndColor(new java.awt.Color(255, 51, 153));
        btnXuatPDF_Tour.setkStartColor(new java.awt.Color(204, 204, 255));
        btnXuatPDF_Tour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPDF_TourActionPerformed(evt);
            }
        });

        btnXuatExcel_Tour.setText("Xuất File Excel");
        btnXuatExcel_Tour.setkEndColor(new java.awt.Color(255, 51, 153));
        btnXuatExcel_Tour.setkStartColor(new java.awt.Color(204, 204, 255));
        btnXuatExcel_Tour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcel_TourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnXuatExcel_Tour, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(btnXuatPDF_Tour, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cboNam_Tour, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 899, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboNam_Tour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatExcel_Tour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatPDF_Tour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        materialTabbed1.addTab("Tour", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên Tour", "Tổng Lộ Trình", "Tổng Số Vé Người Lớn", "Tổng Số Vé Trẻ Em", "Doanh Thu", "Ngày Đi"
            }
        ));
        jScrollPane1.setViewportView(tblDoanhThu);
        if (tblDoanhThu.getColumnModel().getColumnCount() > 0) {
            tblDoanhThu.getColumnModel().getColumn(0).setMinWidth(280);
            tblDoanhThu.getColumnModel().getColumn(0).setMaxWidth(280);
            tblDoanhThu.getColumnModel().getColumn(1).setMinWidth(100);
            tblDoanhThu.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Năm");

        pnlTongSoNhanVien.setColor1(new java.awt.Color(204, 255, 204));
        pnlTongSoNhanVien.setColor2(new java.awt.Color(0, 153, 0));
        pnlTongSoNhanVien.setDescription("Người");
        pnlTongSoNhanVien.setTieuDe("Tổng Số Nhân Viên\n");

        pnlTongSoTour.setColor1(new java.awt.Color(255, 187, 221));
        pnlTongSoTour.setColor2(new java.awt.Color(204, 0, 102));
        pnlTongSoTour.setDescription("Loại");
        pnlTongSoTour.setTieuDe(" Tổng Số Tour");

        pnlTongSoDuKhach.setColor1(new java.awt.Color(227, 228, 249));
        pnlTongSoDuKhach.setDescription("Người");
        pnlTongSoDuKhach.setTieuDe(" Tổng Số Du Khách");

        btnXuatExcel_DoanhThu.setText("Xuất File Excel");
        btnXuatExcel_DoanhThu.setkEndColor(new java.awt.Color(255, 51, 153));
        btnXuatExcel_DoanhThu.setkStartColor(new java.awt.Color(204, 204, 255));
        btnXuatExcel_DoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcel_DoanhThuActionPerformed(evt);
            }
        });

        btnXuatPDF_DoanhThu.setText("Xuất File PDF");
        btnXuatPDF_DoanhThu.setkEndColor(new java.awt.Color(255, 51, 153));
        btnXuatPDF_DoanhThu.setkStartColor(new java.awt.Color(204, 204, 255));
        btnXuatPDF_DoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPDF_DoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXuatPDF_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnXuatExcel_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(pnlTongSoDuKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(pnlTongSoTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(pnlTongSoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel2)
                        .addGap(76, 76, 76)
                        .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTongSoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTongSoTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTongSoDuKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatExcel_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatPDF_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99))
        );

        materialTabbed1.addTab("Doanh Thu", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        showTableDoanhThu();
    }//GEN-LAST:event_cboNamActionPerformed

    private void cboNam_TourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNam_TourActionPerformed
        showTableTour();
    }//GEN-LAST:event_cboNam_TourActionPerformed

    private void cboTheoNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTheoNamItemStateChanged
        try {
            loadChart();
            LoadTable_DoanhThuQuy();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_cboTheoNamItemStateChanged

    private void cboQuyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboQuyItemStateChanged
        try {

            LoadTable_DoanhThuQuy();
            loadChart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboQuyItemStateChanged

    private void btnXuatExcel_TourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcel_TourActionPerformed

        new ExportExcel().exportExcel("DANH SÁCH THỐNG KÊ TOUR ĐƯỢC YÊU THÍCH NHẤT", "GreenHouseTour", tblTour, new int[]{10000, 6500, 5000, 6000});
        MsgBox.alert(null, "Xuất file excel thành công!\nHãy kiểm tra lại!");

    }//GEN-LAST:event_btnXuatExcel_TourActionPerformed

    private void btnXuatPDF_TourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDF_TourActionPerformed
        new ExportPDF().exportPDF("DANH SÁCH THỐNG KÊ TOUR ĐƯỢC YÊU THÍCH NHẤT", tblTour);
        MsgBox.alert(null, "Xuất file PDF thành công!Hãy kiểm tra lại!");

    }//GEN-LAST:event_btnXuatPDF_TourActionPerformed

    private void btnXuatExcel_DoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcel_DoanhThuActionPerformed
        new ExportExcel().exportExcel("DANH SÁCH THỐNG KÊ DOANH THU THEO NĂM", "GreenHouseTour", tblDoanhThu, new int[]{10000, 6500, 5000, 6000, 5000, 6000});
        MsgBox.alert(null, "Xuất file excel thành công!Hãy kiểm tra lại!");
    }//GEN-LAST:event_btnXuatExcel_DoanhThuActionPerformed

    private void btnXuatPDF_DoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDF_DoanhThuActionPerformed
        new ExportPDF().exportPDF("DANH SÁCH THỐNG KÊ DOANH THU THEO NĂM", tblDoanhThu);
        MsgBox.alert(null, "Xuất file PDF thành công!Hãy kiểm tra lại!");
    }//GEN-LAST:event_btnXuatPDF_DoanhThuActionPerformed

    private void btnXuatExcel_Tour1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcel_Tour1ActionPerformed
        new ExportExcel().exportExcel("DANH SÁCH THỐNG KÊ DOANH THU THEO QUÝ", "GreenHouseTour", tblThongKeTheoQuy, new int[]{6000, 6500, 5000, 6000, 6000, 6000});
        MsgBox.alert(null, "Xuất file excel thành công!\nHãy kiểm tra lại!");
    }//GEN-LAST:event_btnXuatExcel_Tour1ActionPerformed

    private void btnXuatPDF_Tour1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDF_Tour1ActionPerformed
        new ExportPDF().exportPDF("DANH SÁCH THỐNG KÊ DOANH THU THEO QUÝ", tblThongKeTheoQuy);
        MsgBox.alert(null, "Xuất file PDF thành công!Hãy kiểm tra lại!");
    }//GEN-LAST:event_btnXuatPDF_Tour1ActionPerformed
    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.Swing.KButton btnXuatExcel_DoanhThu;
    private com.GreenHouse.Swing.KButton btnXuatExcel_Tour;
    private com.GreenHouse.Swing.KButton btnXuatExcel_Tour1;
    private com.GreenHouse.Swing.KButton btnXuatPDF_DoanhThu;
    private com.GreenHouse.Swing.KButton btnXuatPDF_Tour;
    private com.GreenHouse.Swing.KButton btnXuatPDF_Tour1;
    private com.GreenHouse.Swing.Combobox cboNam;
    private com.GreenHouse.Swing.Combobox cboNam_Tour;
    private com.GreenHouse.Swing.Combobox cboQuy;
    private com.GreenHouse.Swing.Combobox cboTheoNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.GreenHouse.Swing.MaterialTabbed materialTabbed1;
    private javax.swing.JPanel pnlBieuDo;
    private javax.swing.JPanel pnlBieuDo1;
    private com.GreenHouse.Swing.Card pnlTongSoDuKhach;
    private com.GreenHouse.Swing.Card pnlTongSoNhanVien;
    private com.GreenHouse.Swing.Card pnlTongSoTour;
    private com.GreenHouse.Swing.Table_1 tblDoanhThu;
    private com.GreenHouse.Swing.Table_1 tblThongKeTheoQuy;
    private com.GreenHouse.Swing.Table_1 tblTour;
    // End of variables declaration//GEN-END:variables
}
