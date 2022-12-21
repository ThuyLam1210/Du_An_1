
package com.GreenHouse.Model;

import java.io.Serializable;

/**
 *
 * @author Thuy
 */
public class ModelDoanhThuTheoNam implements Serializable{
    private int nam;
    private double tongTien;

    public ModelDoanhThuTheoNam(int nam, double tongTien) {
        this.nam = nam;
        this.tongTien = tongTien;
    }

    public ModelDoanhThuTheoNam() {
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
    
    
}
