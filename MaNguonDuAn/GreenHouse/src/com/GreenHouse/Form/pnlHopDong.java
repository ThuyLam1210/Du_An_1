package com.GreenHouse.Form;

import com.GreenHouse.DAO.HopDongChiTietDAO;
import com.GreenHouse.DAO.HopDongDAO;
import com.GreenHouse.DAO.VeDAO;
import com.GreenHouse.Main.DangNhap;
import com.GreenHouse.Model.ModelHDCT;
import com.GreenHouse.Model.ModelHopDong;
import com.GreenHouse.Model.ModelVe;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Utils.MsgBox;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Thuy
 */
public class pnlHopDong extends javax.swing.JPanel {

    String chucVu = "";
    VeDAO veDAO = new VeDAO();
    ModelVe list = new ModelVe();
    HopDongDAO hdDAO = new HopDongDAO();
    HopDongChiTietDAO hdctDAO = new HopDongChiTietDAO();

    public pnlHopDong() throws SQLException {
        initComponents();
        fillBang();
        btnInHopDong.setVisible(false);
        tblHopDong.fixTable(jScrollPane3);
        btnLapHopDong2.setVisible(false);
        tblHopDongChiTiet.setDefaultEditor(Object.class, null);
        tblHopDong.setDefaultEditor(Object.class, null);
        txtMaHopDong.setText(hdDAO.generateMaHD());
        txtTimKiem.setHint("Nhập vào Mã Hợp đồng, Mã Vé, Số lượng khách.....");
    }

