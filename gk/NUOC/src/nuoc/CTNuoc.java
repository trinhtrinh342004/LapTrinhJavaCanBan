/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nuoc;

import java.util.ArrayList;

/**
 *
 * @author Trinh
 */
public abstract class CTNuoc {
    protected String maKH;
    protected String hoTen;
    protected double dongia;
    protected ArrayList<Integer> lntt;
    
    public CTNuoc(){
        lntt = new ArrayList<>();
    }
    
    public CTNuoc(String m, String h, double d){
        this.maKH = m;
        this.hoTen = h;
        this.dongia = d;
        lntt = new ArrayList<>();
    }
    
    public void ghiNhanLNTT(int thang,int ln){
        this.lntt.add(ln);
    } 
    
    public abstract double tinhTienThang(int thang);  
    
    
}
