/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Form;

import com.GreenHouse.Swing.ChuChay;
import com.GreenHouse.slideshow.Slide1;
import com.GreenHouse.slideshow.Slide2;
import com.GreenHouse.slideshow.Slide3;
import com.GreenHouse.slideshow.Slide4;
import com.GreenHouse.slideshow.Slide5;

/**
 *
 * @author Thuy
 */
public class TrangChu extends javax.swing.JPanel {

    /**
     * Creates new form TrangChu
     */
    public TrangChu() {
        initComponents();
        chuChay();

        slide.initSlideshow(new Slide4(), new Slide1(), new Slide2(), new Slide3(), new Slide5());
    }

    //cho chữ chạy từ trái qua pải
    public void chuChay() {
        ChuChay thread1 = new ChuChay(lbl_ChuChay);
        thread1.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slide = new com.GreenHouse.slideshow.Slideshow();
        pictureBox1 = new com.GreenHouse.Utils.PictureBox();
        panelTransparent1 = new com.GreenHouse.slideshow.PanelTransparent();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pictureBox4 = new com.GreenHouse.Utils.PictureBox();
        pictureBox3 = new com.GreenHouse.Utils.PictureBox();
        pictureBox2 = new com.GreenHouse.Utils.PictureBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_ChuChay = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1010, 900));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        slide.setBackground(new java.awt.Color(255, 255, 255));
        add(slide, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 430, 1110, 280));

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/backg.jpg"))); // NOI18N

        panelTransparent1.setBackground(new java.awt.Color(197, 122, 10));
        panelTransparent1.setAlpha(0.1F);

        jLabel7.setFont(new java.awt.Font("Sitka Small", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("nâng cao chất lượng nhằm mục đích cao nhất là làm hài lòng mọi yêu cầu của quý khách hàng.");
        panelTransparent1.add(jLabel7);
        jLabel7.setBounds(80, 120, 870, 30);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Sitka Small", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Chúng tôi tin rằng với uy tín đã tạo được trên thị trường và chỉ đạo của Ban lãnh đạo của Green House.");
        panelTransparent1.add(jLabel8);
        jLabel8.setBounds(80, 150, 980, 30);

        jLabel9.setFont(new java.awt.Font("Sitka Small", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("luôn đem đến cho khách hàng những sản phẩm, dịch vụ đạt chất lượng tốt nhất với giá cả cạnh tranh. ");
        panelTransparent1.add(jLabel9);
        jLabel9.setBounds(80, 180, 872, 21);

        jLabel10.setFont(new java.awt.Font("Sitka Small", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(" “Sự hài lòng của quý khách luôn là tiêu chí hàng đầu của Green House”.");
        panelTransparent1.add(jLabel10);
        jLabel10.setBounds(170, 200, 630, 30);

        jLabel11.setFont(new java.awt.Font("Sitka Small", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Trải qua nhiều năm hoạt động,  Green House đã không ngừng phát triển ");
        panelTransparent1.add(jLabel11);
        jLabel11.setBounds(80, 100, 730, 21);

        pictureBox1.add(panelTransparent1);
        panelTransparent1.setBounds(-30, 0, 1140, 390);

        add(pictureBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 390));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pictureBox4.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-gmail-32.png"))); // NOI18N

        pictureBox3.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/address.png"))); // NOI18N

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-phone-30.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("SĐT: +84 886 333 666");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Feedback: greenhousetour@gmail.com");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Địa Chỉ: Số 132, P1, TX.Giá Rai, Bạc Liêu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(pictureBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(pictureBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pictureBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pictureBox4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pictureBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 720, 1100, 60));

        lbl_ChuChay.setFont(new java.awt.Font("Sitka Small", 1, 16)); // NOI18N
        lbl_ChuChay.setText("                                                                                                                                          CÁC ĐỊA ĐIỂM ĐƯỢC YÊU THÍCH");
        add(lbl_ChuChay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 391, 1000, 40));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_ChuChay;
    private com.GreenHouse.slideshow.PanelTransparent panelTransparent1;
    private com.GreenHouse.Utils.PictureBox pictureBox1;
    private com.GreenHouse.Utils.PictureBox pictureBox2;
    private com.GreenHouse.Utils.PictureBox pictureBox3;
    private com.GreenHouse.Utils.PictureBox pictureBox4;
    private com.GreenHouse.slideshow.Slideshow slide;
    // End of variables declaration//GEN-END:variables
}
