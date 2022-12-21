/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Model;

/**
 *
 * @author ADMIN
 */
public class ModelPhuongTien {
    String maPhuongTien, maLoaiPhuongTien, tenPhuongTien, bienSo, ghiChu;
    float cuocPhi;
    int soLuongChua;

    public ModelPhuongTien() {
    }

    public ModelPhuongTien(String maPhuongTien, String maLoaiPhuongTien, String tenPhuongTien, String bienSo, String ghiChu, int soLuongChua, float cuocPhi) {
        this.maPhuongTien = maPhuongTien;
        this.maLoaiPhuongTien = maLoaiPhuongTien;
        this.tenPhuongTien = tenPhuongTien;
        this.bienSo = bienSo;
        this.ghiChu = ghiChu;
        this.cuocPhi = cuocPhi;
        this.soLuongChua = soLuongChua;
    }

    public ModelPhuongTien(String maPhuongTien) {
        this.maPhuongTien = maPhuongTien;
    }

    public String getMaPhuongTien() {
        return maPhuongTien;
    }

    public void setMaPhuongTien(String maPhuongTien) {
        this.maPhuongTien = maPhuongTien;
    }

    public String getMaLoaiPhuongTien() {
        return maLoaiPhuongTien;
    }

    public void setMaLoaiPhuongTien(String maLoaiPhuongTien) {
        this.maLoaiPhuongTien = maLoaiPhuongTien;
    }

    public String getTenPhuongTien() {
        return tenPhuongTien;
    }

    public void setTenPhuongTien(String tenPhuongTien) {
        this.tenPhuongTien = tenPhuongTien;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public float getCuocPhi() {
        return cuocPhi;
    }

    public void setCuocPhi(float cuocPhi) {
        this.cuocPhi = cuocPhi;
    }

    public int getSoLuongChua() {
        return soLuongChua;
    }

    public void setSoLuongChua(int soLuongChua) {
        this.soLuongChua = soLuongChua;
    }
}
