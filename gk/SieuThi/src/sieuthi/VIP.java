/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sieuthi;

/**
 *
 * @author Trinh
 */
public class VIP extends The{
    int sonam;
    
    public VIP(){
        super();
    }
    
    public VIP(String m, String h, String l, double t, int s){
        super(m,h,l,t);
        this.sonam = s;
    }
    
    public double tinhThuong(){
        return Math.max(sonam*0.05, 0.20);
    }
}

