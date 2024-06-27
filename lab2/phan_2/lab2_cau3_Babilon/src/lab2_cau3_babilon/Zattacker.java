/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau3_babilon;

/**
 *
 * @author Trinh
 */
public class Zattacker extends Robot {
    private int p;
    
    public Zattacker(){
        super.loaiRobot = 2;
        super.m = 50;
        p = (int)(Math.random()*10 + 20);
    }
    
    public Zattacker(int s){
        super(50,s,0,2);
        p = (int)(Math.random()*10 + 20);
    }
    
    public int getP(){
        return p;
    }
    
    public void setP(int p){
        this.p = p;
    }
    
    @Override
    public void tinhNangLuong(){
        super.nangluongtieuthu = m*s+p*p*s;
    }
    
    @Override
    public void xuat(){
        super.xuat();
        System.out.println("Suc manh: " + p);
    }
}
