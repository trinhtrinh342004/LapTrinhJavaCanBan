/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau1_ctabc;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public abstract class KhachHang {
    protected String maKH;
    protected String tenKH;
    protected int ngayHD;
    protected double sldien;
    protected double dg;
    protected double thanhtien;
    protected int loaiKH;
    
    public String getMaKhachHang(){
        return this.maKH;
    }
    
    public String getTenKhachHang(){
        return this.tenKH;
    }
    
    public double getSoLuongDien(){
        return this.sldien;
    }
    
    public void setMaKhachHang(String m){
        this.maKH = m;
    }
    
    public void setTenKhachHang(String t){
        this.tenKH = t;
    }
    
    public void setSoLuongDien(double sl){
        this.sldien = sl;
    }
    
    
    public KhachHang(){
        this.maKH = null;
        this.tenKH = null;
        this.ngayHD = 0;
        this.sldien = 0;
        this.dg = 0;
        this.thanhtien = 0;
        this.loaiKH = 0;
    }
    
    public KhachHang(String m, String t, int n, double sl, double dg, int l){
        this.maKH = m;
        this.tenKH = t;
        this.ngayHD = n;
        this.sldien = sl;
        this.dg = dg;
        this.loaiKH = l;
    }
    
    public abstract void tinhThanhTien();
    
    
    
    public void xuat(){
        System.out.println("Thong tin cua khach hang!!!!!!!!");
        System.out.println("Ma khach hang: " + this.maKH);
        System.out.println("Ten khach hang: " + this.tenKH);
        System.out.println("Ngay hoa don: " + this.ngayHD);
        System.out.println("So luong dien: " + this.sldien);
        System.out.println("Don gia: " + this.dg);
        System.out.println("Thanh tien: " + this.thanhtien);
    }

    public void nhap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
