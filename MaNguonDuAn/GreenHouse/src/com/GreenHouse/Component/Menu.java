/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.GreenHouse.Component;

import com.GreenHouse.Event.EventMenu;
import com.GreenHouse.Event.EventMenuSelected;
import com.GreenHouse.Event.EventShowPopupMenu;
import com.GreenHouse.Main.DangNhap;
import com.GreenHouse.Model.ModelMenu;
import com.GreenHouse.Swing.MenuAnimation;
import com.GreenHouse.Swing.MenuItem;
import com.GreenHouse.Swing.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author ADMIN
 */
public class Menu extends javax.swing.JPanel {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItem() {
        if (new DangNhap().vt.equalsIgnoreCase("Quản Lý")) {
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-country-house-20.png")), "Trang Chủ"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-papers-20.png")), "Hướng Dẫn Sử Dụng"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-ticket-20.png")), "Quản Lí Vé"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-trip-20.png")), "Quản Lí Tour"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-location-20.png")), "Quản Lí Điểm Tham Quan"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-airplane-20.png")), "Quản Lí Phương Tiện"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-hotel-building-20.png")), "Quản Lí Khách Sạn"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-route-20.png")), "Quản Lí Lịch Trình"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-paid-bill-20.png")), "Quản Lí Hợp Đồng"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-customer-20.png")), "Quản Lí Khách Hàng"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-collaborator-male-20.png")), "Quản Lí Nhân Viên"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-password-key-20.png")), "Quản Lí Tài Khoản"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-combo-chart-20.png")), "Thống Kê"));

        } else if (new DangNhap().vt.equalsIgnoreCase("Admin")) {
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-country-house-20.png")), "Trang Chủ"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-papers-20.png")), "Hướng Dẫn Sử Dụng"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-password-key-20.png")), "Quản Lí Tài Khoản"));

        } else if (new DangNhap().vt.equalsIgnoreCase("Nhân Viên Trực Quầy")) {
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-country-house-20.png")), "Trang Chủ"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-papers-20.png")), "Hướng Dẫn Sử Dụng"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-trip-20.png")), "Quản Lí Tour"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-customer-20.png")), "Quản Lí Khách Hàng"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-route-20.png")), "Quản Lí Lịch Trình"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-ticket-20.png")), "Quản Lí Vé"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-paid-bill-20.png")), "Quản Lí Hợp Đồng"));

        } else {
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-country-house-20.png")), "Trang Chủ"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-papers-20.png")), "Hướng Dẫn Sử Dụng"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-trip-20.png")), "Quản Lí Tour"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-route-20.png")), "Quản Lí Lịch Trình"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-customer-20.png")), "Quản Lí Khách Hàng"));
        }

    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
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

        logo1 = new com.GreenHouse.Component.Logo();
        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(102, 204, 255));

        sp.setOpaque(false);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sp)
                    .addComponent(logo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sp))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(9, 9, 121), getWidth(), 0, new Color(25, 163, 201));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.Component.Logo logo1;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
