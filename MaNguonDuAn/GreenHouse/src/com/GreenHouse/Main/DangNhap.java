package com.GreenHouse.Main;

import com.GreenHouse.DAO.TaiKhoanDAO;
import com.GreenHouse.Model.ModelNhanVien;
import com.GreenHouse.Model.TaiKhoan;
import com.GreenHouse.Utils.MsgBox;
import com.GreenHouse.Utils.XImage;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;

/**
 *
 * @author Thuy
 */
public class DangNhap extends javax.swing.JFrame implements Runnable {

    File file = new File("..\\GreenHouse\\save.txt");
    public static String tendangnhap = "";
    public static String vt = "";
    public static ModelNhanVien nv = new ModelNhanVien();

    public DangNhap() {
        initComponents();
        this.setIconImage(XImage.getAppIcon());
        UPDATE();
    }
    int dem = 0;

    public void run() {
        while (true) {

            dem++;
            probar.setMinimum(0);
            probar.setMaximum(100);
            probar.setValue(dem);
            lblPercent.setText(dem + "%");
            if (dem == 80) {
                lblText.setText("Done!");
            }
            if (dem == 100) {
                try {
                    Main m = new Main();
                    m.setVisible(true);
                    this.setVisible(false);
                    ManHinhChao.dispose();
                } catch (Exception e) {
                }

            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void load() {
        showDialog(ManHinhChao);
        this.dispose();
        Thread t1 = new Thread(this);
        t1.start();
    }

    private void login() throws SQLException, InterruptedException, IOException {
        String tenDN = txtTenDN.getText().trim();
        String mk = txtMatKhau.getText().trim();
        String vaitro = new TaiKhoanDAO().checkDangNhap(tenDN, mk);
        if (vaitro.isEmpty()) {
            MsgBox.error(null, "Đăng nhập thất bại");
            txtTenDN.setText("");
            txtTenDN.requestFocus();
            txtMatKhau.setText("");
        } else {
            MsgBox.alert(null, "Đăng nhập thành công");

            if (cbxNhoMK.isSelected()) {
                SAVE();
            } else {
                notSave();
            }
            load();
            tendangnhap = tenDN;
            vt = vaitro;
            nv = new TaiKhoanDAO().selectNV(tendangnhap);
            this.close();
        }
    }

    private void close() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ManHinhChao = new javax.swing.JDialog();
        lblText = new javax.swing.JLabel();
        lblPercent = new javax.swing.JLabel();
        probar = new javax.swing.JProgressBar();
        Nen = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbxNhoMK = new javax.swing.JCheckBox();
        lblQuenMatKhau1 = new javax.swing.JLabel();
        pictureBox2 = new com.GreenHouse.Utils.PictureBox();
        txtTenDN = new com.GreenHouse.Swing.TextField();
        btnDangNhap = new com.GreenHouse.Swing.KButton();
        pictureBox7 = new com.GreenHouse.Utils.PictureBox();
        pictureBox6 = new com.GreenHouse.Utils.PictureBox();
        pictureBox3 = new com.GreenHouse.Utils.PictureBox();
        pictureBox4 = new com.GreenHouse.Utils.PictureBox();
        pictureBox5 = new com.GreenHouse.Utils.PictureBox();
        txtMatKhau = new com.GreenHouse.Swing.PasswordField();
        pictureBox1 = new com.GreenHouse.Utils.PictureBox();

        ManHinhChao.setLocation(new java.awt.Point(500, 500));
        ManHinhChao.setUndecorated(true);
        ManHinhChao.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblText.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblText.setForeground(new java.awt.Color(255, 102, 102));
        lblText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblText.setText("Loading project....");
        ManHinhChao.getContentPane().add(lblText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 120, -1));

        lblPercent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPercent.setForeground(new java.awt.Color(204, 0, 0));
        lblPercent.setText("0%");
        ManHinhChao.getContentPane().add(lblPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 50, -1));
        ManHinhChao.getContentPane().add(probar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 480, 30));

