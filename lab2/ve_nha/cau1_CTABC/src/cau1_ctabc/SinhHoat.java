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
public class SinhHoat extends KhachHang {
    private double dinhmuc;
    
    public SinhHoat(){
        super.loaiKH = 1;
    }
    
    public SinhHoat(double dm){
        this.dinhmuc = dm;
    }
    
    @Override
    public void tinhThanhTien(){
        if (this.sldien <= this.dinhmuc)
            this.thanhtien = this.sldien *this.dg;
        else 
            this.thanhtien = this.sldien * this.dg + (this.sldien - this.dinhmuc)*this.dg * 2;
        System.out.println("Sinh Hoat thanh tien: " + this.thanhtien);
    }
    
    
    @Override
    public void xuat(){
        super.xuat();
        System.out.println("Dinh muc cua khach hang: " + this.dinhmuc);
    }
}
