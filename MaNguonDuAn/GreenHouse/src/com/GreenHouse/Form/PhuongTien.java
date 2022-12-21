/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Form;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.DAO.PhuongTienDAO;
import com.GreenHouse.Model.ModelLoaiPhuongTien;
import com.GreenHouse.Model.ModelPhuongTien;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ADMIN
 */
public class PhuongTien extends javax.swing.JPanel {

    int dong = -1;
    PhuongTienDAO ptDAO = new PhuongTienDAO();

    public PhuongTien() {
        initComponents();
        bang();
        cbo();
        txtMaPhuongTien.setHint("PTxxxx");
        txtTenPhuongTien.setHint("Xe xxx");
        txtCuocPhi.setHint("0");
        txtBienSo1.setHint("95N1_xxxx");
        txtSoLuongChua.setHint("0");
        tblPhuongTien.fixTable(jScrollPane1);
        txtTimKiem.setHint("Mã phương tiện, tên phương tiện, loại phương tiện, số hiệu");

    }

    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    private void bang() {
        new PhuongTienDAO().loadTable(tblPhuongTien);
    }

    private void hienThi(int row) {

        ModelPhuongTien pt = new ModelPhuongTien();
        new PhuongTienDAO().hienThi(tblPhuongTien, pt, row);

        txtMaPhuongTien.setText(pt.getMaPhuongTien());
        String selected = (String) pt.getMaLoaiPhuongTien();
        List<ModelLoaiPhuongTien> list = new PhuongTienDAO().layDS_PhuongTien();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaLoaiPT().equals(selected)) {
                cboLoaiPhuongTien.setSelectedIndex(i);
            }
        }

        txtTenPhuongTien.setText(pt.getTenPhuongTien());
        txtCuocPhi.setText(String.valueOf(pt.getCuocPhi()));
        txtBienSo1.setText(pt.getBienSo());
        txtSoLuongChua.setText(String.valueOf(pt.getSoLuongChua()));
        txtGhiChu.setText(pt.getGhiChu());

    }

    public void cbo() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiPhuongTien.getModel();
            model.removeAllElements();
            List<ModelLoaiPhuongTien> list = new PhuongTienDAO().layDS_PhuongTien();
            for (ModelLoaiPhuongTien lpt : list) {
                model.addElement(lpt.getMaLoaiPT() + " _ " + lpt.getTenLoaiPhuongTien());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean checkTrung() throws SQLException {
        String maPhuongTien = txtMaPhuongTien.getText().trim();
        String bienSo = txtBienSo1.getText().trim();

        if (ptDAO.checkTrungPhuongTien(maPhuongTien, bienSo)) {
            txtMaPhuongTien.setText("");

            txtBienSo1.setText("");

            MsgBox.error(null, "Mã Phương tiện hoặc Biển số đã tồn tại!");
            return false;

        }
        return true;
    }

    public boolean checkma(String user) throws SQLException {
        boolean tim = false;
        PreparedStatement p = JDBCHelper.conn.prepareStatement("SELECT * FROM dbo.PhuongTien WHERE  = ? ");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            tim = true;
        }
        r.close();
        p.close();
        return tim;
    }

    private boolean check() {
        String ten = txtTenPhuongTien.getText();
        String cuocphi = txtCuocPhi.getText();
        String ma = txtMaPhuongTien.getText();
        String bienso = txtSoLuongChua.getText();
        String ghichu = txtGhiChu.getText();
        if (ma.isEmpty()) {
            MsgBox.error(null, "Mã phương tiện không được để trống!");

            txtMaPhuongTien.requestFocus();
            return false;

        } else if (ten.length() == 0) {
            MsgBox.error(null, "Chưa Nhập Tên Phương Tiện!");
            txtTenPhuongTien.requestFocus();
            return false;

        } else if (cuocphi.length() == 0) {
            txtCuocPhi.requestFocus();
            MsgBox.error(null, "Chưa Nhập Cước Phí!");
            return false;

        } else if (bienso.length() == 0) {
            txtSoLuongChua.requestFocus();
            MsgBox.error(null, "Chưa Nhập Biển Số!");
            return false;
        } else if (ma.length() < 6 && ten.length() > 10) {
            txtMaPhuongTien.setText("");
            txtMaPhuongTien.requestFocus();
            MsgBox.error(null, "Mã phương tiện phải từ 6 đến 10 kí tự!");

            return false;
        }
        return true;
    }

    public boolean checkName(String ten) {
        for (int i = 0; i < ten.length(); i++) {
            if (ten.charAt(i) < 32) {
                return false;
            }
            if (ten.charAt(i) > 32 && ten.charAt(i) < 65) {
                return false;
            }
            if (ten.charAt(i) > 90 && ten.charAt(i) < 97) {
                return false;
            }
            if (ten.charAt(i) > 122 && ten.charAt(i) < 192) {
                return false;
            }
        }

        return true;
    }

    private void Moi() {
        new Table().reset(new JTextField[]{txtTimKiem, txtMaPhuongTien, txtTenPhuongTien, txtCuocPhi, txtBienSo1, txtSoLuongChua, txtGhiChu});
        cboLoaiPhuongTien.setSelectedIndex(0);
        btnLuu.setVisible(true);
    }

    private void them() {

        try {
            if (check() && checkTrung()) {

                String ma = txtMaPhuongTien.getText().trim();
                String maloaipt = cboLoaiPhuongTien.getSelectedItem().toString();
                String maLoaiPT = maloaipt.substring(0, maloaipt.indexOf(" "));
                String ten = txtTenPhuongTien.getText().trim();
                float cuocphi = Float.valueOf(txtCuocPhi.getText());
                String bienso = txtBienSo1.getText().trim();
                int slc = Integer.valueOf(txtSoLuongChua.getText());
                String ghichu = txtGhiChu.getText().trim();

                int kt = new PhuongTienDAO().them(new ModelPhuongTien(ma, maLoaiPT, ten, bienso, ghichu, slc, cuocphi));

                if (kt == 1) {
                    bang();
//                    tblDiemThamQuan.setRowSelectionInterval(dong, dong);
                    MsgBox.alert(null, "Thêm phương tiện thành công!");
                    ModelPhuongTien pt = ptDAO.timMa(ma);
                    for (int i = 0; i < tblPhuongTien.getRowCount(); i++) {
                        String ma1 = String.valueOf(tblPhuongTien.getValueAt(i, 1));
                        if (ma1.equals(pt.getMaPhuongTien())) {
                            tabs.setSelectedIndex(1);
                            tblPhuongTien.setRowSelectionInterval(i, i);
                        }
                    }
                    Moi();
                }

            }
        } catch (SQLException ex) {

        }

    }

    private void capNhat() {
        if (check()) {

            String ma = txtMaPhuongTien.getText().trim();
            String ten = txtTenPhuongTien.getText().trim();
            float cuocphi = Float.valueOf(txtCuocPhi.getText());
            String bienso = txtBienSo1.getText().trim();
            int slc = Integer.valueOf(txtSoLuongChua.getText());
            String ghichu = txtGhiChu.getText().trim();
            String maloaipt = cboLoaiPhuongTien.getSelectedItem().toString();
            String maLoaiPT = maloaipt.substring(0, maloaipt.indexOf(" "));
//            String[] str1 = cboLoaiPhuongTien.getSelectedItem().toString().trim().split(" - ");
//           String manv = str1[0].trim();
            int kt = new PhuongTienDAO().sua(new ModelPhuongTien(ma, maLoaiPT, ten, bienso, ghichu, slc, cuocphi));
            try {
                if (kt == 1) {
                    bang();
                    MsgBox.alert(null, "Cập nhật phương tiện thành công");
                    tabs.setSelectedIndex(1);
                    tblPhuongTien.setRowSelectionInterval(dong, dong);
                } else {
                    MsgBox.error(null, "Cập nhật thất bại");
                }
            } catch (Exception e) {
                System.out.print(e);
            }

        }
        Moi();
    }

    private void Xoa() {
        int index = tblPhuongTien.getSelectedRow();
        if (index >= 0) {
            int r = JOptionPane.showConfirmDialog(null, "Bạn cần xóa phương tiện " + tblPhuongTien.getValueAt(dong, 1) + "?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaPhuongTien.getText().trim();
                int kt = new PhuongTienDAO().xoa(new ModelPhuongTien(ma));
                MsgBox.alert(null, "Xóa phương tiện thành công !");
                bang();
                if (kt == 1) {
                    bang();
                }
            } else {
                MsgBox.error(null, "Xóa phương tiện thất bại!");
            }

        } else {
            MsgBox.error(null, "Chưa chọn phương tiện để xóa!");
        }
        Moi();
    }

    private void nut(String s) {
        try {
            if (s.equals("first")) {
                dong = 0;
            } else if (s.equals("last")) {
                dong = tblPhuongTien.getRowCount() - 1;
            } else if (s.equals("pre")) {
                dong--;
                if (dong < 0) {
                    MsgBox.alert(null, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (s.equals("next")) {
                dong++;
                if (dong >= tblPhuongTien.getRowCount()) {
                    MsgBox.alert(null, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }
            tblPhuongTien.setRowSelectionInterval(dong, dong);

            lblSTT.setText(dong + 1 + "");
            hienThi(dong);
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tabs = new com.GreenHouse.Swing.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaPhuongTien = new com.GreenHouse.Swing.MyTextField();
        txtGhiChu = new com.GreenHouse.Swing.MyTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenPhuongTien = new com.GreenHouse.Swing.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuongChua = new com.GreenHouse.Swing.MyTextField();
        txtCuocPhi = new com.GreenHouse.Swing.MyTextField();
        jLabel7 = new javax.swing.JLabel();
        btnLuu = new com.GreenHouse.Swing.Button();
        btnCapNhat = new com.GreenHouse.Swing.Button();
        btnLamMoi = new com.GreenHouse.Swing.Button();
        btnXoa = new com.GreenHouse.Swing.Button();
        btnFisrt = new javax.swing.JLabel();
        btnPre = new javax.swing.JLabel();
        lblSTT = new javax.swing.JLabel();
        btnNext = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboLoaiPhuongTien = new com.GreenHouse.Swing.Combobox();
        btnLast = new com.GreenHouse.Utils.PictureBox();
        txtBienSo1 = new com.GreenHouse.Swing.MyTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new com.GreenHouse.Swing.MyTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhuongTien = new com.GreenHouse.Swing.Table_1();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÍ PHƯƠNG TIỆN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã phương tiện");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Loại phương tiện");

        txtTenPhuongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenPhuongTienActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên phương tiện");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Số lượng chứa");

        txtSoLuongChua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongChuaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoLuongChuaKeyTyped(evt);
            }
        });

        txtCuocPhi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuocPhiKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Cước phí");

        btnLuu.setBackground(new java.awt.Color(255, 255, 153));
        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 153));
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(255, 255, 153));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 255, 153));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnFisrt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-double-left-48.png"))); // NOI18N
        btnFisrt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFisrtMouseClicked(evt);
            }
        });

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-previous-48.png"))); // NOI18N
        btnPre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreMouseClicked(evt);
            }
        });

        lblSTT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSTT.setText("0");

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-forward-48.png"))); // NOI18N
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Ghi chú");

        cboLoaiPhuongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiPhuongTienActionPerformed(evt);
            }
        });

        btnLast.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/last.jpg"))); // NOI18N
        btnLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastMouseClicked(evt);
            }
        });

        txtBienSo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBienSo1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Số hiệu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(64, 64, 64)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(84, 84, 84)
                            .addComponent(btnFisrt)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnPre)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblSTT)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnNext)
                            .addGap(6, 6, 6))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(83, 83, 83)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(txtMaPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(txtTenPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(cboLoaiPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5)
                                .addComponent(txtCuocPhi, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addComponent(txtSoLuongChua, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(txtBienSo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(835, 835, 835)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(130, 130, 130))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCuocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboLoaiPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtBienSo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuongChua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(33, 33, 33)
                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblSTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFisrt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnPre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        tabs.addTab("Cập nhật", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Tìm kiếm");

        tblPhuongTien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Phương Tiện", "Mã Loại Phương Tiện", "Tên Phường Tiện", "Cước Phí", "Biển Số", "Số Lượng Chứa", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhuongTien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhuongTienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhuongTien);
        if (tblPhuongTien.getColumnModel().getColumnCount() > 0) {
            tblPhuongTien.getColumnModel().getColumn(0).setMinWidth(35);
            tblPhuongTien.getColumnModel().getColumn(0).setMaxWidth(35);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );

        tabs.addTab("Danh sách", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(702, Short.MAX_VALUE))
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseClicked
        nut("last");
    }//GEN-LAST:event_btnLastMouseClicked

    private void txtTenPhuongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenPhuongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenPhuongTienActionPerformed

    private void tblPhuongTienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhuongTienMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            dong = tblPhuongTien.getSelectedRow();
            hienThi(dong);
            btnLuu.setVisible(true);
            tabs.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblPhuongTienMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        Moi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnFisrtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFisrtMouseClicked
        // TODO add your handling code here:
        nut("first");
    }//GEN-LAST:event_btnFisrtMouseClicked

    private void btnPreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreMouseClicked
        // TODO add your handling code here:
        nut("pre");
    }//GEN-LAST:event_btnPreMouseClicked

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        // TODO add your handling code here:
        nut("next");
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        Xoa();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cboLoaiPhuongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiPhuongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiPhuongTienActionPerformed

    private void txtBienSo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBienSo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBienSo1ActionPerformed

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        them();
    }//GEN-LAST:event_btnLuuMouseClicked

    private void txtCuocPhiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuocPhiKeyTyped
        char testChar = evt.getKeyChar();
        if (!((Character.isDigit(testChar)))) {
            evt.consume();
        }


    }//GEN-LAST:event_txtCuocPhiKeyTyped

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        capNhat();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtSoLuongChuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongChuaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongChuaKeyPressed

    private void txtSoLuongChuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongChuaKeyTyped
        // TODO add your handling code here:
        char testChar = evt.getKeyChar();
        if (!((Character.isDigit(testChar)))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoLuongChuaKeyTyped

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        new PhuongTienDAO().timkiem(tblPhuongTien, txtTimKiem.getText().trim());
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.Swing.Button btnCapNhat;
    private javax.swing.JLabel btnFisrt;
    private com.GreenHouse.Swing.Button btnLamMoi;
    private com.GreenHouse.Utils.PictureBox btnLast;
    private com.GreenHouse.Swing.Button btnLuu;
    private javax.swing.JLabel btnNext;
    private javax.swing.JLabel btnPre;
    private com.GreenHouse.Swing.Button btnXoa;
    private com.GreenHouse.Swing.Combobox cboLoaiPhuongTien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSTT;
    private com.GreenHouse.Swing.MaterialTabbed tabs;
    private com.GreenHouse.Swing.Table_1 tblPhuongTien;
    private com.GreenHouse.Swing.MyTextField txtBienSo1;
    private com.GreenHouse.Swing.MyTextField txtCuocPhi;
    private com.GreenHouse.Swing.MyTextField txtGhiChu;
    private com.GreenHouse.Swing.MyTextField txtMaPhuongTien;
    private com.GreenHouse.Swing.MyTextField txtSoLuongChua;
    private com.GreenHouse.Swing.MyTextField txtTenPhuongTien;
    private com.GreenHouse.Swing.MyTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
