/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau2_hinhchunhat;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class HCN {
    private double dai;
    private double rong;
    
    public HCN(){
        this.dai = 0;
        this.rong = 0;
    }
    
    public HCN(double d, double r){
        this.dai = d;
        this.rong = r;
    }
    
    public double getDai(){
        return this.dai;
    }
    
    public double getRong(){
        return this.rong;
    }
    
    public void setDai(double d){
        this.dai = d;
    }
    
    public void setRong(double r){
        this.rong = r;
    }
    
    public void nhapHCN(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap chieu dai: ");
        dai = scanner.nextDouble();
        System.out.print("Nhap chieu rong: ");
        rong = scanner.nextDouble();
    }
    
    public double tinhChuVi(){
        double cv = (this.dai + this.rong) * 2;
        return cv;
    }
    
    public double tinhDienTich(){
        double dt = this.dai * this.rong;
        return dt;
    }
    
    public void xuatHCN(){
        System.out.println("Chieu dai: " + this.dai);
        System.out.println("Chieu rong: " + this.rong);
    }
    
    public String toString(){
        double cv = tinhChuVi();
        double dt = tinhDienTich();
        return "Chieu dai: " + this.dai + "\nChieu rong: " 
                + this.rong + "\nChu vi: " + cv + "\nDien tich: " + dt;
    }
}
