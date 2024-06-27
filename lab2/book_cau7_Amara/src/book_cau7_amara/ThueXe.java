/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book_cau7_amara;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class ThueXe extends DichVu {
    private int n;
    private int sogio[];
    private int tonggiothue;
    private int dongia;
    
    public ThueXe(){
        super.loaiDV = 1;
    }
    
    @Override
    public void nhap(){
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap vao so xe thue: ");
        n = in.nextInt();
        sogio = new int[n];
        System.out.println("Nhap vao don gia thue xe moi gio: ");
        dongia = in.nextInt();
        tonggiothue = 0;
        System.out.println("Nhap vao gio thue xe cua moi xe! ");
        for(int i = 0; i < n; i++){
            System.out.println("Gio thue xe cua xe thu " + (i+1));
            sogio[i] = in.nextInt();
            tonggiothue +=sogio[i];
        }  
    }
    
    @Override
    public void tinhChiPhi(){
        super.chiphi = 0;
        for(int i =0; i < n; i++){
            if(sogio[i] >= 6)
                super.chiphi +=sogio[i]*dongia*95/100;
            else
                super.chiphi += sogio[i]*dongia;
        }
        
        if(tonggiothue >= 72)
            super.chiphi = super.chiphi *98/100;
    }
}
