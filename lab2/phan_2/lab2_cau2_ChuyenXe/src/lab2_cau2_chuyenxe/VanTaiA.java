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
public class VanTaiA {
    private ChuyenXe array[];
    private int n;
    
    public void nhap(){
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap so luong chuyen xe: ");
        n = in.nextInt();
        array = new ChuyenXe[n];
        
        for(int i = 0; i<n; i++){
            System.out.println("Nhap thong tin chuyen xe " + (i+1));
            System.out.println("Chon loai chuyen xe: ");
            System.out.println("1. Noi thanh");
            System.out.println("2. Ngoai thanh");
            System.out.print("Chon: ");
            int flag = in.nextInt();
            
            switch(flag){
                case 1 -> { 
                    array[i] = new NoiThanh();
                    array[i].nhap();
                }
                case 2 -> { 
                    array[i] = new NgoaiThanh();
                    array[i].nhap();
                }
                default -> System.out.println("Lua chon khong hop le. Vui long nhap lai");
            }
        }
    }
    
    public void xuat(){
        for(int i = 0; i< n; i++){
            array[i].xuat();
        }
    }
    
    public void tinhTongDoanhThu(){
        double tongNoi = 0;
        double tongNgoai = 0;
        
        for (int i = 0; i< n; i++){
            if(array[i] instanceof NoiThanh)
                tongNoi += array[i].getDoanhThu();
            if (array[i] instanceof NgoaiThanh)
                tongNgoai += array[i].getDoanhThu();
        }
        
        System.out.println("Tong Doanh thu chuyen noi thanh la " + tongNoi);
        System.out.println("Tong Doanh thu chuyen ngoai thanh la " + tongNgoai);
    }
    
    public void inXeMax(){
        double maxNoi = -1;
        double maxNgoai= -1;
        int indexNoi = -1;
        int indexNgoai = -1;
        
        for(int i = 0; i < n; i++){
            if (array[i] instanceof NoiThanh){
                if (array[i].getDoanhThu() > maxNoi){
                    maxNoi = array[i].getDoanhThu();
                    indexNoi = i;
                }
            }
            else if (array[i] instanceof NgoaiThanh){
                if (array[i].getDoanhThu() > maxNgoai){
                    maxNgoai = array[i].getDoanhThu();
                    indexNgoai = i;
                }
            }
        }
        
        if (indexNoi!= -1){
            System.out.println("Chuyen xe noi thanh co doanh thu cao nhat: ");
        array[indexNoi].xuat();
        } else {
            System.out.println("Khong co chuyen xe noi thanh nao.");
        }

        if (indexNgoai != -1){
            System.out.println("Chuyen xe ngoai thanh co doanh thu cao nhat: ");
            array[indexNgoai].xuat();
        } else {
            System.out.println("Khong co chuyen xe ngoai thanh nao.");
        }
    }
}
