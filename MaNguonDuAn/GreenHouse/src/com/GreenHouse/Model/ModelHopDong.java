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
public class ModelHopDong {

    String maHD, maVe, noiDungHD;
    int soLuongKhach;
    Date ngayLap;

    public ModelHopDong() {
    }

    public ModelHopDong(String maHD, String maVe, Date ngayLap, int soLuongKhach, String noiDungHD) {
        this.maHD = maHD;
        this.maVe = maVe;
        this.noiDungHD = noiDungHD;
        this.soLuongKhach = soLuongKhach;
        this.ngayLap = ngayLap;
    }

    public ModelHopDong(String maHD) {
        this.maHD = maHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getNoiDungHD() {
        return noiDungHD;
    }

    public void setNoiDungHD(String noiDungHD) {
        this.noiDungHD = noiDungHD;
    }

    public int getSoLuongKhach() {
        return soLuongKhach;
    }

    public void setSoLuongKhach(int soLuongKhach) {
        this.soLuongKhach = soLuongKhach;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

}
