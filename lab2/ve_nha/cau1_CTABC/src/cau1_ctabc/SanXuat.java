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
public class SanXuat extends KhachHang {
    private int loaidien;
    
    public SanXuat(){
        super.loaiKH = 3;
    }
    
    public SanXuat(int ld){
        this.loaidien = ld;
    }
    
    @Override
    public void tinhThanhTien(){
        if (this.loaidien == 2)
            if (this.sldien<= 200){
                this.thanhtien = this.sldien * this.dg;
            }
            else {
                this.thanhtien = this.sldien * this.dg*0.98;
            }
        else if (this.loaidien == 3)
            if (this.sldien<= 150){
                this.thanhtien = this.sldien * this.dg;
            }
            else {
                this.thanhtien = this.sldien * this.dg*0.97;
            }
    }
    
   
    
    @Override
    public void xuat(){
        super.xuat();
        System.out.println("Loai dien la: " + this.loaidien);
    }
}
