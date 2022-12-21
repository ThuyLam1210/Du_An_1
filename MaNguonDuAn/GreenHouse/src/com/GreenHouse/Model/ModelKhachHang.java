/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Model;

/**
 *
 * @author maiva
 */
public class ModelKhachHang {
    private String id,name, cccd,numberPhone, email, adress;
    private boolean sex;

    public ModelKhachHang() {
    }

    public ModelKhachHang(String id) {
        this.id = id;
    }
     
    public ModelKhachHang(String id, String name, boolean sex, String cccd, String numberPhone, String email, String adress) {
        this.id = id;
        this.name = name;
        this.cccd = cccd;
        this.numberPhone = numberPhone;
        this.email = email;
        this.adress = adress;
        this.sex = sex;
    }

 

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCccd() {
        return cccd;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }

    public boolean isSex() {
        return sex;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
    
}
