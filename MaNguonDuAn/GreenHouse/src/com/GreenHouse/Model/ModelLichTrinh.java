/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Model;

import java.util.Date;

/**
 *
 * @author Thuy
 */
public class ModelLichTrinh {

    String maLT, NoiXuatPhat, NoiDen;
    String trangThai;
    Date ngayBatDau;
    Date ngayKetThuc;

    public ModelLichTrinh() {
    }

    public ModelLichTrinh(String maLT) {
        this.maLT = maLT;
    }
    
    public ModelLichTrinh(String maLT, Date ngayBatDau, Date ngayKetThuc, String NoiXuatPhat, String NoiDen, String trangThai) {
        this.maLT = maLT;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.NoiXuatPhat = NoiXuatPhat;
        this.NoiDen = NoiDen;
        this.trangThai = trangThai;
    }

    public String getMaLT() {
        return maLT;
    }

    public void setMaLT(String maLT) {
        this.maLT = maLT;
    }

    public String getNoiXuatPhat() {
        return NoiXuatPhat;
    }

    public void setNoiXuatPhat(String NoiXuatPhat) {
        this.NoiXuatPhat = NoiXuatPhat;
    }

    public String getNoiDen() {
        return NoiDen;
    }

    public void setNoiDen(String NoiDen) {
        this.NoiDen = NoiDen;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

}
