/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau3_sinhvien;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class SinhVien {
    private int mssv;
    private String hoten;
    private float lt;
    private float th;
    
    public SinhVien(){
        this.mssv = 0;
        this.hoten = null;
        this.lt = 0;
        this.th = 0;
    }
    
    public SinhVien(SinhVien sv){
        this.mssv = sv.mssv;
        this.hoten = sv.hoten;
        this.lt = sv.lt;
        this.th = sv.th;
    }
    
    public int getMS(){
        return this.mssv;
    }
    
    public String getHT(){
        return this.hoten;
    }
    
    public float getLT(){
        return this.lt;
    }
    
    public float getTH(){
        return this.th;
    }
    
    public void setMS(int ms){
        this.mssv = ms;
    }
    
    public void setHT(String ht){
        this.hoten = ht;
    }
    
    public void setLT(float lt){
        this.lt = lt;
    }
    
    public void setTH(float th){
        this.th = th;
    }
    
    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap mssv: ");
        this.mssv = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nhap ho ten: ");
        this.hoten = scanner.nextLine();
        
        System.out.print("Nhap lt: ");
        this.lt = scanner.nextFloat();
        
        System.out.print("Nhap th: ");
        this.th = scanner.nextFloat();
    }
    
    public float tinhDTB(){
        return (this.lt + this.th)/2; 
    }
    
    @Override
    public String toString(){
        return mssv + "\t" + hoten + "\t\t" + lt + "\t" + th + "\t" + tinhDTB();
    }
}
