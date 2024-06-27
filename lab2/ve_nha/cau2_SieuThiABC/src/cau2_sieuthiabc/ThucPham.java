/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau2_sieuthiabc;

/**
 *
 * @author Trinh
 */
public class ThucPham extends HangHoa {
    private String tt_ngaysx;
    private int ngayhh;
    private String nhacc;
    
    public ThucPham(){
        super.loaiHH = 2;
    }

    public ThucPham(String tt_ngaysx, int ngayhh, String nhacc, String m, String t, int s, double d, int l) {
        super(m, t, s, d, l);
        this.tt_ngaysx = tt_ngaysx;
        this.ngayhh = ngayhh;
        this.nhacc = nhacc;
    }
   
    
    @Override
    public void xuat(){
        
    }
}