    public void innit(ModelVe list) {
        try {
            this.list = list;
            layDuLieuVe();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fillHopDongChiTiet() throws SQLException {
        DefaultTableModel modelSetDiaDiem = (DefaultTableModel) tblHopDongChiTiet.getModel();
        modelSetDiaDiem.setRowCount(0);
        String maHD = txtMaHopDong_CT.getText().trim();
        ArrayList<ModelHDCT> list = hdctDAO.fillBang(maHD);
        int i = 0;
        for (ModelHDCT hdct : list) {
            modelSetDiaDiem.addRow(new Object[]{++i, hdct.getMaHD(), hdct.getTenKhachHang(), hdct.getCmnd(), hdct.getSdt()});
        }
    }

    public void LapHopDongChiTiet() throws SQLException {
        try {
            if (CheckHDCT() && CheckTrung()) {
                ModelHDCT hdct = new ModelHDCT();
                hdct.setMaHD(txtMaHopDong_CT.getText());
                hdct.setTenKhachHang(txtTenKH.getText());
                hdct.setCmnd(txtCMND.getText().trim());
                hdct.setSdt(txtSoDT.getText().trim());
                hdctDAO.LapHopDongChiTiet(hdct);
                MsgBox.alert(null, "Thêm Thành Công");
                fillHopDongChiTiet();
                int soLuong = Integer.parseInt(txtSoLuongKhachNhapThongTin.getText()) - 1;
                txtSoLuongKhachNhapThongTin.setText(soLuong + "");
                if (soLuong == 0) {
                    btnThem_HDCT.setVisible(false);
                    MsgBox.alert(null, "Đã nhập đủ thông tin của khách đi cùng");
                    btnInHopDong.setVisible(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void LapHopDong() {
        try {
            if (Check()) {
                ModelHopDong hd = new ModelHopDong();
                hd.setMaHD(txtMaHopDong.getText().trim());
                hd.setMaVe(txtMaVe.getText().trim());
                hd.setNoiDungHD(txtNDHopDong.getText().trim());
                hd.setSoLuongKhach(Integer.parseInt(txtSoLuongKhach.getText().trim()));
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                hd.setNgayLap(dateFormat.parse(txtNgayLap.getText()));
                hdDAO.lapHopDong(hd);
                fillBang();
                MsgBox.alert(null, "Đã Lập Hợp Đồng Thành Công");
                if (Integer.parseInt(txtSoLuongKhach.getText().trim()) == 1) {
                    MsgBox.alert(null, "Đã đủ số lượng người đặt vé");

                    btnLapHopDong.setVisible(false);
                    btnLapHopDong2.setVisible(true);
                } else {
                    txtSoLuongKhachNhapThongTin.setText(hd.getSoLuongKhach() - 1 + "");
                    showDialog(HopDongChiTiet);
                    txtMaHopDong_CT.setText(txtMaHopDong.getText());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void layDuLieuVe() throws SQLException {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            //System.out.println(list.getMaTour());
            txtMaTour.setText(list.getMaTour());
            txtMaVe.setText(list.getMaVe());
            txtTenNhanVien.setText(list.getMaNhanVien());
            txtTenKhachHang.setText(list.getMaKhachHang());
            int soluongkhach = list.getSoLuongNguoiLon() + list.getSoLuongTreEm();
            txtSoLuongKhach.setText(soluongkhach + "");
            txtNgayLap.setText(df.format(new Date().getTime()));
            txtSoLuongKhachNhapThongTin.setText(txtSoLuongKhach.getText());
            txtMaHopDong.setText(hdDAO.generateMaHD());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void fillBang() throws SQLException {
        ArrayList<ModelHopDong> list = hdDAO.selectHopDong();
        DefaultTableModel m = (DefaultTableModel) tblHopDong.getModel();
        m.setRowCount(0);
        int i = 0;
        for (ModelHopDong hd : list) {
            m.addRow(new Object[]{++i, hd.getMaHD(), hd.getMaVe(), hdDAO.selectMaTourByMaVe(hd.getMaVe()), hd.getNgayLap(), hd.getSoLuongKhach(), hd.getNoiDungHD()});
        }

    }

    private void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HopDongChiTiet = new javax.swing.JDialog();
        jPanel23 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtMaHopDong_CT = new com.GreenHouse.Swing.MyTextField();
        txtTenKH = new com.GreenHouse.Swing.MyTextField();
        txtSoDT = new com.GreenHouse.Swing.MyTextField();
        txtCMND = new com.GreenHouse.Swing.MyTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnThem_HDCT = new com.GreenHouse.Swing.KButton();
        btnInHopDong = new com.GreenHouse.Swing.KButton();
        btnCapNhat_HDCT = new com.GreenHouse.Swing.KButton();
        jLabel2 = new javax.swing.JLabel();
        txtSoLuongKhachNhapThongTin = new javax.swing.JLabel();
        btnNew = new com.GreenHouse.Swing.KButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHopDongChiTiet = new com.GreenHouse.Swing.Table_1();
        jLabel17 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        materialTabbed2 = new com.GreenHouse.Swing.MaterialTabbed();
        jPanel24 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtNgayLap = new com.GreenHouse.Swing.MyTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTenKhachHang = new com.GreenHouse.Swing.MyTextField();
        jLabel25 = new javax.swing.JLabel();
        txtSoLuongKhach = new com.GreenHouse.Swing.MyTextField();
        jLabel26 = new javax.swing.JLabel();
        textAreaScroll1 = new com.GreenHouse.Swing.TextAreaScroll();
        txtNDHopDong = new com.GreenHouse.Swing.TextArea();
        jLabel27 = new javax.swing.JLabel();
        txtMaHopDong = new com.GreenHouse.Swing.MyTextField();
        txtTenNhanVien = new com.GreenHouse.Swing.MyTextField();
        jLabel28 = new javax.swing.JLabel();
        btnLapHopDong = new com.GreenHouse.Swing.KButton();
        jLabel29 = new javax.swing.JLabel();
        txtMaTour = new com.GreenHouse.Swing.MyTextField();
        txtMaVe = new com.GreenHouse.Swing.MyTextField();
        jLabel30 = new javax.swing.JLabel();
        btnLapHopDong2 = new com.GreenHouse.Swing.KButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHopDong = new com.GreenHouse.Swing.Table_1();
        txtTimKiem = new com.GreenHouse.Swing.MyTextField();
        jLabel19 = new javax.swing.JLabel();
        kButton1 = new com.GreenHouse.Swing.KButton();

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập Thông Tin Khách Đi Cùng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 153)))); // NOI18N

        txtMaHopDong_CT.setEditable(false);
        txtMaHopDong_CT.setNextFocusableComponent(txtTenKH);

        txtTenKH.setNextFocusableComponent(txtCMND);

        txtCMND.setNextFocusableComponent(txtSoDT);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Tên Khách Hàng");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Mã Hợp Đồng");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Số Điện Thoại");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("CMND/CCCD");

        btnThem_HDCT.setText("Thêm");
        btnThem_HDCT.setkEndColor(new java.awt.Color(255, 204, 204));
        btnThem_HDCT.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnThem_HDCT.setkStartColor(new java.awt.Color(51, 0, 204));
        btnThem_HDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_HDCTActionPerformed(evt);
            }
        });

        btnInHopDong.setText("In Hợp Đồng");
        btnInHopDong.setkEndColor(new java.awt.Color(255, 204, 204));
        btnInHopDong.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnInHopDong.setkStartColor(new java.awt.Color(51, 0, 204));
        btnInHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHopDongActionPerformed(evt);
            }
        });

        btnCapNhat_HDCT.setText("Cập Nhật");
        btnCapNhat_HDCT.setkEndColor(new java.awt.Color(255, 204, 204));
        btnCapNhat_HDCT.setkSelectedColor(new java.awt.Color(255, 153, 153));
        btnCapNhat_HDCT.setkStartColor(new java.awt.Color(51, 0, 204));
        btnCapNhat_HDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhat_HDCTActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("* Số lượng khách cần nhập thông tin: ");

        txtSoLuongKhachNhapThongTin.setForeground(new java.awt.Color(255, 51, 51));

        btnNew.setText("Mới");
        btnNew.setkEndColor(new java.awt.Color(204, 204, 255));
        btnNew.setkStartColor(new java.awt.Color(0, 0, 153));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnThem_HDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnCapNhat_HDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(btnInHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtMaHopDong_CT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                        .addComponent(txtCMND, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuongKhachNhapThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(106, 106, 106))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSoLuongKhachNhapThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHopDong_CT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem_HDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat_HDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(181, 181, 181))
        );

