/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau2_chuyenxe;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public abstract class ChuyenXe {
    protected String ms;
    protected String hoten;
    protected int soxe;
    protected double klhh;
    protected double doanhthu;
    protected int loaixe;
    
    public int getLoai(){
        return loaixe;
    }
    
    public void setLoai(int loai){
        loaixe = loai;
    }
    
    public double getDoanhThu(){
        return doanhthu;
    }
    
    public void setDoanhThu(double dt){
        doanhthu = dt;
    }
    
    public ChuyenXe(){
        ms = null;
        hoten = null;
        soxe = 0;
        klhh = 0;
        doanhthu =0;
        loaixe = 0;
    }
    
    public void xuat(){
        System.out.println("Danh sach chuyen xe!!!!");
        System.out.println("Ma so chuyen: " + ms);
        System.out.println("Ho va ten tai xe: " + hoten);
        System.out.println("So xe: " + soxe);
        System.out.println("Khoi luong hang hoa: " + klhh);
        System.out.println("Doanh thu: " + doanhthu);
    }
    
    public void nhap(){
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap ma so chuyen: ");
        ms = in.nextLine();
        
        System.out.print("Nhap ho va ten: ");
        hoten = in.nextLine();
        
        System.out.print("Nhap so xe: ");
        soxe = in.nextInt();
        
        System.out.print("Nhap khoi luong hang hoa: ");
        klhh = in.nextDouble();
        
        System.out.print("Nhap doanh thu: ");
        doanhthu = in.nextDouble();  
    }
    
}
