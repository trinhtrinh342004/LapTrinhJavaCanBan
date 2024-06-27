/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nuoc;

import java.util.ArrayList;
/**
 *
 * @author Trinh
 */
public class NUOC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<CTNuoc> dskh = new ArrayList<>();
        
        ghiNhanKH("N0053", 9, 550, dskh);
        ghiNhanKH("N0051", 8, 550, dskh);
        ghiNhanKH("N0052", 7, 550, dskh);
        
        lietKe(8, dskh);
                
        
    }
    public static void ghiNhanKH(String maKH, int thang, int ln, Iterable<CTNuoc> dskh){
       
        for (CTNuoc n : dskh){
            if (n.maKH.equals(maKH)){
                n.ghiNhanLNTT(thang, ln);
                break;
            }
        }
    }
    public static void lietKe(int thang, Iterable<CTNuoc> dskh){
        System.out.println("Ma KH\tHo ten\tLoai KD\tLuong nuoc\tThanh tien");
        for(CTNuoc n : dskh){
            if (n instanceof KinhDoanh kd){
                int ln = kd.lntt.get( thang - 1);
                double thanhtien = kd.tinhTienThang(thang);
                System.out.printf(kd.maKH, kd.hoTen, kd.heso*100, ln, thanhtien);
            }
        }
    }
}
