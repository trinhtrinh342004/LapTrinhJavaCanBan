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
public class NgoaiThanh extends ChuyenXe {
    private String noiden;
    private int songay;
    
    public NgoaiThanh(){
        super.loaixe = 2;
    }
    
    @Override
    public void nhap(){
        super.nhap();
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap noi den: ");
        noiden = in.nextLine();
        
        System.out.print("Nhap so ngay: ");
        songay = in.nextInt();
    }
    
    @Override
    public void xuat(){
        super.xuat();
        System.out.println("Noi den " + noiden);
        System.out.println("So ngay " + songay);
    }
}
