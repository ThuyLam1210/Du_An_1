package com.GreenHouse.Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Thuy
 */
public class TaiKhoan {

    private int ID;
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;
    private String Email;
    private String maNhanVien;

    public TaiKhoan() {
    }

    public int getID() {
        return ID;
    }

    public TaiKhoan(String Email) {
        this.Email = Email;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public TaiKhoan(int ID) {
        this.ID = ID;
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String Email, String vaiTro, String maNhanVien) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;

        this.Email = Email;
        this.vaiTro = vaiTro;
        this.maNhanVien = maNhanVien;
    }

    public TaiKhoan(int ID, String tenDangNhap, String matKhau, String Email, String vaiTro, String maNhanVien) {
        this.ID = ID;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.Email = Email;
        this.maNhanVien = maNhanVien;
    }

  
    public String getTenDangNhap() {
        return tenDangNhap;
    }
    
    public TaiKhoan(String matKhau, String Email) {
        this.matKhau = matKhau;
        this.Email = Email;
    }

    

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

}
