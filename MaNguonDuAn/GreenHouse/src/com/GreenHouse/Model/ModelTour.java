
package com.GreenHouse.Model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class ModelTour implements Serializable{
    
    String maTour, tenTour, hinhAnh;
    int soLuongKhach, soNgay, soDem;
    float giaTour;

    public ModelTour(String maTour, String tenTour, int soLuongKhach, int soNgay, int soDem, float giaTour, String hinhAnh) {
        this.maTour = maTour;
        this.tenTour = tenTour;
        this.hinhAnh = hinhAnh;
        this.soLuongKhach = soLuongKhach;
        this.soNgay = soNgay;
        this.soDem = soDem;
        this.giaTour = giaTour;
    }

    public ModelTour(String tenTour) {
        this.tenTour = tenTour;
    }
    
    public ModelTour() {
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSoLuongKhach() {
        return soLuongKhach;
    }

    public void setSoLuongKhach(int soLuongKhach) {
        this.soLuongKhach = soLuongKhach;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }

    public int getSoDem() {
        return soDem;
    }

    public void setSoDem(int soDem) {
        this.soDem = soDem;
    }

    public float getGiaTour() {
        return giaTour;
    }

    public void setGiaTour(float giaTour) {
        this.giaTour = giaTour;
    }
    
    @Override
    public String toString(){
        return this.getTenTour();
    }
}
