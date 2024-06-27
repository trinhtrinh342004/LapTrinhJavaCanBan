/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nuoc;

/**
 *
 * @author Trinh
 */
public class KinhDoanh extends CTNuoc{
    double heso;
    
    public KinhDoanh(){
        super();
    }
    
    public KinhDoanh(String m, String h,double d, double he){
        super(m,h,d);
        this.heso = he;
    }
    
    @Override
    public double tinhTienThang(int thang){
        if (thang >= 1 && thang <= 12) {
            double ln = lntt.get(thang - 1);
            if (ln <= 500){
                return ln * dongia;
            } 
            else if (ln > 500) {
                return ln * dongia * (1 - heso);
            }
        }
        return 0;
    }
    
    
}
