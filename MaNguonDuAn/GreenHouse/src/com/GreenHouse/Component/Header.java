package com.GreenHouse.Component;

import com.GreenHouse.DAO.TaiKhoanDAO;
import com.GreenHouse.Main.DangNhap;
import com.GreenHouse.Main.DoiMatKhau;
import com.GreenHouse.Main.Main;
import com.GreenHouse.Model.TaiKhoan;
import com.GreenHouse.Utils.XImage;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Header extends javax.swing.JPanel {

    DoiMatKhau dmk = new DoiMatKhau();
    DangNhap dn = new DangNhap();
    String chucVu = "";

    public Header() {
        initComponents();
        TenTruyCap();
        Img();
        //  lblHinh.setIcon(new ImageIcon("/com/GreenHouse/HinhAnh/Login.png"));
    }

    private void Img() {
        ImageIcon icon = XImage.read("hinh1.jpg");
        lblHinh.setIcon(icon);
        lblHinh.setToolTipText("hinh1.jpg");
    }

    public void addMenuEvent(ActionListener event) {
        btnMenu.addActionListener(event);
    }

    private void TenTruyCap() {

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        chucVu = DangNhap.vt;
                        String ten = new TaiKhoanDAO().getHoTen(DangNhap.tendangnhap);
                        if (ten.isEmpty()) {
                            lblTenTK.setText("NULL");
                        } else {
                            lblTenTK.setText(ten);
                            lblChucVu.setText(DangNhap.vt);
                        }
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void logout(MouseListener e){
        lblHinh.addMouseListener(e);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMenu = new com.GreenHouse.Swing.Button();
        lblTenTK = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        lblChuChay = new com.GreenHouse.Utils.PictureBox();
        lblHinh = new com.GreenHouse.Swing.ImageAvatar();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1070, 100));

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/menu.png"))); // NOI18N

        lblTenTK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenTK.setText("Tên tài khoản");

        lblChucVu.setBackground(new java.awt.Color(153, 153, 153));
        lblChucVu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(102, 102, 102));
        lblChucVu.setText("Chức vụ");

        lblChuChay.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/hinh.png"))); // NOI18N
        lblChuChay.setMaximumSize(new java.awt.Dimension(500, 100));

        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/avt_mau.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152)
                .addComponent(lblChuChay, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenTK, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblChucVu, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblChuChay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTenTK)
                        .addGap(5, 5, 5)
                        .addComponent(lblChucVu)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.Swing.Button btnMenu;
    private com.GreenHouse.Utils.PictureBox lblChuChay;
    private javax.swing.JLabel lblChucVu;
    private com.GreenHouse.Swing.ImageAvatar lblHinh;
    private javax.swing.JLabel lblTenTK;
    // End of variables declaration//GEN-END:variables
}
