/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Form;

import com.GreenHouse.DAO.KhachSanDAO;
import com.GreenHouse.Model.ModelKhachSan;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Van Anh
 */
public class KhachSan extends javax.swing.JPanel {

    KhachSanDAO KsDAO = new KhachSanDAO();
    int dong = -1;
   

    public KhachSan() {
        try {
            initComponents();
            bang();
            fillCboTinh();
            tblKhachSan.fixTable(jScrollPane1);
            txtTimKiem.setHint("Nhập vào Tên khách sạn, địa chỉ, thành phố...");
            txtXepHang.setHint("Nhập vào 1 sao,2 sao...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void fillCboTinh() throws SQLException {
        DefaultComboBoxModel modelCboDiaDiem = (DefaultComboBoxModel) cboTinh.getModel();
        modelCboDiaDiem.removeAllElements();
        modelCboDiaDiem.addElement("Hãy chọn Tỉnh");
        ArrayList<ModelKhachSan> mt = KsDAO.selectTinh();
        for (ModelKhachSan model1 : mt) {
            modelCboDiaDiem.addElement(model1.getTinh());
        }

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
        new KhachSanDAO().loadTable(tblKhachSan);
    }

    private void hienThi(int row) {
        ModelKhachSan ks = new ModelKhachSan();
        new KhachSanDAO().HienThi(tblKhachSan, ks, row);
        txtMaKS.setText(ks.getMaKhachSan());
        txtTenKS.setText(ks.getTenKhachSan());
        txtGia.setText(ks.getGiaKhachSan() + "");
        txtXepHang.setText(ks.getHang());
        txtSDT.setText(ks.getSdt());
        txtDiaChi.setText(ks.getDiaChi());
        txtMoTa.setText(ks.getMoTa());
        txtTinh.setText(ks.getTinh());

    }

    public boolean checkTrung() throws SQLException {
        if (KsDAO.checkMaks(txtMaKS.getText().trim())) {
            txtMaKS.setText("");
            txtMaKS.requestFocus();
            MsgBox.error(null, "Mã khách sạn đã tồn tại");
        }
        return true;
    }

    private boolean check() {
        String ma = txtMaKS.getText();
        String ten = txtTenKS.getText();
        String sdt = txtSDT.getText();
        String gia = txtGia.getText();
        String dc = txtDiaChi.getText();
        String tinh = txtTinh.getText();
        String mota = txtMoTa.getText();
        String regexma = "KS{1}[0-9]{1,}";
        String so = "^(\\+84|0)\\d{9,10}$";

        if (ma.length() == 0) {
            MsgBox.error(null, "Chưa nhập mã khách sạn!");
            txtMaKS.requestFocus();
            return false;
        } else if (!ma.matches(regexma)) {
            MsgBox.error(null, "Mã khách sạn không đúng định dạng 'KS...'");
            txtMaKS.setText("");
            txtMaKS.requestFocus();
            return false;
        } else if (ten.length() == 0) {
            MsgBox.error(null, "Chưa nhập tên khách sạn!");
            txtTenKS.requestFocus();
            return false;
        } else if (!checkName(ten)) {
            txtTenKS.setText("");
            txtTenKS.requestFocus();
            MsgBox.error(null, "Tên khách sạn không được chứa kí tự đặt biệt!");
            return false;
        } else if (sdt.length() == 0) {
            MsgBox.error(null, "Chưa nhập số điện thoại!");
            txtSDT.requestFocus();
            return false;
        } else if (!sdt.matches(so)) {
            MsgBox.error(null, "Số điện thoại không đúng định dạng 10 số hoặc chứ ký tự chữ!");
            return false;
        } else if (gia.length() == 0) {
            MsgBox.error(null, "Chưa nhập giá khách sạn!");
            txtGia.requestFocus();
            return false;
        }
        double gia1 = Double.parseDouble(txtGia.getText());
        try {
            if (gia1 <= 0) {
                MsgBox.error(null, "Giá khách sạn phải lớn hơn 0!");
                txtGia.requestFocus();
                return false;
            }
        } catch (Exception e) {
        }

        if (txtXepHang.getText().equals("")) {

            txtXepHang.requestFocus();
            MsgBox.error(null, "Loại xếp hạng không được để trống!");
            return false;
        }
        if (dc.length() == 0) {
            MsgBox.error(null, "Chưa nhập địa chỉ!");
            txtDiaChi.requestFocus();
            return false;
        } else if (tinh.length() == 0) {
            MsgBox.error(null, "Chưa nhập tỉnh!");
            txtTinh.requestFocus();
            return false;
        }
        return true;
    }

    private boolean KiemTraChuaChuVaSo(String chuoi) {
        boolean ketqua = false;
        Pattern p = Pattern.compile("[a-zA-Z][0-9]");
        Matcher m = p.matcher(chuoi);
        if (!(chuoi == chuoi.toLowerCase())) {
            ketqua = m.find();
        }
        return ketqua;
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

    private void nut(String s) {
        try {
            if (s.equals("first")) {
                dong = 0;
            } else if (s.equals("last")) {
                dong = tblKhachSan.getRowCount() - 1;
            } else if (s.equals("pre")) {
                dong--;
                if (dong < 0) {
                    MsgBox.alert(this, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (s.equals("next")) {
                dong++;
                if (dong >= tblKhachSan.getRowCount()) {
                    MsgBox.alert(null, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }
            tblKhachSan.setRowSelectionInterval(dong, dong);
            lblSTT.setText(dong + 1 + "");
            hienThi(dong);
        } catch (Exception e) {

        }
    }

    private void them() {
        try {
            if (check() && checkTrung()) {
                String ma = txtMaKS.getText().trim();
                String ten = txtTenKS.getText().trim();
                float gia = Float.parseFloat(String.valueOf(txtGia.getText()));
                String sdt = txtSDT.getText().trim();
                String xepHang = txtXepHang.getText().trim();
                String diachi = txtDiaChi.getText().trim();
                String mota = txtMoTa.getText().trim();
                String tinh = txtTinh.getText().trim();
                new KhachSanDAO().insert(new ModelKhachSan(ma, ten, gia, xepHang, sdt, diachi, tinh, mota));

                MsgBox.alert(null, "Thêm khách sạn thành công!");
                bang();
                ModelKhachSan ks = KsDAO.timMa(ma);
                tabs.setSelectedIndex(1);
                for (int i = 0; i < tblKhachSan.getRowCount(); i++) {
                    String ma1 = String.valueOf(tblKhachSan.getValueAt(i, 1));
                    if (ma1.equals(ks.getMaKhachSan())) {
                        tabs.setSelectedIndex(1);
                        tblKhachSan.setRowSelectionInterval(i, i);
                    }
                }
                new Tour().fillCboTinhKhachSan();
                new Tour().fillTblKhachSanByTinh();
                fillCboTinh();
                Moi();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    //tblKhachSan.setRowSelectionInterval(dong, dong);

    private void Xoa() throws SQLException {
        int index = tblKhachSan.getSelectedRow();
        if (index >= 0) {
            int r = JOptionPane.showConfirmDialog(null, "Bạn cần khách sạn " + tblKhachSan.getValueAt(dong, 2) + "?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaKS.getText();
                 new KhachSanDAO().delete(new ModelKhachSan(ma));
                
                    MsgBox.alert(null, "Xóa khách sạn thành công !");
                    bang();
                    Moi();
     
            }

        } else {
            MsgBox.error(null, "Chưa chọn đối tượng để xóa!");
        }
    }

    private void Moi() {
        new Table().reset(new JTextField[]{txtMaKS, txtXepHang, txtTenKS, txtSDT, txtDiaChi, txtTinh});
        txtMoTa.setText(null);
        txtGia.setText("0");
        txtMaKS.setEditable(true);
        btnThem.setVisible(true);
        lblSTT.setText("0");
        dong = -1;
    }

    private void update() throws SQLException {
        try {
            if (check()) {
                ModelKhachSan ks = getForm();
                if (ks != null) {
                    KsDAO.updateKS(ks);
                    bang();
                    MsgBox.alert(null, "Cập nhật thành công");
                    tabs.setSelectedIndex(1);
                    tblKhachSan.setRowSelectionInterval(dong, dong);

                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private ModelKhachSan getForm() {
        String ma = txtMaKS.getText().trim();
        String ten = txtTenKS.getText().trim();
        float gia = Float.parseFloat(txtGia.getText().trim());
        String sdt = txtSDT.getText().trim();
        String xepHang = txtXepHang.getText().trim();
        String diachi = txtDiaChi.getText().trim();
        String mota = txtMoTa.getText().trim();
        String tinh = txtTinh.getText().trim();
        ModelKhachSan ks = new ModelKhachSan(ma, ten, gia, xepHang, sdt, diachi, tinh, mota);
        return ks;
    }

    private void capNhat() throws SQLException {
        String ma = txtMaKS.getText().trim();
        String ten = txtTenKS.getText().trim();
        float gia = Float.parseFloat(String.valueOf(txtGia.getText().trim()));
        String sdt = txtSDT.getText().trim();
        String xepHang = txtXepHang.getText().trim();
        String diachi = txtDiaChi.getText().trim();
        String mota = txtMoTa.getText().trim();
        String tinh = txtTinh.getText().trim();
        new KhachSanDAO().update(new ModelKhachSan(ma, ten, gia, xepHang, sdt, diachi, tinh, mota));

        bang();

        MsgBox.alert(null, "Cập nhật Khách sạn thành công");
        tabs.setSelectedIndex(1);
        tblKhachSan.setRowSelectionInterval(dong, dong);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        msgBox1 = new com.GreenHouse.Utils.MsgBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabs = new com.GreenHouse.Swing.MaterialTabbed();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaKS = new com.GreenHouse.Swing.MyTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDiaChi = new com.GreenHouse.Swing.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        lblFirst = new javax.swing.JLabel();
        lblPrev = new javax.swing.JLabel();
        lblSTT = new javax.swing.JLabel();
        lblNext = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new com.GreenHouse.Swing.MyTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMoTa = new com.GreenHouse.Swing.MyTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenKS = new com.GreenHouse.Swing.MyTextField();
        txtGia = new com.GreenHouse.Swing.MyTextField();
        lblLast = new com.GreenHouse.Utils.PictureBox();
        jLabel9 = new javax.swing.JLabel();
        txtTinh = new com.GreenHouse.Swing.MyTextField();
        btnLamMoi = new com.GreenHouse.Swing.KButton();
        btnCapNhat = new com.GreenHouse.Swing.KButton();
        btnThem = new com.GreenHouse.Swing.KButton();
        btnXoa = new com.GreenHouse.Swing.KButton();
        jLabel6 = new javax.swing.JLabel();
        txtXepHang = new com.GreenHouse.Swing.MyTextField();
        jPanel3 = new javax.swing.JPanel();
        txtTimKiem = new com.GreenHouse.Swing.MyTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachSan = new com.GreenHouse.Swing.Table_1();
        pictureBox1 = new com.GreenHouse.Utils.PictureBox();
        cboTinh = new com.GreenHouse.Swing.Combobox();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÍ KHÁCH SẠN ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã khách sạn ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Số điện thoại  ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Địa chỉ");

        lblFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-double-left-48.png"))); // NOI18N

        lblPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-previous-48.png"))); // NOI18N

        lblSTT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSTT.setText("0");

        lblNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-forward-48.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Mô tả");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Giá phòng ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tên khách sạn ");

        txtTenKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKSActionPerformed(evt);
            }
        });

        txtGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiaKeyTyped(evt);
            }
        });

        lblLast.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/last.jpg"))); // NOI18N
        lblLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLastMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Tỉnh/Thành Phố");

        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Xếp Hạng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(lblFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSTT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLast, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtMaKS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7)
                                .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtXepHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtXepHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(21, 21, 21)
                        .addComponent(txtTenKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(21, 21, 21)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFirst)
                    .addComponent(lblLast, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNext)
                    .addComponent(lblPrev)
                    .addComponent(lblSTT, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(217, 217, 217))
        );

        tabs.addTab("Cập nhật", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Tìm kiếm");

        tblKhachSan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã KS", "Tên Khách Sạn", "Giá", "Xếp Hạng", "Số Điện Thoại", "Địa Chỉ", "Tỉnh", "Mô Tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachSan.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblKhachSanAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblKhachSan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachSanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachSan);
        if (tblKhachSan.getColumnModel().getColumnCount() > 0) {
            tblKhachSan.getColumnModel().getColumn(0).setMinWidth(35);
            tblKhachSan.getColumnModel().getColumn(0).setMaxWidth(35);
            tblKhachSan.getColumnModel().getColumn(1).setMinWidth(30);
        }

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-filter-and-sort-32.png"))); // NOI18N

        cboTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTinhItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 991, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(27, 27, 27)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        tabs.addTab("Danh sách", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachSanAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblKhachSanAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblKhachSanAncestorAdded

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void lblLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLastMouseClicked
        //        nut("last");
    }//GEN-LAST:event_lblLastMouseClicked

    private void txtTenKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKSActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        Moi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (tblKhachSan.getSelectedRow() == -1) {
            MsgBox.error(null, "Chọn Khách sạn cần cập nhật");

        } else {
            try {
                update();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            Xoa();
            new Tour().fillCboTinhKhachSan();
        } catch (Exception e) {
            MsgBox.error(null, "Khách Sạn đang được sử dụng!");
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblKhachSanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachSanMouseClicked
        if (evt.getClickCount() == 2) {
            dong = tblKhachSan.getSelectedRow();
            hienThi(dong);
            txtMaKS.setEditable(false);
            btnThem.setVisible(false);
            tabs.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblKhachSanMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new KhachSanDAO().timkiem(tblKhachSan, txtTimKiem.getText().trim());
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtGiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaKeyTyped
        char testChar = evt.getKeyChar();
        if (!((Character.isDigit(testChar)))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGiaKeyTyped

    private void cboTinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTinhItemStateChanged

        try {
            if (cboTinh.getSelectedIndex() <= 0) {
                bang();
            } else {
                fillBang();
            }

        } catch (Exception e) {
        }

    }//GEN-LAST:event_cboTinhItemStateChanged

    private void fillBang() throws SQLException {
        //String[] header = new String[]{"STT", "Mã Khách Sạn", "Tên Khách Sạn", "Giá", "Xếp Hạng", "Số Điện Thoại", "Địa Chỉ", "Tỉnh", "Mô Tả"};
        DefaultTableModel m = (DefaultTableModel) tblKhachSan.getModel();
        m.setRowCount(0);

        ArrayList<ModelKhachSan> mt = KsDAO.selectTinh(String.valueOf(cboTinh.getSelectedItem()));
        int i = 0;

        DecimalFormat df = new DecimalFormat("#,###");
        for (ModelKhachSan mo : mt) {
            m.addRow(new Object[]{
                ++i, mo.getMaKhachSan(), mo.getTenKhachSan(), df.format(mo.getGiaKhachSan()), mo.getHang(), mo.getSdt(), mo.getDiaChi(), mo.getTinh(), mo.getMoTa()
            });
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.Swing.KButton btnCapNhat;
    private com.GreenHouse.Swing.KButton btnLamMoi;
    private com.GreenHouse.Swing.KButton btnThem;
    private com.GreenHouse.Swing.KButton btnXoa;
    private com.GreenHouse.Swing.Combobox cboTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblFirst;
    private com.GreenHouse.Utils.PictureBox lblLast;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblPrev;
    private javax.swing.JLabel lblSTT;
    private com.GreenHouse.Utils.MsgBox msgBox1;
    private com.GreenHouse.Utils.PictureBox pictureBox1;
    private com.GreenHouse.Swing.MaterialTabbed tabs;
    private com.GreenHouse.Swing.Table_1 tblKhachSan;
    private com.GreenHouse.Swing.MyTextField txtDiaChi;
    private com.GreenHouse.Swing.MyTextField txtGia;
    private com.GreenHouse.Swing.MyTextField txtMaKS;
    private com.GreenHouse.Swing.MyTextField txtMoTa;
    private com.GreenHouse.Swing.MyTextField txtSDT;
    private com.GreenHouse.Swing.MyTextField txtTenKS;
    private com.GreenHouse.Swing.MyTextField txtTimKiem;
    private com.GreenHouse.Swing.MyTextField txtTinh;
    private com.GreenHouse.Swing.MyTextField txtXepHang;
    // End of variables declaration//GEN-END:variables
}
