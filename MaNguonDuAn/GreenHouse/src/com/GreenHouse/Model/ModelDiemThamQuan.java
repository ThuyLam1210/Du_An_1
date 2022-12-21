package com.GreenHouse.Model;

public class ModelDiemThamQuan {

    String maDiaDiem, tenDiaDiem, diaChi, tinh, moTa, hinhAnh;

    public ModelDiemThamQuan() {
    }

    public ModelDiemThamQuan(String id) {
        this.maDiaDiem = id;
    }

    public ModelDiemThamQuan(String maDiaDiem, String tenDiaDiem, String diaChi, String tinh, String moTa, String hinhAnh) {
        this.maDiaDiem = maDiaDiem;
        this.tenDiaDiem = tenDiaDiem;
        this.diaChi = diaChi;
        this.tinh = tinh;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getMaDiaDiem() {
        return maDiaDiem;
    }

    public void setMaDiaDiem(String maDiaDiem) {
        this.maDiaDiem = maDiaDiem;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

}
