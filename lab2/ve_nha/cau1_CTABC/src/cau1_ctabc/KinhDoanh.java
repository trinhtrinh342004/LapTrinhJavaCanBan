/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau1_ctabc;

/**
 *
 * @author Trinh
 */
public class KinhDoanh extends KhachHang {
    
    public KinhDoanh(){
        super.loaiKH = 2;
    }
    
    @Override
    public void tinhThanhTien(){
        if (this.sldien <= 400)
            this.thanhtien = this.sldien * this.dg;
        else
            this.thanhtien = this.sldien* this.dg*0.05;
        
        System.out.println("Kinh doanh thanh tien la: " + this.thanhtien);
    }
    
    
}
