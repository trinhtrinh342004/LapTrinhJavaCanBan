/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nuoc;

/**
 *
 * @author Trinh
 */
public class SinhHoat extends CTNuoc {
    private double dinhmuc;
    
    public SinhHoat(){
        super();
    }
    public SinhHoat(String m, String h, double d, double dm){
        super(m, h, d);
        this.dinhmuc = dm;
    }
    
    @Override
    public double tinhTienThang(int thang){
        if (thang >= 1 && thang <= 12) {
            double ln = lntt.get(thang - 1);
            if (ln <= dinhmuc){
                return ln * dongia;
            } else {
                return ln * dongia * dinhmuc + (ln - dinhmuc) * dongia * 1.5;
            }
        }
        return 0;
    }
}
