/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sieuthi;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Trinh
 */
public abstract class The {
    protected String maTV;
    protected String hoTen;
    protected String loaiThe;
    protected double ttienmh;
    protected ArrayList<LocalDate> ngaysdt;
    
    public The(){
        this.ngaysdt = new ArrayList<>();
    }
    
    public The(String m, String h, String l, double t){
        this.maTV = m;
        this.hoTen = h;
        this.loaiThe = l;
        this.ttienmh = t;
        this.ngaysdt = new ArrayList<>();
    }
    
    public void ghiNhanMuaHang(double tienMua) {
        this.ttienmh += tienMua;
        this.ngaysdt.add(LocalDate.now());
    }
    
    public void chuyenLoaiThe(String loai){
        this.loaiThe = loai;
    }
    
    public static void themSoTienMuaHang(ArrayList<The> dsthe, String ma, double tien){
        for (The the : dsthe){
            if (the.maTV.equals(ma)){
                the.ghiNhanMuaHang(tien);
                if (the instanceof ThanhVien && the.ttienmh > 5000){
                    the.chuyenLoaiThe("VIP");
                    ((VIP) the).sonam = 1;
                }
                break;
            }
        }
    }
    
    public static void kiemtraSDT(ArrayList<The> danhSachThe) {
    for (The the : danhSachThe) {
        if (the instanceof VIP vip) {
            // Kiểm tra xem có mua hàng trong năm không
            if (vip.ngaysdt.isEmpty()) {
                // Nếu không mua hàng trong năm, chuyển thành thẻ Thành viên
                ThanhVien tv = new ThanhVien(vip.maTV, vip.hoTen, "thường", 0);
                danhSachThe.set(danhSachThe.indexOf(the), tv);
            }
        } else if (the instanceof ThanhVien tv) {
            // Kiểm tra xem có mua hàng trong năm không
            if (tv.ngaysdt.isEmpty()) {
                // Nếu không mua hàng trong năm, reset tổng tiền mua hàng về 0
                tv.ttienmh = 0;
                }
            }
        }
    }
}