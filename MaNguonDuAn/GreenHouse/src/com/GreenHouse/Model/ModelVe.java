/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ModelVe {

    String maVe, maTour, maNhanVien, maKhachHang, maLichTrinh;
    int soLuongNguoiLon, soLuongTreEm;
    Date ngayBan;
    double tongTien;

    public ModelVe() {
    }

    public ModelVe(String maVe, String maTour, String maNhanVien, String maKhachHang, String maLichTrinh, int soLuongNguoiLon, int soLuongTreEm, Date ngayBan, double tongTien) {
        this.maVe = maVe;
        this.maTour = maTour;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.maLichTrinh = maLichTrinh;
        this.soLuongNguoiLon = soLuongNguoiLon;
        this.soLuongTreEm = soLuongTreEm;
        this.ngayBan = ngayBan;
        this.tongTien = tongTien;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaLichTrinh() {
        return maLichTrinh;
    }

    public void setMaLichTrinh(String maLichTrin) {
        this.maLichTrinh = maLichTrin;
    }

    public int getSoLuongNguoiLon() {
        return soLuongNguoiLon;
    }

    public void setSoLuongNguoiLon(int soLuongNguoiLon) {
        this.soLuongNguoiLon = soLuongNguoiLon;
    }

    public int getSoLuongTreEm() {
        return soLuongTreEm;
    }

    public void setSoLuongTreEm(int soLuongTreEm) {
        this.soLuongTreEm = soLuongTreEm;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

}
