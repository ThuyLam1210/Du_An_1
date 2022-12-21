
package com.GreenHouse.Model;


public class ModelThemDTQ {
    String maTour, tenTour, maDiemThamQuan, tenDiemThamQuan, diaChi;

    public ModelThemDTQ(String maTour, String maDiemThamQuan) {
        this.maTour = maTour;
        this.maDiemThamQuan = maDiemThamQuan;
    }

    public ModelThemDTQ() {
    }

    public ModelThemDTQ(String maTour, String tenTour, String maDiemThamQuan, String tenDiemThamQuan, String diaChi) {
        this.maTour = maTour;
        this.tenTour = tenTour;
        this.maDiemThamQuan = maDiemThamQuan;
        this.tenDiemThamQuan = tenDiemThamQuan;
        this.diaChi = diaChi;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public String getMaDiemThamQuan() {
        return maDiemThamQuan;
    }

    public void setMaDiemThamQuan(String maDiemThamQuan) {
        this.maDiemThamQuan = maDiemThamQuan;
    }

    public String getTenDiemThamQuan() {
        return tenDiemThamQuan;
    }

    public void setTenDiemThamQuan(String tenDiemThamQuan) {
        this.tenDiemThamQuan = tenDiemThamQuan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
}
