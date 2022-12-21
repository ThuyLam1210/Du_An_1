/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Form;

import com.GreenHouse.DAO.KhachHangDAO;
import com.GreenHouse.DAO.VeDAO;
import com.GreenHouse.Main.DangNhap;
import com.GreenHouse.Model.ModelKhachHang;
import com.GreenHouse.Model.ModelLichTrinh;
import com.GreenHouse.Model.ModelNhanVien;
import com.GreenHouse.Model.ModelTour;
import com.GreenHouse.Model.ModelVe;
import com.GreenHouse.Utils.MsgBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Ve extends javax.swing.JPanel {

    private ModelLichTrinh data = new ModelLichTrinh();
    private VeDAO veDAO = new VeDAO();
    private DecimalFormat df = new DecimalFormat("#,###");
    private int indextbl = 0;
    KhachHangDAO khDAO = new KhachHangDAO();
    String chucVu = "";

    public void taoHopDong(ActionListener ac) {

        btnLapHopDong.addActionListener(ac);
    }

    public Ve() {
        initComponents();
        btnLayThongTin.setVisible(false);
        btnLapHopDong.setVisible(false);
        tblVe.fixTable(jScrollPane1);
        
        tblCustomer.setDefaultEditor(Object.class, null);
        //=====================
        try {
            fillTableVe();
            veDAO.fillBangKhachHang(tblCustomer);
            tblCustomer.fixTable(jScrollPane2);
            txtTimKiem.setHint("Tên khách hàng, email, địa chỉ");
        } catch (SQLException ex) {
            Logger.getLogger(Ve.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ModelVe layDuLieu() {
        ModelVe list = new ModelVe();
        try {
            list.setMaTour(txtMaTour.getText().trim());
            list.setMaVe(txtMaVe.getText().trim());
            list.setMaKhachHang(txtTenKhachHang.getText().trim());
            list.setMaNhanVien(txtTenNhanVien.getText().trim());
            list.setSoLuongNguoiLon(Integer.parseInt(txtSoNguoiLon.getText()));
            list.setSoLuongTreEm(Integer.parseInt(txtSoTreEm.getText()));
        } catch (Exception e) {
            Logger.getLogger(LichTrinh.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public void setData(ModelLichTrinh data) {
        try {
            this.data = data;
            init();
            btnLayThongTin.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Ve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // CAP NHAT ================================================================================================================
    private void init() throws SQLException {
        tblVe.fixTable(jScrollPane1);
        ModelTour mt = veDAO.selectTourByMaLT(data.getMaLT());
        txtMaTour.setText(mt.getMaTour());
        txtTenTour.setText(mt.getTenTour());
        txtGiaVe.setText(df.format(mt.getGiaTour()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        txtNgayBan.setText(dateFormat.format(new Date().getTime()));
        txtMaNhanVien.setText(DangNhap.nv.getMaNV());
        txtMaVe.setText(veDAO.generateMaVE());
    }
 
       private void tinhTongTien() {
        int soNguoiLon = 0;
        int soTreEm = 0;
        if (!txtSoNguoiLon.getText().equals("")) {
            soNguoiLon = Integer.parseInt(txtSoNguoiLon.getText().trim());
        }
        
        if (!txtSoTreEm.getText().equals("")) {
            soTreEm = Integer.parseInt(txtSoTreEm.getText().trim());
        } 
        float giaTour = Float.parseFloat(txtGiaVe.getText().trim().replace(",", ""));
        float tongTien = (float) (giaTour * (soNguoiLon + soTreEm * 0.75));
        txtTongTien.setText(df.format(tongTien));
    
    }

    private void clearForm() {
        if (data.getMaLT() != null) {
            txtMaKhachHang.setText("");
            txtTenKhachHang.setText("");
            txtMaVe.setText("");
            txtSoNguoiLon.setText("0");
            txtSoTreEm.setText("0");
        } else {
            txtMaTour.setText("");
            txtTenTour.setText("");
            txtMaKhachHang.setText("");
            txtTenKhachHang.setText("");
            txtMaVe.setText("");
            txtSoNguoiLon.setText("0");
            txtSoTreEm.setText("0");
        }

    }

    private boolean validateForm() {
        if (txtMaKhachHang.getText().equals("")) {
            txtMaKhachHang.requestFocus();
            JOptionPane.showMessageDialog(null, "Lỗi trống thông tin mã khách hàng");
            return false;
        }
        if (txtMaVe.getText().equals("")) {
            txtMaVe.requestFocus();
            JOptionPane.showMessageDialog(null, "Lỗi trống thông tin mã vé");
            return false;
        }
        if (txtSoNguoiLon.getText().equals("")) {
            txtSoNguoiLon.requestFocus();
            JOptionPane.showMessageDialog(null, "Lỗi trống thông tin số vé người lớn");
            return false;
        }
        if (txtSoTreEm.getText().equals("")) {
            txtSoTreEm.requestFocus();
            JOptionPane.showMessageDialog(null, "Lỗi trống thông tin số vé trẻ em");
            return false;
        }
        int i = Integer.parseInt(txtSoNguoiLon.getText());
        int j = Integer.parseInt(txtSoTreEm.getText());
        if (i == 0 && j == 0) {
            JOptionPane.showMessageDialog(null, "Lỗi số người đặt vé không hợp lệ");
            return false;
        } else if (i == 0 && j != 0) {
            JOptionPane.showMessageDialog(null, "Người đặt vé phải là người lớn");
            return false;
        }

        return true;
    }

    private void insertVe() throws SQLException {
        try {
            ModelVe ve = new ModelVe();
            ve.setMaVe(txtMaVe.getText().trim());
            ve.setMaTour(txtMaTour.getText().trim());
            ve.setMaLichTrinh(data.getMaLT());
            ve.setMaKhachHang(txtMaKhachHang.getText().trim());
            ve.setMaNhanVien(txtMaNhanVien.getText().trim());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            ve.setNgayBan(dateFormat.parse(txtNgayBan.getText().trim()));
            ve.setSoLuongNguoiLon(Integer.parseInt(txtSoNguoiLon.getText().trim()));
            ve.setSoLuongTreEm(Integer.parseInt(txtSoTreEm.getText().trim()));
            ve.setTongTien(Double.parseDouble(txtTongTien.getText().trim().replace(",", "")));
            veDAO.insertVe(ve);
            fillTableVe();
            JOptionPane.showMessageDialog(null, "Thêm thành công\n Nhấn Lập Hợp Đồng");
            new LichTrinh().fillBang();
            btnLapHopDong.setVisible(true);
            btnLuu.setVisible(false);
        } catch (Exception e) {
            MsgBox.error(null, "Thêm thất bại");
        }
    }

    private void fillTenNhanVien() {
        ModelNhanVien mt = new ModelNhanVien();
        try {
            mt = veDAO.selectNhanVienByID(txtMaNhanVien.getText().trim());
        } catch (SQLException ex) {
            Logger.getLogger(Ve.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mt.getTenNV() != null) {
            txtTenNhanVien.setText(mt.getTenNV());
        } else {
            txtTenNhanVien.setText("");
        }
    }

    private void fillTenKhachHang() {
        ModelKhachHang mt = new ModelKhachHang();
        try {
            mt = veDAO.selectKhachHangByID(txtMaKhachHang.getText().trim());
        } catch (SQLException ex) {
            Logger.getLogger(Ve.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mt.getName() != null) {
            txtTenKhachHang.setText(mt.getName());
        } else {
            txtTenKhachHang.setText("");
        }
    }

    // DANH SACH ===================================================================================
    private void fillTableVe() throws SQLException {
        ArrayList<ModelVe> list = veDAO.selectVeAll();
        DefaultTableModel m = (DefaultTableModel) tblVe.getModel();
        m.setRowCount(0);
        int i = 0;
        DecimalFormat df = new DecimalFormat("#,###");
        for (ModelVe mo : list) {
            m.addRow(new Object[]{
                ++i, mo.getMaVe(), mo.getMaNhanVien(), mo.getMaKhachHang(), mo.getMaLichTrinh(), mo.getSoLuongNguoiLon(), mo.getSoLuongTreEm(), df.format(mo.getTongTien()), mo.getNgayBan()
            });
        }
    }

    private void showData(int index) {
        try {
            ModelTour mt = veDAO.selectTourByMaLT(tblVe.getValueAt(index, 4).toString());
            txtMaTour.setText(mt.getMaTour());
            txtTenTour.setText(mt.getTenTour());
            txtGiaVe.setText(df.format(mt.getGiaTour()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            txtNgayBan.setText(dateFormat.format(tblVe.getValueAt(index, 8)));
            txtMaNhanVien.setText(tblVe.getValueAt(index, 2).toString());

            txtMaVe.setText(tblVe.getValueAt(index, 1).toString());
            txtTenNhanVien.setText(tblVe.getValueAt(index, 2).toString());
            // txtTenKhachHang.setText(tblVe.getValueAt(index, 3).toString());
            txtSoNguoiLon.setText(tblVe.getValueAt(index, 5).toString());
            txtSoTreEm.setText(tblVe.getValueAt(index, 6).toString());
            txtTongTien.setText(tblVe.getValueAt(index, 7).toString());
        } catch (SQLException ex) {
            Logger.getLogger(Ve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        KhachHang = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        lblCustomerManager = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtID = new com.GreenHouse.Swing.MyTextField();
        txtName = new com.GreenHouse.Swing.MyTextField();
        lblName = new javax.swing.JLabel();
        txtCccd = new com.GreenHouse.Swing.MyTextField();
        lblCccd = new javax.swing.JLabel();
        lblNumberPhone = new javax.swing.JLabel();
        txtAdress = new com.GreenHouse.Swing.MyTextField();
        txtEmail = new com.GreenHouse.Swing.MyTextField();
        lblSex = new javax.swing.JLabel();
        lblAdress = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFamale = new javax.swing.JRadioButton();
        txtNumberPhone = new com.GreenHouse.Swing.MyTextField();
        lblEmail = new javax.swing.JLabel();
        btnThem = new com.GreenHouse.Swing.KButton();
        btnDanhSachKhachHang = new com.GreenHouse.Swing.KButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        DanhSachKhachHang = new javax.swing.JDialog();
        pnlList = new javax.swing.JPanel();
        txtTimKiem = new com.GreenHouse.Swing.MyTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomer = new com.GreenHouse.Swing.Table_1();
        jLabel1 = new javax.swing.JLabel();
        tab = new com.GreenHouse.Swing.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaTour = new com.GreenHouse.Swing.MyTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenTour = new com.GreenHouse.Swing.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMaVe = new com.GreenHouse.Swing.MyTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenKhachHang = new com.GreenHouse.Swing.MyTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenNhanVien = new com.GreenHouse.Swing.MyTextField();
        txtNgayBan = new com.GreenHouse.Swing.MyTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoNguoiLon = new com.GreenHouse.Swing.MyTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSoTreEm = new com.GreenHouse.Swing.MyTextField();
        txtGiaVe = new com.GreenHouse.Swing.MyTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTongTien = new com.GreenHouse.Swing.MyTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnLamMoi = new com.GreenHouse.Swing.Button();
        btnLuu = new com.GreenHouse.Swing.Button();
        txtMaKhachHang = new com.GreenHouse.Swing.MyTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtMaNhanVien = new com.GreenHouse.Swing.MyTextField();
        btnLayThongTin = new com.GreenHouse.Swing.Button();
        btnLapHopDong = new com.GreenHouse.Swing.Button();
        jPanel2 = new javax.swing.JPanel();
        txtFind = new com.GreenHouse.Swing.MyTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVe = new com.GreenHouse.Swing.Table_1();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblCustomerManager.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCustomerManager.setText("THÔNG TIN KHÁCH HÀNG");

        lblID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblID.setText("Mã khách hàng");

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblName.setText("Tên khách hàng");

        lblCccd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCccd.setText("CCCD/CMND");

        lblNumberPhone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNumberPhone.setText("Số điện thoại");

        lblSex.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSex.setText("Giới tính");

        lblAdress.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAdress.setText("Địa chỉ");

        rdoMale.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoMale);
        rdoMale.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdoMale.setSelected(true);
        rdoMale.setText("Nam");

        rdoFamale.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoFamale);
        rdoFamale.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdoFamale.setText("Nữ");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail.setText("Email");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnDanhSachKhachHang.setText("Danh sách khách hàng");
        btnDanhSachKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCustomerManager, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(560, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDanhSachKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(lblID)
                            .addGap(379, 379, 379)
                            .addComponent(lblNumberPhone))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(180, 180, 180)
                            .addComponent(txtNumberPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(lblName)
                            .addGap(376, 376, 376)
                            .addComponent(lblEmail))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(180, 180, 180)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(lblCccd)
                            .addGap(394, 394, 394)
                            .addComponent(lblSex))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtCccd, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(180, 180, 180)
                            .addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(rdoFamale, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(lblAdress))
                        .addComponent(txtAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCustomerManager, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDanhSachKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblID)
                        .addComponent(lblNumberPhone))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumberPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(22, 22, 22)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(lblName))
                        .addComponent(lblEmail))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(22, 22, 22)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(lblCccd))
                        .addComponent(lblSex))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoMale)
                        .addComponent(rdoFamale))
                    .addGap(32, 32, 32)
                    .addComponent(lblAdress)
                    .addGap(20, 20, 20)
                    .addComponent(txtAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout KhachHangLayout = new javax.swing.GroupLayout(KhachHang.getContentPane());
        KhachHang.getContentPane().setLayout(KhachHangLayout);
        KhachHangLayout.setHorizontalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        KhachHangLayout.setVerticalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlList.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.setToolTipText("Mã khách hàng, tên khách hàng, CCCD/CMND, Số điện thoại, Địa chỉ");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Tìm kiếm");

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCustomer);

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel19)
                        .addGap(27, 27, 27)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlListLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DanhSachKhachHangLayout = new javax.swing.GroupLayout(DanhSachKhachHang.getContentPane());
        DanhSachKhachHang.getContentPane().setLayout(DanhSachKhachHangLayout);
        DanhSachKhachHangLayout.setHorizontalGroup(
            DanhSachKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1009, Short.MAX_VALUE)
            .addGroup(DanhSachKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DanhSachKhachHangLayout.setVerticalGroup(
            DanhSachKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
            .addGroup(DanhSachKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÍ VÉ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 265, 35));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã Tour");

        txtMaTour.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên Tour");

        txtTenTour.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Mã Vé");

        txtMaVe.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tên Khách Hàng ");

        txtTenKhachHang.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tên Nhân Viên");

        txtTenNhanVien.setEditable(false);

        txtNgayBan.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Ngày Bán");

        txtSoNguoiLon.setText("0");
        txtSoNguoiLon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoNguoiLonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoNguoiLonKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Số lượng người lớn");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Số lượng trẻ em");

        txtSoTreEm.setText("0");
        txtSoTreEm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoTreEmKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoTreEmKeyTyped(evt);
            }
        });

        txtGiaVe.setEditable(false);
        txtGiaVe.setText("0");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Giá vé");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Tổng tiền");

        txtTongTien.setEditable(false);
        txtTongTien.setText("0");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Người");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Người");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("VNĐ/1 người");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("VNĐ");

        btnLamMoi.setBackground(new java.awt.Color(255, 255, 153));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(255, 255, 153));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaKhachHangCaretUpdate(evt);
            }
        });
        txtMaKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaKhachHangMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Mã Khách Hàng");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Mã Nhân Viên");

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaNhanVienCaretUpdate(evt);
            }
        });

        btnLayThongTin.setBackground(new java.awt.Color(255, 255, 153));
        btnLayThongTin.setText("Lấy Thông Tin");
        btnLayThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayThongTinActionPerformed(evt);
            }
        });

        btnLapHopDong.setBackground(new java.awt.Color(255, 204, 204));
        btnLapHopDong.setText("Lập Hợp Đồng");
        btnLapHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLapHopDongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnLapHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaTour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                .addComponent(txtTenTour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMaKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNgayBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGiaVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSoTreEm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSoNguoiLon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaVe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel15))
                                    .addComponent(btnLayThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(53, 53, 53))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoNguoiLon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoTreEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLayThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLapHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        tab.addTab("Cập nhật", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Tìm kiếm");

        tblVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã vé", "Mã nhân viên", "Mã khách hàng", "Mã lịch trình", "Số người lớn", "Số trẻ em", "Tổng tiền", "Ngày bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVe);
        if (tblVe.getColumnModel().getColumnCount() > 0) {
            tblVe.getColumnModel().getColumn(0).setMinWidth(40);
            tblVe.getColumnModel().getColumn(0).setMaxWidth(40);
            tblVe.getColumnModel().getColumn(1).setMinWidth(100);
            tblVe.getColumnModel().getColumn(1).setMaxWidth(100);
            tblVe.getColumnModel().getColumn(2).setMinWidth(60);
            tblVe.getColumnModel().getColumn(3).setMinWidth(60);
            tblVe.getColumnModel().getColumn(4).setMinWidth(60);
            tblVe.getColumnModel().getColumn(5).setMinWidth(100);
            tblVe.getColumnModel().getColumn(5).setMaxWidth(100);
            tblVe.getColumnModel().getColumn(6).setMinWidth(80);
            tblVe.getColumnModel().getColumn(6).setMaxWidth(80);
            tblVe.getColumnModel().getColumn(7).setMinWidth(120);
            tblVe.getColumnModel().getColumn(7).setMaxWidth(120);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel20)
                        .addGap(33, 33, 33)
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Danh sách", jPanel2);

        add(tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 990, 690));
    }// </editor-fold>//GEN-END:initComponents

    private void txtFindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyReleased
        if (txtFind.getText().equals("")) {
            try {
                fillTableVe();
            } catch (SQLException ex) {
                Logger.getLogger(Ve.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                veDAO.selectVeByAnyThing(txtFind.getText().trim(), tblVe);
            } catch (SQLException ex) {
                Logger.getLogger(Ve.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_txtFindKeyReleased

    private void tblVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVeMouseClicked
        if (evt.getClickCount() == 2) {
            indextbl = tblVe.getSelectedRow();
            if (indextbl != -1) {
                showData(indextbl);
                tab.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblVeMouseClicked

    private void txtSoNguoiLonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoNguoiLonKeyReleased
        tinhTongTien();
    }//GEN-LAST:event_txtSoNguoiLonKeyReleased

    private void txtSoTreEmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoTreEmKeyReleased
        tinhTongTien();
    }//GEN-LAST:event_txtSoTreEmKeyReleased

    private void txtSoNguoiLonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoNguoiLonKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoNguoiLonKeyTyped

    private void txtSoTreEmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoTreEmKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoTreEmKeyTyped

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm()) {
            try {
                if (new DangNhap().vt.equalsIgnoreCase("Nhân Viên Trực Quầy")) {
                    if (veDAO.checkSoLuongVe(data.getMaLT())) {
                        insertVe();
                    } else {
                        JOptionPane.showMessageDialog(null, "Số lượng vé đã quá số lượng khách còn lại của tour");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Chỉ Nhân Viên Trực Quầy được phép sử dụng chức năng này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Ve.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearForm();
        txtMaVe.setText(veDAO.generateMaVE());
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtMaNhanVienCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaNhanVienCaretUpdate
        fillTenNhanVien();
    }//GEN-LAST:event_txtMaNhanVienCaretUpdate

    private void txtMaKhachHangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaKhachHangCaretUpdate
        //fillTenKhachHang();
    }//GEN-LAST:event_txtMaKhachHangCaretUpdate

    private void txtMaKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaKhachHangMouseClicked
        showDialog(KhachHang);
    }//GEN-LAST:event_txtMaKhachHangMouseClicked

    private void btnLayThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayThongTinActionPerformed
        try {
            init();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnLayThongTinActionPerformed

    private void btnLapHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLapHopDongActionPerformed

        clearForm();
    }//GEN-LAST:event_btnLapHopDongActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            them();

        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        try {
            khDAO.tim(tblCustomer, txtTimKiem.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            indextbl = tblCustomer.getSelectedRow();
            txtMaKhachHang.setText(tblCustomer.getValueAt(indextbl, 1).toString());
            txtTenKhachHang.setText(tblCustomer.getValueAt(indextbl, 2).toString());
            DanhSachKhachHang.setVisible(false);
            KhachHang.setVisible(false);
        }
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnDanhSachKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachKhachHangActionPerformed
        showDialog(DanhSachKhachHang);
    }//GEN-LAST:event_btnDanhSachKhachHangActionPerformed
    ///////////////////////////////LƯU THÔNG TIN KHÁCH HÀNG////////////////////////////

    public void them() {

        try {
            if (check() && checkTrung()) {
                String id = txtID.getText().trim();
                String name = txtName.getText().trim();
                String cccd = txtCccd.getText().trim();
                String email = txtEmail.getText().trim();
                String adress = txtAdress.getText().trim();
                String numberPhone = txtNumberPhone.getText().trim();
                boolean male = rdoMale.isSelected();
                int kt = new KhachHangDAO().add(new ModelKhachHang(id, name, male, cccd, numberPhone, email, adress));

                if (kt == 1) {
                    MsgBox.alert(null, "Thêm khách hàng thành công!");
                    txtMaKhachHang.setText(txtID.getText().trim());
                    txtTenKhachHang.setText(txtName.getText().trim());
                    KhachHang.dispose();
                } else {
                    MsgBox.error(null, "Thêm khách hàng thất bại!");
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean checkTrung() throws SQLException {
        if (khDAO.checkMaKH(txtID.getText().trim())) {
            MsgBox.error(null, "Mã khách hàng đã tồn tại!");
            txtID.setText("");
            txtID.requestFocus();
            return false;
        }
        if (khDAO.checkEmail(txtEmail.getText().trim())) {
            MsgBox.error(null, "Email đã tồn tại!");
            txtEmail.setText("");
            txtEmail.requestFocus();
            return false;
        }
        if (khDAO.checkCccd(txtCccd.getText().trim())) {
            txtCccd.setText("");
            txtCccd.requestFocus();
            MsgBox.error(null, "CMND_CCCD đăng đã tồn tại!");
            return false;
        }
        return true;
    }

    private boolean check() {
        String id = txtID.getText();
        String name = txtName.getText();
        String cccd = txtCccd.getText();
        String adress = txtAdress.getText();
        String numberPhone = txtCccd.getText();
        boolean male = rdoMale.isSelected();
        String patternSDT = "^((09|03|07|08|05)+([0-9]{8})\\b)$";
        boolean regexma = Pattern.matches("KH{1}[0-9]{1,}", txtID.getText());
        boolean checkCMND = Pattern.matches("\\d{12}", txtCccd.getText().trim());
        boolean checkCMND1 = Pattern.matches("\\d{9}", txtCccd.getText().trim());
        String patternEmail = "^[a-z][a-z0-9_\\.]{6,50}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
        if (id.isEmpty()) {
            MsgBox.error(null, "Mã khách hàng không được để trống!");

            txtID.requestFocus();
            return false;
        }
        if (!regexma) {
            MsgBox.error(null, "Mã khách hàng không đúng định dạng KH...");

            txtID.requestFocus();
            return false;
        }
        if (name.length() == 0) {
            MsgBox.error(null, "Chưa Nhập tên nkhách hàng!");
            txtName.requestFocus();
            return false;
        } else if (name.length() < 6 && name.length() > 50) {
            txtName.setText("");
            txtName.requestFocus();
            MsgBox.error(null, "Tên khách hàng phải từ 6 đến 50 kí tự!");

            return false;
        } else if (!checkName(name)) {
            txtName.setText("");
            txtName.requestFocus();
            MsgBox.error(null, "Tên Khách hàng không được chứa kí tự đặt biệt!");
            return false;
        } else if (cccd.length() == 0) {
            txtCccd.requestFocus();
            MsgBox.error(null, "Chưa Nhập CCCd_CMND!");
            return false;
        }
        if (txtCccd.getText().length() == 9 || txtCccd.getText().length() == 12) {

            //  return true;
        } else {
            MsgBox.error(null, "Chưa nhập đúng định dạng CCCD!\n CCCD phải là 9 hoặc 12 số!");
            txtCccd.requestFocus();
            return false;
        }
        if (numberPhone.length() == 0) {
            txtNumberPhone.requestFocus();
            MsgBox.error(null, "Chưa Nhập số điện thoại!");
            return false;
        } else if (!txtNumberPhone.getText().matches(patternSDT)) {
            txtNumberPhone.requestFocus();
            JOptionPane.showMessageDialog(null, "SĐT phải là đầu số Việt Nam và có 10 chữ số!");
            return false;
        }

        if (!txtEmail.getText().matches(patternEmail)) {
            MsgBox.alert(null, "Email không đúng định dạng, không ngắn hơn 6 kí tự, dài quá 50 kí tự !");
            txtEmail.setText(null);
            txtEmail.requestFocus();
            return false;
        }
        if (adress.length() == 0) {
            txtAdress.requestFocus();
            MsgBox.error(null, "Chưa Nhập địa chỉ!");
            return false;
        }
        if (!rdoFamale.isSelected() && !rdoMale.isSelected()) {
            MsgBox.alert(null, "Giới tính không được để trống");
            return false;
        }
        return true;
    }

    public boolean checkName(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) < 32) {
                return false;
            }
            if (name.charAt(i) > 32 && name.charAt(i) < 65) {
                return false;
            }
            if (name.charAt(i) > 90 && name.charAt(i) < 97) {
                return false;
            }
            if (name.charAt(i) > 122 && name.charAt(i) < 192) {
                return false;
            }
        }

        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    private void showDialog(JDialog nameDialog) {
        nameDialog.setVisible(true);
        nameDialog.setResizable(false);
        nameDialog.getContentPane().setBackground(Color.WHITE);
        nameDialog.pack();
        nameDialog.setLocationRelativeTo(null);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DanhSachKhachHang;
    private javax.swing.JDialog KhachHang;
    private com.GreenHouse.Swing.KButton btnDanhSachKhachHang;
    private com.GreenHouse.Swing.Button btnLamMoi;
    private com.GreenHouse.Swing.Button btnLapHopDong;
    private com.GreenHouse.Swing.Button btnLayThongTin;
    private com.GreenHouse.Swing.Button btnLuu;
    private com.GreenHouse.Swing.KButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblCccd;
    private javax.swing.JLabel lblCustomerManager;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumberPhone;
    private javax.swing.JLabel lblSex;
    private javax.swing.JPanel pnlList;
    private javax.swing.JRadioButton rdoFamale;
    private javax.swing.JRadioButton rdoMale;
    private com.GreenHouse.Swing.MaterialTabbed tab;
    private com.GreenHouse.Swing.Table_1 tblCustomer;
    private com.GreenHouse.Swing.Table_1 tblVe;
    private com.GreenHouse.Swing.MyTextField txtAdress;
    private com.GreenHouse.Swing.MyTextField txtCccd;
    private com.GreenHouse.Swing.MyTextField txtEmail;
    private com.GreenHouse.Swing.MyTextField txtFind;
    private com.GreenHouse.Swing.MyTextField txtGiaVe;
    private com.GreenHouse.Swing.MyTextField txtID;
    private com.GreenHouse.Swing.MyTextField txtMaKhachHang;
    private com.GreenHouse.Swing.MyTextField txtMaNhanVien;
    private com.GreenHouse.Swing.MyTextField txtMaTour;
    private com.GreenHouse.Swing.MyTextField txtMaVe;
    private com.GreenHouse.Swing.MyTextField txtName;
    private com.GreenHouse.Swing.MyTextField txtNgayBan;
    private com.GreenHouse.Swing.MyTextField txtNumberPhone;
    private com.GreenHouse.Swing.MyTextField txtSoNguoiLon;
    private com.GreenHouse.Swing.MyTextField txtSoTreEm;
    private com.GreenHouse.Swing.MyTextField txtTenKhachHang;
    private com.GreenHouse.Swing.MyTextField txtTenNhanVien;
    private com.GreenHouse.Swing.MyTextField txtTenTour;
    private com.GreenHouse.Swing.MyTextField txtTimKiem;
    private com.GreenHouse.Swing.MyTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
