/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Swing;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Thuy
 */
public class ChuChay extends Thread implements Runnable{
    public JLabel lbl_chuChay;
    
     public ChuChay(JLabel lbl_chuChay) {
        this.lbl_chuChay = lbl_chuChay;
    }
      @Override
    public void run() {
      //  Thread th = new Thread(); // 24h
      String txt = lbl_chuChay.getText() +" ";
        while (true) {
              
           txt = txt.charAt(txt.length()-1)+txt.substring(0,txt.length()-1);
            try {
           
                Thread.sleep(500);
            } catch (Exception e) {
                break;
            }
            lbl_chuChay.setText(txt);
        }
    }
}
