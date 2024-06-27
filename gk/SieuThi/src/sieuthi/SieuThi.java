/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sieuthi;

import java.util.ArrayList;
import static sieuthi.The.kiemtraSDT;
import static sieuthi.The.themSoTienMuaHang;

/**
 *
 * @author Trinh
 */
public class SieuThi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<The> dsthe = new ArrayList<>();
        
        ThanhVien tv1 = new ThanhVien("T0084", "Nguyen X", "thuong", 0);
        dsthe.add(tv1);
        
        themSoTienMuaHang(dsthe, "T0084", 250);
        
        kiemtraSDT(dsthe);
    }
    
    
}
