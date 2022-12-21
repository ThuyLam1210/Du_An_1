/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Form;

import com.GreenHouse.DAO.NhanVienDAO;
import com.GreenHouse.Model.ChucVu;
import com.GreenHouse.Model.ModelNhanVien;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import com.GreenHouse.Utils.XImage;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ADMIN
 */
public class NhanVien extends javax.swing.JPanel {

    NhanVienDAO NvDAO = new NhanVienDAO();
    int dong = -1;

    /**
     * Creates new form KhachHang
     */
    public NhanVien() {

        initComponents();
        table();
        cbo();
        tblStaff.fixTable(jScrollPane1);
        tblStaff.setDefaultEditor(Object.class, null);
        txtSearch.setHint("Tên khách hàng, địa chỉ, giới tính, số điện thoại");
    }

    private void table() {
        try {
            NvDAO.fillBangNV(tblStaff);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
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

    private void controll(String s) {
        try {
            if (s.equals("first")) {
                dong = 0;
            } else if (s.equals("last")) {
                dong = tblStaff.getRowCount() - 1;
            } else if (s.equals("pre")) {
                dong--;
                if (dong < 0) {
                    MsgBox.alert(this, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (s.equals("next")) {
                dong++;
                if (dong >= tblStaff.getRowCount()) {
                    MsgBox.alert(null, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }
            tblStaff.setRowSelectionInterval(dong, dong);
            // lblSTT.setText(String.valueOf(tblTaiKhoan.getValueAt(tblTaiKhoan.getSelectedRow(), 0)));
            lblSta.setText(dong + 1 + "");
            hienThi(dong);
        } catch (Exception e) {

        }
    }

    public void cbo() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboRole.getModel();
            model.removeAllElements();
            List<ChucVu> list = new NhanVienDAO().layDS_ChucVu();
            for (ChucVu cd : list) {
                //add name of the subject to cbo
                model.addElement(cd.getMa() + " - " + cd.getTenCV());
                System.out.println(cd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void hienThi(int row) {

        ModelNhanVien staff = new ModelNhanVien();
        new NhanVienDAO().hienThi(tblStaff, staff, row);
        txtID.setText(staff.getMaNV());
        txtName.setText(staff.getTenNV());
        txtCccd.setText(staff.getCMND());
        txtEmail.setText(staff.getEmail());
        String ma = (String) staff.getMaCV();
        ArrayList<ChucVu> list = new NhanVienDAO().layDS_ChucVu();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMa().equals(ma)) {
                cboRole.setSelectedIndex(i);
            }
        }
        // cboRole.setSelectedItem(staff.getRole());
        txtAdress.setText(staff.getDiaChi());
        txtNumberPhone.setText(staff.getSDT());
        if (staff.isGtinh()) {
            rdoMale.setSelected(true);
        } else {
            rdoFemale.setSelected(true);
        }
        if (staff.isTrangthai()) {
            rdoDoing.setSelected(true);

        } else {

            rdoQuit.setSelected(true);
        }
        if (staff.getHinhAnh() != null) {
            lblImage.setToolTipText(staff.getHinhAnh());
            ImageIcon icon = XImage.read(staff.getHinhAnh());
            ImageIcon imageIconNV = new ImageIcon(icon.getImage().getScaledInstance(
                    lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_DEFAULT));
            lblImage.setIcon(imageIconNV);
        }

    }

    private void Xoa() throws SQLException {
        int index = tblStaff.getSelectedRow();
        if (index >= 0) {
            int r = JOptionPane.showConfirmDialog(null, "Bạn cần xóa tài khoản " + tblStaff.getValueAt(dong, 2) + "?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String id = txtID.getText().trim();
                int kt = new NhanVienDAO().delete(new ModelNhanVien(id));
                table();
                if (kt == 1) {
                    MsgBox.alert(null, "Xóa nhân viên thành công !");
                    table();
                } else {
                    MsgBox.alert(null, "Xóa thất bại!");

                }

            }

        } else {
            MsgBox.error(this, "Chưa chọn đối tượng để xóa!");
        }
    }

    private void Moi() {
        new Table().reset(new JTextField[]{txtSearch, txtID, txtAdress, txtEmail, txtCccd, txtName, txtNumberPhone});
        cboRole.setSelectedIndex(0);
        btnSex.clearSelection();
        buttonGroup2.clearSelection();
        btnSave.setVisible(true);
        txtID.setEditable(true);
    }

    private void chonAnh() {
        JFileChooser fchooser = new JFileChooser();
        if (fchooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fchooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            // Scale for img
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaleIcon = new ImageIcon(imgScale);
            lblImage.setIcon(scaleIcon);
            lblImage.setToolTipText(file.getName());
        }
    }

    private void them() {

        try {
            if (check() && checkTrung()) {
                String id = txtID.getText().trim();
                String name = txtName.getText().trim();
                String cccd = txtCccd.getText().trim();
                String email = txtEmail.getText().trim();
                String adress = txtAdress.getText().trim();
                String[] str1 = cboRole.getSelectedItem().toString().trim().split("-");
                String roll = str1[0].trim();
                //String role = cboRole.getSelectedItem().toString();
                String numberPhone = txtNumberPhone.getText().trim();
                boolean gioitinh = rdoMale.isSelected();
                boolean tt = rdoDoing.isSelected();
                String hinhanh = lblImage.getToolTipText();
                int kt = new NhanVienDAO().add(new ModelNhanVien(id, roll, name, gioitinh, tt, cccd, numberPhone, email, adress, hinhanh));

                if (kt == 1) {
                    table();
//                    TabStaffManager.setSelectedIndex(1);
//                    ModelNhanVien nv = NvDAO.TimMa(id);
//                    for (int i = 0; i < tblStaff.getRowCount(); i++) {
//                        String ma = String.valueOf(tblStaff.getValueAt(i, 1));
//                        if (ma.equals(nv.getMaNV())) {
//                            
//                            tblStaff.setRowSelectionInterval(i, i);
//                        }
//                    }
                    MsgBox.alert(null, "Thêm Nhân Viên thành công!");

                    Moi();
                }

            }
        } catch (SQLException ex) {

        }

    }

    public boolean checkTrung() throws SQLException {
        if (NvDAO.checkMaNV(txtID.getText().trim())) {
            MsgBox.error(null, "Mã Nhân viên đã tồn tại!");
            txtID.setText("");
            txtID.requestFocus();
            return false;
        }
        if (NvDAO.checkEmail(txtEmail.getText().trim())) {
            MsgBox.error(null, "Email đã tồn tại!");
            txtEmail.setText("");
            txtEmail.requestFocus();
            return false;
        }
        if (NvDAO.checkCccd(txtCccd.getText().trim())) {
            txtCccd.setText("");
            txtCccd.requestFocus();
            MsgBox.error(null, "CMND_CCCD đăng đã tồn tại!");
            return false;
        }
        if (NvDAO.checkSDT(txtNumberPhone.getText().trim())) {
            txtCccd.setText("");
            txtCccd.requestFocus();
            MsgBox.error(null, "Số điện thoại đã đăng ký!");
            return false;
        }
        return true;
    }

    private boolean check() {
        String patternSDT = "^((09|03|07|08|05)+([0-9]{8})\\b)$";
        boolean checkCMND1 = Pattern.matches("\\d{9}", txtCccd.getText().trim());
        String id = txtID.getText();
        String name = txtName.getText();
        String cccd = txtCccd.getText();
        String adress = txtAdress.getText();
        String numberPhone = txtCccd.getText();
        boolean male = rdoMale.isSelected();
        boolean quit = rdoQuit.isSelected();
        boolean regexma = Pattern.matches("NV{1}[0-9]{1,}", txtID.getText());
        boolean checkCMND = Pattern.matches("\\d{12}", txtCccd.getText().trim());
        String patternEmail = "^[a-z][a-z0-9_\\.]{6,50}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
        if (id.isEmpty()) {
            MsgBox.alert(null, "Mã nhân viên không được để trống!");

            txtID.requestFocus();
            return false;
        }
        if (!regexma) {
            MsgBox.error(null, "Mã nhân viên không đúng định dạng NV...");

            txtID.requestFocus();
            return false;
        }
        if (name.length() == 0) {
            MsgBox.error(null, "Chưa Nhập tên nhân viên!");
            txtName.requestFocus();
            return false;
        } else if (name.length() < 6 && name.length() > 50) {
            txtName.setText("");
            txtName.requestFocus();
            MsgBox.error(null, "Tên nhân viên phải từ 6 đến 50 kí tự!");

            return false;
        } else if (!checkName(name)) {
            txtName.setText("");
            txtName.requestFocus();
            MsgBox.error(this, "Tên nhân viên không được chứa kí tự đặt biệt!");
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
            MsgBox.alert(this, "SĐT phải là đầu số Việt Nam và có 10 chữ số");
            txtNumberPhone.requestFocus();
            return false;
        }

        if (!txtEmail.getText().matches(patternEmail)) {
            MsgBox.alert(null, "Email không đúng định dạng, không ngắn hơn 6 kí tự, dài quá 50 kí tự !");
            txtEmail.setText(null);
            txtEmail.requestFocus();
            return false;
        } else if (adress.length() == 0) {
            txtAdress.requestFocus();
            MsgBox.error(null, "Chưa Nhập địa chỉ!");
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

    private void capNhat() throws SQLException {
        if (check()) {
            String id = txtID.getText().trim();
            String name = txtName.getText().trim();
            String cccd = txtCccd.getText().trim();
            String email = txtEmail.getText().trim();
            String adress = txtAdress.getText().trim();
            String[] str1 = cboRole.getSelectedItem().toString().trim().split("-");
            String roll = str1[0].trim();
            //String role = cboRole.getSelectedItem().toString();
            String numberPhone = txtNumberPhone.getText().trim();
            boolean gioitinh = rdoMale.isSelected();
            boolean tt = rdoQuit.isSelected();
            String hinhanh = lblImage.getToolTipText();
//           if(NvDAO.checkId()==true){
//}
            int kt = new NhanVienDAO().update(new ModelNhanVien(id, roll, name, gioitinh, tt, cccd, numberPhone, email, adress, hinhanh));
            try {
                if (kt == 1) {
                    table();
                    TabStaffManager.setSelectedIndex(1);
                    MsgBox.alert(null, "Cập nhật nhân viên thành công");
                    tblStaff.setRowSelectionInterval(dong, dong);
                } else {
                    MsgBox.error(null, "Sửa thất bại");
                }
            } catch (Exception e) {
                System.out.print(e);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSex = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        lblStaffManager = new javax.swing.JLabel();
        TabStaffManager = new com.GreenHouse.Swing.MaterialTabbed();
        pnlUpdate = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        txtID = new com.GreenHouse.Swing.MyTextField();
        txtName = new com.GreenHouse.Swing.MyTextField();
        lblName = new javax.swing.JLabel();
        txtCccd = new com.GreenHouse.Swing.MyTextField();
        lblCccd = new javax.swing.JLabel();
        lblNumberPhone = new javax.swing.JLabel();
        txtNumberPhone = new com.GreenHouse.Swing.MyTextField();
        txtEmail = new com.GreenHouse.Swing.MyTextField();
        lblSex = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        btnSave = new com.GreenHouse.Swing.Button();
        btnUpdate = new com.GreenHouse.Swing.Button();
        btnNew = new com.GreenHouse.Swing.Button();
        lblFisrt = new javax.swing.JLabel();
        lblPrew = new javax.swing.JLabel();
        lblSta = new javax.swing.JLabel();
        lblNext = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        cboRole = new com.GreenHouse.Swing.Combobox();
        btnImage = new com.GreenHouse.Swing.ButtonOutLine();
        lblLast = new com.GreenHouse.Utils.PictureBox();
        lblStatus = new javax.swing.JLabel();
        rdoDoing = new javax.swing.JRadioButton();
        rdoQuit = new javax.swing.JRadioButton();
        lblAdress = new javax.swing.JLabel();
        txtAdress = new com.GreenHouse.Swing.MyTextField();
        lblImage = new javax.swing.JLabel();
        pnlList = new javax.swing.JPanel();
        txtSearch = new com.GreenHouse.Swing.MyTextField();
        lblSearch = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStaff = new com.GreenHouse.Swing.Table_1();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStaffManager.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblStaffManager.setText("QUẢN LÍ NHÂN VIÊN");
        add(lblStaffManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 319, 35));

        pnlUpdate.setBackground(new java.awt.Color(255, 255, 255));
        pnlUpdate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblID.setText("Mã nhân viên");
        pnlUpdate.add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 42, -1, -1));
        pnlUpdate.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 68, 300, -1));
        pnlUpdate.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 157, 300, -1));

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblName.setText("Tên nhân viên");
        pnlUpdate.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 131, -1, -1));
        pnlUpdate.add(txtCccd, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 239, 300, -1));

        lblCccd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCccd.setText("CCCD/CMND");
        pnlUpdate.add(lblCccd, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 213, -1, -1));

        lblNumberPhone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNumberPhone.setText("Số điện thoại");
        pnlUpdate.add(lblNumberPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, -1, -1));
        pnlUpdate.add(txtNumberPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 300, -1));
        pnlUpdate.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 300, -1));

        lblSex.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSex.setText("Giới tính");
        pnlUpdate.add(lblSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail.setText("Email");
        pnlUpdate.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, -1));

        rdoMale.setBackground(new java.awt.Color(255, 255, 255));
        btnSex.add(rdoMale);
        rdoMale.setText("Nam");
        pnlUpdate.add(rdoMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 66, -1));

        rdoFemale.setBackground(new java.awt.Color(255, 255, 255));
        btnSex.add(rdoFemale);
        rdoFemale.setText("Nữ");
        pnlUpdate.add(rdoFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 66, -1));

        btnSave.setBackground(new java.awt.Color(255, 255, 153));
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnlUpdate.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 527, 113, 48));

        btnUpdate.setBackground(new java.awt.Color(255, 255, 153));
        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        pnlUpdate.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 527, 113, 48));

        btnNew.setBackground(new java.awt.Color(255, 255, 153));
        btnNew.setText("Làm Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        pnlUpdate.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 527, 113, 48));

        lblFisrt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-double-left-48.png"))); // NOI18N
        lblFisrt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFisrtMouseClicked(evt);
            }
        });
        pnlUpdate.add(lblFisrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 527, -1, -1));

        lblPrew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-previous-48.png"))); // NOI18N
        lblPrew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrewMouseClicked(evt);
            }
        });
        pnlUpdate.add(lblPrew, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 527, -1, -1));

        lblSta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSta.setText("0");
        pnlUpdate.add(lblSta, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 527, -1, 48));

        lblNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-forward-48.png"))); // NOI18N
        lblNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNextMouseClicked(evt);
            }
        });
        pnlUpdate.add(lblNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 530, -1, -1));

        lblRole.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRole.setText("Chức vụ");
        pnlUpdate.add(lblRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, -1, -1));

        pnlUpdate.add(cboRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 300, -1));

        btnImage.setBackground(new java.awt.Color(51, 204, 0));
        btnImage.setText("Chọn hình ảnh");
        btnImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageActionPerformed(evt);
            }
        });
        pnlUpdate.add(btnImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 320, 102, 48));

        lblLast.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/last.jpg"))); // NOI18N
        lblLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLastMouseClicked(evt);
            }
        });
        pnlUpdate.add(lblLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 530, 30, 50));

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatus.setText("Trạng Thái Làm Việc");
        pnlUpdate.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, -1, -1));

        rdoDoing.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoDoing);
        rdoDoing.setText("Đang Làm Việc");
        pnlUpdate.add(rdoDoing, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 120, -1));

        rdoQuit.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoQuit);
        rdoQuit.setText("Đã Thôi Việc");
        pnlUpdate.add(rdoQuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, 130, -1));

        lblAdress.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAdress.setText("Địa chỉ");
        pnlUpdate.add(lblAdress, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, -1, -1));
        pnlUpdate.add(txtAdress, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 800, 80));

        lblImage.setBackground(new java.awt.Color(204, 255, 255));
        lblImage.setOpaque(true);
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });
        pnlUpdate.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 210, 280));

        TabStaffManager.addTab("Cập nhật", pnlUpdate);

        pnlList.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch.setToolTipText("Mã nhân viên, tên nhân viên, CCCD/CMND, Số điện thoại, Địa chỉ");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSearch.setText("Tìm kiếm");

        tblStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStaffMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStaff);

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(lblSearch)
                .addGap(46, 46, 46)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 128, Short.MAX_VALUE))
            .addGroup(pnlListLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );

        TabStaffManager.addTab("Danh sách", pnlList);

        add(TabStaffManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1010, 640));
    }// </editor-fold>//GEN-END:initComponents

    private void lblLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLastMouseClicked
        controll("last");
    }//GEN-LAST:event_lblLastMouseClicked

    private void lblFisrtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFisrtMouseClicked
        // TODO add your handling code here:
        controll("first");
    }//GEN-LAST:event_lblFisrtMouseClicked

    private void lblPrewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrewMouseClicked
        // TODO add your handling code here:
        controll("pre");
    }//GEN-LAST:event_lblPrewMouseClicked

    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseClicked
        // TODO add your handling code here:
        controll("next");
    }//GEN-LAST:event_lblNextMouseClicked

    private void tblStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStaffMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            dong = tblStaff.getSelectedRow();
            hienThi(dong);
            txtID.setEditable(false);
            btnSave.setVisible(false);
            TabStaffManager.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblStaffMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        them();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            capNhat();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        Moi();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageActionPerformed
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_btnImageActionPerformed

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        // TODO add your handling code here:
        chonAnh();

    }//GEN-LAST:event_lblImageMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            NvDAO.timkiem(tblStaff, txtSearch.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.Swing.MaterialTabbed TabStaffManager;
    private com.GreenHouse.Swing.ButtonOutLine btnImage;
    private com.GreenHouse.Swing.Button btnNew;
    private com.GreenHouse.Swing.Button btnSave;
    private javax.swing.ButtonGroup btnSex;
    private com.GreenHouse.Swing.Button btnUpdate;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.GreenHouse.Swing.Combobox cboRole;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblCccd;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFisrt;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblImage;
    private com.GreenHouse.Utils.PictureBox lblLast;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblNumberPhone;
    private javax.swing.JLabel lblPrew;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblSta;
    private javax.swing.JLabel lblStaffManager;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel pnlList;
    private javax.swing.JPanel pnlUpdate;
    private javax.swing.JRadioButton rdoDoing;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JRadioButton rdoQuit;
    private com.GreenHouse.Swing.Table_1 tblStaff;
    private com.GreenHouse.Swing.MyTextField txtAdress;
    private com.GreenHouse.Swing.MyTextField txtCccd;
    private com.GreenHouse.Swing.MyTextField txtEmail;
    private com.GreenHouse.Swing.MyTextField txtID;
    private com.GreenHouse.Swing.MyTextField txtName;
    private com.GreenHouse.Swing.MyTextField txtNumberPhone;
    private com.GreenHouse.Swing.MyTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
