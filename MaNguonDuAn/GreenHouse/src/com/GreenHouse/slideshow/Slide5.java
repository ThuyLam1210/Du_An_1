/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.slideshow;

/**
 *
 * @author Thuy
 */
public class Slide5 extends javax.swing.JPanel {

    /**
     * Creates new form Slide1
     */
    public Slide5() {
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

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/slide5.jpg"))); // NOI18N

        panelTransparent2.setBackground(new java.awt.Color(102, 102, 102));
        panelTransparent2.setAlpha(0.5F);
        pictureBox1.add(panelTransparent2);
        panelTransparent2.setBounds(0, 0, 610, 270);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.slideshow.PanelTransparent panelTransparent2;
    private com.GreenHouse.Utils.PictureBox pictureBox1;
    // End of variables declaration//GEN-END:variables
}
