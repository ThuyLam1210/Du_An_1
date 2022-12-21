package com.GreenHouse.Main;

import com.GreenHouse.Component.Loading;
import com.GreenHouse.DAO.TaiKhoanDAO;
import com.GreenHouse.Model.TaiKhoan;
import com.GreenHouse.Utils.MsgBox;
import com.GreenHouse.Utils.XImage;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.awt.Color;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;

import javax.swing.JOptionPane;

/**
 *
 * @author Thuy
 */
public class DoiMatKhau extends javax.swing.JFrame {

    public DoiMatKhau() {
        initComponents();
        this.setIconImage(XImage.getAppIcon());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SendCode = new javax.swing.JDialog();
        panelRound1 = new com.GreenHouse.Swing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaXacNhan = new com.GreenHouse.Swing.MyTextField();
        btnGuiLai = new com.GreenHouse.Swing.ButtonOutLine();
        btnXacNhan = new com.GreenHouse.Swing.ButtonOutLine();
        btnHuy = new com.GreenHouse.Swing.ButtonOutLine();
        DoiMK = new javax.swing.JDialog();
        panelRound2 = new com.GreenHouse.Swing.PanelRound();
        txtNhapLaiMkMoi = new com.GreenHouse.Swing.PasswordField();
        txtMatKhauMoi = new com.GreenHouse.Swing.PasswordField();
        btnDoiMatKhauMoi = new com.GreenHouse.Swing.KButton();
        lblDuongDan = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDangNhap = new javax.swing.JLabel();
        cbxNhoMK = new javax.swing.JCheckBox();
        pictureBox2 = new com.GreenHouse.Utils.PictureBox();
        txtEmail = new com.GreenHouse.Swing.TextField();
        btnDoiMatKhau = new com.GreenHouse.Swing.KButton();
        pictureBox3 = new com.GreenHouse.Utils.PictureBox();
        pictureBox4 = new com.GreenHouse.Utils.PictureBox();
        pictureBox5 = new com.GreenHouse.Utils.PictureBox();
        txtTenDangNhap = new com.GreenHouse.Swing.TextField();
        pictureBox7 = new com.GreenHouse.Utils.PictureBox();
        pictureBox6 = new com.GreenHouse.Utils.PictureBox();
        pictureBox1 = new com.GreenHouse.Utils.PictureBox();

        SendCode.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MÃ XÁC NHẬN");
        panelRound1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 5, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Hãy kiểm tra Email của bạn để lấy mã xác nhận");
        panelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));
        panelRound1.add(txtMaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 230, -1));

        btnGuiLai.setBackground(new java.awt.Color(255, 0, 153));
        btnGuiLai.setText("Gửi Lại");
        btnGuiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiLaiActionPerformed(evt);
            }
        });
        panelRound1.add(btnGuiLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 80, 40));

        btnXacNhan.setBackground(new java.awt.Color(51, 0, 255));
        btnXacNhan.setText("Xác Nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        panelRound1.add(btnXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 90, 40));

        btnHuy.setBackground(new java.awt.Color(255, 0, 102));
        btnHuy.setText("Hủy Bỏ");
        panelRound1.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 90, 40));

        SendCode.getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 250));

        DoiMK.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNhapLaiMkMoi.setLabelText("Nhập Lại Mật Khẩu");
        txtNhapLaiMkMoi.setLineColor(new java.awt.Color(0, 153, 0));
        txtNhapLaiMkMoi.setShowAndHide(true);
        panelRound2.add(txtNhapLaiMkMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 370, 50));

        txtMatKhauMoi.setLabelText("Mật Khẩu Mới");
        txtMatKhauMoi.setLineColor(new java.awt.Color(0, 153, 0));
        txtMatKhauMoi.setShowAndHide(true);
        panelRound2.add(txtMatKhauMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 370, 50));

        btnDoiMatKhauMoi.setText("XÁC NHẬN");
        btnDoiMatKhauMoi.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnDoiMatKhauMoi.setkEndColor(new java.awt.Color(51, 153, 0));
        btnDoiMatKhauMoi.setkSelectedColor(new java.awt.Color(0, 204, 0));
        btnDoiMatKhauMoi.setkStartColor(new java.awt.Color(204, 255, 204));
        btnDoiMatKhauMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauMoiActionPerformed(evt);
            }
        });
        panelRound2.add(btnDoiMatKhauMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        lblDuongDan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDuongDan.setForeground(new java.awt.Color(0, 51, 51));
        lblDuongDan.setText("ĐỔI MẬT KHẨU MỚI");
        panelRound2.add(lblDuongDan, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 301, 35));

        DoiMK.getContentPane().add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 300));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Bạn đã có tài khoản? ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 570, -1, -1));

        lblDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDangNhap.setText("Đăng Nhập");
        lblDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangNhapMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDangNhapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDangNhapMouseExited(evt);
            }
        });
        jPanel1.add(lblDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 570, -1, -1));

        cbxNhoMK.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxNhoMK.setForeground(new java.awt.Color(153, 153, 153));
        cbxNhoMK.setText("Nhớ mật khẩu");
        jPanel1.add(cbxNhoMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, -1));

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/Doimk.png"))); // NOI18N
        jPanel1.add(pictureBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, 510, 170));

        txtEmail.setLabelText("Email");
        txtEmail.setLineColor(new java.awt.Color(106, 216, 150));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 340, -1));

        btnDoiMatKhau.setText("XÁC NHẬN");
        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDoiMatKhau.setkEndColor(new java.awt.Color(106, 216, 150));
        btnDoiMatKhau.setkHoverEndColor(new java.awt.Color(0, 204, 0));
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        jPanel1.add(btnDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 340, -1));

        pictureBox3.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-instagram-40 (1).png"))); // NOI18N
        jPanel1.add(pictureBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 50, 60));

        pictureBox4.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-facebook-circled-40.png"))); // NOI18N
        jPanel1.add(pictureBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, 80, 40));

        pictureBox5.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-google-40_1.png"))); // NOI18N
        jPanel1.add(pictureBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 80, 40));

        txtTenDangNhap.setLabelText("Tên Đăng Nhập");
        txtTenDangNhap.setLineColor(new java.awt.Color(106, 216, 150));
        jPanel1.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 340, -1));

        pictureBox7.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/mai.png"))); // NOI18N
        jPanel1.add(pictureBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 50, 40));

        pictureBox6.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-user-33.png"))); // NOI18N
        jPanel1.add(pictureBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 50, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 510, 590));

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/pexels-henry-&-co-1406282 (1).jpg"))); // NOI18N
        getContentPane().add(pictureBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 590));

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

    private void lblDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseClicked
        new DangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblDangNhapMouseClicked

    private void lblDangNhapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseEntered
        lblDangNhap.setText("<html><b>Đăng Nhập</b></html>");
        lblDangNhap.setForeground(Color.GREEN);
    }//GEN-LAST:event_lblDangNhapMouseEntered

    private void lblDangNhapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseExited
        lblDangNhap.setText("<html>Đăng Nhập</html>");
        lblDangNhap.setForeground(Color.black);
    }//GEN-LAST:event_lblDangNhapMouseExited

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed

        if (Check()) {
            Gui();
        }


    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
        /*try {
        //String newpass = txtMatKhauMoi.getText().trim();
        String email = txtEmail.getText().trim();
        String ok = new TaiKhoanDAO().checkDoiMatKhau(new TaiKhoan(newpass, email));
        if (ok.isEmpty()) {
        MsgBox.error(null, "Đăng ký thất bại");
        DoiMatKhau.dispose();
        
        } else {
        new DangNhap().setVisible(true);
        this.dispose();
        MsgBox.error(null, "Đăng ký thành công");
        }
        } catch (SQLException ex) {
        System.out.print(ex);
        Logger.getLogger(DoiMatKhau.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnDoiMKActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed

        if (Integer.valueOf(txtMaXacNhan.getText().trim()) == this.code) {
            //loading.setVisible(false);
            showDialog(DoiMK);
            //  SendCode.dispose();

            // JOptionPane.showMessageDialog(this, "Thay đổi mật khẩu thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại mã");
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnGuiLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiLaiActionPerformed
        sendcode();
    }//GEN-LAST:event_btnGuiLaiActionPerformed

    private void btnDoiMatKhauMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMoiActionPerformed
        if (Check_DoiMK()) {
            try {
                String mkmoi = txtMatKhauMoi.getText();
                String nhapLai = txtNhapLaiMkMoi.getText();
                String email = txtEmail.getText();
                new TaiKhoanDAO().ForgetPass(new TaiKhoan(mkmoi, email));
                MsgBox.alert(null, "Đổi mật khẩu thành công");
                SendCode.dispose();

                DangNhap dn = new DangNhap();
                dn.setVisible(true);
                DoiMK.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnDoiMatKhauMoiActionPerformed
    private void DoiMK() {

    }

    public boolean Check() {
        if (txtTenDangNhap.getText().equals("")) {
            txtTenDangNhap.requestFocus();
            MsgBox.error(null, "Chưa nhập Tên Đăng Nhập");
            return false;
        }
        if (txtEmail.getText().equals("")) {
            txtEmail.requestFocus();
            MsgBox.error(null, "Chưa nhập Email");
            return false;
        }

        return true;
    }

    public boolean Check_DoiMK() {
        String pass_New = txtMatKhauMoi.getText();
        String RePass_New = txtNhapLaiMkMoi.getText();
        if (pass_New.length() < 6 && pass_New.length() > 50) {
            MsgBox.error(null, "Password mới phải nhập nhiều hơn 6 ký tự và ít hơn 50 ký tự");
            txtMatKhauMoi.setText("");
            txtNhapLaiMkMoi.requestFocus();
            return false;
        }

        if (!KiemTraChuaChuVaSo(pass_New)) {
            MsgBox.error(null, "Password phải bao gồm chữ IN HOA, chữ thường và số");
            txtMatKhauMoi.setText("");
            txtMatKhauMoi.requestFocus();
            return false;
        }
        if (RePass_New.length() == 0) {
            MsgBox.error(null, "Chưa nhập Xác nhận mật khẩu mới");
            txtNhapLaiMkMoi.requestFocus();
        } else if (!pass_New.equals(RePass_New)) {
            MsgBox.error(null, "Password không khớp!");
            txtNhapLaiMkMoi.setText("");
            txtNhapLaiMkMoi.requestFocus();
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

    public boolean ChekEmail() {
        TaiKhoanDAO tk = new TaiKhoanDAO();
        for (TaiKhoan taikhoan : tk.getAll()) {
            if (taikhoan.getEmail().equalsIgnoreCase(txtEmail.getText().trim())) {
                return true;
            }
        }

        return false;
    }

    public void Gui() {
        try {
            if (!ChekEmail()) {
                try {
                    MsgBox.error(null, "Email không tồn tại");
                    txtTenDangNhap.requestFocus();
                    txtTenDangNhap.setText("");
                    txtEmail.setText("");
                } catch (Exception e) {
                    System.out.print(e);
                }

            } else {
                try {
                    sendcode();
                    showDialog(SendCode);
                    // code1.setVisible(true);
                } catch (Exception e) {
                    System.out.print(e);
                }

            }

        } catch (Exception e) {
            System.out.print(e);
        }

    }

    private void showDialog(JDialog nameDialog) {
        nameDialog.setVisible(true);
        nameDialog.setResizable(false);
        nameDialog.getContentPane().setBackground(Color.WHITE);
        nameDialog.pack();
        nameDialog.setLocationRelativeTo(null);
    }

    int code = 0;

    public void sendcode() {
        final String username = "thuyldpc02874@fpt.edu.vn";
        final String password = "Thuy1210#";
        //final String username = "thucnmpc02975@fpt.edu.vn";
        //final String password = "Nguyen2003@";//"ngemudntvdmhwwju"
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(txtEmail.getText()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(txtEmail.getText())
            );

            message.setSubject("Hệ thống gửi mã xác nhận thay đổi mật khẩu");
            //random mã xác nhận
            int randomNumber = 0;
            Random objGenerator = new Random();
            for (int iCount = 0; iCount < 10; iCount++) {
                randomNumber = objGenerator.nextInt(1000000);
                //System.out.println("Random No : " + randomNumber);
            }
            this.code = randomNumber;
            message.setSubject("Mail thay đổi mật khẩu");
            //message.setText(txtMessage.getText());
            String html = ("<h1>hello</h1> <br> <h2>Mã xác nhận hệ thống Green House là</b></h2> <br> " + randomNumber);
            message.setContent(html, "text/html");
            jakarta.mail.Transport.send(message);
            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại email");
        } catch (MessagingException e) {
            System.out.print(e);
        }

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
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoiMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DoiMK;
    private javax.swing.JDialog SendCode;
    private com.GreenHouse.Swing.KButton btnDoiMatKhau;
    private com.GreenHouse.Swing.KButton btnDoiMatKhauMoi;
    private com.GreenHouse.Swing.ButtonOutLine btnGuiLai;
    private com.GreenHouse.Swing.ButtonOutLine btnHuy;
    private com.GreenHouse.Swing.ButtonOutLine btnXacNhan;
    private javax.swing.JCheckBox cbxNhoMK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDangNhap;
    private javax.swing.JLabel lblDuongDan;
    private com.GreenHouse.Swing.PanelRound panelRound1;
    private com.GreenHouse.Swing.PanelRound panelRound2;
    private com.GreenHouse.Utils.PictureBox pictureBox1;
    private com.GreenHouse.Utils.PictureBox pictureBox2;
    private com.GreenHouse.Utils.PictureBox pictureBox3;
    private com.GreenHouse.Utils.PictureBox pictureBox4;
    private com.GreenHouse.Utils.PictureBox pictureBox5;
    private com.GreenHouse.Utils.PictureBox pictureBox6;
    private com.GreenHouse.Utils.PictureBox pictureBox7;
    private com.GreenHouse.Swing.TextField txtEmail;
    private com.GreenHouse.Swing.MyTextField txtMaXacNhan;
    private com.GreenHouse.Swing.PasswordField txtMatKhauMoi;
    private com.GreenHouse.Swing.PasswordField txtNhapLaiMkMoi;
    private com.GreenHouse.Swing.TextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
