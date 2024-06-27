package cau1_ctabc;

import java.util.Scanner;

public class QLKH {
    private KhachHang array[];
    private int n;
    
    public void nhap(){
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap so luong phan tu");
        n = in.nextInt();
        array = new KhachHang[n];
        
        for (int i = 0; i< n; i++){
            System.out.println("----------------------------------------------");
            System.out.println("Chon loai khach hang " + (i+1));
            System.out.println("1. Sinh hoat");
            System.out.println("2. Kinh doanh");
            System.out.println("3. San xuat");
            System.out.print("Chon: ");
            
            int flag = in.nextInt();
            
            switch(flag){
                case 1 -> { 
                    array[i] = new SinhHoat();
                    array[i].nhap();
                    array[i].tinhThanhTien();
                }
                case 2 -> { 
                    array[i] = new KinhDoanh();
                    array[i].nhap();
                    array[i].tinhThanhTien();
                }
                case 3 -> { 
                    array[i] = new SanXuat();
                    array[i].nhap();
                    array[i].tinhThanhTien();
                }
                default -> System.out.println("Khong hop le.");
            }
        }
    }
    
    public void xuat(){
        for (int i = 0; i < n; i++){
            array[i].xuat();
        }
    }
}