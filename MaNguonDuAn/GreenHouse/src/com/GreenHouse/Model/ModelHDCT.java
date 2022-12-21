/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Model;

/**
 *
 * @author Thuy
 */
public class ModelHDCT {
    int id ;
    String maHD;
    String tenKhachHang;
    String cmnd;
    String sdt;

    public ModelHDCT() {
    }

    public ModelHDCT(int id, String maHD, String tenKhachHang, String cmnd, String sdt) {
        this.id = id;
        this.maHD = maHD;
        this.tenKhachHang = tenKhachHang;
        this.cmnd = cmnd;
        this.sdt = sdt;
    }

    public ModelHDCT(String maHD, String tenKhachHang, String cmnd, String sdt) {
        this.maHD = maHD;
        this.tenKhachHang = tenKhachHang;
        this.cmnd = cmnd;
        this.sdt = sdt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
   
}
