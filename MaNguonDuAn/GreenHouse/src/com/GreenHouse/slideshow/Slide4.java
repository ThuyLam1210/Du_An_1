/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.slideshow;

/**
 *
 * @author Thuy
 */
public class Slide4 extends javax.swing.JPanel {

    /**
     * Creates new form Slide1
     */
    public Slide4() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pictureBox1 = new com.GreenHouse.Utils.PictureBox();
        panelTransparent2 = new com.GreenHouse.slideshow.PanelTransparent();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/slide1_1.jpg"))); // NOI18N

        panelTransparent2.setAlpha(0.5F);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setText("GreenHouse For You");
        panelTransparent2.add(jLabel1);
        jLabel1.setBounds(20, 80, 460, 52);

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel2.setText("“Look at the world. That's better than any dream. ”");
        panelTransparent2.add(jLabel2);
        jLabel2.setBounds(20, 150, 554, 24);

        pictureBox1.add(panelTransparent2);
        panelTransparent2.setBounds(0, 0, 650, 280);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.GreenHouse.slideshow.PanelTransparent panelTransparent2;
    private com.GreenHouse.Utils.PictureBox pictureBox1;
    // End of variables declaration//GEN-END:variables
}
