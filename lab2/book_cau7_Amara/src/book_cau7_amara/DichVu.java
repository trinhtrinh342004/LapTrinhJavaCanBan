/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book_cau7_amara;

/**
 *
 * @author Trinh
 */
public abstract class DichVu {
    protected int loaiDV;
    protected double chiphi;
    
    public int getLoai(){
        return loaiDV;
    }
    
    public void detLoai(int loai){
        this.loaiDV = loai;
    }
    
    public double getChiPhi(){
        return chiphi;
    }
    
    public void setChiPhi(double cp){
        this.chiphi = cp;
    }
    
    public DichVu(){
        loaiDV = 0;
        chiphi = 0;
    }
    
    public void xuat(){
        switch(loaiDV){
            case 1 -> System.out.println("Thue xe: ");
            case 2 -> System.out.println("Dien thoai: ");
            case 3 -> System.out.println("Tang hoa: ");
        }
    }
    
    public abstract void nhap();
    public abstract void tinhChiPhi();
}
