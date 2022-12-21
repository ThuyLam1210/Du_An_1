package com.GreenHouse.Main;

import com.GreenHouse.Component.Header;
import com.GreenHouse.Component.Menu;
import com.GreenHouse.Event.EventMenuSelected;
import com.GreenHouse.Event.EventShowPopupMenu;
import com.GreenHouse.Form.*;
import com.GreenHouse.Swing.MenuItem;
import com.GreenHouse.Swing.PopupMenu;
import com.GreenHouse.Utils.MsgBox;
import com.GreenHouse.Utils.XImage;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author ADMIN
 */
public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm mainForm;
    private Animator animator;
    private DangNhap dangNhap;
    String vaitro = "";

    // Khai báo các panel chức năng
    private Tour pnlTour;
    private TrangChu pnlHome;
    private pnlTaiKhoan pnlTaiKhoan;
    private pnlThongKe pnlThongKe;
    private KhachSan pnlKhachSan;
    private DiemThamQuan pnlDTQ;
    private PhuongTien pnlPhuongTien;
    private KhachHang pnlKhachHang;
    private NhanVien pnlNhanVien;
    private Ve pnlVe;
    private pnlHopDong pnlHopDong;
    private LichTrinh pnllichtrinh;
    //END

    public Main() {
        try {
            initComponents();
            initMain();
            checkUser();
            this.setIconImage(XImage.getAppIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMain() throws SQLException {

        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        mainForm = new MainForm();
        dangNhap = new DangNhap();

        //Khai báo các panel chức năng
        pnlTour = new Tour();
        pnlVe = new Ve();
        pnlHopDong = new pnlHopDong();
        pnlPhuongTien = new PhuongTien();
        pnlDTQ = new DiemThamQuan();
        pnlKhachHang = new KhachHang();
        pnlHome = new TrangChu();
        pnlNhanVien = new NhanVien();
        pnllichtrinh = new LichTrinh();
        pnlTaiKhoan = new pnlTaiKhoan();
        pnlKhachSan = new KhachSan();
        pnlThongKe = new pnlThongKe();
        // pnlLTCT = new LichTrinhChiTiet();
        // End
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);// If Menu -> If Submenu
                // ==================================== QUAN LY ====================================
                if (dangNhap.vt.equalsIgnoreCase("Quản Lý")) {

                    if (menuIndex == 0) {
                        mainForm.showForm(pnlHome);
                    } else if (menuIndex == 1) {
                        try {
                            Desktop.getDesktop().browse(new File("Web/index.html").toURI());
                        } catch (IOException ex) {
                            System.out.print(ex);
                        }
                    } else if (menuIndex == 2) {
                        mainForm.showForm(pnlVe);
                    } else if (menuIndex == 3) {
                        mainForm.showForm(pnlTour);
                    } else if (menuIndex == 4) {
                        mainForm.showForm(pnlDTQ);
                    } else if (menuIndex == 5) {
                        mainForm.showForm(pnlPhuongTien);
                    } else if (menuIndex == 6) {
                        mainForm.showForm(pnlKhachSan);
                    } else if (menuIndex == 7) {
                        mainForm.showForm(pnllichtrinh);
                    } else if (menuIndex == 8) {
                        mainForm.showForm(pnlHopDong);
                    } else if (menuIndex == 9) {
                        mainForm.showForm(pnlKhachHang);
                    } else if (menuIndex == 10) {
                        mainForm.showForm(pnlNhanVien);
                    } else if (menuIndex == 11) {
                        mainForm.showForm(pnlTaiKhoan);
                    } else if (menuIndex == 12) {
                       mainForm.showForm(pnlThongKe);
                    }

                    // ==================================== ADMIN ====================================
                } else if (dangNhap.vt.equalsIgnoreCase("Admin")) {

                    if (menuIndex == 0) {
                        mainForm.showForm(pnlHome);
                    } else if (menuIndex == 1) {
                        try {
                            Desktop.getDesktop().browse(new File("Web/index.html").toURI());
                        } catch (IOException ex) {
                            System.out.print(ex);
                        }
                    } else if (menuIndex == 2) {
                        mainForm.showForm(pnlTaiKhoan);
                    }

                    // ==================================== NHAN VIEN TRUC QUAY ====================================
                } else if (dangNhap.vt.equalsIgnoreCase("Nhân Viên Trực Quầy")) {

                    if (menuIndex == 0) {
                        mainForm.showForm(pnlHome);
                    } else if (menuIndex == 1) {
                        try {
                            Desktop.getDesktop().browse(new File("Web/index.html").toURI());
                        } catch (IOException ex) {
                            System.out.print(ex);
                        }
                    } else if (menuIndex == 2) {
                        mainForm.showForm(pnlTour);
                    } else if (menuIndex == 3) {
                        mainForm.showForm(pnlKhachHang);
                    } else if (menuIndex == 4) {
                        mainForm.showForm(pnllichtrinh);
                    } else if (menuIndex == 5) {
                        mainForm.showForm(pnlVe);
                    } else if (menuIndex == 6) {
                        mainForm.showForm(pnlHopDong);
                    }

                    // ==================================== NHAN VIEN DAN TOUR ====================================
                } else if (dangNhap.vt.equalsIgnoreCase("Nhân Viên Hướng Dẫn")) {

                    if (menuIndex == 0) {
                        mainForm.showForm(pnlHome);
                    } else if (menuIndex == 1) {
                        try {
                            Desktop.getDesktop().browse(new File("Web/index.html").toURI());
                        } catch (IOException ex) {
                            System.out.print(ex);
                        }
                    } else if (menuIndex == 2) {
                        mainForm.showForm(pnlTour);
                    } else if (menuIndex == 3) {
                        mainForm.showForm(pnllichtrinh);
                    } else if (menuIndex == 4) {
                        mainForm.showForm(pnlKhachHang);
                    }

                }
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 70;
                int y = Main.this.getY() + com.getY() + 120;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 70 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }
        };

        menu.initMenuItem();

        bg.add(menu, "w 240!, spany 2");// Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(mainForm, "w 100%, h 100%");

        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);

        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        header.logout(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = JOptionPane.showConfirmDialog(null, "Bạn muốn đăng xuất?", "Thông Báo", JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.YES_OPTION) {
                    new DangNhap().setVisible(true);
                    closeMain();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        pnllichtrinh.datVe(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pnllichtrinh.getData() != null) {
                    mainForm.showForm(pnlVe);
                    pnlVe.setData(pnllichtrinh.getData());
                } else {
                    MsgBox.error(null, "Vui lòng chọn Tour cần đặt!");
                }

            }
        });
        pnlVe.taoHopDong(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainForm.showForm(pnlHopDong);

                pnlHopDong.innit(pnlVe.layDuLieu());

            }
        });

        //  Start with this form
        mainForm.showForm(pnlHome);
    }

    void checkUser() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        vaitro = DangNhap.vt;
                        //  String ten = new TaiKhoanDAO().getHoTen(DangNhap.tendangnhap);

                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void closeMain() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);
        bg.setPreferredSize(new java.awt.Dimension(1404, 900));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1404, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                //new Main().setVisible(true);
                new DangNhap().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
