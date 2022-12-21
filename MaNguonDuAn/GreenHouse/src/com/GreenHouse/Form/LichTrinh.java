package com.GreenHouse.Form;

import com.GreenHouse.DAO.LichTrinhChiTietDAO;
import com.GreenHouse.DAO.LichTrinhDAO;
import com.GreenHouse.DAO.TourDAO;
import com.GreenHouse.Main.DangNhap;
import com.GreenHouse.Model.ModelDiemThamQuan;
import com.GreenHouse.Model.ModelLichTrinh;
import com.GreenHouse.Model.ModelLichTrinhChiTiet;
import com.GreenHouse.Model.ModelTour;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Swing.Table;
import com.GreenHouse.Utils.MsgBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN
 */
public class LichTrinh extends javax.swing.JPanel {

    //DefaultTableModel dm = new Table().model;
    int dong = -1;
    int ngay = 0;
    TourDAO tourDAO = new TourDAO();
    LichTrinhChiTietDAO ltctDAO = new LichTrinhChiTietDAO();
    LichTrinhDAO ltDAO = new LichTrinhDAO();
    String chucVu = "";

    public LichTrinh() throws SQLException {
        initComponents();
        //    fillCombo();
        fillCboTenTour();
        ltDAO.CapNhatTrangThai();
        fillBang();
        // fillLTCT();
        fillLichTrinh();
        fillcboNoiThamQuan();
        fillCboTenTour_LT();
        fillcboTinhThanh();
        tblLichTrinh.fixTable(jScrollPane1);
        tblLichTrinhCT.fixTable(jScrollPane2);
        tblLichTrinh1.fixTable(jScrollPane3);
        txtNgayKhoiHanh.setText("");
        txtNgayKetThuc.setText("");
        //  init();

        fillBang();
        // editColumnWidth();
//        txtTimKiem_LTCT.setHint("Nhập vào Mã lịch trình, Thời gian đi, Nơi tham quan...");
        txtTimKiem_LT.setHint("Nhập vào Mã lịch trình, Thời gian đi, Nơi xuất phát, Nơi đến, Trạng Thái ...");
    }

    private Date tinhNgay(Date time) throws SQLException {
        String q = cboTenLichTrinh.getSelectedItem().toString();
        ModelTour tour = ltDAO.selectTenTour(q);
        //ModelTour a =new  ModelTour();

        txtMaTour.setText(tour.getMaTour());
        ModelTour t = tourDAO.checkMaTour(tour.getMaTour());
        // int ngay = 0 ;// = t.getSoNgay() + t.getSoDem();
        Calendar cal = Calendar.getInstance();
        if (t.getSoNgay() > t.getSoDem() || t.getSoNgay() == t.getSoDem()) {
            ngay = (int) t.getSoNgay();

            System.out.println(ngay);
            // break;
        } else if (t.getSoDem() > t.getSoNgay()) {
            ngay = (int) t.getSoDem();
            //  cal.add(Calendar.DATE, ngay);
            System.out.println(ngay);
        }
        cal.setTime(time);
        // System.out.println("So ngay" + cal);
        cal.add(Calendar.DATE, ngay);

        System.out.println(cal.getTime());
        return cal.getTime();
    }

