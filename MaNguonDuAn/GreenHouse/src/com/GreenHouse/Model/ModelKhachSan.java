package com.GreenHouse.Model;

public class ModelKhachSan {

    String maKhachSan, tenKhachSan, sdt, diaChi, moTa, tinh, maTour, hang;
    float giaKhachSan;

    public ModelKhachSan() {
    }

    public ModelKhachSan(String maKhachSan) {
        this.maKhachSan = maKhachSan;
    }

    public ModelKhachSan(String maKhachSan, String tenKhachSan, float giaKhachSan, String hang, String sdt, String diaChi,  String tinh,String moTa) {
        this.maKhachSan = maKhachSan;
        this.tenKhachSan = tenKhachSan;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.moTa = moTa;
        this.tinh = tinh;
      
        this.hang = hang;
        this.giaKhachSan = giaKhachSan;
    }

   
    public String getMaTour() {
        return maTour;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public ModelKhachSan(String maKhachSan, String tenKhachSan, float giaKhachSan, String sdt, String diaChi, String tinh, String moTa) {
        this.maKhachSan = maKhachSan;
        this.tenKhachSan = tenKhachSan;
        this.giaKhachSan = giaKhachSan;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.moTa = moTa;
        this.tinh = tinh;

    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(String maKhachSan) {
        this.maKhachSan = maKhachSan;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

    public float getGiaKhachSan() {
        return giaKhachSan;
    }

    public void setGiaKhachSan(float giaKhachSan) {
        this.giaKhachSan = giaKhachSan;
    }

}
