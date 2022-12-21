/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Form;

import com.GreenHouse.Connection.JDBCHelper;
import com.GreenHouse.DAO.DiemThamQuanDAO;
import com.GreenHouse.DAO.TaiKhoanDAO;

import com.GreenHouse.Model.ModelDiemThamQuan;
import com.GreenHouse.Model.ModelKhachSan;
import com.GreenHouse.Model.TaiKhoan;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import com.GreenHouse.Utils.XImage;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DiemThamQuan extends javax.swing.JPanel {

    private int dong = -1;
    private DiemThamQuanDAO dtqDAO = new DiemThamQuanDAO();

    /**
     * Creates new form KhachHang
     */
    public DiemThamQuan() {
        try {
            initComponents();
            tblDiemThamQuan.setDefaultEditor(Object.class, null);
            bang();
            fillCboTinh();
            //fillBang();
            tblDiemThamQuan.fixTable(jScrollPane2);
            txtMaDtq.setHint("DDxxxx");
            txtTenDtq.setHint("Bến Ninh Kiều");
            txtDiaChi1.setHint("Huyện...Tỉnh...Thành Phố...");
            txtTinh.setHint("Tỉnh/Thành Phố...");
            txtTimKiem.setHint("Mã điểm tham quan, tên điểm tham quan, địa chỉ");
        } catch (Exception e) {
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

    private void hienThi(int row) {

        ModelDiemThamQuan dtq = new ModelDiemThamQuan();
        new DiemThamQuanDAO().hienThi(tblDiemThamQuan, dtq, row);

        txtMaDtq.setText(dtq.getMaDiaDiem());
        txtTenDtq.setText(dtq.getTenDiaDiem());

        txtDiaChi1.setText(dtq.getDiaChi());

        txtTinh.setText(dtq.getTinh());
        txtMoTa.setText(dtq.getMoTa());

        if (dtq.getHinhAnh() != null) {
            ImageIcon icon = XImage.read(dtq.getHinhAnh());
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaleicon = new ImageIcon(imgScale);
            lblHinh.setToolTipText(dtq.getHinhAnh());
            lblHinh.setIcon(scaleicon);
        }
    }

    void fillCboTinh() throws SQLException {
        DefaultComboBoxModel modelCboDiaDiem = (DefaultComboBoxModel) cboTinh.getModel();
        modelCboDiaDiem.removeAllElements();
        modelCboDiaDiem.addElement("Hãy chọn Tỉnh");
        ArrayList<ModelDiemThamQuan> mt = dtqDAO.selectTinh();
        System.out.println(mt);
        for (ModelDiemThamQuan model1 : mt) {
            modelCboDiaDiem.addElement(model1.getTinh());
        }

    }

    private void bang() {
        new DiemThamQuanDAO().loadTable(tblDiemThamQuan);
    }

    public boolean checkTrung() throws SQLException {

        if (dtqDAO.checkma(txtMaDtq.getText().trim())) {
            txtMaDtq.setText("");
            txtMaDtq.requestFocus();
            MsgBox.error(null, "Mã điểm tham quan đã tồn tại!");
            return false;
        }
        return true;
    }

    public boolean checkma(String user) throws SQLException {
        boolean tim = false;
        PreparedStatement p = JDBCHelper.conn.prepareStatement("SELECT * FROM dbo.DiemThamQuan WHERE  = ? ");
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
        String name = txtTenDtq.getText();
        String diachi = txtTinh.getText();
        String ma = txtMaDtq.getText();
        String tinh = txtTinh.getText();

        if (name.length() == 0) {
            MsgBox.error(null, "Chưa Nhập Tên Điểm Tham Quan!");
            txtTenDtq.requestFocus();
            return false;
        } else if (name.length() < 6 && name.length() > 50) {
            txtTenDtq.setText("");
            txtTenDtq.requestFocus();
            MsgBox.error(null, "Tên điểm tham quan phải từ 6 đến 50 kí tự!");

            return false;
        } else if (diachi.length() == 0) {
            txtDiaChi1.requestFocus();
            MsgBox.error(null, "Chưa Nhập Địa Chỉ!");
            return false;

        } else if (tinh.length() == 0) {
            txtTinh.requestFocus();
            MsgBox.error(null, "Chưa Nhập Tỉnh!");
            return false;
        }
        if (ma.isEmpty()) {
            MsgBox.alert(null, "Mã điểm tham quan không được để trống!");

            txtMaDtq.requestFocus();
            return false;

        } else if (ma.length() < 6 && name.length() > 10) {
            txtMaDtq.setText("");
            txtMaDtq.requestFocus();
            MsgBox.error(null, "Mã điểm tham quan phải từ 6 đến 10 kí tự!");

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

    private void chonAnh() {
        JFileChooser fchooser = new JFileChooser();
        if (fchooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fchooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            // Scale for img
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaleIcon = new ImageIcon(imgScale);
            lblHinh.setIcon(scaleIcon);
            lblHinh.setToolTipText(file.getName());
        }
    }

    private void Img() {
        ImageIcon icon = XImage.read("hinh1.jpg");
        lblHinh.setIcon(icon);
        lblHinh.setToolTipText("hinh1.jpg");
    }

    private void Moi() {
        new Table().reset(new JTextField[]{txtTimKiem, txtMaDtq, txtTenDtq, txtDiaChi1, txtTinh});
        txtMoTa.setText(null);
        lblHinh.setIcon(null);
        btnLuu.setVisible(true);
        Img();

    }

    private void capNhat() {
        if (check()) {
            String ma = txtMaDtq.getText().trim();
            String ten = txtTenDtq.getText().trim();
            String diachi = txtDiaChi1.getText().trim();
            String mota = txtMoTa.getText().trim();
            String tinh = txtTinh.getText().trim();
            String hinh = lblHinh.getToolTipText();
            int kt = new DiemThamQuanDAO().sua(new ModelDiemThamQuan(ma, ten, diachi, tinh, mota, hinh));
            try {
                if (kt == 1) {
                    bang();
                    tabs.setSelectedIndex(1);

                    MsgBox.alert(null, "Cập nhật điểm tham quan thành công");
                    Moi();
                    tblDiemThamQuan.setRowSelectionInterval(dong, dong);
                } else {
                    MsgBox.error(null, "Lỗi trùng mã");
                }
            } catch (Exception e) {
                System.out.print(e);
            }

        }
    }

    private void them() {

        try {
            if (check() && checkTrung()) {

                String ma = txtMaDtq.getText().trim();
                String ten = txtTenDtq.getText().trim();
                String diachi = txtDiaChi1.getText().trim();
                String tinh = txtTinh.getText().trim();
                String mota = txtMoTa.getText().trim();
                String hinh = lblHinh.getToolTipText();
                int kt = new DiemThamQuanDAO().them(new ModelDiemThamQuan(ma, ten, diachi, tinh, mota, hinh));

                if (kt == 1) {
                    bang();
                    ModelDiemThamQuan tq = dtqDAO.timMa(ma);
                    tabs.setSelectedIndex(1);
                    for (int i = 0; i < tblDiemThamQuan.getRowCount(); i++) {
                        String id = String.valueOf(tblDiemThamQuan.getValueAt(i, 1));

                        if (id.equals(tq.getMaDiaDiem())) {
                            System.out.println(id.equals(tq.getMaDiaDiem()));
                            tblDiemThamQuan.setRowSelectionInterval(i, i);
                        }
                    }
//                    tblDiemThamQuan.setRowSelectionInterval(dong, dong);
                    MsgBox.alert(null, "Thêm điểm tham quan thành công!");
                    Moi();
                     fillCboTinh();
                    new Tour().fillCboTinhThanh();
                    new Tour().fillTableDiaDiemByTinh();
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }

    }

    private void Xoa() {
        int index = tblDiemThamQuan.getSelectedRow();
        if (index >= 0) {
            int r = JOptionPane.showConfirmDialog(null, "Bạn cần xóa điểm tham quan " + tblDiemThamQuan.getValueAt(dong, 1) + "?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaDtq.getText().trim();
                int kt = new DiemThamQuanDAO().xoa(new ModelDiemThamQuan(ma));
                bang();
                if (kt == 1) {
                    bang();
                }
            } else {
                MsgBox.error(null, "Xóa điểm tham quan thất bại!");
            }

        } else {
            MsgBox.error(null, "Chưa chọn điểm tham quan để xóa!");
        }

    }

    private void nut(String s) {
        try {
            if (s.equals("first")) {
                dong = 0;
            } else if (s.equals("last")) {
                dong = tblDiemThamQuan.getRowCount() - 1;
            } else if (s.equals("pre")) {
                dong--;
                if (dong < 0) {
                    MsgBox.alert(null, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (s.equals("next")) {
                dong++;
                if (dong >= tblDiemThamQuan.getRowCount()) {
                    MsgBox.alert(null, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }
            tblDiemThamQuan.setRowSelectionInterval(dong, dong);

            lblSTT.setText(dong + 1 + "");
            hienThi(dong);
        } catch (Exception e) {

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

        jLabel1 = new javax.swing.JLabel();
        tabs = new com.GreenHouse.Swing.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenDtq = new com.GreenHouse.Swing.MyTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTinh = new com.GreenHouse.Swing.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        btnLuu = new com.GreenHouse.Swing.Button();
        tbtnCapNhat = new com.GreenHouse.Swing.Button();
        btnLamMoi = new com.GreenHouse.Swing.Button();
        btnXoa = new com.GreenHouse.Swing.Button();
        btnFirst = new javax.swing.JLabel();
        btnPrev = new javax.swing.JLabel();
        lblSTT = new javax.swing.JLabel();
        btnNext = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaDtq = new com.GreenHouse.Swing.MyTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btnChonAnh = new com.GreenHouse.Swing.ButtonOutLine();
        btnlast = new com.GreenHouse.Utils.PictureBox();
        jLabel6 = new javax.swing.JLabel();
        txtDiaChi1 = new com.GreenHouse.Swing.MyTextField();
        lblHinh = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new com.GreenHouse.Swing.MyTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDiemThamQuan = new com.GreenHouse.Swing.Table_1();
        cboTinh = new com.GreenHouse.Swing.Combobox();
        pictureBox1 = new com.GreenHouse.Utils.PictureBox();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÍ ĐIỂM THAM QUAN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã điểm tham quan");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));
        jPanel1.add(txtTenDtq, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 300, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên điểm tham quan");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));
        jPanel1.add(txtTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 300, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tỉnh");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        btnLuu.setBackground(new java.awt.Color(255, 255, 153));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 495, 113, 48));

        tbtnCapNhat.setBackground(new java.awt.Color(255, 255, 153));
        tbtnCapNhat.setText("Cập Nhật");
        tbtnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnCapNhatActionPerformed(evt);
            }
        });
        jPanel1.add(tbtnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 495, 113, 48));

        btnLamMoi.setBackground(new java.awt.Color(255, 255, 153));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 495, 113, 48));

        btnXoa.setBackground(new java.awt.Color(255, 255, 153));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(457, 495, 113, 48));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-double-left-48.png"))); // NOI18N
        btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirstMouseClicked(evt);
            }
        });
        jPanel1.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 495, -1, -1));

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-previous-48.png"))); // NOI18N
        btnPrev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevMouseClicked(evt);
            }
        });
        jPanel1.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 495, -1, -1));

        lblSTT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSTT.setText("0");
        jPanel1.add(lblSTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(762, 495, -1, 48));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-forward-48.png"))); // NOI18N
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMouseClicked(evt);
            }
        });
        jPanel1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(781, 495, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Mô tả");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));

        txtMaDtq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDtqActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaDtq, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 300, -1));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 500, 100));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Hình ảnh");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, -1, -1));

        btnChonAnh.setBackground(new java.awt.Color(0, 204, 0));
        btnChonAnh.setText("Chọn hình ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });
        jPanel1.add(btnChonAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 330, 123, 39));

        btnlast.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/last.jpg"))); // NOI18N
        btnlast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnlastMouseClicked(evt);
            }
        });
        jPanel1.add(btnlast, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 500, 40, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Địa chỉ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));
        jPanel1.add(txtDiaChi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 300, -1));

        lblHinh.setBackground(new java.awt.Color(204, 255, 204));
        lblHinh.setOpaque(true);
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        jPanel1.add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 360, 240));

        tabs.addTab("Cập nhật", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Tìm kiếm");

        tblDiemThamQuan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Địa Điểm", "Tên Địa Điểm", "Địa Điểm", "Mô Tả", "Tỉnh", "Hình Ảnh"
            }
        ));
        tblDiemThamQuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiemThamQuanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDiemThamQuan);
        if (tblDiemThamQuan.getColumnModel().getColumnCount() > 0) {
            tblDiemThamQuan.getColumnModel().getColumn(0).setMinWidth(35);
            tblDiemThamQuan.getColumnModel().getColumn(0).setMaxWidth(35);
            tblDiemThamQuan.getColumnModel().getColumn(6).setMinWidth(50);
        }

        cboTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTinhItemStateChanged(evt);
            }
        });
        cboTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTinhMouseClicked(evt);
            }
        });

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-filter-and-sort-32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(27, 27, 27)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabs.addTab("Danh sách", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnlastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlastMouseClicked
        nut("last");
    }//GEN-LAST:event_btnlastMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            them();

        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtMaDtqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDtqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDtqActionPerformed

    private void tblDiemThamQuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiemThamQuanMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            dong = tblDiemThamQuan.getSelectedRow();
            hienThi(dong);
            btnLuu.setVisible(false);
            tabs.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblDiemThamQuanMouseClicked

    private void tbtnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnCapNhatActionPerformed
        // TODO add your handling code here:
        capNhat();
    }//GEN-LAST:event_tbtnCapNhatActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        Moi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        //Xoa();
        try {
            Xoa();
            Moi();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseClicked
        // TODO add your handling code here:
        nut("first");
    }//GEN-LAST:event_btnFirstMouseClicked

    private void btnPrevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevMouseClicked
        // TODO add your handling code here:
        nut("pre");
    }//GEN-LAST:event_btnPrevMouseClicked

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        // TODO add your handling code here:
        nut("next");
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        chonAnh();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        try {
            new DiemThamQuanDAO().timkiem(tblDiemThamQuan, txtTimKiem.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // editColumnWidth();
    }//GEN-LAST:event_txtTimKiemKeyReleased

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

    private void cboTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTinhMouseClicked

    }//GEN-LAST:event_cboTinhMouseClicked
    private void fillBang() throws SQLException {
        //String[] header = new String[]{"STT", "Mã Khách Sạn", "Tên Khách Sạn", "Giá", "Xếp Hạng", "Số Điện Thoại", "Địa Chỉ", "Tỉnh", "Mô Tả"};
        DefaultTableModel m = (DefaultTableModel) tblDiemThamQuan.getModel();
        m.setRowCount(0);

        ArrayList<ModelDiemThamQuan> mt = dtqDAO.selectTinhDD(String.valueOf(cboTinh.getSelectedItem()));
        int i = 0;

        DecimalFormat df = new DecimalFormat("#,### VND");
        for (ModelDiemThamQuan mo : mt) {
            m.addRow(new Object[]{
                ++i, mo.getMaDiaDiem(), mo.getTenDiaDiem(), mo.getDiaChi(), mo.getMoTa(), mo.getTinh(), mo.getHinhAnh()
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.Swing.ButtonOutLine btnChonAnh;
    private javax.swing.JLabel btnFirst;
    private com.GreenHouse.Swing.Button btnLamMoi;
    private com.GreenHouse.Swing.Button btnLuu;
    private javax.swing.JLabel btnNext;
    private javax.swing.JLabel btnPrev;
    private com.GreenHouse.Swing.Button btnXoa;
    private com.GreenHouse.Utils.PictureBox btnlast;
    private com.GreenHouse.Swing.Combobox cboTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblSTT;
    private com.GreenHouse.Utils.PictureBox pictureBox1;
    private com.GreenHouse.Swing.MaterialTabbed tabs;
    private com.GreenHouse.Swing.Table_1 tblDiemThamQuan;
    private com.GreenHouse.Swing.Button tbtnCapNhat;
    private com.GreenHouse.Swing.MyTextField txtDiaChi1;
    private com.GreenHouse.Swing.MyTextField txtMaDtq;
    private javax.swing.JTextArea txtMoTa;
    private com.GreenHouse.Swing.MyTextField txtTenDtq;
    private com.GreenHouse.Swing.MyTextField txtTimKiem;
    private com.GreenHouse.Swing.MyTextField txtTinh;
    // End of variables declaration//GEN-END:variables
}