    public void fillBang() throws SQLException {
        if (cboNgayKhoiHanh.getSelectedItem() != null) {
            try {
                String temp = cboNgayKhoiHanh.getSelectedItem().toString().replace(" ", "");
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date ngayKhoiHanh = df.parse(temp.substring(temp.indexOf("Từ") + 2, temp.indexOf("Đến")));
                Date ngayKetThuc = df.parse(temp.substring(temp.indexOf("Đến") + 3, temp.length()));
                ArrayList<ModelLichTrinh> list = ltDAO.selectLichTrinhByNgay(ngayKhoiHanh, ngayKetThuc);
                DefaultTableModel dm = (DefaultTableModel) tblLichTrinh.getModel();
                dm.setRowCount(0);
                int i = 0;
                for (ModelLichTrinh mo : list) {
                    dm.addRow(new Object[]{
                        ++i, mo.getMaLT(), ltDAO.getSoLuongKhachCon(mo.getMaLT()), mo.getNoiXuatPhat(), mo.getNoiDen(), mo.getTrangThai()
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //tblLichTrinh.setRo
        }
    }

    void fillComBoLT(String maTour) {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboNgayKhoiHanh.getModel();
            model.removeAllElements();
            List<String> list = ltDAO.selectTGLichTrinh(maTour);
            for (String cd : list) {
                //add name of the subject to cbo
                model.addElement(cd);
            }
        } catch (Exception e) {
            System.out.println(e);
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

    private void fillCboTenTour() throws SQLException {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenTour.getModel();
        model.removeAllElements();
        List<ModelTour> list = tourDAO.TourDAO();
        for (ModelTour tour : list) {
            model.addElement(tour.getMaTour() + " _ " + tour.getTenTour());
        }
    }

    private void fillCboTenTour_LT() throws SQLException {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenLichTrinh.getModel();
        model.removeAllElements();

        List<ModelTour> list = tourDAO.TourDAO();

        for (ModelTour tour : list) {
            model.addElement(tour.getTenTour());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CapNhatLTCT = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtMaLichTrinh_CT = new com.GreenHouse.Swing.MyTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        cboNoiThamQuan = new com.GreenHouse.Swing.Combobox();
        txtGhiChu = new com.GreenHouse.Swing.TextAreaScroll();
        txtGhiCHu = new javax.swing.JTextPane();
        btnLamMoi_CT = new com.GreenHouse.Swing.KButton();
        btnCapNhat2 = new com.GreenHouse.Swing.KButton();
        btnThem2 = new com.GreenHouse.Swing.KButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichTrinhCT = new com.GreenHouse.Swing.Table_1();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
        txtThoiGianDiChuyen = new javax.swing.JSpinner(sm);
        txtMaTour = new com.GreenHouse.Swing.MyTextField();
        txtIDLTCT = new javax.swing.JLabel();
        cboTinhThanh = new com.GreenHouse.Swing.Combobox();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtNgayKhoiHanh = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        CapNhatLichTrinh = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNoiKhoiHanh = new com.GreenHouse.Swing.MyTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNoiDen = new com.GreenHouse.Swing.MyTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnXoa_LichTrinh = new com.GreenHouse.Swing.KButton();
        btnLamMoi_LichTrinh = new com.GreenHouse.Swing.KButton();
        btnCapNhat_LichTrinh = new com.GreenHouse.Swing.KButton();
        btnThem_LichTrinh = new com.GreenHouse.Swing.KButton();
        jLabel15 = new javax.swing.JLabel();
        cdNgayKetThuc = new com.toedter.calendar.JDateChooser();
        txtMaLichTrinh = new com.GreenHouse.Swing.MyTextField();
        rdoChuaBD = new javax.swing.JRadioButton();
        rdoKetThuc = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        cdNgayKhoiHanh = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichTrinh1 = new com.GreenHouse.Swing.Table_1();
        cboTenLichTrinh = new com.GreenHouse.Swing.Combobox();
        txtTimKiem_LT = new com.GreenHouse.Swing.MyTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboNgayKhoiHanh = new com.GreenHouse.Swing.Combobox();
        cboTenTour = new com.GreenHouse.Swing.Combobox();
        jPanel5 = new javax.swing.JPanel();
        btnLamMoi1 = new com.GreenHouse.Swing.KButton();
        btnThem1 = new com.GreenHouse.Swing.KButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichTrinh = new com.GreenHouse.Swing.Table_1();
        btnDatVe = new com.GreenHouse.Swing.KButton();
        btnDatVe1 = new com.GreenHouse.Swing.KButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cboNgayKhoiHanh1 = new com.GreenHouse.Swing.Combobox();
        cboTenTour1 = new com.GreenHouse.Swing.Combobox();
        jPanel7 = new javax.swing.JPanel();
        btnLamMoi2 = new com.GreenHouse.Swing.KButton();
        btnThem3 = new com.GreenHouse.Swing.KButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLichTrinh2 = new com.GreenHouse.Swing.Table_1();
        btnDatVe2 = new com.GreenHouse.Swing.KButton();
        btnDatVe3 = new com.GreenHouse.Swing.KButton();

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setText("Mã Tour");
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 30, -1, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setText("Mã Lịch Trình");
        jPanel6.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 112, -1, -1));

        txtMaLichTrinh_CT.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaLichTrinh_CTCaretUpdate(evt);
            }
        });
        jPanel6.add(txtMaLichTrinh_CT, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 304, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setText("Nơi Tham Quan");
        jPanel6.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, -1, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setText("Ghi Chú");
        jPanel6.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, -1, -1));

        cboNoiThamQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNoiThamQuanActionPerformed(evt);
            }
        });
        jPanel6.add(cboNoiThamQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 370, -1));

        txtGhiChu.setLabelText("");
        txtGhiChu.setViewportView(txtGhiCHu);

        jPanel6.add(txtGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 380, 90));

        btnLamMoi_CT.setText("Làm Mới");
        btnLamMoi_CT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi_CTActionPerformed(evt);
            }
        });
        jPanel6.add(btnLamMoi_CT, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 100, 40));

        btnCapNhat2.setText("Cập Nhật");
        btnCapNhat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhat2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnCapNhat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 100, 40));

        btnThem2.setText("Thêm");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnThem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 100, 40));

        tblLichTrinhCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID", "Mã LT", "MaTour", "Thời Gian Di Chuyển", "Nơi Tham Quan", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichTrinhCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichTrinhCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLichTrinhCT);
        if (tblLichTrinhCT.getColumnModel().getColumnCount() > 0) {
            tblLichTrinhCT.getColumnModel().getColumn(0).setMinWidth(40);
            tblLichTrinhCT.getColumnModel().getColumn(0).setMaxWidth(40);
            tblLichTrinhCT.getColumnModel().getColumn(1).setMinWidth(40);
            tblLichTrinhCT.getColumnModel().getColumn(1).setMaxWidth(40);
            tblLichTrinhCT.getColumnModel().getColumn(2).setMinWidth(65);
            tblLichTrinhCT.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblLichTrinhCT.getColumnModel().getColumn(2).setMaxWidth(65);
            tblLichTrinhCT.getColumnModel().getColumn(3).setMinWidth(65);
            tblLichTrinhCT.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblLichTrinhCT.getColumnModel().getColumn(3).setMaxWidth(65);
        }

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 860, 260));

        JSpinner.DateEditor de = new JSpinner.DateEditor(txtThoiGianDiChuyen,"dd-MM-yyyy HH:mm:ss");
        txtThoiGianDiChuyen.setEditor(de);
        txtThoiGianDiChuyen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtThoiGianDiChuyen.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1669552861548L), null, null, java.util.Calendar.HOUR_OF_DAY));
        txtThoiGianDiChuyen.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtThoiGianDiChuyenStateChanged(evt);
            }
        });
        txtThoiGianDiChuyen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtThoiGianDiChuyenPropertyChange(evt);
            }
        });
        jPanel6.add(txtThoiGianDiChuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 300, 40));
        jPanel6.add(txtMaTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 304, -1));

        txtIDLTCT.setForeground(new java.awt.Color(255, 255, 255));
        txtIDLTCT.setText("jLabel5");
        jPanel6.add(txtIDLTCT, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        cboTinhThanh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTinhThanhItemStateChanged(evt);
            }
        });
        jPanel6.add(cboTinhThanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 370, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setText("Tỉnh thành");
        jPanel6.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, -1, -1));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setText("Mốc Thời Gian Di Chuyển");
        jPanel6.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 194, -1, -1));

        txtNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNgayKetThuc.setText("Ngày khởi hành:");
        jPanel6.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, -1, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setText("Ngày khởi hành:");
        jPanel6.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        txtNgayKhoiHanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNgayKhoiHanh.setText("Ngày khởi hành:");
        jPanel6.add(txtNgayKhoiHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setText("Ngày kết thúc:");
        jPanel6.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        javax.swing.GroupLayout CapNhatLTCTLayout = new javax.swing.GroupLayout(CapNhatLTCT.getContentPane());
        CapNhatLTCT.getContentPane().setLayout(CapNhatLTCTLayout);
        CapNhatLTCTLayout.setHorizontalGroup(
            CapNhatLTCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 922, Short.MAX_VALUE)
            .addGroup(CapNhatLTCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE))
        );
        CapNhatLTCTLayout.setVerticalGroup(
            CapNhatLTCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
            .addGroup(CapNhatLTCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CapNhatLTCTLayout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        CapNhatLichTrinh.setTitle("Cập Nhật Lịch Trình");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên Tour");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nơi khởi hành");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

        txtNoiKhoiHanh.setEditable(false);
        jPanel1.add(txtNoiKhoiHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 370, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Ngày kết thúc");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, -1));

        txtNoiDen.setEditable(false);
        jPanel1.add(txtNoiDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 370, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Trạng Thái");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 80, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tìm Kiếm");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 80, -1));

        btnXoa_LichTrinh.setText("Xóa");
        btnXoa_LichTrinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_LichTrinhActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa_LichTrinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 230, 100, 40));

        btnLamMoi_LichTrinh.setText("Làm Mới");
        btnLamMoi_LichTrinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi_LichTrinhActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi_LichTrinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 100, 40));

        btnCapNhat_LichTrinh.setText("Cập Nhật");
        btnCapNhat_LichTrinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhat_LichTrinhActionPerformed(evt);
            }
        });
        jPanel1.add(btnCapNhat_LichTrinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 100, 40));

        btnThem_LichTrinh.setText("Thêm");
        btnThem_LichTrinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_LichTrinhActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem_LichTrinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 100, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Ngày khởi hành");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        cdNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(cdNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 180, 40));
        jPanel1.add(txtMaLichTrinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 400, 40));

        buttonGroup1.add(rdoChuaBD);
        rdoChuaBD.setText("Chưa Khởi Hành");
        jPanel1.add(rdoChuaBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, -1, -1));

        buttonGroup1.add(rdoKetThuc);
        rdoKetThuc.setText("Kết Thúc");
        jPanel1.add(rdoKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, 80, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Nơi đến");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, -1, -1));

        cdNgayKhoiHanh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cdNgayKhoiHanh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cdNgayKhoiHanhPropertyChange(evt);
            }
        });
        cdNgayKhoiHanh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cdNgayKhoiHanhKeyTyped(evt);
            }
        });
        jPanel1.add(cdNgayKhoiHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 180, 40));

        tblLichTrinh1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLichTrinh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichTrinh1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLichTrinh1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 990, 270));

        cboTenLichTrinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTenLichTrinhItemStateChanged(evt);
            }
        });
        cboTenLichTrinh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboTenLichTrinhPropertyChange(evt);
            }
        });
        jPanel1.add(cboTenLichTrinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 400, -1));

        txtTimKiem_LT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiem_LTKeyReleased(evt);
            }
        });
        jPanel1.add(txtTimKiem_LT, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 530, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Mã lịch trình");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        javax.swing.GroupLayout CapNhatLichTrinhLayout = new javax.swing.GroupLayout(CapNhatLichTrinh.getContentPane());
        CapNhatLichTrinh.getContentPane().setLayout(CapNhatLichTrinhLayout);
        CapNhatLichTrinhLayout.setHorizontalGroup(
            CapNhatLichTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1088, Short.MAX_VALUE)
            .addGroup(CapNhatLichTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE))
        );
        CapNhatLichTrinhLayout.setVerticalGroup(
            CapNhatLichTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
            .addGroup(CapNhatLichTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Ngày Khởi Hành");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 130, 35));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("QUẢN LÍ LỊCH TRÌNH");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 265, 35));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên Tour");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 74, 96, 35));

        cboNgayKhoiHanh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNgayKhoiHanhItemStateChanged(evt);
            }
        });
        add(cboNgayKhoiHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 330, -1));

        cboTenTour.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTenTourItemStateChanged(evt);
            }
        });
        add(cboTenTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 340, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLamMoi1.setText("Cập Nhật Lịch Trình");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnLamMoi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 560, 150, 40));

        btnThem1.setText("Cập Nhật Lịch Trình Chi Tiết");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, 170, 40));

        tblLichTrinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã lịch trình", "Số lượng khách còn", "Nơi xuất phát", "Nơi đến", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichTrinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichTrinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLichTrinh);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 980, 370));

        btnDatVe.setText("Đặt Vé ");
        btnDatVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatVeActionPerformed(evt);
            }
        });
        jPanel5.add(btnDatVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 150, 40));

        btnDatVe1.setText("Làm mới");
        btnDatVe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatVe1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnDatVe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, 150, 40));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Ngày Khởi Hành");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 130, 35));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("QUẢN LÍ LỊCH TRÌNH");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 265, 35));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Tên Tour");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 74, 96, 35));

        cboNgayKhoiHanh1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNgayKhoiHanh1ItemStateChanged(evt);
            }
        });
        jPanel2.add(cboNgayKhoiHanh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 330, -1));

        cboTenTour1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTenTour1ItemStateChanged(evt);
            }
        });
        jPanel2.add(cboTenTour1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 340, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLamMoi2.setText("Cập Nhật Lịch Trình");
        btnLamMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi2ActionPerformed(evt);
            }
        });
        jPanel7.add(btnLamMoi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 560, 150, 40));

        btnThem3.setText("Cập Nhật Lịch Trình Chi Tiết");
        btnThem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem3ActionPerformed(evt);
            }
        });
        jPanel7.add(btnThem3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, 170, 40));

        tblLichTrinh2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã lịch trình", "Số lượng khách còn", "Nơi xuất phát", "Nơi đến", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichTrinh2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichTrinh2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblLichTrinh2);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 980, 370));

        btnDatVe2.setText("Đặt Vé ");
        btnDatVe2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatVe2ActionPerformed(evt);
            }
        });
        jPanel7.add(btnDatVe2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 150, 40));

        btnDatVe3.setText("Làm mới");
        btnDatVe3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatVe3ActionPerformed(evt);
            }
        });
        jPanel7.add(btnDatVe3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, 150, 40));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cboTenTourItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTenTourItemStateChanged
        try {
            if (cboTenTour.getSelectedItem() != null) {
                String maTour = cboTenTour.getSelectedItem().toString().substring(0, cboTenTour.getSelectedItem().toString().indexOf(" _ "));
                fillComBoLT(maTour);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cboTenTourItemStateChanged

    private void btnXoa_LichTrinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_LichTrinhActionPerformed
        try {
            if (chucVu.equalsIgnoreCase("Nhân Viên Trực Quầy")) {
                btnXoa_LichTrinh.setVisible(false);
            }
            Xoa_LT();

        } catch (Exception e) {
            MsgBox.error(null, "Lịch trình này đang sử dụng nên không thể xóa được");
        }
    }//GEN-LAST:event_btnXoa_LichTrinhActionPerformed

    private void btnLamMoi_LichTrinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi_LichTrinhActionPerformed
        Moi();
    }//GEN-LAST:event_btnLamMoi_LichTrinhActionPerformed
    private void showDialog(JDialog nameDialog) {
        nameDialog.setVisible(true);
        nameDialog.setResizable(false);
        nameDialog.getContentPane().setBackground(Color.WHITE);
        nameDialog.pack();
        nameDialog.setLocationRelativeTo(null);
    }

    private void showLTCT() {
        showDialog(CapNhatLTCT);
        CapNhatLichTrinh.dispose();

        String maLt = txtMaLichTrinh.getText().trim();
        txtMaLichTrinh_CT.setText(maLt);
        txtMaLichTrinh_CT.setEditable(false);
        txtMaTour.setEditable(false);
        fillcboNoiThamQuan();

    }


    private void btnCapNhat_LichTrinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhat_LichTrinhActionPerformed
        if (tblLichTrinh1.getSelectedRow() == -1) {
            MsgBox.error(null, "Chọn Lịch trình cần cập nhật");
        } else {
            try {
                CapNhat_LT();
            } catch (SQLException ex) {
                Logger.getLogger(LichTrinh.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnCapNhat_LichTrinhActionPerformed

    private void btnThem_LichTrinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_LichTrinhActionPerformed
        try {
            Them_Lt();
            btnThem2.setVisible(true);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }//GEN-LAST:event_btnThem_LichTrinhActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed

        if (new DangNhap().vt.equalsIgnoreCase("Quản Lý")) {

        } else {
            btnThem_LichTrinh.setVisible(false);
            btnCapNhat_LichTrinh.setVisible(false);
            btnXoa_LichTrinh.setVisible(false);
        }
        showDialog(CapNhatLichTrinh);

    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        if (new DangNhap().vt.equalsIgnoreCase("Quản Lý")) {

        } else {
            btnCapNhat2.setVisible(false);
        }
        showLTCT();
        btnThem2.setVisible(false);

    }//GEN-LAST:event_btnThem1ActionPerformed

    private void cboNoiThamQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNoiThamQuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNoiThamQuanActionPerformed

    private void btnLamMoi_CTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi_CTActionPerformed
        New_CT();
    }//GEN-LAST:event_btnLamMoi_CTActionPerformed

    private void btnCapNhat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhat2ActionPerformed
        if (tblLichTrinhCT.getSelectedRow() == -1) {
            MsgBox.error(null, "Chọn lịch trình chi tiết cần cập nhật");
        } else {
            try {
                if (checkThoiGian_CT()) {
                    CapNhat();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LichTrinh.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnCapNhat2ActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        try {
            if (checkThoiGian_CT()) {
                if (cboTinhThanh.getSelectedIndex() > 0) {

                    Them_LTCT();

                } else {
                    MsgBox.error(null, "Hãy chọn Tỉnh");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(LichTrinh.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void cdNgayKhoiHanhKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cdNgayKhoiHanhKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cdNgayKhoiHanhKeyTyped

    private void cdNgayKhoiHanhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cdNgayKhoiHanhPropertyChange
        if (cdNgayKhoiHanh.getDate() != null) {
            try {
                //             System.out.println(tinhngayKT(txtThoigianXP.getDate()));
                cdNgayKetThuc.setDate(tinhNgay(cdNgayKhoiHanh.getDate()));
            } catch (SQLException ex) {
                Logger.getLogger(LichTrinh.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cdNgayKhoiHanhPropertyChange

    private void tblLichTrinh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichTrinh1MouseClicked
        //showTableLoTrinh();
        dong = tblLichTrinh1.getSelectedRow();
        HienThi(dong);
        checkTrangThai(dong);
    }//GEN-LAST:event_tblLichTrinh1MouseClicked

    private void txtThoiGianDiChuyenStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtThoiGianDiChuyenStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThoiGianDiChuyenStateChanged

    private void txtThoiGianDiChuyenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtThoiGianDiChuyenPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThoiGianDiChuyenPropertyChange

    private void tblLichTrinhCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichTrinhCTMouseClicked
        dong = tblLichTrinhCT.getSelectedRow();
        HienThi_CT(dong);

    }//GEN-LAST:event_tblLichTrinhCTMouseClicked

    private void cboTenLichTrinhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboTenLichTrinhPropertyChange
        /* try {
        ModelTour mt = (ModelTour) tourDAO.TourDAO();
        txtTenPhuongTien.setText(mt.getTenPhuongTien());
        } catch (SQLException ex) {
        Logger.getLogger(LichTrinh.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_cboTenLichTrinhPropertyChange

    private void cboTinhThanhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTinhThanhItemStateChanged
        fillcboNoiThamQuan();
    }//GEN-LAST:event_cboTinhThanhItemStateChanged

    private void btnDatVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatVeActionPerformed

    }//GEN-LAST:event_btnDatVeActionPerformed

    private void cboNgayKhoiHanhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNgayKhoiHanhItemStateChanged
        try {
            fillBang();
        } catch (SQLException ex) {
            Logger.getLogger(LichTrinh.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboNgayKhoiHanhItemStateChanged

    private void tblLichTrinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichTrinhMouseClicked
        if (tblLichTrinh.getSelectedRow() != -1) {
            if (tblLichTrinh.getValueAt(tblLichTrinh.getSelectedRow(), 5).toString().equalsIgnoreCase("Chưa khởi hành")) {
                btnDatVe.setVisible(true);
            } else {
                btnDatVe.setVisible(false);
            }
        }
    }//GEN-LAST:event_tblLichTrinhMouseClicked

    private void btnDatVe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatVe1ActionPerformed
        try {
            fillBang();
        } catch (SQLException ex) {
            Logger.getLogger(LichTrinh.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDatVe1ActionPerformed

    private void cboTenLichTrinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTenLichTrinhItemStateChanged
        if (cboTenLichTrinh.getSelectedItem() != null) {
            setNoiXuatPhatNoiDen(cboTenLichTrinh.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cboTenLichTrinhItemStateChanged

    private void txtTimKiem_LTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem_LTKeyReleased
        try {
            ltDAO.selectbyTimKiem(tblLichTrinh1, txtTimKiem_LT.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_txtTimKiem_LTKeyReleased

    private void cboNgayKhoiHanh1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNgayKhoiHanh1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNgayKhoiHanh1ItemStateChanged

    private void cboTenTour1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTenTour1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTenTour1ItemStateChanged

    private void btnLamMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoi2ActionPerformed

    private void btnThem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem3ActionPerformed

    private void tblLichTrinh2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichTrinh2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLichTrinh2MouseClicked

    private void btnDatVe2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatVe2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDatVe2ActionPerformed

    private void btnDatVe3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatVe3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDatVe3ActionPerformed

    private void txtMaLichTrinh_CTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaLichTrinh_CTCaretUpdate
        try {

            ModelLichTrinh m = ltDAO.selectByMaLichTrinh(txtMaLichTrinh_CT.getText().trim());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            txtNgayKhoiHanh.setText(dateFormat.format(m.getNgayBatDau()));
            txtNgayKetThuc.setText(dateFormat.format(m.getNgayKetThuc()));
        } catch (Exception e) {
        }

    }//GEN-LAST:event_txtMaLichTrinh_CTCaretUpdate

    private void setNoiXuatPhatNoiDen(String s) {
        s = s.substring(0, s.lastIndexOf(" - "));
        String noiXuatPhat = s.substring(0, s.indexOf(" - "));
        String noiDen = s.substring(s.indexOf(" - ") + 3, s.length());
        txtNoiKhoiHanh.setText(noiXuatPhat);
        txtNoiDen.setText(noiDen);
    }

    public void datVe(ActionListener ac) {
        btnDatVe.addActionListener(ac);
    }

    public ModelLichTrinh getData() {
        ModelLichTrinh data = new ModelLichTrinh();
        try {
            int index = tblLichTrinh.getSelectedRow();
            if (index == -1) {
                return data = null;
            }
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            data.setMaLT(tblLichTrinh.getValueAt(index, 1).toString());

            String temp = cboNgayKhoiHanh.getSelectedItem().toString().replace(" ", "");
            Date ngayKhoiHanh = df.parse(temp.substring(temp.indexOf("Từ") + 2, temp.indexOf("Đến")));
            Date ngayKetThuc = df.parse(temp.substring(temp.indexOf("Đến") + 3, temp.length()));
            data.setNgayBatDau(ngayKhoiHanh);
            data.setNgayKetThuc(ngayKetThuc);
            data.setNoiXuatPhat(tblLichTrinh.getValueAt(index, 3).toString());
            data.setNoiDen(tblLichTrinh.getValueAt(index, 4).toString());
            data.setTrangThai(tblLichTrinh.getValueAt(index, 5).toString());
        } catch (ParseException ex) {
            Logger.getLogger(LichTrinh.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    private void fillcboTinhThanh() throws SQLException {
        ArrayList<ModelDiemThamQuan> list = tourDAO.selectTinhDiemThamQuan();
        DefaultComboBoxModel mt = (DefaultComboBoxModel) cboTinhThanh.getModel();
        mt.removeAllElements();
        mt.addElement("Chọn Tên Lịch Trình");
        for (ModelDiemThamQuan mo : list) {
            mt.addElement(mo.getTinh());
        }
    }

    private void chucNang(String str) {
        try {
            if (str.equals("dau")) {
                dong = 0;
            } else if (str.equals("cuoi")) {
                dong = tblLichTrinhCT.getRowCount() - 1;
                dong = tblLichTrinh1.getRowCount() - 1;
            } else if (str.equals("pre")) {
                dong--;
                if (dong < 0) {
                    MsgBox.alert(null, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (str.equals("next")) {
                dong++;
                if (dong >= tblLichTrinhCT.getRowCount() || dong >= tblLichTrinh1.getRowCount()) {
                    MsgBox.alert(null, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }
            tblLichTrinh1.setRowSelectionInterval(dong, dong);
            tblLichTrinhCT.setRowSelectionInterval(dong, dong);

            HienThi_CT(dong);
            HienThi(dong);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    //////////////
    private boolean CheckAll() {
        String ma = txtMaLichTrinh.getText();
        String noiDen = txtNoiDen.getText();
        String noiXuatPhat = txtNoiKhoiHanh.getText();
        String regexma = "LT{1}[0-9]{1,}";

        if (ma.equals("")) {
            MsgBox.error(null, "Mã lịch trình không được để trống!");
            txtMaLichTrinh.requestFocus();
            return false;
        }
      if (!ma.matches(regexma)) {
           MsgBox.error(null, "Mã lịch trình không đúng định dạng 'LT...'");
           txtMaLichTrinh.setText("");
            txtMaLichTrinh.requestFocus();
            return false;
        }
        if (cdNgayKhoiHanh.getDate() == null) {
            MsgBox.error(null, "Ngày xuất phát không được để trống!");
            cdNgayKhoiHanh.setBorder(new LineBorder(Color.red, 1));
            return false;
        }
        if (noiXuatPhat.equals("")) {
            MsgBox.error(null, "Nơi xuất phát không được để trống");
            txtNoiKhoiHanh.requestFocus();
            return false;
        }
        if (noiDen.equals("")) {
            MsgBox.error(null, "Nơi đến không được để trống");
            txtNoiDen.requestFocus();
            return false;
        }
        if (!rdoChuaBD.isSelected() && !rdoKetThuc.isSelected()) {
            MsgBox.error(null, "Chưa chọn trạng thái lịch trình");
            return false;
        }
        return true;
    }

    private boolean checkThoiGian_CT() throws SQLException {
        String maLichTrinh = txtMaLichTrinh_CT.getText().trim();
        Date mocThoiGian = (Date) txtThoiGianDiChuyen.getValue();
        if (!ltctDAO.checkMocThoiGian_CT(maLichTrinh, mocThoiGian)) {
            JOptionPane.showMessageDialog(null, "Lỗi thời gian di chuyển rồi");
            return false;
        }
        return true;
    }

    private void CapNhat() {
        try {
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String ma = txtMaTour.getText().trim();
            String malt = txtMaLichTrinh_CT.getText();
            Date date = (Date) txtThoiGianDiChuyen.getValue();
            //  date =  txtThoiGianDiChuyen.getValue();
            String noithamquan = cboNoiThamQuan.getSelectedItem().toString();
            String ghichu = txtGhiCHu.getText();

            ModelLichTrinhChiTiet m = new ModelLichTrinhChiTiet(malt, ma, date, noithamquan, ghichu);
            m.setId(Integer.parseInt(txtIDLTCT.getText()));

            ltctDAO.CapNhat(m);
            fillLTCT();

            MsgBox.alert(null, "Cập nhật Lịch Trình Chi Tiết thành công");
            tblLichTrinhCT.setRowSelectionInterval(dong, dong);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void HienThi(int i) {
        try {
            ModelLichTrinh lt = new ModelLichTrinh();
            new LichTrinhDAO().HienThi(tblLichTrinh1, lt, i);
            txtMaLichTrinh.setText(lt.getMaLT());
            cdNgayKhoiHanh.setDate(lt.getNgayBatDau());
            cdNgayKetThuc.setDate(lt.getNgayKetThuc());
            txtNoiKhoiHanh.setText(lt.getNoiXuatPhat().toString());
            txtNoiDen.setText(lt.getNoiDen().toString());
            if (lt.getTrangThai().trim().equalsIgnoreCase("Chưa Khởi Hành")) {
                rdoChuaBD.setSelected(true);
            } else {
                rdoKetThuc.setSelected(true);
            }
            System.out.println(lt.getTrangThai());
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void Xoa_LT() throws SQLException {
        int i = tblLichTrinh1.getSelectedRow();
        if (i >= 0) {

            int r = JOptionPane.showConfirmDialog(null, "Bạn cần xóa Lịch trình " + tblLichTrinh1.getValueAt(dong, 1) + "?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaLichTrinh.getText().trim();
                int kt = ltDAO.XoaLT(new ModelLichTrinh(ma));

                fillLichTrinh();
                if (kt == 1) {
                    fillLichTrinh();
                    MsgBox.alert(null, "Xóa Thành Công");
                    Moi();
                }
            } else {
                MsgBox.error(null, "Xóa thất bại!");
            }

        } else {
            MsgBox.error(null, "Chưa chọn đối tượng để xóa!");
        }
    }

    private void checkTrangThai(int index) {
        if (tblLichTrinh1.getValueAt(index, 5).toString().equalsIgnoreCase("Kết Thúc")) {
            btnCapNhat_LichTrinh.setEnabled(false);
            btnCapNhat_LichTrinh.setVisible(false);
        } else {
            btnCapNhat_LichTrinh.setEnabled(true);
            btnCapNhat_LichTrinh.setVisible(true);
        }

    }

    private void CapNhat_LT() throws SQLException {
        if (CheckAll()) {
            if (!ltDAO.checkMaLT(txtMaLichTrinh.getText().trim())) {
                MsgBox.error(null, "Không được cập nhật mã lịch trình");
                txtMaLichTrinh.requestFocus();
            } else {
                String ma = txtMaLichTrinh.getText().trim();
                String NoiXP = txtNoiKhoiHanh.getText().trim();
                String NoiDen = txtNoiDen.getText().trim();
                Date ngayBD = cdNgayKhoiHanh.getDate();
                Date ngayKT = cdNgayKetThuc.getDate();
                String trangThai = rdoChuaBD.isSelected() ? "Chưa Khởi Hành" : "Kết Thúc";

                ltDAO.CapNhat(new ModelLichTrinh(ma, ngayBD, ngayKT, NoiXP, NoiDen, trangThai));
                MsgBox.alert(null, "Cập nhật Lịch Trình thành công");
                fillLichTrinh();
                fillBang();
                Moi();
                tblLichTrinh1.setRowSelectionInterval(dong, dong);
            }
        }
    }

    private void Them_Lt() throws SQLException {
        try {
            if (CheckAll()) {

                SimpleDateFormat st = new SimpleDateFormat("dd-MM-yyyy");

                Date now = st.parse(st.format(new Date().getTime()));

                String ma = txtMaLichTrinh.getText().trim();
                String NoiXP = txtNoiKhoiHanh.getText().trim();
                String NoiDen = txtNoiDen.getText().trim();
                Date ngayBD = cdNgayKhoiHanh.getDate();
                Date ngayKT = cdNgayKetThuc.getDate();
                //boolean trangThai = rdoChuaBD.isSelected();
                String trangThai = rdoChuaBD.isSelected() ? "Chưa Khởi Hành" : "Kết Thúc";

                if (now.before(ngayBD)) {
                    try {
                        if (ltDAO.checkMaLT(ma)) {
                            MsgBox.error(null, "Mã lịch trình đã tồn tại");
                            txtMaLichTrinh.setText("");
                            txtMaLichTrinh.requestFocus();
                            return;
                        } else {
                            new LichTrinhDAO().Them(new ModelLichTrinh(ma, ngayBD, ngayKT, NoiXP, NoiDen, trangThai));
                            fillLichTrinh();
                            ModelLichTrinh lt = new LichTrinhDAO().timNVToDen(ma);
                            for (int i = 0; i < tblLichTrinh1.getRowCount(); i++) {

                                String ma1 = String.valueOf(tblLichTrinh1.getValueAt(i, 2));
                                if (ma1.equals(lt.getMaLT())) {
                                    tblLichTrinh1.setRowSelectionInterval(i, i);
                                }
                                //tblLichTrinh1.setRowSelectionInterval(dong, dong);
                            }

                            //tblLichTrinh.setRowSelectionInterval(dong, dong);
                            MsgBox.alert(null, "Tạo Lịch Trình thành công!");
                            //  Moi();
                            showLTCT();
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                } else {
                    MsgBox.error(null, "Ngày khởi đầu hợp hợp lệ!");
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(LichTrinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean CheckMaLT() throws SQLException {
        if (ltDAO.checkMaLT(txtMaLichTrinh.getText().trim())) {
            MsgBox.error(null, "Mã lịch trình đã tồn tại!");
            txtMaLichTrinh.setText("");
            txtMaLichTrinh.requestFocus();
            return false;
        }

        return true;
    }

    private void Moi() {
        new Table().reset(new JTextField[]{txtMaLichTrinh, txtNoiDen, txtNoiKhoiHanh});
        buttonGroup1.clearSelection();
        cdNgayKetThuc.setDate(null);
        cdNgayKhoiHanh.setDate(null);

    }

    /////Code Chức năng lịch trình Chi Tiết
    private void fillcboNoiThamQuan() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboNoiThamQuan.getModel();
            model.removeAllElements();
            // String t = txtMaTour.getText().trim();

            ArrayList<ModelDiemThamQuan> list = ltctDAO.selectDiemThamQuanByTinhThanh(cboTinhThanh.getSelectedItem().toString());
            //System.out.print(list.size());
            for (ModelDiemThamQuan mo : list) {
                model.addElement(mo.getTenDiaDiem());
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void HienThi_CT(int i) {
        try {
            ModelLichTrinhChiTiet ltct = new ModelLichTrinhChiTiet();
            new LichTrinhChiTietDAO().hienThi(tblLichTrinhCT, ltct, i);
            ModelDiemThamQuan mt = ltctDAO.selectTinhThanhByTenDiaDiem(ltct.getNoiThamQuan());
            cboTinhThanh.setSelectedItem(mt.getTinh());

            txtMaLichTrinh_CT.setText(ltct.getMaLt());
            txtMaTour.setText(ltct.getMaTour());
            txtThoiGianDiChuyen.setValue(ltct.getTgDiChuyen());
            cboNoiThamQuan.setSelectedItem(ltct.getNoiThamQuan());
            txtGhiCHu.setText(ltct.getGhiChu());
            txtIDLTCT.setText(ltct.getId() + "");

            ModelLichTrinh m = ltDAO.selectByMaLichTrinh(ltct.getMaLt());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            txtNgayKhoiHanh.setText(dateFormat.format(m.getNgayBatDau()));
            txtNgayKetThuc.setText(dateFormat.format(m.getNgayKetThuc()));

        } catch (SQLException ex) {
            Logger.getLogger(LichTrinh.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void New_CT() {
        //new Table().reset(new JTextField[]{txtMaLichTrinh_CT});
        txtGhiCHu.setText("");
        txtMaLichTrinh_CT.setText("");
        txtIDLTCT.setText("");
        txtNgayKetThuc.setText("");
        txtNoiKhoiHanh.setText("");
        // txtThoiGianDiChuyen.setValue("");
    }

    void fillLTCT() {
        try {
            DefaultTableModel modelSetDiaDiem = (DefaultTableModel) tblLichTrinhCT.getModel();
            modelSetDiaDiem.setRowCount(0);
            String maHD = txtMaLichTrinh_CT.getText().trim();
            ArrayList<ModelLichTrinhChiTiet> list = ltctDAO.fillTableLTCT(maHD);

            int i = 0;
            for (ModelLichTrinhChiTiet hdct : list) {
                modelSetDiaDiem.addRow(new Object[]{++i, hdct.getId(), hdct.getMaLt(), hdct.getMaTour(), hdct.getTgDiChuyen(), hdct.getNoiThamQuan(), hdct.getGhiChu()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  new LichTrinhChiTietDAO().loadTable(tblLichTrinhCT);

    }

    void fillLichTrinh() {
        ltDAO.loadTableLichTrinh(tblLichTrinh1);
        //  editColumnWidth();
    }

    private void Them_LTCT() {
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //    System.out.println(d.format(txtThoiGianDiChuyen.getValue()));
        String ma = txtMaTour.getText().trim();
        String malt = txtMaLichTrinh_CT.getText();
        Date date = (Date) txtThoiGianDiChuyen.getValue();
        //  System.out.println(date);

        //  date =  txtThoiGianDiChuyen.getValue();
        String noithamquan = cboNoiThamQuan.getSelectedItem().toString();
        String ghichu = txtGhiCHu.getText();
        new LichTrinhChiTietDAO().Them(new ModelLichTrinhChiTiet(malt, ma, date, noithamquan, ghichu));

        fillLTCT();

        //  tblLichTrinhCT.setRowSelectionInterval(dong, dong);
        //editColumnWidth();
        MsgBox.alert(null, "Tạo Lịch Trình thành công!");

    }

    ///////////////////////////

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CapNhatLTCT;
    private javax.swing.JDialog CapNhatLichTrinh;
    private com.GreenHouse.Swing.KButton btnCapNhat2;
    private com.GreenHouse.Swing.KButton btnCapNhat_LichTrinh;
    private com.GreenHouse.Swing.KButton btnDatVe;
    private com.GreenHouse.Swing.KButton btnDatVe1;
    private com.GreenHouse.Swing.KButton btnDatVe2;
    private com.GreenHouse.Swing.KButton btnDatVe3;
    private com.GreenHouse.Swing.KButton btnLamMoi1;
    private com.GreenHouse.Swing.KButton btnLamMoi2;
    private com.GreenHouse.Swing.KButton btnLamMoi_CT;
    private com.GreenHouse.Swing.KButton btnLamMoi_LichTrinh;
    private com.GreenHouse.Swing.KButton btnThem1;
    private com.GreenHouse.Swing.KButton btnThem2;
    private com.GreenHouse.Swing.KButton btnThem3;
    private com.GreenHouse.Swing.KButton btnThem_LichTrinh;
    private com.GreenHouse.Swing.KButton btnXoa_LichTrinh;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.GreenHouse.Swing.Combobox cboNgayKhoiHanh;
    private com.GreenHouse.Swing.Combobox cboNgayKhoiHanh1;
    private com.GreenHouse.Swing.Combobox cboNoiThamQuan;
    private com.GreenHouse.Swing.Combobox cboTenLichTrinh;
    private com.GreenHouse.Swing.Combobox cboTenTour;
    private com.GreenHouse.Swing.Combobox cboTenTour1;
    private com.GreenHouse.Swing.Combobox cboTinhThanh;
    private com.toedter.calendar.JDateChooser cdNgayKetThuc;
    private com.toedter.calendar.JDateChooser cdNgayKhoiHanh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdoChuaBD;
    private javax.swing.JRadioButton rdoKetThuc;
    private com.GreenHouse.Swing.Table_1 tblLichTrinh;
    private com.GreenHouse.Swing.Table_1 tblLichTrinh1;
    private com.GreenHouse.Swing.Table_1 tblLichTrinh2;
    private com.GreenHouse.Swing.Table_1 tblLichTrinhCT;
    private javax.swing.JTextPane txtGhiCHu;
    private com.GreenHouse.Swing.TextAreaScroll txtGhiChu;
    private javax.swing.JLabel txtIDLTCT;
    private com.GreenHouse.Swing.MyTextField txtMaLichTrinh;
    private com.GreenHouse.Swing.MyTextField txtMaLichTrinh_CT;
    private com.GreenHouse.Swing.MyTextField txtMaTour;
    private javax.swing.JLabel txtNgayKetThuc;
    private javax.swing.JLabel txtNgayKhoiHanh;
    private com.GreenHouse.Swing.MyTextField txtNoiDen;
    private com.GreenHouse.Swing.MyTextField txtNoiKhoiHanh;
    private javax.swing.JSpinner txtThoiGianDiChuyen;
    private com.GreenHouse.Swing.MyTextField txtTimKiem_LT;
    // End of variables declaration//GEN-END:variables
}