        Nen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/Logo.gif"))); // NOI18N
        ManHinhChao.getContentPane().add(Nen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 500));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxNhoMK.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxNhoMK.setForeground(new java.awt.Color(153, 153, 153));
        cbxNhoMK.setText("Nhớ mật khẩu");
        jPanel1.add(cbxNhoMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, -1));

        lblQuenMatKhau1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblQuenMatKhau1.setForeground(new java.awt.Color(51, 51, 51));
        lblQuenMatKhau1.setText("Quên mật khẩu?");
        lblQuenMatKhau1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuenMatKhau1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblQuenMatKhau1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblQuenMatKhau1MouseExited(evt);
            }
        });
        jPanel1.add(lblQuenMatKhau1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, -1, -1));

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/Screenshot 2022-11-14 193337.png"))); // NOI18N
        jPanel1.add(pictureBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -5, 510, 170));

        txtTenDN.setLabelText("Tên Đăng Nhập");
        txtTenDN.setLineColor(new java.awt.Color(106, 216, 150));
        jPanel1.add(txtTenDN, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 340, -1));

        btnDangNhap.setText("ĐĂNG NHẬP");
        btnDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangNhap.setkEndColor(new java.awt.Color(106, 216, 150));
        btnDangNhap.setkHoverEndColor(new java.awt.Color(0, 204, 0));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        jPanel1.add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 340, -1));

        pictureBox7.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-user-33.png"))); // NOI18N
        jPanel1.add(pictureBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 50, 60));

        pictureBox6.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-lock-32.png"))); // NOI18N
        jPanel1.add(pictureBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 50, 60));

        pictureBox3.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-instagram-40 (1).png"))); // NOI18N
        jPanel1.add(pictureBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 50, 60));

        pictureBox4.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-facebook-circled-40.png"))); // NOI18N
        jPanel1.add(pictureBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, 80, 40));

        pictureBox5.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-google-40_1.png"))); // NOI18N
        jPanel1.add(pictureBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 80, 40));

        txtMatKhau.setLabelText("Mật Khẩu");
        txtMatKhau.setLineColor(new java.awt.Color(106, 216, 150));
        txtMatKhau.setShowAndHide(true);
        jPanel1.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 340, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 500, 610));

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/pexels-henry-&-co-1406282 (1).jpg"))); // NOI18N
        getContentPane().add(pictureBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pictureBox7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pictureBox7MouseClicked
        MsgBox.alert(null, "Chức năng đang phát triển >_<");
    }//GEN-LAST:event_pictureBox7MouseClicked

    private void pictureBox8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pictureBox8MouseClicked
        MsgBox.alert(null, "Chức năng đang phát triển >_<");
    }//GEN-LAST:event_pictureBox8MouseClicked

    private void pictureBox9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pictureBox9MouseClicked
        MsgBox.alert(null, "Chức năng đang phát triển >_<");
    }//GEN-LAST:event_pictureBox9MouseClicked

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        if (DangNhap()) {
            try {
                login();
            } catch (SQLException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void lblQuenMatKhau1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuenMatKhau1MouseClicked
        DoiMatKhau dmk = new DoiMatKhau();
        dmk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblQuenMatKhau1MouseClicked

    private void lblQuenMatKhau1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuenMatKhau1MouseEntered
        lblQuenMatKhau1.setText("<html><b>Quên mật khẩu?</b></html>");
        lblQuenMatKhau1.setForeground(Color.GREEN);
    }//GEN-LAST:event_lblQuenMatKhau1MouseEntered

    private void lblQuenMatKhau1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuenMatKhau1MouseExited
        lblQuenMatKhau1.setText("<html><b>Quên mật khẩu?</b></html>");
        lblQuenMatKhau1.setForeground(Color.black);
    }//GEN-LAST:event_lblQuenMatKhau1MouseExited
    public void SAVE() {      //Save the UserName and Password (for one user)

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));

            if (!file.exists()) {
                file.createNewFile();  //if the file !exist create a new one
            }

            bw.write(txtTenDN.getText()); //write the name
            bw.newLine(); //leave a new Line
            bw.write(txtMatKhau.getText()); //write the password
            bw.close();
            //close the BufferdWriter

        } catch (IOException e) {
            e.printStackTrace();
        }

    }//End Of Save

    public void UPDATE() { //UPDATE ON OPENING THE APPLICATION

        try {
            if (file.exists()) {
                if (file.length() > 0) {
                    try ( //if this file exists
                            Scanner scan = new Scanner(file) //Use Scanner to read the File
                            ) {
                        txtTenDN.setText(scan.nextLine());  //append the text to name field
                        txtMatKhau.setText(scan.nextLine()); //append the text to password field
                        cbxNhoMK.setSelected(true);
                    } //append the text to name field
                } else {
                    txtTenDN.setText("");
                    txtMatKhau.setText("");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }//End OF UPDATE

    public void notSave() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
        if (!file.exists()) {
            file.createNewFile();  //if the file !exist create a new one
        }
        bw.write("");
    }

    public boolean DangNhap() {
        String name = txtTenDN.getText();
        String pass = txtMatKhau.getText();
        if (name.length() == 0) {
            MsgBox.error(this, "Chưa Nhập Username!");
            return false;
        } else if (name.length() < 6 && name.length() > 50) {
            txtTenDN.setText("");
            requestFocus();
            MsgBox.error(this, "Username phải từ 6 đến 50 kí tự!");

            return false;
        } else if (!checkName(name)) {
            txtMatKhau.setText("");
            requestFocus();
            MsgBox.error(this, "Username không được chứa kí tự đặt biệt!");
            return false;
        } else if (pass.length() == 0) {
            requestFocus();
            MsgBox.error(this, "Chưa Nhập Password!");
            return false;
        } else if (!KiemTraChuaChuVaSo(pass)) {
            txtTenDN.setText("");
            requestFocus();
            MsgBox.error(this, "Password phải bao gồm chữ IN HOA ,chữ thường và số!");
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

    void showDialog(JDialog nameDialog) {
        nameDialog.setVisible(true);

        nameDialog.setResizable(false);
        nameDialog.getContentPane().setBackground(Color.WHITE);
        nameDialog.pack();
        nameDialog.setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog ManHinhChao;
    private javax.swing.JLabel Nen;
    private com.GreenHouse.Swing.KButton btnDangNhap;
    private javax.swing.JCheckBox cbxNhoMK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblPercent;
    private javax.swing.JLabel lblQuenMatKhau1;
    private javax.swing.JLabel lblText;
    private com.GreenHouse.Utils.PictureBox pictureBox1;
    private com.GreenHouse.Utils.PictureBox pictureBox2;
    private com.GreenHouse.Utils.PictureBox pictureBox3;
    private com.GreenHouse.Utils.PictureBox pictureBox4;
    private com.GreenHouse.Utils.PictureBox pictureBox5;
    private com.GreenHouse.Utils.PictureBox pictureBox6;
    private com.GreenHouse.Utils.PictureBox pictureBox7;
    private javax.swing.JProgressBar probar;
    private com.GreenHouse.Swing.PasswordField txtMatKhau;
    private com.GreenHouse.Swing.TextField txtTenDN;
    // End of variables declaration//GEN-END:variables
}