        jPanel23.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 590, 670));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 153)))); // NOI18N

        tblHopDongChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hợp Đồng", "Tên Khách Hàng", "CMND/CCCD", "SĐT"
            }
        ));
        tblHopDongChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHopDongChiTietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHopDongChiTiet);
        if (tblHopDongChiTiet.getColumnModel().getColumnCount() > 0) {
            tblHopDongChiTiet.getColumnModel().getColumn(1).setMinWidth(0);
            tblHopDongChiTiet.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
        );

        jPanel23.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 590, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("HỢP ĐỒNG CHI TIẾT");
        jPanel23.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 40));

        javax.swing.GroupLayout HopDongChiTietLayout = new javax.swing.GroupLayout(HopDongChiTiet.getContentPane());
        HopDongChiTiet.getContentPane().setLayout(HopDongChiTietLayout);
        HopDongChiTietLayout.setHorizontalGroup(
            HopDongChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        HopDongChiTietLayout.setVerticalGroup(
            HopDongChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HopDongChiTietLayout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(1100, 800));
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1100, 800));

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1150, 813));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ HỢP ĐỒNG");

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Tên Đại Diện Công Ty");

        txtNgayLap.setEditable(false);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Số Lượng Khách");

        txtTenKhachHang.setEditable(false);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("Ngày Lập");

        txtSoLuongKhach.setEditable(false);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Ghi Chú");

        textAreaScroll1.setLabelText("");

        txtNDHopDong.setColumns(20);
        txtNDHopDong.setRows(5);
        textAreaScroll1.setViewportView(txtNDHopDong);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Tên Tour");

        txtTenNhanVien.setEditable(false);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Tên Đại Diện Khách Hàng");

        btnLapHopDong.setText("Lập Hợp Đồng");
        btnLapHopDong.setkEndColor(new java.awt.Color(255, 204, 255));
        btnLapHopDong.setkStartColor(new java.awt.Color(0, 51, 204));
        btnLapHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLapHopDongActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Mã Hợp Đồng");

        txtMaTour.setEditable(false);

        txtMaVe.setEditable(false);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Mã Vé");

        btnLapHopDong2.setText("In Hợp Đồng");
        btnLapHopDong2.setkEndColor(new java.awt.Color(204, 255, 255));
        btnLapHopDong2.setkStartColor(new java.awt.Color(0, 0, 204));
        btnLapHopDong2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLapHopDong2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(txtMaTour, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(txtMaHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(43, 43, 43))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(18, 42, Short.MAX_VALUE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuongKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(textAreaScroll1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaVe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(btnLapHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173)
                        .addComponent(btnLapHopDong2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addComponent(txtSoLuongKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(txtMaHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(102, 102, 102)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLapHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLapHopDong2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        jPanel24.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1090, 650));

        materialTabbed2.addTab("Cập Nhật", jPanel24);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hợp Đồng", "Mã Vé", "Mã Tour", "Ngày Lập", "Số Lượng Khách", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblHopDong);
        if (tblHopDong.getColumnModel().getColumnCount() > 0) {
            tblHopDong.getColumnModel().getColumn(0).setMinWidth(35);
            tblHopDong.getColumnModel().getColumn(0).setMaxWidth(35);
            tblHopDong.getColumnModel().getColumn(2).setMinWidth(40);
        }

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Tìm kiếm");

        kButton1.setText("In Hợp Đồng");
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel19)
                        .addGap(30, 30, 30)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        materialTabbed2.addTab("Danh sách", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(materialTabbed2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialTabbed2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 830));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLapHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLapHopDongActionPerformed
        if (new DangNhap().vt.equalsIgnoreCase("Nhân Viên Trực Quầy")) {
            LapHopDong();
        } else {
            JOptionPane.showMessageDialog(null, "Chỉ Nhân Viên Trực Quầy được phép sử dụng chức năng này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnLapHopDongActionPerformed

    private void btnThem_HDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_HDCTActionPerformed
        try {
            if (new DangNhap().vt.equalsIgnoreCase("Nhân Viên Trực Quầy")) {
                LapHopDongChiTiet();
            } else {
                JOptionPane.showMessageDialog(null, "Chỉ Nhân Viên Trực Quầy được phép sử dụng chức năng này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnThem_HDCTActionPerformed

    private void btnCapNhat_HDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhat_HDCTActionPerformed
        if (tblHopDongChiTiet.getSelectedRow() != -1) {
            updateHDCT(tblHopDongChiTiet.getSelectedRow());
        } else {
            MsgBox.error(null, "Chưa chọn đối tượng cần cập nhật");
        }
    }//GEN-LAST:event_btnCapNhat_HDCTActionPerformed
    private void New() {
        txtTenKH.setText("");
        txtCMND.setText("");
        txtSoDT.setText("");
        btnThem_HDCT.setVisible(true);
    }
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        New();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnLapHopDong2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLapHopDong2ActionPerformed
        XuatHoaDon_HopDong();
    }//GEN-LAST:event_btnLapHopDong2ActionPerformed

    private void btnInHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHopDongActionPerformed
        XuatHoaDon_HDCT();
    }//GEN-LAST:event_btnInHopDongActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        if (txtTimKiem.getText().equals("")) {
            try {
                fillBang();
            } catch (SQLException ex) {
                Logger.getLogger(pnlHopDong.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            hdDAO.selectbyTimKiem(tblHopDong, txtTimKiem.getText().trim());
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblHopDongChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHopDongChiTietMouseClicked
        showDataHDCT();
        btnThem_HDCT.setVisible(false);
    }//GEN-LAST:event_tblHopDongChiTietMouseClicked

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        int i = tblHopDong.getSelectedRow();
        if (i == -1) {
            MsgBox.alert(null, "Vui lòng chọn Hợp đồng");
        } else {
            String maHopDong = tblHopDong.getValueAt(i, 1).toString();
            String maVe = tblHopDong.getValueAt(i, 2).toString();
            String maTour = tblHopDong.getValueAt(i, 3).toString();
            int slk = Integer.parseInt(tblHopDong.getValueAt(i, 5).toString());
            System.out.println(slk);
            if (slk == 1) {
                try {
                    Hashtable map = new Hashtable();
                    JasperReport rp = JasperCompileManager.compileReport("src//com//GreenHouse//Export//XuatHopDong.jrxml");
                    // int ma = Integer.parseInt(txtMaHD.getText());
                    map.put("MaHopDong", maHopDong);
                    map.put("MaTour", maTour);
                    map.put("MaVe", maVe);
                    map.put("SUBREPORT_DIR", "src//com//GreenHouse//Export//");
                    Connection con = com.GreenHouse.Connection.JDBCHelper.dbConnection();
                    JasperPrint jasperPrint = JasperFillManager.fillReport(rp, map, con);

                    JasperViewer.viewReport(jasperPrint,false);
                } catch (Exception e) {
                }

            } else {
                try {
                    Hashtable map = new Hashtable();
                    JasperReport rp = JasperCompileManager.compileReport("src//com//GreenHouse//Export//InHopDong.jrxml");
                    // int ma = Integer.parseInt(txtMaHD.getText());
                    map.put("MaHopDong", maHopDong);
                    map.put("MaTour", maTour);
                    map.put("MaVe", maVe);
                    map.put("SUBREPORT_DIR", "src//com//GreenHouse//Export//");
                    Connection con = com.GreenHouse.Connection.JDBCHelper.dbConnection();
                    JasperPrint jasperPrint = JasperFillManager.fillReport(rp, map, con);

                    JasperViewer.viewReport(jasperPrint,false);
                } catch (Exception e) {
                }

            }
        }

    }//GEN-LAST:event_kButton1ActionPerformed

    private void showDataHDCT() {
        int index = tblHopDongChiTiet.getSelectedRow();
        txtTenKH.setText(tblHopDongChiTiet.getValueAt(index, 2).toString());
        txtCMND.setText(tblHopDongChiTiet.getValueAt(index, 3).toString());
        txtSoDT.setText(tblHopDongChiTiet.getValueAt(index, 4).toString());
    }

///////////////
    public void XuatHoaDon_HopDong() {
        try {
            String maHD = txtMaHopDong.getText();
            String maTour = txtMaTour.getText();
            String mave = txtMaVe.getText();

            Hashtable map = new Hashtable();
            JasperReport rp = JasperCompileManager.compileReport("src//com//GreenHouse//Export//XuatHopDong.jrxml");
            // int ma = Integer.parseInt(txtMaHD.getText());
            map.put("MaHopDong", maHD);
            map.put("MaTour", maTour);
            map.put("MaVe", mave);
            map.put("SUBREPORT_DIR", "src//com//GreenHouse//Export//");
            Connection con = com.GreenHouse.Connection.JDBCHelper.dbConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(rp, map, con);

            JasperViewer.viewReport(jasperPrint,false);
            //i    JasperExportManager.exportReportToPdfFile(jasperPrint, "test.pdf");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void XuatHoaDon_HDCT() {
        try {
            String maHD = txtMaHopDong.getText();
            String maTour = txtMaTour.getText();
            String mave = txtMaVe.getText();
            System.out.println(maHD + " " + maTour + " " + mave);
            Hashtable map = new Hashtable();
            JasperReport rp = JasperCompileManager.compileReport("src//com//GreenHouse//Export//InHopDong.jrxml");
            // int ma = Integer.parseInt(txtMaHD.getText());
            map.put("MaHopDong", maHD);
            map.put("MaTour", maTour);
            map.put("MaVe", mave);
            map.put("SUBREPORT_DIR", "src//com//GreenHouse//Export//");
            Connection con = com.GreenHouse.Connection.JDBCHelper.dbConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(rp, map, con);

            JasperViewer.viewReport(jasperPrint,false);
            //i    JasperExportManager.exportReportToPdfFile(jasperPrint, "test.pdf");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    ///////////////////////////BẮT LỖI//////////////

    private boolean CheckHDCT() {
        String regex = "^([a-zA-Z\\xC0-\\uFFFF]{1,50}[ \\-\\']{0,1}){1,}$";
        String patternSDT = "^((09|03|07|08|05)+([0-9]{8})\\b)$";
        boolean checkCMND = Pattern.matches("\\d{9}", txtCMND.getText().trim());

        if (txtTenKH.getText().equals("")) {
            MsgBox.error(null, "Tên Khách hàng không được chứa kí tự đặt biệt!");
            txtTenKH.requestFocus();
            return false;
        }

        if (!txtTenKH.getText().matches(regex)) {
            txtTenKH.requestFocus();
            MsgBox.error(null, "Tên không đúng định dạng!");
            return false;
        }
        if (txtSoDT.getText().equals("")) {
            txtSoDT.requestFocus();
            MsgBox.error(null, "Số điện thoại không được để trống");
        }
        if (!txtSoDT.getText().matches(patternSDT)) {
            txtSoDT.requestFocus();
            MsgBox.error(null, "SĐT phải là đầu số Việt Nam và có 10 chữ số!");
            return false;
        }

        if (txtCMND.getText().equals("")) {
            txtCMND.requestFocus();
            MsgBox.error(null, "Chưa Nhập CCCD_CMND!");
            return false;
        }
        if (txtCMND.getText().length() == 9 || txtCMND.getText().length() == 12) {

            //  return true;
        } else {
            MsgBox.error(null, "Chưa nhập đúng định dạng CCCD!\n CCCD phải là 9 hoặc 12 số!");
            txtCMND.requestFocus();
            return false;
        }

        return true;
    }

    private boolean CheckTrung() {
        try {
            if (hdDAO.CheckCMNDCCCD(txtCMND.getText(), txtMaHopDong_CT.getText())) {
                txtCMND.requestFocus();
                MsgBox.error(null, "Đã tồn tại CCCD/CMND này!");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlHopDong.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (hdDAO.CheckSDT(txtSoDT.getText(), txtMaHopDong_CT.getText())) {
                txtSoDT.requestFocus();
                MsgBox.error(null, "Đã tồn tại số điện thoại này!");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlHopDong.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private boolean Check() throws SQLException {
        boolean regexma = Pattern.matches("HD{1}[0-9]{1,}", txtMaHopDong.getText());

        if (txtMaHopDong.getText().equals("")) {
            txtMaHopDong.requestFocus();
            MsgBox.error(null, "Mã Hợp Đồng không được để trống");
            return false;
        }
        if (!regexma) {
            MsgBox.error(null, "Mã khách hàng không đúng định dạng KH...");

            txtMaHopDong.requestFocus();
            return false;
        }
        if (hdDAO.CheckMaHopDong(txtMaHopDong.getText().trim())) {
            txtMaHopDong.requestFocus();
            MsgBox.error(null, "Hợp Đồng Đã Tồn Tại!");
            return false;
        }

        if (txtNDHopDong.getText().equals("")) {
            txtNDHopDong.requestFocus();
            MsgBox.error(null, "Vui lòng nhập vào ghi chú hợp đồng!");
            return false;
        }

        return true;
    }
//================================================================================================================================

    private void updateHDCT(int index) {
        try {
            if (CheckHDCT()) {
                ModelHDCT hdct = new ModelHDCT();
                hdct.setMaHD(txtMaHopDong_CT.getText());
                hdct.setTenKhachHang(txtTenKH.getText());
                hdct.setCmnd(txtCMND.getText().trim());
                hdct.setSdt(txtSoDT.getText().trim());

                hdctDAO.updateHDCT(hdct, tblHopDongChiTiet.getValueAt(index, 3).toString());
                fillHopDongChiTiet();

                MsgBox.alert(null, "Cập nhật thành công");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//================================================================================================================================
    private void showDialog(JDialog nameDialog) {
        nameDialog.setVisible(true);
        nameDialog.setResizable(false);
        nameDialog.getContentPane().setBackground(Color.WHITE);
        nameDialog.pack();
        nameDialog.setLocationRelativeTo(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog HopDongChiTiet;
    private com.GreenHouse.Swing.KButton btnCapNhat_HDCT;
    private com.GreenHouse.Swing.KButton btnInHopDong;
    private com.GreenHouse.Swing.KButton btnLapHopDong;
    private com.GreenHouse.Swing.KButton btnLapHopDong2;
    private com.GreenHouse.Swing.KButton btnNew;
    private com.GreenHouse.Swing.KButton btnThem_HDCT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.GreenHouse.Swing.KButton kButton1;
    private com.GreenHouse.Swing.MaterialTabbed materialTabbed2;
    private com.GreenHouse.Swing.Table_1 tblHopDong;
    private com.GreenHouse.Swing.Table_1 tblHopDongChiTiet;
    private com.GreenHouse.Swing.TextAreaScroll textAreaScroll1;
    private com.GreenHouse.Swing.MyTextField txtCMND;
    private com.GreenHouse.Swing.MyTextField txtMaHopDong;
    private com.GreenHouse.Swing.MyTextField txtMaHopDong_CT;
    private com.GreenHouse.Swing.MyTextField txtMaTour;
    private com.GreenHouse.Swing.MyTextField txtMaVe;
    private com.GreenHouse.Swing.TextArea txtNDHopDong;
    private com.GreenHouse.Swing.MyTextField txtNgayLap;
    private com.GreenHouse.Swing.MyTextField txtSoDT;
    private com.GreenHouse.Swing.MyTextField txtSoLuongKhach;
    private javax.swing.JLabel txtSoLuongKhachNhapThongTin;
    private com.GreenHouse.Swing.MyTextField txtTenKH;
    private com.GreenHouse.Swing.MyTextField txtTenKhachHang;
    private com.GreenHouse.Swing.MyTextField txtTenNhanVien;
    private com.GreenHouse.Swing.MyTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
