/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau2_sieuthiabc;

/**
 *
 * @author Trinh
 */
public abstract class HangHoa {
    protected String maHH;
    protected String tenHH;
    protected int slton;
    protected double dg;
    protected int loaiHH;
    
    public HangHoa(){
        this.maHH = null;
        this.tenHH = null;
        this.slton = 0;
        this.dg = 0;
        this.loaiHH = 0;
    }
    
    public HangHoa(String m, String t, int s, double d, int l){
        this.maHH = m;
        this.tenHH = t;
        this.slton = s;
        this.dg = d;
        this.loaiHH = l;
    }
    
    public void xuat(){
        
    }
}
