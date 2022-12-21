/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Model;

/**
 *
 * @author Thuy
 */
public class ModelNhanVien {
    
    String maNV,maCV,TenNV,CMND,SDT,email,diaChi,hinhAnh;
    
    boolean gtinh,trangthai;

    public ModelNhanVien() {
    }

    public ModelNhanVien(String maNV, String TenNV) {
        this.maNV = maNV;
        this.TenNV = TenNV;
    }
    
    public ModelNhanVien(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getMaNV() {
        return maNV;
    }

    
    
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaCV() {
        return maCV;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isGtinh() {
        return gtinh;
    }

    public void setGtinh(boolean gtinh) {
        this.gtinh = gtinh;
    }

    public boolean isTrangthai() {
        return trangthai;
    }
    
    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
    
    public ModelNhanVien(String maNV, String maCV, String TenNV, boolean gtinh, boolean trangthai,String CMND, String SDT, String email, String diaChi, String hinhAnh) {
        this.maNV = maNV;
        this.maCV = maCV;
        this.TenNV = TenNV;
        this.CMND = CMND;
        this.SDT = SDT;
        this.email = email;
        this.diaChi = diaChi;
        this.hinhAnh = hinhAnh;
        this.gtinh = gtinh;
        this.trangthai = trangthai;
    }
    @Override
    public boolean equals(Object obj) {
        ModelNhanVien other = (ModelNhanVien) obj;
        return other.getMaNV().equals(this.getMaNV());
    }
}
