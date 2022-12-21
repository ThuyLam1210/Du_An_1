package com.GreenHouse.Form;

import com.GreenHouse.DAO.TourDAO;
import com.GreenHouse.Main.DangNhap;
import com.GreenHouse.Main.Main;
import com.GreenHouse.Model.ModelDiemThamQuan;
import com.GreenHouse.Model.ModelKhachSan;
import com.GreenHouse.Model.ModelPhuongTien;
import com.GreenHouse.Model.ModelThemDTQ;
import com.GreenHouse.Model.ModelTour;
import com.GreenHouse.Swing.ScrollBarCustom;
import com.GreenHouse.Utils.EnglishNumberToWords;
import com.GreenHouse.Utils.MsgBox;
import com.GreenHouse.Utils.XImage;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Tour extends javax.swing.JPanel {

    private DefaultTableModel model;
    private int indexTbl = -1;
    private int dong = -1;
    private String itemInsert;
    private DecimalFormat df = new DecimalFormat("#,###");
    private TourDAO tourDAO;

    public Tour() {
        try {
            initComponents();
            tourDAO = new TourDAO();
            initQuyenTruyCap(new DangNhap().vt);
            initTable();

            initALL();
            updateGiaTour();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void initALL() {
        initSetDiaDiem();
        initSetPhuongTien();
        initSetKhachSan();
    }

    private void initTable() throws SQLException {
        fillTableTour();
        tblTour.fixTable(jScrollPane2);
        tblDiaDiemByTinh.fixTable(jScrollPane1);
        tblDiemThamQuan.fixTable(jScrollPane3);
        tblKhachSanByTinh.fixTable(jScrollPane4);
        tblSetKhachSan.fixTable(jScrollPane6);
        txtFind.setHint("Mã tour, Tên tour, số lượng khách, số ngày, số đêm, điểm tham quan, phương tiện, khách sạn, ...");
        fillTableSetDiaDiem();
    }

//
    //Cap Nhat ==================================================================================================================
    private ModelTour getFormTour() {
        String maTour = txtMaTour.getText().trim();
        String tenTour = txtTenTour.getText().trim();
        int soNgay = Integer.parseInt(txtSoNgay.getText().trim());
        int soDem = Integer.parseInt(txtSoDem.getText().trim());
        int giaTour = Integer.parseInt(txtGiaTour.getText().trim().replace(",", ""));
        int soLuongKhach = Integer.parseInt(txtSoLuongKhach.getText().trim());
        String hinhAnh = pic.getToolTipText();
        ModelTour mt = new ModelTour(maTour, tenTour, soLuongKhach, soNgay, soDem, giaTour, hinhAnh);
        return mt;
    }

    private void clearFormTour() {
        txtMaTour.setText("");
        txtTenTour.setText("");
        txtSoLuongKhach.setText("0");
        txtSoNgay.setText("0");
        txtSoDem.setText("0");
        txtGiaTour.setText("0");
        txtMaTour.requestFocus();
        txtPhiThamQuan.setText("0 VND");
        txtPhiPhuongTien.setText("0 VND");
        txtPhiKhachSan.setText("0 VND");
        txtLoiNhuanMucTieu.setText("0 VND");
        txtGiaCoBanTour.setText("0 VND");
    }

    private boolean validateFormTour() {
        boolean kt = true;
        if (txtMaTour.getText().equals("")) {
            txtMaTour.requestFocus();
            JOptionPane.showMessageDialog(null, "Bỏ trống thông tin");
            kt = false;
        }
        if (txtTenTour.getText().equals("")) {
            txtTenTour.requestFocus();
            JOptionPane.showMessageDialog(null, "Bỏ trống thông tin");
            kt = false;
        }
        if (txtSoLuongKhach.getText().equals("")) {
            txtSoLuongKhach.requestFocus();
            JOptionPane.showMessageDialog(null, "Bỏ trống thông tin");
            kt = false;
        }
        if (Integer.parseInt(txtSoLuongKhach.getText().trim()) <= 0) {
            txtSoLuongKhach.requestFocus();
            JOptionPane.showMessageDialog(null, "Số lượng khách tối thiểu là 1");
            kt = false;
        }
        if (txtSoNgay.getText().equals("")) {
            txtMaTour.requestFocus();
            JOptionPane.showMessageDialog(null, "Bỏ trống thông tin");
            kt = false;
        }
        if (Integer.parseInt(txtSoNgay.getText().trim()) <= 0) {
            txtSoNgay.requestFocus();
            JOptionPane.showMessageDialog(null, "Số ngày tối thiểu phải là 1");
            kt = false;
        }
        if (txtSoDem.getText().equals("")) {
            txtSoDem.requestFocus();
            JOptionPane.showMessageDialog(null, "Bỏ trống thông tin");
            kt = false;
        }
        if (Integer.parseInt(txtSoDem.getText().trim()) <= 0) {
            txtSoDem.requestFocus();
            JOptionPane.showMessageDialog(null, "Số đêm tối thiểu phải là 1");
            kt = false;
        }
        if (txtGiaTour.getText().equals("")) {
            txtGiaTour.requestFocus();
            JOptionPane.showMessageDialog(null, "Bỏ trống thông tin");
            kt = false;
        }
        return kt;
    }

    private void insertTour() throws SQLException {
        ModelTour mt = getFormTour();
        String maTour = txtMaTour.getText().trim();
        if (mt != null) {
            if (!tourDAO.checkDuplicateTour(mt.getMaTour())) {
                tourDAO.insertTour(mt);
                tab.setSelectedIndex(1);
                ModelTour tour = tourDAO.timMa(maTour);
                for (int i = 0; i < tblTour.getRowCount(); i++) {
                    String id = String.valueOf(tblTour.getValueAt(i, 1));
                    if (id.equals(tour.getMaTour())) {
                        System.out.println(id.equals(tour.getMaTour()));
                        tblTour.setRowSelectionInterval(i, i);
                    }

                }
                MsgBox.alert(null, "Tạo Tour thành công");

            } else {
                JOptionPane.showMessageDialog(null, "Mã tour này đã tồn tại");
            }
        }
    }

    private void updateTour() throws SQLException {
        ModelTour mt = getFormTour();
        if (mt != null) {
            if (tourDAO.checkDuplicateTour(mt.getMaTour())) {
                tourDAO.updateTour(mt);
                fillTableTour();
                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                fillTblInfoTour();
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy tour có mã tour: " + mt.getMaTour());
            }
        }
    }

    private void deleteTour() throws SQLException {
        ModelTour mt = getFormTour();
        if (mt != null) {
            tourDAO.deleteTour(mt.getMaTour());
        }
    }

    private void fillTblInfoTour() throws SQLException {
        tblPhuongTien.fixTable(jScrollPane8);
        tblDiemThamQuan.fixTable(jScrollPane9);
        tblKhachSan.fixTable(jScrollPane10);

        DecimalFormat df = new DecimalFormat("#,###");
        long tongPhiThamQuan = 0, tongPhiPhuongTien = 0, tongPhiKhachSan = 0, loiNhuanMucTieu = 0, tongGiaTourCoBan, giaTour;

        DefaultTableModel m = (DefaultTableModel) tblDiemThamQuan.getModel();
        m.setRowCount(0);
        ArrayList<ModelThemDTQ> list = tourDAO.selectSetThamQuanByMaTour(txtMaTour.getText().trim());

        for (ModelThemDTQ mo : list) {
            m.addRow(new Object[]{
                mo.getTenDiemThamQuan(), mo.getDiaChi()
            });
            tongPhiThamQuan += 500000;

        }
        tongPhiThamQuan = tongPhiThamQuan * Integer.parseInt(txtSoLuongKhach.getText());
        txtPhiThamQuan.setText(df.format(tongPhiThamQuan) + " VND");

        m = (DefaultTableModel) tblPhuongTien.getModel();
        m.setRowCount(0);
        ArrayList<ModelPhuongTien> list1 = tourDAO.selectSetPhuongTienByMaTour(txtMaTour.getText().trim());
        for (ModelPhuongTien mo : list1) {
            m.addRow(new Object[]{
                mo.getTenPhuongTien(), mo.getBienSo(), df.format(mo.getCuocPhi())
            });
            tongPhiPhuongTien += mo.getCuocPhi();
        }
        txtPhiPhuongTien.setText(df.format(tongPhiPhuongTien) + " VND");

        m = (DefaultTableModel) tblKhachSan.getModel();
        m.setRowCount(0);
        ArrayList<ModelKhachSan> list2 = tourDAO.selectSetKhachSanByMaTour(txtMaTour.getText().trim());
        for (ModelKhachSan mo : list2) {
            m.addRow(new Object[]{
                mo.getTenKhachSan(), df.format(mo.getGiaKhachSan()), mo.getDiaChi()
            });
            tongPhiKhachSan += mo.getGiaKhachSan();
        }
        if (Integer.parseInt(txtSoNgay.getText().trim()) >= Integer.parseInt(txtSoDem.getText().trim())) {
            tongPhiKhachSan = tongPhiKhachSan * Integer.parseInt(txtSoNgay.getText().trim());

        } else {
            tongPhiKhachSan = (long) (tongPhiKhachSan * (Integer.parseInt(txtSoNgay.getText().trim()) + 0.5));

        }
        int soluongphong = (int) Math.round(Integer.parseInt(txtSoLuongKhach.getText()) / 4);

        System.out.println(soluongphong);
        tongPhiKhachSan = soluongphong * tongPhiKhachSan;
        //   System.out.println((Integer.parseInt(txtSoNgay.getText().trim()) + 0.5));
        txtPhiKhachSan.setText(df.format(tongPhiKhachSan) + " VND");
        tongGiaTourCoBan = tongPhiKhachSan + tongPhiPhuongTien + tongPhiThamQuan;

        txtGiaTour.setText(df.format(tongGiaTourCoBan));
        txtGiaCoBanTour.setText(df.format(tongGiaTourCoBan) + " VND");

        loiNhuanMucTieu = tongGiaTourCoBan * 15 / 100;
        txtLoiNhuanMucTieu.setText(df.format(loiNhuanMucTieu) + " VND");
        txtGiaTour1.setText(df.format(tongGiaTourCoBan + loiNhuanMucTieu) + "");

        giaTour = (tongGiaTourCoBan + loiNhuanMucTieu); // gia chua tinh VAT
        giaTour = giaTour + giaTour / 100 * 10; // VAT 10%
        giaTour = giaTour / Integer.parseInt(txtSoLuongKhach.getText().trim()); // Gia 1 du khach
        txtGiaTour.setText(df.format(giaTour));

        String a = EnglishNumberToWords.convert(giaTour);
        a = a.substring(0, 1).toUpperCase() + a.substring(1);
        txtTienThanhChu.setText(a);
        tourDAO.updateGiaTour(txtMaTour.getText().trim(), giaTour);
        fillTableTour();
    }

    private void updateGiaTour() throws SQLException {
        for (int i = 0; i < tblTour.getRowCount(); i++) {
            txtMaTour.setText(tblTour.getValueAt(i, 1).toString());
            txtTenTour.setText(tblTour.getValueAt(i, 2).toString());
            txtSoLuongKhach.setText(tblTour.getValueAt(i, 3).toString());
            txtSoNgay.setText(tblTour.getValueAt(i, 4).toString());
            txtSoDem.setText(tblTour.getValueAt(i, 5).toString());
            txtGiaTour.setText(tblTour.getValueAt(i, 6).toString());
            fillTblInfoTour();
        }
        fillTableTour();
    }

    private void chonHinhAnh() {
        JFileChooser fileChooser = new JFileChooser();
        String currentDirectory = "..\\GreenHouse\\src\\com\\GreenHouse\\HinhAnh";
        fileChooser.setCurrentDirectory(new File(currentDirectory));
        fileChooser.setAcceptAllFileFilterUsed(true);
        String[] exts = {"PNG", "JPG", "GIF"};
        String description = "supported files: " + Arrays.toString(exts).replace('[', '(').replace(']', ')');
        fileChooser.setFileFilter(new FileNameExtensionFilter(description, exts));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            pic.setIcon(icon);
            pic.setToolTipText(file.getName());
        }
    }

    // Danh Sach ================================================================================================================
    private void fillTableTour() throws SQLException {
        model = (DefaultTableModel) tblTour.getModel();
        model.setRowCount(0);
        ArrayList<ModelTour> list = tourDAO.selectAll();
        int i = 0;
        for (ModelTour modelTour : list) {
            model.addRow(new Object[]{
                ++i, modelTour.getMaTour(), modelTour.getTenTour(), modelTour.getSoLuongKhach(), modelTour.getSoNgay(), modelTour.getSoDem(), df.format(modelTour.getGiaTour()), modelTour.getHinhAnh()
            });
        }
    }

    private void findFillTableByAny(String key) throws SQLException {
        model = (DefaultTableModel) tblTour.getModel();
        model.setRowCount(0);
        ArrayList<ModelTour> list = tourDAO.selectByAnything(key);
        int i = 0;
        for (ModelTour modelTour : list) {
            model.addRow(new Object[]{
                ++i,
                modelTour.getMaTour(),
                modelTour.getTenTour(),
                modelTour.getSoLuongKhach(),
                modelTour.getSoNgay(),
                modelTour.getSoDem(),
                modelTour.getGiaTour(),
                modelTour.getHinhAnh()
            });
        }
    }

    private void showDataTour(int i) {
        txtMaTour.setText(tblTour.getValueAt(i, 1).toString());
        txtTenTour.setText(tblTour.getValueAt(i, 2).toString());
        txtSoLuongKhach.setText(tblTour.getValueAt(i, 3).toString());
        txtSoNgay.setText(tblTour.getValueAt(i, 4).toString());
        txtSoDem.setText(tblTour.getValueAt(i, 5).toString());
        txtGiaTour.setText(tblTour.getValueAt(i, 6).toString());
        String hinhAnh = "";
        if (tblTour.getValueAt(i, 7) != null) {
            hinhAnh = tblTour.getValueAt(i, 7).toString();
            ImageIcon icon = XImage.read(hinhAnh);
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaleicon = new ImageIcon(imgScale);
            pic.setToolTipText(hinhAnh);
            pic.setIcon(scaleicon);
        }
        // pic.setIcon(new ImageIcon(hinhAnh));
        //  pic.setToolTipText(hinhAnh);
        try {
            fillTblInfoTour();
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Them Set Dia Diem ================================================================================================================
    private void initSetDiaDiem() {
        try {
            fillCboMaTour();
            fillCboTinhThanh();
            jScrollPane3.setVerticalScrollBar(new ScrollBarCustom());
            jScrollPane3.setHorizontalScrollBar(new ScrollBarCustom());
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillTableSetDiaDiem() throws SQLException {
        DefaultTableModel modelSetDiaDiem = (DefaultTableModel) tblSetDiaDiem.getModel();
        modelSetDiaDiem.setRowCount(0);
        if (cboMaTour.getSelectedIndex() > 0) {
            String maTour = cboMaTour.getSelectedItem().toString().substring(0, cboMaTour.getSelectedItem().toString().indexOf(" _ "));
            ArrayList<ModelThemDTQ> mt = tourDAO.selectSetThamQuanByMaTour(maTour);
            int i = 0;
            for (ModelThemDTQ modelThemDTQ : mt) {
                modelSetDiaDiem.addRow(new Object[]{
                    ++i, modelThemDTQ.getTenTour(), modelThemDTQ.getTenDiemThamQuan(), modelThemDTQ.getDiaChi(), modelThemDTQ.getMaTour(), modelThemDTQ.getMaDiemThamQuan()
                });
            }
        }

    }

    public void fillTableDiaDiemByTinh() throws SQLException {
        DefaultTableModel m = (DefaultTableModel) tblDiaDiemByTinh.getModel();
        m.setRowCount(0);
        ArrayList<ModelDiemThamQuan> mt = tourDAO.selectDiemThamQuanByTinhThanh(String.valueOf(cboTinhThanh.getSelectedItem()));
        int i = 0;
        for (ModelDiemThamQuan mo : mt) {
            m.addRow(new Object[]{
                ++i, mo.getTenDiaDiem(), mo.getDiaChi(), mo.getMaDiaDiem()
            });
        }
    }

    private void fillCboMaTour() throws SQLException {
        ArrayList<ModelTour> mt = tourDAO.selectAll();

        DefaultComboBoxModel modelCboTour = (DefaultComboBoxModel) cboMaTour.getModel();
        modelCboTour.removeAllElements();
        modelCboTour.addElement("Hãy chọn tour");
        for (ModelTour model1 : mt) {
            modelCboTour.addElement(model1.getMaTour() + " _ " + model1.getTenTour());
        }

        DefaultComboBoxModel modelCboTour1 = (DefaultComboBoxModel) cboMaTour1.getModel();
        modelCboTour1.removeAllElements();
        modelCboTour1.addElement("Hãy chọn tour");
        for (ModelTour model1 : mt) {
            modelCboTour1.addElement(model1.getMaTour() + " _ " + model1.getTenTour());
        }

        DefaultComboBoxModel modelCboTour2 = (DefaultComboBoxModel) cboMaTour2.getModel();
        modelCboTour2.removeAllElements();
        modelCboTour2.addElement("Hãy chọn tour");
        for (ModelTour model1 : mt) {
            modelCboTour2.addElement(model1.getMaTour() + " _ " + model1.getTenTour());
        }
    }

    public void fillCboTinhThanh() throws SQLException {
        DefaultComboBoxModel modelCboDiaDiem = (DefaultComboBoxModel) cboTinhThanh.getModel();
        modelCboDiaDiem.removeAllElements();
        ArrayList<ModelDiemThamQuan> mt = tourDAO.selectTinhDiemThamQuan();
        for (ModelDiemThamQuan model1 : mt) {
            modelCboDiaDiem.addElement(model1.getTinh());
        }
    }

    private void insertSetDiaDiem(int index) throws SQLException {
        String maTour = cboMaTour.getSelectedItem().toString().substring(0, cboMaTour.getSelectedItem().toString().indexOf(" _ "));
        String maDiaDiem = String.valueOf(tblDiaDiemByTinh.getValueAt(index, 3));
        itemInsert = maDiaDiem;
        if (maTour != null && maDiaDiem != null) {
            if (!tourDAO.checkDuplicateSetDiemThamQuan(maTour, maDiaDiem)) {
                tourDAO.insertSetDiemThamQuan(maDiaDiem, maTour);
            } else {
                JOptionPane.showMessageDialog(null, "Da ton tai diem tham quan nay trong tour");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chua chon thong tin");
        }
    }

    private void deleteSetDiaDiem(int index) throws SQLException {
        String maTour = String.valueOf(tblSetDiaDiem.getValueAt(index, 4));
        String maDiaDiem = String.valueOf(tblSetDiaDiem.getValueAt(index, 5));
        if (maTour != null && maDiaDiem != null) {
            if (tourDAO.checkDuplicateSetDiemThamQuan(maTour, maDiaDiem)) {
                tourDAO.deleteSetDiemThamQuan(maTour, maDiaDiem);
            } else {
                JOptionPane.showMessageDialog(null, "Khong ton tai diem tham quan nay trong tour");
            }
        }

    }

//    // Them Set Phuong Tien ================================================================================================================
    private void initSetPhuongTien() {
        try {
            fillCboMaTour();
            fillCboLoaiPhuongTien();
            tblSetPhuongTien.fixTable(jScrollPane5);
            tblPhuongTienByLoai.fixTable(jScrollPane11);
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCboLoaiPhuongTien() throws SQLException {
        DefaultComboBoxModel modelCboPT = (DefaultComboBoxModel) cboLoaiPhuongTien.getModel();
        modelCboPT.removeAllElements();
        String list[] = tourDAO.selectLoaiPhuongTien();
        for (String string : list) {
            modelCboPT.addElement(string);
        }
    }

    private void fillTableSetPhuongTien() throws SQLException {
        DefaultTableModel modelSetPT = (DefaultTableModel) tblSetPhuongTien.getModel();
        modelSetPT.setRowCount(0);
        if (cboMaTour1.getSelectedIndex() > 0) {
            String maTour = cboMaTour1.getSelectedItem().toString().substring(0, cboMaTour1.getSelectedItem().toString().indexOf(" _ "));
            ArrayList<ModelPhuongTien> mt = tourDAO.selectSetPhuongTienByMaTour(maTour);
            ModelTour tenTour = tourDAO.selectByID(maTour);
            int i = 0;
            DecimalFormat df = new DecimalFormat("#,### VND");
            for (ModelPhuongTien m : mt) {
                modelSetPT.addRow(new Object[]{
                    ++i, tenTour.getTenTour(), m.getTenPhuongTien(), m.getBienSo(), df.format(m.getCuocPhi()), tenTour.getMaTour(), m.getMaPhuongTien()
                });
            }
        }

    }

    private void fillTablePhuongTienByLoai() throws SQLException {
        DefaultTableModel m = (DefaultTableModel) tblPhuongTienByLoai.getModel();
        m.setRowCount(0);
        ArrayList<ModelPhuongTien> mt = tourDAO.selectPhuongTienByLoai(String.valueOf(cboLoaiPhuongTien.getSelectedItem()));
        int i = 0;
        DecimalFormat df = new DecimalFormat("#,### VND");
        for (ModelPhuongTien mo : mt) {
            m.addRow(new Object[]{
                ++i, mo.getTenPhuongTien(), mo.getBienSo(), df.format(mo.getCuocPhi()), mo.getMaPhuongTien()
            });
        }
    }

    private void insertSetPhuongTien(int index) throws SQLException {
        String maTour = cboMaTour1.getSelectedItem().toString().substring(0, cboMaTour1.getSelectedItem().toString().indexOf(" _ "));
        String maPhuongTien = String.valueOf(tblPhuongTienByLoai.getValueAt(index, 4));
        if (maTour != null && maPhuongTien != null) {
            if (!tourDAO.checkDuplicateSetPhuongTien(maTour, maPhuongTien)) {
                tourDAO.insertSetPhuongTien(maPhuongTien, maTour);
            } else {
                JOptionPane.showMessageDialog(null, "Da ton tai phuong tien nay");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chua chon thong tin");
        }
    }

    private void deleteSetPhuongTien(int index) throws SQLException {
        String maTour = cboMaTour1.getSelectedItem().toString().substring(0, cboMaTour1.getSelectedItem().toString().indexOf(" _ "));
        String maPhuongTien = String.valueOf(tblSetPhuongTien.getValueAt(index, 6));
        if (maTour != null && maPhuongTien != null) {
            if (tourDAO.checkDuplicateSetPhuongTien(maTour, maPhuongTien)) {
                tourDAO.deleteSetPhuongTien(maTour, maPhuongTien);
            } else {
                JOptionPane.showMessageDialog(null, "Khong ton tai phuong tien nay trong tour");
            }
        }
    }

    // Them Set Khach San ================================================================================================================
    private void initSetKhachSan() {
        try {
            fillCboMaTour();
            fillCboTinhKhachSan();
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillTableSetKhachSan() throws SQLException {
        DefaultTableModel modelSetPT = (DefaultTableModel) tblSetKhachSan.getModel();
        modelSetPT.setRowCount(0);
        if (cboMaTour2.getSelectedIndex() > 0) {
            String maTour = cboMaTour2.getSelectedItem().toString().substring(0, cboMaTour2.getSelectedItem().toString().indexOf(" _ "));
            ArrayList<ModelKhachSan> mt = tourDAO.selectSetKhachSanByMaTour(maTour);
            ModelTour tenTour = tourDAO.selectByID(maTour);
            DecimalFormat df = new DecimalFormat("#,### VND");
            for (ModelKhachSan m : mt) {
                modelSetPT.addRow(new Object[]{
                    tenTour.getTenTour(), m.getTenKhachSan(), df.format(m.getGiaKhachSan()), m.getDiaChi(), tenTour.getMaTour(), m.getMaKhachSan()
                });
            }
        }
    }

    public void fillCboTinhKhachSan() throws SQLException {
        DefaultComboBoxModel modelCboKS = (DefaultComboBoxModel) cboTinhKhachSan.getModel();
        modelCboKS.removeAllElements();
        ArrayList<ModelKhachSan> mt = tourDAO.selectKhachSanAll();
        for (ModelKhachSan model1 : mt) {
            modelCboKS.addElement(model1.getTinh());
        }
    }

    public void fillTblKhachSanByTinh() throws SQLException {
        DefaultTableModel m = (DefaultTableModel) tblKhachSanByTinh.getModel();
        m.setRowCount(0);
        ArrayList<ModelKhachSan> list = tourDAO.selectKhachSanByTinh(cboTinhKhachSan.getSelectedItem().toString());
        DecimalFormat df = new DecimalFormat("#,### VND");
        for (ModelKhachSan mo : list) {
            m.addRow(new Object[]{
                mo.getTenKhachSan(), df.format(mo.getGiaKhachSan()), mo.getSdt(), mo.getDiaChi(), mo.getMaKhachSan()
            });
        }
    }

    private void insertSetKhachSan(int index) throws SQLException {
        String maTour = cboMaTour2.getSelectedItem().toString().substring(0, cboMaTour2.getSelectedItem().toString().indexOf(" _ "));
        String maKhachSan = String.valueOf(tblKhachSanByTinh.getValueAt(index, 4));
        if (maTour != null && maKhachSan != null) {
            if (!tourDAO.checkDuplicateSetKhachSan(maTour, maKhachSan)) {
                tourDAO.insertSetKhachSan(maKhachSan, maTour);
            } else {
                JOptionPane.showMessageDialog(null, "Da ton tai khach san nay trong tour");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chua chon thong tin");
        }
    }

    private void deleteSetKhachSan(int index) throws SQLException {
        String maTour = cboMaTour2.getSelectedItem().toString().substring(0, cboMaTour2.getSelectedItem().toString().indexOf(" _ "));
        String maKhachSan = String.valueOf(tblSetKhachSan.getValueAt(index, 5));
        if (maTour != null && maKhachSan != null) {
            if (tourDAO.checkDuplicateSetKhachSan(maTour, maKhachSan)) {
                tourDAO.deleteSetKhachSan(maTour, maKhachSan);
            } else {
                JOptionPane.showMessageDialog(null, "Khong ton tai khach san nay trong tour");
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tab = new com.GreenHouse.Swing.MaterialTabbed();
        pnlCapNhat = new javax.swing.JPanel();
        txtMaTour = new com.GreenHouse.Swing.MyTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenTour = new com.GreenHouse.Swing.MyTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSoLuongKhach = new com.GreenHouse.Swing.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoDem = new com.GreenHouse.Swing.MyTextField();
        txtSoNgay = new com.GreenHouse.Swing.MyTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGiaTour = new com.GreenHouse.Swing.MyTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnChonHinhAnh = new com.GreenHouse.Swing.ButtonOutLine();
        btnUpdateTour = new com.GreenHouse.Swing.Button();
        btnSaveTour = new com.GreenHouse.Swing.Button();
        btnNewTour = new com.GreenHouse.Swing.Button();
        btnDeleteTour = new com.GreenHouse.Swing.Button();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblDiemThamQuan = new com.GreenHouse.Swing.Table_1();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblPhuongTien = new com.GreenHouse.Swing.Table_1();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblKhachSan = new com.GreenHouse.Swing.Table_1();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtGiaCoBanTour = new javax.swing.JLabel();
        txtPhiThamQuan = new javax.swing.JLabel();
        txtPhiKhachSan = new javax.swing.JLabel();
        txtPhiPhuongTien = new javax.swing.JLabel();
        txtTienThanhChu = new javax.swing.JLabel();
        txtLoiNhuanMucTieu = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pic = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtGiaTour1 = new javax.swing.JLabel();
        pnlDanhSach = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtFind = new com.GreenHouse.Swing.MyTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTour = new com.GreenHouse.Swing.Table_1();
        pnlThemDiemThamQuan = new javax.swing.JPanel();
        cboMaTour = new com.GreenHouse.Swing.Combobox();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSetDiaDiem = new com.GreenHouse.Swing.Table_1();
        cboTinhThanh = new com.GreenHouse.Swing.Combobox();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiaDiemByTinh = new com.GreenHouse.Swing.Table_1();
        btnDeleteSetDiaDiem = new javax.swing.JLabel();
        btnSaveSetDiaDiem = new javax.swing.JLabel();
        pnlThemPhuongTien = new javax.swing.JPanel();
        cboLoaiPhuongTien = new com.GreenHouse.Swing.Combobox();
        jLabel43 = new javax.swing.JLabel();
        btnSaveSetPT = new javax.swing.JLabel();
        cboMaTour1 = new com.GreenHouse.Swing.Combobox();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPhuongTienByLoai = new com.GreenHouse.Swing.Table_1();
        btnDeleteSetPT = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblSetPhuongTien = new com.GreenHouse.Swing.Table_1();
        pnlThemKhachSan = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        cboTinhKhachSan = new com.GreenHouse.Swing.Combobox();
        btnSaveSetKS = new javax.swing.JLabel();
        cboMaTour2 = new com.GreenHouse.Swing.Combobox();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSetKhachSan = new com.GreenHouse.Swing.Table_1();
        btnDeleteSetKS = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhachSanByTinh = new com.GreenHouse.Swing.Table_1();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1020, 900));
        setPreferredSize(new java.awt.Dimension(1260, 900));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÍ TOUR");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 265, 35));

        tab.setMinimumSize(new java.awt.Dimension(1155, 900));

        pnlCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        pnlCapNhat.setMinimumSize(new java.awt.Dimension(1150, 900));
        pnlCapNhat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlCapNhat.add(txtMaTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 66, 300, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã Tour");
        pnlCapNhat.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 40, -1, -1));
        pnlCapNhat.add(txtTenTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 148, 300, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên Tour");
        pnlCapNhat.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 122, -1, -1));

        txtSoLuongKhach.setText("0");
        txtSoLuongKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoLuongKhachKeyTyped(evt);
            }
        });
        pnlCapNhat.add(txtSoLuongKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 230, 300, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số lượng khách");
        pnlCapNhat.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 204, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Số đêm");
        pnlCapNhat.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, -1, -1));

        txtSoDem.setText("0");
        txtSoDem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoDemKeyTyped(evt);
            }
        });
        pnlCapNhat.add(txtSoDem, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 270, -1));

        txtSoNgay.setText("0");
        txtSoNgay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoNgayKeyTyped(evt);
            }
        });
        pnlCapNhat.add(txtSoNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 270, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Số ngày");
        pnlCapNhat.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, -1));

        txtGiaTour.setEditable(false);
        txtGiaTour.setText("0");
        txtGiaTour.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiaTourKeyTyped(evt);
            }
        });
        pnlCapNhat.add(txtGiaTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 270, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Giá tour/1 khách");
        pnlCapNhat.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Khách");
        pnlCapNhat.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Ngày");
        pnlCapNhat.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Đêm");
        pnlCapNhat.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("VNĐ");
        pnlCapNhat.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 240, -1, -1));

        btnChonHinhAnh.setBackground(new java.awt.Color(0, 204, 0));
        btnChonHinhAnh.setText("Chọn Hình Ảnh");
        btnChonHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonHinhAnhMouseClicked(evt);
            }
        });
        btnChonHinhAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonHinhAnhActionPerformed(evt);
            }
        });
        pnlCapNhat.add(btnChonHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, 110, 48));

        btnUpdateTour.setBackground(new java.awt.Color(255, 255, 153));
        btnUpdateTour.setText("Cập Nhật");
        btnUpdateTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTourActionPerformed(evt);
            }
        });
        pnlCapNhat.add(btnUpdateTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 690, 113, 48));

        btnSaveTour.setBackground(new java.awt.Color(255, 255, 153));
        btnSaveTour.setText("Lưu");
        btnSaveTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTourActionPerformed(evt);
            }
        });
        pnlCapNhat.add(btnSaveTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 690, 113, 48));

        btnNewTour.setBackground(new java.awt.Color(255, 255, 153));
        btnNewTour.setText("Làm Mới");
        btnNewTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTourActionPerformed(evt);
            }
        });
        pnlCapNhat.add(btnNewTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 690, 113, 48));

        btnDeleteTour.setBackground(new java.awt.Color(255, 255, 153));
        btnDeleteTour.setText("Xóa");
        btnDeleteTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTourActionPerformed(evt);
            }
        });
        pnlCapNhat.add(btnDeleteTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 690, 113, 48));

        tblDiemThamQuan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên điểm tham quan", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblDiemThamQuan);

        pnlCapNhat.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 340, 260));

        tblPhuongTien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phương tiện", "Số hiệu", "Cước phí"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tblPhuongTien);

        pnlCapNhat.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 390, 260));

        tblKhachSan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên khách sạn", "Giá", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tblKhachSan);

        pnlCapNhat.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, 370, 260));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Giá cơ bản của tour (5) = (1) + (2) + (3) : ");
        pnlCapNhat.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Tổng phí tham quan dự kiến ( 500k / 1 điểm/1ng) (1): ");
        pnlCapNhat.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Tổng chi phí khách sạn (2): ");
        pnlCapNhat.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Tổng chi phí phương tiện (3):");
        pnlCapNhat.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        txtGiaCoBanTour.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaCoBanTour.setForeground(new java.awt.Color(102, 102, 102));
        txtGiaCoBanTour.setText("0 VND");
        pnlCapNhat.add(txtGiaCoBanTour, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, -1, -1));

        txtPhiThamQuan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtPhiThamQuan.setForeground(new java.awt.Color(102, 102, 102));
        txtPhiThamQuan.setText("0 VND");
        pnlCapNhat.add(txtPhiThamQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        txtPhiKhachSan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtPhiKhachSan.setForeground(new java.awt.Color(102, 102, 102));
        txtPhiKhachSan.setText("0 VND");
        pnlCapNhat.add(txtPhiKhachSan, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 310, -1, -1));

        txtPhiPhuongTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtPhiPhuongTien.setForeground(new java.awt.Color(102, 102, 102));
        txtPhiPhuongTien.setText("0 VND");
        pnlCapNhat.add(txtPhiPhuongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, -1, -1));

        txtTienThanhChu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTienThanhChu.setForeground(new java.awt.Color(102, 102, 102));
        txtTienThanhChu.setText("thành chữ");
        pnlCapNhat.add(txtTienThanhChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 370, -1));

        txtLoiNhuanMucTieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtLoiNhuanMucTieu.setForeground(new java.awt.Color(102, 102, 102));
        txtLoiNhuanMucTieu.setText("0 VND");
        pnlCapNhat.add(txtLoiNhuanMucTieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 340, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Giá tour = (5) + (4): ");
        pnlCapNhat.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, -1, -1));

        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/HinhAnh/Back1.jpg"))); // NOI18N
        pnlCapNhat.add(pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 320, 190));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Lợi nhuận mục tiêu (4):");
        pnlCapNhat.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, -1));

        txtGiaTour1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaTour1.setForeground(new java.awt.Color(102, 102, 102));
        txtGiaTour1.setText("0 VND");
        pnlCapNhat.add(txtGiaTour1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 370, -1, -1));

        tab.addTab("Cập nhật", pnlCapNhat);

        pnlDanhSach.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Tìm kiếm");

        txtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindKeyReleased(evt);
            }
        });

        tblTour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Tour", "Tên Tour", "Số lượng khách", "Số ngày", "Số đêm", "Giá Tour", "Hình ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTourMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTour);
        if (tblTour.getColumnModel().getColumnCount() > 0) {
            tblTour.getColumnModel().getColumn(0).setMinWidth(40);
            tblTour.getColumnModel().getColumn(0).setMaxWidth(40);
            tblTour.getColumnModel().getColumn(1).setMinWidth(150);
            tblTour.getColumnModel().getColumn(1).setMaxWidth(150);
            tblTour.getColumnModel().getColumn(3).setMinWidth(150);
            tblTour.getColumnModel().getColumn(3).setMaxWidth(150);
            tblTour.getColumnModel().getColumn(4).setMinWidth(150);
            tblTour.getColumnModel().getColumn(4).setMaxWidth(150);
            tblTour.getColumnModel().getColumn(5).setMinWidth(150);
            tblTour.getColumnModel().getColumn(5).setMaxWidth(150);
            tblTour.getColumnModel().getColumn(6).setMinWidth(150);
            tblTour.getColumnModel().getColumn(6).setMaxWidth(150);
            tblTour.getColumnModel().getColumn(7).setMinWidth(0);
            tblTour.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addGroup(pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDanhSachLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel19)
                        .addGap(27, 27, 27)
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDanhSachLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        tab.addTab("Danh sách", pnlDanhSach);

        pnlThemDiemThamQuan.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemDiemThamQuan.setPreferredSize(new java.awt.Dimension(1025, 900));

        cboMaTour.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaTourItemStateChanged(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Mã Tour");

        jScrollPane3.setPreferredSize(new java.awt.Dimension(470, 402));

        tblSetDiaDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Tour", "Tên Điểm Tham Quan", "Địa Chỉ", "MaTour", "MaDiaDiem"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblSetDiaDiem);
        if (tblSetDiaDiem.getColumnModel().getColumnCount() > 0) {
            tblSetDiaDiem.getColumnModel().getColumn(0).setMinWidth(40);
            tblSetDiaDiem.getColumnModel().getColumn(0).setMaxWidth(40);
            tblSetDiaDiem.getColumnModel().getColumn(4).setMinWidth(0);
            tblSetDiaDiem.getColumnModel().getColumn(4).setMaxWidth(0);
            tblSetDiaDiem.getColumnModel().getColumn(5).setMinWidth(0);
            tblSetDiaDiem.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        cboTinhThanh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTinhThanhItemStateChanged(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("Tỉnh thành");

        tblDiaDiemByTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Điểm Tham Quan", "Địa Chỉ", "MaDiaDiem"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDiaDiemByTinh);
        if (tblDiaDiemByTinh.getColumnModel().getColumnCount() > 0) {
            tblDiaDiemByTinh.getColumnModel().getColumn(0).setMinWidth(40);
            tblDiaDiemByTinh.getColumnModel().getColumn(0).setMaxWidth(40);
            tblDiaDiemByTinh.getColumnModel().getColumn(3).setMinWidth(0);
            tblDiaDiemByTinh.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        btnDeleteSetDiaDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-previous-48.png"))); // NOI18N
        btnDeleteSetDiaDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteSetDiaDiemMouseClicked(evt);
            }
        });

        btnSaveSetDiaDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-forward-48.png"))); // NOI18N
        btnSaveSetDiaDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveSetDiaDiemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlThemDiemThamQuanLayout = new javax.swing.GroupLayout(pnlThemDiemThamQuan);
        pnlThemDiemThamQuan.setLayout(pnlThemDiemThamQuanLayout);
        pnlThemDiemThamQuanLayout.setHorizontalGroup(
            pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemDiemThamQuanLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(cboTinhThanh, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThemDiemThamQuanLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDeleteSetDiaDiem)
                            .addComponent(btnSaveSetDiaDiem))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlThemDiemThamQuanLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(426, 426, 426))
                    .addGroup(pnlThemDiemThamQuanLayout.createSequentialGroup()
                        .addGroup(pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboMaTour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlThemDiemThamQuanLayout.setVerticalGroup(
            pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemDiemThamQuanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMaTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTinhThanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThemDiemThamQuanLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThemDiemThamQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(pnlThemDiemThamQuanLayout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(btnDeleteSetDiaDiem)
                        .addGap(12, 12, 12)
                        .addComponent(btnSaveSetDiaDiem)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tab.addTab("Thêm điểm tham quan", pnlThemDiemThamQuan);

        pnlThemPhuongTien.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemPhuongTien.setPreferredSize(new java.awt.Dimension(927, 900));
        pnlThemPhuongTien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboLoaiPhuongTien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiPhuongTienItemStateChanged(evt);
            }
        });
        pnlThemPhuongTien.add(cboLoaiPhuongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 300, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setText("Loại Phương Tiện");
        pnlThemPhuongTien.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btnSaveSetPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-forward-48.png"))); // NOI18N
        btnSaveSetPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveSetPTMouseClicked(evt);
            }
        });
        pnlThemPhuongTien.add(btnSaveSetPT, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, -1, -1));

        cboMaTour1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaTour1ItemStateChanged(evt);
            }
        });
        pnlThemPhuongTien.add(cboMaTour1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 390, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setText("Mã Tour");
        pnlThemPhuongTien.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));

        tblPhuongTienByLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Phương Tiện", "Số Hiệu", "Cước Phí", "MaPhuongTien"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblPhuongTienByLoai);
        if (tblPhuongTienByLoai.getColumnModel().getColumnCount() > 0) {
            tblPhuongTienByLoai.getColumnModel().getColumn(0).setMinWidth(40);
            tblPhuongTienByLoai.getColumnModel().getColumn(0).setMaxWidth(40);
            tblPhuongTienByLoai.getColumnModel().getColumn(4).setMinWidth(0);
            tblPhuongTienByLoai.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        pnlThemPhuongTien.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 430, 710));

        btnDeleteSetPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-previous-48.png"))); // NOI18N
        btnDeleteSetPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteSetPTMouseClicked(evt);
            }
        });
        pnlThemPhuongTien.add(btnDeleteSetPT, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, -1, -1));

        tblSetPhuongTien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Tour", "Tên Phương Tiện", "Số Hiệu", "Cước Phí", "MaTour", "MaPhuongTien"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tblSetPhuongTien);
        if (tblSetPhuongTien.getColumnModel().getColumnCount() > 0) {
            tblSetPhuongTien.getColumnModel().getColumn(0).setMinWidth(40);
            tblSetPhuongTien.getColumnModel().getColumn(0).setMaxWidth(40);
            tblSetPhuongTien.getColumnModel().getColumn(5).setMinWidth(0);
            tblSetPhuongTien.getColumnModel().getColumn(5).setMaxWidth(0);
            tblSetPhuongTien.getColumnModel().getColumn(6).setMinWidth(0);
            tblSetPhuongTien.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        pnlThemPhuongTien.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 110, 490, 710));

        tab.addTab("Thêm phương tiện", pnlThemPhuongTien);

        pnlThemKhachSan.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemKhachSan.setPreferredSize(new java.awt.Dimension(1003, 900));
        pnlThemKhachSan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Tỉnh thành");
        pnlThemKhachSan.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        cboTinhKhachSan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTinhKhachSanItemStateChanged(evt);
            }
        });
        pnlThemKhachSan.add(cboTinhKhachSan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 290, -1));

        btnSaveSetKS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-forward-48.png"))); // NOI18N
        btnSaveSetKS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveSetKSMouseClicked(evt);
            }
        });
        pnlThemKhachSan.add(btnSaveSetKS, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, -1, -1));

        cboMaTour2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaTour2ItemStateChanged(evt);
            }
        });
        pnlThemKhachSan.add(cboMaTour2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 500, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Mã Tour");
        pnlThemKhachSan.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

        tblSetKhachSan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Tour", "Tên Khách Sạn", "Giá", "Địa Chỉ", "MaTour", "MaKhachSan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblSetKhachSan);
        if (tblSetKhachSan.getColumnModel().getColumnCount() > 0) {
            tblSetKhachSan.getColumnModel().getColumn(4).setMinWidth(0);
            tblSetKhachSan.getColumnModel().getColumn(4).setMaxWidth(0);
            tblSetKhachSan.getColumnModel().getColumn(5).setMinWidth(0);
            tblSetKhachSan.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        pnlThemKhachSan.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 500, 700));

        btnDeleteSetKS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GreenHouse/Icon/icons8-previous-48.png"))); // NOI18N
        btnDeleteSetKS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteSetKSMouseClicked(evt);
            }
        });
        pnlThemKhachSan.add(btnDeleteSetKS, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, -1, -1));

        tblKhachSanByTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên khách sạn", "Giá", "Số điện thoại", "Địa chỉ", "MaKhachSan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblKhachSanByTinh);
        if (tblKhachSanByTinh.getColumnModel().getColumnCount() > 0) {
            tblKhachSanByTinh.getColumnModel().getColumn(4).setMinWidth(0);
            tblKhachSanByTinh.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        pnlThemKhachSan.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 430, 700));

        tab.addTab("Thêm khách sạn", pnlThemKhachSan);

        add(tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1170, 910));
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteSetKSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteSetKSMouseClicked
        int index = 0;
        if (tblKhachSanByTinh.getSelectedRow() != -1) {
            index = tblKhachSanByTinh.getSelectedRow();
        }
        try {
            deleteSetKhachSan(index);
            fillTableSetKhachSan();
            updateGiaTour();
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnDeleteSetKSMouseClicked

    private void cboMaTour2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaTour2ItemStateChanged
        if (cboMaTour2.getSelectedItem() != null) {
            try {
                fillTableSetKhachSan();
            } catch (SQLException ex) {
                Logger.getLogger(Tour.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cboMaTour2ItemStateChanged

    private void btnSaveSetKSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveSetKSMouseClicked
        int index = 0;
        if (tblKhachSanByTinh.getSelectedRow() != -1) {
            index = tblKhachSanByTinh.getSelectedRow();
        }
        if (cboMaTour2.getSelectedIndex() > 0) {
            try {
                insertSetKhachSan(index);
                fillTableSetKhachSan();
                updateGiaTour();
            } catch (SQLException ex) {
                Logger.getLogger(Tour.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn tour trước khi thêm địa điểm");
        }


    }//GEN-LAST:event_btnSaveSetKSMouseClicked

    private void cboTinhKhachSanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTinhKhachSanItemStateChanged
        try {
            if (cboTinhKhachSan.getSelectedItem() != null) {
                fillTblKhachSanByTinh();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboTinhKhachSanItemStateChanged

    private void btnDeleteSetPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteSetPTMouseClicked
        int index = 0;
        if (tblPhuongTienByLoai.getSelectedRow() != -1) {
            index = tblPhuongTienByLoai.getSelectedRow();
        }
        try {
            deleteSetPhuongTien(index);
            fillTableSetPhuongTien();
            updateGiaTour();
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteSetPTMouseClicked

    private void cboMaTour1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaTour1ItemStateChanged
        if (cboMaTour1.getSelectedItem() != null) {
            try {
                fillTableSetPhuongTien();
            } catch (SQLException ex) {
                Logger.getLogger(Tour.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cboMaTour1ItemStateChanged

    private void btnSaveSetPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveSetPTMouseClicked
        int index = 0;
        if (tblPhuongTienByLoai.getSelectedRow() != -1) {
            index = tblPhuongTienByLoai.getSelectedRow();
        }
        if (cboMaTour1.getSelectedIndex() > 0) {
            try {
                insertSetPhuongTien(index);
                fillTableSetPhuongTien();
                updateGiaTour();
            } catch (SQLException ex) {
                Logger.getLogger(Tour.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn tour trước khi thêm địa điểm");
        }

    }//GEN-LAST:event_btnSaveSetPTMouseClicked

    private void cboLoaiPhuongTienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiPhuongTienItemStateChanged
        try {
            fillTablePhuongTienByLoai();
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboLoaiPhuongTienItemStateChanged

    private void btnSaveSetDiaDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveSetDiaDiemMouseClicked
        int index = 0;
        if (tblDiaDiemByTinh.getSelectedRow() != -1) {
            index = tblDiaDiemByTinh.getSelectedRow();
        }
        if (cboMaTour.getSelectedIndex() > 0) {
            try {
                insertSetDiaDiem(index);
                fillTableSetDiaDiem();
                updateGiaTour();
            } catch (SQLException ex) {
                Logger.getLogger(Tour.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn tour trước khi thêm địa điểm");
        }

    }//GEN-LAST:event_btnSaveSetDiaDiemMouseClicked

    private void btnDeleteSetDiaDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteSetDiaDiemMouseClicked
        int index = 0;
        if (tblSetDiaDiem.getSelectedRow() != -1) {
            index = tblSetDiaDiem.getSelectedRow();
        }
        try {
            deleteSetDiaDiem(index);
            fillTableSetDiaDiem();
            updateGiaTour();
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteSetDiaDiemMouseClicked

    private void cboTinhThanhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTinhThanhItemStateChanged
        try {

            if (cboTinhThanh.getSelectedItem() != null) {
                fillTableDiaDiemByTinh();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboTinhThanhItemStateChanged

    private void cboMaTourItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaTourItemStateChanged
        try {
            fillTableSetDiaDiem();
        } catch (SQLException ex) {
            Logger.getLogger(Tour.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboMaTourItemStateChanged

    private void tblTourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTourMouseClicked
        if (evt.getClickCount() == 2) {
            showDataTour(tblTour.getSelectedRow());
            tab.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblTourMouseClicked

    private void txtFindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyReleased
        if (txtFind.getText().trim().isEmpty()) {
            try {
                fillTableTour();
            } catch (SQLException ex) {
                Logger.getLogger(Tour.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                findFillTableByAny(txtFind.getText().trim());
            } catch (SQLException ex) {
                Logger.getLogger(Tour.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtFindKeyReleased

    private void btnDeleteTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTourActionPerformed
        if (!txtMaTour.getText().equals("")) {
            try {
                if (tourDAO.checkDuplicateTour(txtMaTour.getText().trim())) {
                    if (!tourDAO.checkLTTour(txtMaTour.getText().trim())) {// Ko co lich trinh thi delete
                        tourDAO.deleteTour(txtMaTour.getText().trim());
                        clearFormTour();
                        fillTableTour();
                        JOptionPane.showMessageDialog(null, "Xoa thanh cong");
                    } else {
                        JOptionPane.showMessageDialog(null, "Tour này đã có lịch trình nên không thể xóa");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Khong ton tai tour co ma tour: " + txtMaTour.getText().trim());
                }
            } catch (SQLException ex) {
                Logger.getLogger(Tour.class
                        .getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Tour này đã có lịch trình nên không thể xóa");
            }
        }
    }//GEN-LAST:event_btnDeleteTourActionPerformed

    private void btnNewTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewTourActionPerformed
        clearFormTour();
    }//GEN-LAST:event_btnNewTourActionPerformed

    private void btnSaveTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTourActionPerformed
        if (validateFormTour()) {
            int soNgay = Integer.parseInt(txtSoNgay.getText().trim());
            int soDem = Integer.parseInt(txtSoDem.getText().trim());
            int mt = soNgay - soDem;
            if (mt <= 1 && mt >= -1) {
                try {
                    insertTour();
                    fillTableTour();
                    fillCboMaTour();
                } catch (SQLException ex) {
                    Logger.getLogger(Tour.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Số ngày và số đêm không được lệch nhau quá 1 ngày");
            }

        }
    }//GEN-LAST:event_btnSaveTourActionPerformed

    private void btnUpdateTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTourActionPerformed
        if (validateFormTour()) {
            int soNgay = Integer.parseInt(txtSoNgay.getText().trim());
            int soDem = Integer.parseInt(txtSoDem.getText().trim());
            int mt = soNgay - soDem;
            if (mt <= 1 && mt >= -1) {
                try {
                    updateTour();
                    //tab.setSelectedIndex(1);
                    //  tblTour.setRowSelectionInterval(indexTbl, indexTbl);
                } catch (SQLException ex) {
                    Logger.getLogger(Tour.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Số ngày và số đêm không được lệch nhau quá 1 ngày");
            }

        }
    }//GEN-LAST:event_btnUpdateTourActionPerformed

    private void btnChonHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonHinhAnhMouseClicked
        chonHinhAnh();
    }//GEN-LAST:event_btnChonHinhAnhMouseClicked

    private void txtGiaTourKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaTourKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || KeyEvent.VK_DELETE == evt.getKeyCode() || KeyEvent.VK_BACK_SPACE == evt.getKeyCode()) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGiaTourKeyTyped

    private void txtSoNgayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoNgayKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || KeyEvent.VK_DELETE == evt.getKeyCode() || KeyEvent.VK_BACK_SPACE == evt.getKeyCode()) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoNgayKeyTyped

    private void txtSoDemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDemKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || KeyEvent.VK_DELETE == evt.getKeyCode() || KeyEvent.VK_BACK_SPACE == evt.getKeyCode()) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoDemKeyTyped

    private void txtSoLuongKhachKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKhachKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || KeyEvent.VK_DELETE == evt.getKeyCode() || KeyEvent.VK_BACK_SPACE == evt.getKeyCode()) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoLuongKhachKeyTyped

    private void btnChonHinhAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonHinhAnhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChonHinhAnhActionPerformed

    private void initQuyenTruyCap(String quyen) {
        if (quyen.equalsIgnoreCase("Nhân viên trực quầy") || quyen.equalsIgnoreCase("Nhân viên hướng dẫn")) {
            tab.remove(4);
            tab.remove(3);
            tab.remove(2);
            btnSaveTour.setVisible(false);
            btnUpdateTour.setVisible(false);
            btnNewTour.setVisible(false);
            btnDeleteTour.setVisible(false);
            btnChonHinhAnh.setVisible(false);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.GreenHouse.Swing.ButtonOutLine btnChonHinhAnh;
    private javax.swing.JLabel btnDeleteSetDiaDiem;
    private javax.swing.JLabel btnDeleteSetKS;
    private javax.swing.JLabel btnDeleteSetPT;
    private com.GreenHouse.Swing.Button btnDeleteTour;
    private com.GreenHouse.Swing.Button btnNewTour;
    private javax.swing.JLabel btnSaveSetDiaDiem;
    private javax.swing.JLabel btnSaveSetKS;
    private javax.swing.JLabel btnSaveSetPT;
    private com.GreenHouse.Swing.Button btnSaveTour;
    private com.GreenHouse.Swing.Button btnUpdateTour;
    private com.GreenHouse.Swing.Combobox cboLoaiPhuongTien;
    private com.GreenHouse.Swing.Combobox cboMaTour;
    private com.GreenHouse.Swing.Combobox cboMaTour1;
    private com.GreenHouse.Swing.Combobox cboMaTour2;
    private com.GreenHouse.Swing.Combobox cboTinhKhachSan;
    private com.GreenHouse.Swing.Combobox cboTinhThanh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel pic;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlThemDiemThamQuan;
    private javax.swing.JPanel pnlThemKhachSan;
    private javax.swing.JPanel pnlThemPhuongTien;
    private com.GreenHouse.Swing.MaterialTabbed tab;
    private com.GreenHouse.Swing.Table_1 tblDiaDiemByTinh;
    private com.GreenHouse.Swing.Table_1 tblDiemThamQuan;
    private com.GreenHouse.Swing.Table_1 tblKhachSan;
    private com.GreenHouse.Swing.Table_1 tblKhachSanByTinh;
    private com.GreenHouse.Swing.Table_1 tblPhuongTien;
    private com.GreenHouse.Swing.Table_1 tblPhuongTienByLoai;
    private com.GreenHouse.Swing.Table_1 tblSetDiaDiem;
    private com.GreenHouse.Swing.Table_1 tblSetKhachSan;
    private com.GreenHouse.Swing.Table_1 tblSetPhuongTien;
    private com.GreenHouse.Swing.Table_1 tblTour;
    private com.GreenHouse.Swing.MyTextField txtFind;
    private javax.swing.JLabel txtGiaCoBanTour;
    private com.GreenHouse.Swing.MyTextField txtGiaTour;
    private javax.swing.JLabel txtGiaTour1;
    private javax.swing.JLabel txtLoiNhuanMucTieu;
    private com.GreenHouse.Swing.MyTextField txtMaTour;
    private javax.swing.JLabel txtPhiKhachSan;
    private javax.swing.JLabel txtPhiPhuongTien;
    private javax.swing.JLabel txtPhiThamQuan;
    private com.GreenHouse.Swing.MyTextField txtSoDem;
    private com.GreenHouse.Swing.MyTextField txtSoLuongKhach;
    private com.GreenHouse.Swing.MyTextField txtSoNgay;
    private com.GreenHouse.Swing.MyTextField txtTenTour;
    private javax.swing.JLabel txtTienThanhChu;
    // End of variables declaration//GEN-END:variables
}
