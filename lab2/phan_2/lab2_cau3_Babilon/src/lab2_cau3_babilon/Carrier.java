/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau3_babilon;

/**
 *
 * @author Trinh
 */
public class Carrier extends Robot{
    private int e;
    
    public Carrier(){
        super.loaiRobot = 3;
        super.m = 30;
        e = (int)(Math.random()*50 + 50);
    }
    
    public Carrier(int s){
        super(30,s,0,3);
        e = (int)(Math.random()*50 + 50);
    }
    
    public int getE(){
        return e;
    }
    
    public void setE(int e){
        this.e = e;
    }
    
    @Override
    public void tinhNangLuong(){
        super.nangluongtieuthu = m*s+4*e*s;
    }
    
    @Override
    public void xuat(){
        super.xuat();
        System.out.println("Nang luong van chuyen: " + e);
    }
}
