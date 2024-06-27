/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau2_sieuthiabc;

/**
 *
 * @author Trinh
 */
public class GiaDung extends HangHoa {
    private String tt_nhasx;
    private int ngaynhap;
    private String loai;
    
    public GiaDung(){
        super.loaiHH = 3;
    }
    
    public GiaDung(String tt, int n, String l){
        this.tt_nhasx = tt;
        this.ngaynhap = n;
        this.loai = l;
    }
    
    
    
    @Override
    public void xuat(){
        
    }
}

