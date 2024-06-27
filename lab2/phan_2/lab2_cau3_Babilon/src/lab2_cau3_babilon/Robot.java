/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau3_babilon;

/**
 *
 * @author Trinh
 */
public abstract class Robot {
    protected int m;
    protected int s;
    protected int nangluongtieuthu;
    protected int loaiRobot;
    
    public Robot(){
        loaiRobot = 0;
        nangluongtieuthu = 0;
    }
    
    public Robot(int m, int s, int nl, int l){
        this.m = m;
        this.s = s;
        this.nangluongtieuthu = nl;
        this.loaiRobot = l;
    }
    
    public int getM(){
        return m;
    }
    
    public void setM(int m){
        this.m = m;
    }
    
    public int getS(){
        return s;
    }
    
    public void setS(int s){
        this.s = s;
    }
    
    public int getNangluongtieuthu(){
        return nangluongtieuthu;
    }
    
    public void setNangluongtieuthu(int nangluongtieuthu){
        this.nangluongtieuthu = nangluongtieuthu;
    }
    
    public int getLoai(){
        return loaiRobot;
    }
    
    public void setLoai(int l){
        this.loaiRobot = l;
    }
    
    public abstract void tinhNangLuong();
    
    public void xuat(){
        switch(this.loaiRobot){
            case 0 -> System.out.println("Robot thuong");
            case 1 -> System.out.println("Robot Pedion");
            case 2 -> System.out.println("Robot Zattacker");
            case 3 -> System.out.println("Robot Carrier");
        }
        
        System.out.println("Trong luong Robot: " + m + " kg");
        System.out.println("Quang duong da di chuyen: " + s + " km");
        System.out.println("Nang luong da tieu thu: " + this.nangluongtieuthu);
    }
}
