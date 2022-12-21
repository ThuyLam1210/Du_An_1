/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Form;

import com.GreenHouse.DAO.KhachHangDAO;
import com.GreenHouse.Model.ModelKhachHang;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import java.awt.Color;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ADMIN
 */
public class KhachHang extends javax.swing.JPanel {
    
    KhachHangDAO khDAO = new KhachHangDAO();
    int dong = -1;

    /**
     * Creates new form KhachHang
     */
    public KhachHang() throws SQLException {
        initComponents();
        table();
        tblCustomer.setDefaultEditor(Object.class, null);
        tblCustomer.fixTable(jScrollPane1);
        txtTimKiem.setHint("Nhập vào Tên nhân viên,địa chỉ,giới tính...");
    }
    
    public void table() throws SQLException {
        new KhachHangDAO().fillBangKhachHang(tblCustomer);
    }
    
    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }
    
    private void controll(String s) {
        try {
            if (s.equals("first")) {
                dong = 0;
            } else if (s.equals("last")) {
                dong = tblCustomer.getRowCount() - 1;
            } else if (s.equals("pre")) {
                dong--;
                if (dong < 0) {
                    MsgBox.alert(null, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (s.equals("next")) {
                dong++;
                if (dong >= tblCustomer.getRowCount()) {
                    MsgBox.alert(null, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }
            tblCustomer.setRowSelectionInterval(dong, dong);
            // lblSTT.setText(String.valueOf(tblTaiKhoan.getValueAt(tblTaiKhoan.getSelectedRow(), 0)));
            lblSta.setText(dong + 1 + "");
            showData(dong);
        } catch (Exception e) {
            
        }
    }
    
    private void showData(int i) {
        
        txtID.setText((String) tblCustomer.getValueAt(i, 1));
        txtName.setText((String) tblCustomer.getValueAt(i, 2));
        if (String.valueOf(tblCustomer.getValueAt(i, 3)).equalsIgnoreCase("Nam")) {
            rdoMale.setSelected(true);
        } else {
            rdoFamale.setSelected(true);
        }
        
        txtCccd.setText(tblCustomer.getValueAt(i, 4).toString());
        txtNumberPhone.setText(tblCustomer.getValueAt(i, 5).toString());
        // cboQL_TenNXB.setSelectedItem(tblCustomer.getValueAt(i, 5).toString());
        txtEmail.setText(tblCustomer.getValueAt(i, 6).toString());
        //txtDuongDan.setText((String) tblCustomer.getValueAt(i, 7));
        txtAdress.setText(tblCustomer.getValueAt(i, 7).toString());
        
    }
    
    private void Moi() {
        new Table().reset(new JTextField[]{txtTimKiem, txtID, txtEmail, txtCccd, txtName, txtNumberPhone});
        btnSex.clearSelection();
        txtID.setEditable(true);
        btnSave.setVisible(true);
        txtAdress.setText(null);
        //  txtTimKiem.setEditable(false);
        lblSta.setText("0");

        dong = -1;
    }
    
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
                    
                    table();
                    tabCustomerManager.setSelectedIndex(1);
                    ModelKhachHang kh = khDAO.TimMa(id);
                    for (int i = 0; i < tblCustomer.getRowCount(); i++) {
                        String ma = String.valueOf(tblCustomer.getValueAt(i, 1));
                        if (ma.equals(kh.getId())) {
                            tabCustomerManager.setSelectedIndex(1);
                            tblCustomer.setRowSelectionInterval(i, i);
                        }
                    }
                    MsgBox.alert(null, "Thêm khách hàng thành công!");
                    Moi();
                }
                
            }
        } catch (SQLException ex) {
            
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
            MsgBox.error(null, "Chưa Nhập tên khách hàng!");
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
            MsgBox.error(null, "Chưa Nhập CCCD_CMND!");
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
    
    private void capNhat() {
        if (check()) {
            String id = String.valueOf(txtID.getText());
            String name = txtName.getText().trim();
            String cccd = txtCccd.getText().trim();
            String email = txtEmail.getText().trim();
            String adress = txtAdress.getText().trim();
            String numberPhone = txtNumberPhone.getText().trim();
            boolean male = rdoMale.isSelected();
            ModelKhachHang kh = new ModelKhachHang(id, name, male, cccd, numberPhone, email, adress);
            
            try {
                khDAO.updateKH(kh);
                table();
                MsgBox.alert(null, "Cập nhật Khách hàng thành công");
                tabCustomerManager.setSelectedIndex(1);
                tblCustomer.setRowSelectionInterval(dong, dong);
                
            } catch (Exception e) {
                System.out.print(e);
            }
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSex = new javax.swing.ButtonGroup();
        lblCustomerManager = new javax.swing.JLabel();
        tabCustomerManager = new com.GreenHouse.Swing.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
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
        lblFirst = new javax.swing.JLabel();
        lblPrew = new javax.swing.JLabel();
        lblSta = new javax.swing.JLabel();
        lblNext = new javax.swing.JLabel();
        lblLast = new com.GreenHouse.Utils.PictureBox();
        txtNumberPhone = new com.GreenHouse.Swing.MyTextField();
        lblEmail = new javax.swing.JLabel();
        btnSave = new com.GreenHouse.Swing.KButton();
        btnNew = new com.GreenHouse.Swing.KButton();
        btnUpdate = new com.GreenHouse.Swing.KButton();
        pnlList = new javax.swing.JPanel();
        txtTimKiem = new com.GreenHouse.Swing.MyTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new com.GreenHouse.Swing.Table_1();

        setBackground(new java.awt.Color(255, 255, 255));

        lblCustomerManager.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCustomerManager.setText("QUẢN LÍ KHÁCH HÀNG");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblID.setText("Mã khách hàng");
        jPanel1.add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        txtID.setNextFocusableComponent(txtName);
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 300, -1));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 300, -1));

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblName.setText("Tên khách hàng");
        jPanel1.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        txtCccd.setNextFocusableComponent(txtNumberPhone);
        jPanel1.add(txtCccd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 300, -1));

        lblCccd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCccd.setText("CCCD/CMND");
        jPanel1.add(lblCccd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        lblNumberPhone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNumberPhone.setText("Số điện thoại");
        jPanel1.add(lblNumberPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, -1, -1));
        jPanel1.add(txtAdress, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 800, 80));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 330, -1));

        lblSex.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSex.setText("Giới tính");
        jPanel1.add(lblSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, -1, -1));

        lblAdress.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAdress.setText("Địa chỉ");
        jPanel1.add(lblAdress, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        rdoMale.setBackground(new java.awt.Color(255, 255, 255));
        btnSex.add(rdoMale);
        rdoMale.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdoMale.setSelected(true);
        rdoMale.setText("Nam");
        jPanel1.add(rdoMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 66, -1));

        rdoFamale.setBackground(new java.awt.Color(255, 255, 255));
        btnSex.add(rdoFamale);
        rdoFamale.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdoFamale.setText("Nữ");
        jPanel1.add(rdoFamale, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 66, -1));

        lblFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-double-left-48.png"))); // NOI18N
        lblFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFirstMouseClicked(evt);
            }
        });
        jPanel1.add(lblFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 523, -1, -1));

        lblPrew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-previous-48.png"))); // NOI18N
        lblPrew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrewMouseClicked(evt);
            }
        });
        jPanel1.add(lblPrew, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 523, -1, -1));

        lblSta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSta.setText("0");
        jPanel1.add(lblSta, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 523, -1, 48));

        lblNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-forward-48.png"))); // NOI18N
        lblNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNextMouseClicked(evt);
            }
        });
        jPanel1.add(lblNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(774, 523, -1, -1));

        lblLast.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/last.jpg"))); // NOI18N
        lblLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLastMouseClicked(evt);
            }
        });
        jPanel1.add(lblLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(828, 523, 30, 50));
        jPanel1.add(txtNumberPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 330, -1));

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail.setText("Email");
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, -1, -1));

        btnSave.setText("Thêm");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 100, 40));

        btnNew.setText("Làm mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, 100, 40));

        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 100, 40));

        tabCustomerManager.addTab("Cập nhật", jPanel1);

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblCustomerMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomer);

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel19)
                .addGap(27, 27, 27)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabCustomerManager.addTab("Danh sách", pnlList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCustomerManager, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabCustomerManager, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCustomerManager, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabCustomerManager, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLastMouseClicked
        controll("last");
    }//GEN-LAST:event_lblLastMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        them();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        Moi();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (tblCustomer.getSelectedRow() == -1) {
            MsgBox.error(null, "Chọn Khách hàng cần cập nhật");
            
        } else {
            try {
                capNhat();
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void lblFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFirstMouseClicked
        // TODO add your handling code here:
        controll("first");
    }//GEN-LAST:event_lblFirstMouseClicked

    private void lblPrewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrewMouseClicked
        controll("pre");
    }//GEN-LAST:event_lblPrewMouseClicked

    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseClicked
        controll("next");
    }//GEN-LAST:event_lblNextMouseClicked

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            dong = tblCustomer.getSelectedRow();
            showData(dong);
            txtID.setEditable(false);
           btnSave.setVisible(false);
            tabCustomerManager.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void tblCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCustomerMouseEntered

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        try {
            khDAO.tim(tblCustomer, txtTimKiem.getText().trim());
        } catch (Exception e) {
        }
        

    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.Swing.KButton btnNew;
    private com.GreenHouse.Swing.KButton btnSave;
    private javax.swing.ButtonGroup btnSex;
    private com.GreenHouse.Swing.KButton btnUpdate;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblCccd;
    private javax.swing.JLabel lblCustomerManager;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirst;
    private javax.swing.JLabel lblID;
    private com.GreenHouse.Utils.PictureBox lblLast;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblNumberPhone;
    private javax.swing.JLabel lblPrew;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblSta;
    private javax.swing.JPanel pnlList;
    private javax.swing.JRadioButton rdoFamale;
    private javax.swing.JRadioButton rdoMale;
    private com.GreenHouse.Swing.MaterialTabbed tabCustomerManager;
    private com.GreenHouse.Swing.Table_1 tblCustomer;
    private com.GreenHouse.Swing.MyTextField txtAdress;
    private com.GreenHouse.Swing.MyTextField txtCccd;
    private com.GreenHouse.Swing.MyTextField txtEmail;
    private com.GreenHouse.Swing.MyTextField txtID;
    private com.GreenHouse.Swing.MyTextField txtName;
    private com.GreenHouse.Swing.MyTextField txtNumberPhone;
    private com.GreenHouse.Swing.MyTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
