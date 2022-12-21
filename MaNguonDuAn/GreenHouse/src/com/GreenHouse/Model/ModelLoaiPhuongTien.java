/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Model;

/**
 *
 * @author Van Anh
 */
public class ModelLoaiPhuongTien {
    String maLoaiPT, tenLoaiPhuongTien;

    public ModelLoaiPhuongTien() {
    }

    public ModelLoaiPhuongTien(String maLoaiPT, String tenLoaiPhuongTien) {
        this.maLoaiPT = maLoaiPT;
        this.tenLoaiPhuongTien = tenLoaiPhuongTien;
    }

    public String getMaLoaiPT() {
        return maLoaiPT;
    }

    public void setMaLoaiPT(String maLoaiPT) {
        this.maLoaiPT = maLoaiPT;
    }

    public String getTenLoaiPhuongTien() {
        return tenLoaiPhuongTien;
    }

    public void setTenLoaiPhuongTien(String tenLoaiPhuongTien) {
        this.tenLoaiPhuongTien = tenLoaiPhuongTien;
    }
    
    
    
}
