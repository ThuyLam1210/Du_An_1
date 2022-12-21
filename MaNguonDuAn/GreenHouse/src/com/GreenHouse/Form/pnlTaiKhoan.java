/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Form;

import com.GreenHouse.DAO.NhanVienDAO;
import com.GreenHouse.DAO.TaiKhoanDAO;
import com.GreenHouse.Main.DangNhap;
import com.GreenHouse.Model.ModelNhanVien;
import com.GreenHouse.Model.TaiKhoan;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Thuy
 */
public class pnlTaiKhoan extends javax.swing.JPanel {

    int dong = -1;
    TaiKhoanDAO TKDAO = new TaiKhoanDAO();
    String chucVu = "";

    public pnlTaiKhoan() {
        initComponents();
        bang();
        cbo();
        //cbx_MaNV();
        tblTaiKhoan.fixTable(jScrollPane1);
        txtID.setEditable(false);
        Load_ComboBox_Quyen();
        //txtTimKiem.setHint("Nhập vào Tên nhân viên,địa chỉ,giới tính...");
    }

    public void cbo() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaNV.getModel();
            model.removeAllElements();
            List<ModelNhanVien> list = new TaiKhoanDAO().layDS_NV();
            for (ModelNhanVien cd : list) {
                //add name of the subject to cbo
                model.addElement(cd.getMaNV() + " - " + cd.getTenNV());
               // System.out.println(cd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*private void cbx_MaNV() {
    ArrayList<JLabel> listLBL = new ArrayList<>();
    try {
    int cao = 3;
    ArrayList<ModelNhanVien> arr_NV = new TaiKhoanDAO().layDS_NV();
    for (int i = 0; i < arr_NV.size(); i++) {
    JLabel lbl = new JLabel(arr_NV.get(i).getMaNV()+ " - " + arr_NV.get(i).getTenNV());
    System.out.println(arr_NV.get(i).getMaNV()+ " - " + arr_NV.get(i).getTenNV());
    lbl.setSize(pnlCBX_MaNV.getWidth(), 20);
    lbl.setLocation(10, cao);
    lbl.setName("lbl" + arr_NV.get(i).getMaNV());
    lbl.setForeground(Color.blue);
    lbl.setFont(new Font("Segoe UI", 0, 14));
    lbl.setCursor(new Cursor(HAND_CURSOR));
    lbl.addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {
    String[] s = e.toString().split(" ");
    String str = s[s.length - 1];
    for (int i = 0; i < listLBL.size(); i++) {
    if (listLBL.get(i).getName().equals(str)) {
    txtMaNV.setText(listLBL.get(i).getText());
    pnlCBX_MaNV.setVisible(false);
    //  System.out.println(listLBL.get(i).getText());
    showHide_MaNV++;
    }
    }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    String[] s = e.toString().split(" ");
    String str = s[s.length - 1];
    for (int i = 0; i < listLBL.size(); i++) {
    if (listLBL.get(i).getName().equals(str)) {
    listLBL.get(i).setForeground(new Color(55, 38, 91));
    }
    }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    String[] s = e.toString().split(" ");
    String str = s[s.length - 1];
    for (int i = 0; i < listLBL.size(); i++) {
    if (listLBL.get(i).getName().equals(str)) {
    listLBL.get(i).setForeground(Color.white);
    }
    }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    });
    pnlCBX_MaNV.add(lbl);
    listLBL.add(lbl);
    cao += 20;
    
    }
    pnlCBX_MaNV.setPreferredSize(new Dimension(sptMaNV.getWidth(), listLBL.size() * 20 + 7));
    pnlCBX_MaNV.setVisible(false);
    
    } catch (Exception e) {
    e.printStackTrace();
    }
    }*/
 /*  int showHide_MaNV = 1;
    
    private void showHideCBX_MaNV() {
    showHide_MaNV++;
    if (showHide_MaNV % 2 == 0) {
    pnlCBX_MaNV.setVisible(true);
    } else {
    pnlCBX_MaNV.setVisible(false);
    }
    }*/
    ///////////////
    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    private void Load_ComboBox_Quyen() {
        cboQuyen.addItem("Admin");
        cboQuyen.addItem("Nhân Viên Trực Quầy");
        cboQuyen.addItem("Quản Lý");
        cboQuyen.addItem("Nhân Viên Hướng Dẫn");
    }

    private void nut(String s) {
        try {
            if (s.equals("first")) {
                dong = 0;
            } else if (s.equals("last")) {
                dong = tblTaiKhoan.getRowCount() - 1;
            } else if (s.equals("pre")) {
                dong--;
                if (dong < 0) {
                    MsgBox.alert(this, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (s.equals("next")) {
                dong++;
                if (dong >= tblTaiKhoan.getRowCount()) {
                    MsgBox.alert(null, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }
            tblTaiKhoan.setRowSelectionInterval(dong, dong);
            // lblSTT.setText(String.valueOf(tblTaiKhoan.getValueAt(tblTaiKhoan.getSelectedRow(), 0)));
            lblSTT.setText(dong + 1 + "");
            hienThi(dong);
        } catch (Exception e) {

        }
    }

    private void bang() {
        new TaiKhoanDAO().loadTable(tblTaiKhoan);
    }

    private void hienThi(int row) {
        //  ModelNhanVien nv = new ModelNhanVien();
        TaiKhoan tk = new TaiKhoan();
        new TaiKhoanDAO().hienThi(tblTaiKhoan, tk, row);
        txtID.setText(tk.getID() + "");
        txtTenDangNhap.setText(tk.getTenDangNhap());
        txtMatKhau.setText(tk.getMatKhau());
        txtEmail.setText(tk.getEmail());
        String ma = (String) tk.getMaNhanVien();
        // System.out.println(ma);
        List<ModelNhanVien> list = new TaiKhoanDAO().layDS_NV();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaNV().equals(ma)) {
                cboMaNV.setSelectedIndex(i);
            }
        }

        //cboMaNV.setSelectedItem(tk.getMaNhanVien());
        //  cboMaNV.setSelectedItem(String.valueOf(tblTaiKhoan.getValueAt(row, 5)));
        cboQuyen.setSelectedItem(tblTaiKhoan.getValueAt(row, 4));
    }

    void selectCbbEqualMaNV(String ks) throws SQLException {
        ResultSet rs = (ResultSet) TKDAO.timmaNV(ks);
        if (rs.next()) {
            for (int i = 0; i < cboMaNV.getItemCount(); i++) {
                if (rs.getString("MaNhanVien").matches((String) cboMaNV.getItemAt(i))) {
                    cboMaNV.setSelectedIndex(i);
                } else {
                }
            }
        }

        /*  for (int i = 0; i < cboMaNV.getItemCount(); i++) {
           ModelNhanVien tmp = (ModelNhanVien) cboMaNV.getItemAt(i);
           if (tmp.getMaNV().equals(ks)) {
           cboMaNV.setSelectedIndex(i);
           break;
           }
           }*/
    }

    private void Xoa() {

        int index = tblTaiKhoan.getSelectedRow();
        if (index >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa tài khoản " + tblTaiKhoan.getValueAt(dong, 1) + "?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String tendn = txtTenDangNhap.getText();
                String ten = new TaiKhoanDAO().getHoTen(DangNhap.tendangnhap);
                if (tendn.equals(ten)) {
                    MsgBox.error(null, "Bạn không được xóa chính bạn !");
                } else {
                    int ma = Integer.parseInt(String.valueOf(txtID.getText().trim()));
                    int kt = new TaiKhoanDAO().xoa(new TaiKhoan(ma));
                    MsgBox.alert(this, "Xóa nhân viên thành công !");
                    bang();
                    if (kt == 1) {
                        bang();

                    }
                    Moi();
                }

            } else {
                MsgBox.error(this, "Xóa thất bại!");
            }

        } else {
            MsgBox.error(this, "Chưa chọn đối tượng để xóa!");
        }
    }

    private void Moi() {
        new Table().reset(new JTextField[]{txtID, txtMatKhau, txtEmail, txtTenDangNhap});
        cboQuyen.setSelectedIndex(0);
        txtID.setEditable(false);
        btnThem.setVisible(true);
    }

    private void them() {

        try {
            if (check() && checkTrung()) {
                String ten = txtTenDangNhap.getText().trim();
                String mk = txtMatKhau.getText().trim();
                String email = txtEmail.getText().trim();
                String[] str1 = cboMaNV.getSelectedItem().toString().trim().split("-");
                String manv = str1[0].trim();
//                int id = Integer.parseInt(String.valueOf(txtID.getText().trim()));
                String quyen = cboQuyen.getSelectedItem().toString();
                new TaiKhoanDAO().them(new TaiKhoan(ten, mk, email, quyen, manv));

                bang();
                MsgBox.alert(null, "Thêm tài khoản thành công!");

                tabs.setSelectedIndex(1);
                TaiKhoan hk = new TaiKhoanDAO().timHDToDen(ten);
                for (int i = 0; i < tblTaiKhoan.getRowCount(); i++) {
                    String ma = String.valueOf(tblTaiKhoan.getValueAt(i, 1));
                    // System.out.println(ma);
                    if (ma.equals(hk.getTenDangNhap())) {
                        System.out.println(hk.getTenDangNhap());
                        tblTaiKhoan.setRowSelectionInterval(i, i);
                    }
                }

                //tblTaiKhoan.setRowSelectionInterval(dong, dong);
                Moi();

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public boolean checkTrung() throws SQLException {
        if (TKDAO.checkEmail(txtEmail.getText().trim())) {
            MsgBox.error(null, "Email đã tồn tại!");
            txtEmail.setText("");
            txtEmail.requestFocus();
            return false;
        }
        if (TKDAO.checkTenDangNhap(txtTenDangNhap.getText().trim())) {
            txtTenDangNhap.setText("");
            txtTenDangNhap.requestFocus();
            MsgBox.error(null, "Tên đăng đã tồn tại!");
            return false;
        }
        return true;
    }

    private boolean check() {
        String name = txtTenDangNhap.getText();
        String pass = txtMatKhau.getText();

        String patternEmail = "^[a-z][a-z0-9_\\.]{6,50}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
        if (name.length() == 0) {
            MsgBox.error(null, "Chưa Nhập Username!");
            txtTenDangNhap.requestFocus();
            return false;
        } else if (name.length() < 6 && name.length() > 50) {
            txtTenDangNhap.setText("");
            txtTenDangNhap.requestFocus();
            MsgBox.error(null, "Username phải từ 6 đến 50 kí tự!");

            return false;
        } else if (!checkName(name)) {
            txtTenDangNhap.setText("");
            txtTenDangNhap.requestFocus();
            MsgBox.error(null, "Username không được chứa kí tự đặt biệt!");
            return false;
        } else if (pass.length() == 0) {
            txtMatKhau.requestFocus();
            MsgBox.error(this, "Chưa Nhập Password!");
            return false;
        } else if (!KiemTraChuaChuVaSo(pass)) {
            txtMatKhau.setText("");
            txtMatKhau.requestFocus();
            MsgBox.error(null, "Password phải bao gồm chữ IN HOA ,chữ thường và số!");
            return false;
        }

        if (!txtEmail.getText().matches(patternEmail)) {
            MsgBox.error(null, "Email không đúng định dạng, không ngắn hơn 6 kí tự, dài quá 50 kí tự !");
            txtEmail.setText(null);
            txtEmail.requestFocus();
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

    private void capNhat() {
        if (check()) {
            int id = Integer.parseInt(txtID.getText());
            String ten = txtTenDangNhap.getText().trim();
            String mk = txtMatKhau.getText().trim();
            String email = txtEmail.getText().trim();
            String[] str1 = cboMaNV.getSelectedItem().toString().trim().split("-");
            String manv = str1[0].trim();
            String quyen = cboQuyen.getSelectedItem().toString();
            int kt = new TaiKhoanDAO().sua(new TaiKhoan(id, ten, mk, email, quyen, manv));
            try {
                if (kt == 1) {
                    bang();
                    tabs.setSelectedIndex(1);
                    MsgBox.alert(null, "Cập nhật tài khoản thành công");
                    tblTaiKhoan.setRowSelectionInterval(dong, dong);
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

        jLabel1 = new javax.swing.JLabel();
        tabs = new com.GreenHouse.Swing.MaterialTabbed();
        ThongTin = new javax.swing.JPanel();
        btnLamMoi = new com.GreenHouse.Swing.KButton();
        btnCapNhat = new com.GreenHouse.Swing.KButton();
        btnThem = new com.GreenHouse.Swing.KButton();
        lblSTT = new javax.swing.JLabel();
        lblFirst = new com.GreenHouse.Utils.PictureBox();
        lblLast = new com.GreenHouse.Utils.PictureBox();
        tblNext = new com.GreenHouse.Utils.PictureBox();
        lblPre = new com.GreenHouse.Utils.PictureBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new com.GreenHouse.Swing.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        cboQuyen = new com.GreenHouse.Swing.Combobox();
        jLabel7 = new javax.swing.JLabel();
        txtTenDangNhap = new com.GreenHouse.Swing.MyTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtID = new com.GreenHouse.Swing.MyTextField();
        btnXoa = new com.GreenHouse.Swing.KButton();
        cboMaNV = new com.GreenHouse.Swing.Combobox();
        txtMatKhau = new com.GreenHouse.Swing.PasswordField();
        DanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTaiKhoan = new com.GreenHouse.Swing.Table_1();
        txtTimKiem = new com.GreenHouse.Swing.MyTextField();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÍ TÀI KHOẢN");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 6, 265, 35));

        ThongTin.setBackground(new java.awt.Color(255, 255, 255));
        ThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        ThongTin.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 100, 40));

        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        ThongTin.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 460, 100, 40));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        ThongTin.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 100, 40));

        lblSTT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSTT.setText("0");
        ThongTin.add(lblSTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 460, -1, -1));

        lblFirst.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-double-left-48.png"))); // NOI18N
        lblFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFirstMouseClicked(evt);
            }
        });
        ThongTin.add(lblFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 30, 50));

        lblLast.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/last.jpg"))); // NOI18N
        lblLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLastMouseClicked(evt);
            }
        });
        ThongTin.add(lblLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 450, 30, 50));

        tblNext.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-forward-48.png"))); // NOI18N
        tblNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNextMouseClicked(evt);
            }
        });
        ThongTin.add(tblNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 450, 30, 50));

        lblPre.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-previous-48.png"))); // NOI18N
        lblPre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPreMouseClicked(evt);
            }
        });
        ThongTin.add(lblPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 30, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên đăng nhập");
        ThongTin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã Nhân Viên");
        ThongTin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, -1, -1));
        ThongTin.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 360, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Vai trò");
        ThongTin.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, -1));

        cboQuyen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboQuyen.setLineColor(new java.awt.Color(0, 204, 0));
        ThongTin.add(cboQuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, 370, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Mật khẩu");
        ThongTin.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        txtTenDangNhap.setSelectionColor(new java.awt.Color(204, 255, 204));
        ThongTin.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 310, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Email");
        ThongTin.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("ID");
        ThongTin.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        txtID.setForeground(new java.awt.Color(0, 0, 0));
        txtID.setEnabled(false);
        txtID.setSelectionColor(new java.awt.Color(204, 255, 204));
        ThongTin.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 310, -1));

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        ThongTin.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 100, 40));
        ThongTin.add(cboMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 370, -1));

        txtMatKhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMatKhau.setLabelText("");
        txtMatKhau.setShowAndHide(true);
        ThongTin.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 310, -1));

        tabs.addTab("Cập Nhật", ThongTin);

        DanhSach.setBackground(new java.awt.Color(255, 255, 255));

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTaiKhoan);

        txtTimKiem.setForeground(new java.awt.Color(0, 0, 0));
        txtTimKiem.setSelectionColor(new java.awt.Color(204, 255, 204));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tìm Kiếm");

        javax.swing.GroupLayout DanhSachLayout = new javax.swing.GroupLayout(DanhSach);
        DanhSach.setLayout(DanhSachLayout);
        DanhSachLayout.setHorizontalGroup(
            DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhSachLayout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DanhSachLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(56, 56, 56)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        DanhSachLayout.setVerticalGroup(
            DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DanhSachLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        tabs.addTab("Danh Sách", DanhSach);

        add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 70, 1100, 650));
    }// </editor-fold>//GEN-END:initComponents

    private void lblPreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreMouseClicked

        nut("pre");
    }//GEN-LAST:event_lblPreMouseClicked

    private void tblNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNextMouseClicked
        nut("next");
    }//GEN-LAST:event_tblNextMouseClicked

    private void lblLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLastMouseClicked
        nut("last");
    }//GEN-LAST:event_lblLastMouseClicked

    private void lblFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFirstMouseClicked
        nut("first");
    }//GEN-LAST:event_lblFirstMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        capNhat();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        Moi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new TaiKhoanDAO().timKiem(tblTaiKhoan, txtTimKiem.getText().trim());
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        if (evt.getClickCount() == 2) {
            dong = tblTaiKhoan.getSelectedRow();
            hienThi(dong);
            btnThem.setVisible(false);
            tabs.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        if (new DangNhap().vt.equalsIgnoreCase("Admin")) {
            Xoa();
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ Admin được phép xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DanhSach;
    private javax.swing.JPanel ThongTin;
    private com.GreenHouse.Swing.KButton btnCapNhat;
    private com.GreenHouse.Swing.KButton btnLamMoi;
    private com.GreenHouse.Swing.KButton btnThem;
    private com.GreenHouse.Swing.KButton btnXoa;
    private com.GreenHouse.Swing.Combobox cboMaNV;
    private com.GreenHouse.Swing.Combobox cboQuyen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.GreenHouse.Utils.PictureBox lblFirst;
    private com.GreenHouse.Utils.PictureBox lblLast;
    private com.GreenHouse.Utils.PictureBox lblPre;
    private javax.swing.JLabel lblSTT;
    private com.GreenHouse.Swing.MaterialTabbed tabs;
    private com.GreenHouse.Utils.PictureBox tblNext;
    private com.GreenHouse.Swing.Table_1 tblTaiKhoan;
    private com.GreenHouse.Swing.MyTextField txtEmail;
    private com.GreenHouse.Swing.MyTextField txtID;
    private com.GreenHouse.Swing.PasswordField txtMatKhau;
    private com.GreenHouse.Swing.MyTextField txtTenDangNhap;
    private com.GreenHouse.Swing.MyTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
