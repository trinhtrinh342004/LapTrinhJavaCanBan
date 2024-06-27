/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau3_babilon;

/**
 *
 * @author Trinh
 */
public class Pedion extends Robot{
    private int f; 
    
    public Pedion(){
        super.loaiRobot = 1;
        f = (int)(Math.random()*4 + 1);
        super.m = 20;
    }
    
    public Pedion(int s){
        super(20,s,0,1);
        f = (int)(Math.random()*4 + 1);
    }
    
    public int getF(){
        return f;
    }
    
    public void setF(int f){
        this.f = f;
    }
    
    @Override
    public void tinhNangLuong(){
        super.nangluongtieuthu = m*s + (f+1)*s/2;
    }
    
    @Override
    public void xuat(){
        super.xuat();
        System.out.println("Do linh hoat: " + f);
    }
}
