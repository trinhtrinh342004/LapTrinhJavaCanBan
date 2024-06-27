/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau2_sieuthiabc;

/**
 *
 * @author Trinh
 */
public class DienMay extends HangHoa {
    private String tt_thuonghieu;
    private String loaimay;
    private int tgbh;

    public String getTt_thuonghieu() {
        return tt_thuonghieu;
    }

    public void setTt_thuonghieu(String tt_thuonghieu) {
        this.tt_thuonghieu = tt_thuonghieu;
    }

    public String getLoaimay() {
        return loaimay;
    }

    public void setLoaimay(String loaimay) {
        this.loaimay = loaimay;
    }

    public int getTgbh() {
        return tgbh;
    }

    public void setTgbh(int tgbh) {
        this.tgbh = tgbh;
    }
    
    
    public DienMay(){
        super.loaiHH = 1;
    }
    
    public DienMay(String t, String l, int tg){
        this.tt_thuonghieu = t;
        this.loaimay = l;
        this.tgbh = tg;
    }
    
    
    
    @Override
    public void xuat(){
        
    }
}
