/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Model;

/**
 *
 * @author Thuy
 */
public class LoaiPhuongTien {
    String ma;
    String tenpt;

    public LoaiPhuongTien() {
    }

    public LoaiPhuongTien(String ma, String tenpt) {
        this.ma = ma;
        this.tenpt = tenpt;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenpt() {
        return tenpt;
    }

    public void setTenpt(String tenpt) {
        this.tenpt = tenpt;
    }
    
    
}
