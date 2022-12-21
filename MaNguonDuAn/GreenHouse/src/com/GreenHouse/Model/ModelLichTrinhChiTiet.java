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
public class ModelLichTrinhChiTiet {

    private int id;
    private String maTour, maLt, noiThamQuan, GhiChu;
    private Date tgDiChuyen;

    public ModelLichTrinhChiTiet() {
    }

    public ModelLichTrinhChiTiet(int id) {
        this.id = id;
    }

    public ModelLichTrinhChiTiet( String maLt,String maTour, Date tgDiChuyen, String noiThamQuan, String GhiChu) {
        this.maTour = maTour;
        this.maLt = maLt;
        this.noiThamQuan = noiThamQuan;
        this.GhiChu = GhiChu;
        this.tgDiChuyen = tgDiChuyen;
    }
    
    
    public ModelLichTrinhChiTiet(int id, String maTour, String maLt, String noiThamQuan, String GhiChu, Date tgDiChuyen) {
        this.id = id;
        this.maTour = maTour;
        this.maLt = maLt;
        this.noiThamQuan = noiThamQuan;
        this.GhiChu = GhiChu;
        this.tgDiChuyen = tgDiChuyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getMaLt() {
        return maLt;
    }

    public void setMaLt(String maLt) {
        this.maLt = maLt;
    }

    public String getNoiThamQuan() {
        return noiThamQuan;
    }

    public void setNoiThamQuan(String noiThamQuan) {
        this.noiThamQuan = noiThamQuan;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Date getTgDiChuyen() {
        return tgDiChuyen;
    }

    public void setTgDiChuyen(Date tgDiChuyen) {
        this.tgDiChuyen = tgDiChuyen;
    }
    
}
