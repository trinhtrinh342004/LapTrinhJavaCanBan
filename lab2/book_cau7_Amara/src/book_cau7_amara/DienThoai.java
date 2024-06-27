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
public class DienThoai extends DichVu {
    private int n;
    private int sophutgoi[];
    private int dongia;
    
    public DienThoai(){
        super.loaiDV = 2;
    }
    
    @Override
    public void nhap(){
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap vao so cuoc goi: ");
        n = in.nextInt();
        sophutgoi = new int[n];
        System.out.println("Nhap vao don gia moi phut: ");
        dongia = in.nextInt();
        System.out.println("Nhap vao so phut cho moi cuoc goi: ");
        for(int i = 0; i < n;i++){
            System.out.println("Cuoc goi " + i);
            sophutgoi[i] = in.nextInt();
        }
    }
    
    @Override
    public void tinhChiPhi(){
        super.chiphi = 0;
        for (int i =0; i< n;i++){
            super.chiphi +=sophutgoi[i] * dongia;
        }
        if (super.chiphi >= 300000)
            super.chiphi = super.chiphi * 80/100;
    }
}
