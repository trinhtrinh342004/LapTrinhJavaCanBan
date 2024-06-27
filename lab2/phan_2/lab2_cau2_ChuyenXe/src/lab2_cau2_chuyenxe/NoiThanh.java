/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau2_chuyenxe;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class NoiThanh extends ChuyenXe {
    private double s;
    private int n;
    
    public NoiThanh(){
        super.loaixe = 1;
    }
    
    @Override
    public void nhap(){
        super.nhap();
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap vao quang duong di: ");
        s = in.nextDouble();
    }
    
    @Override
    public void xuat(){
        super.xuat();
        System.out.println("Quang duong di: " + s);
    }
}
