/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau4_xe;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class Xe {
    private String tenchu;
    private String loaixe;
    private double trigia;
    private double dungtich;
    
    public Xe(){
        this.tenchu = null;
        this.loaixe = null;
        this.trigia = 0;
        this.dungtich = 0;
    }
    
    public Xe(Xe x){
        this.tenchu = x.tenchu;
        this.loaixe = x.loaixe;
        this.trigia = x.trigia;
        this.dungtich = x.dungtich;
    }
    
    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên chủ xe (nhập rỗng để kết thúc): ");
        this.tenchu = scanner.nextLine();
        if (this.tenchu.isEmpty()) {
            return;
        }
        
        System.out.print("Nhập loại xe: ");
        this.loaixe = scanner.nextLine();
        
        System.out.print("Nhập giá trị xe: ");
        this.trigia = scanner.nextDouble();
        
        System.out.print("Nhập dung tích xylanh: ");
        this.dungtich = scanner.nextDouble();
    }
    
    public double tinhThue(){
        if (dungtich < 100)
            return trigia * 0.01;
        else if (dungtich >= 100 && dungtich <= 175)
            return trigia * 0.03;
        else
            return trigia * 0.05;
    }
    
    public void xuat(){
        System.out.println("Tên chủ xe: " + tenchu);
        System.out.println("Loại xe: " + loaixe);
        System.out.println("Giá trị xe: " + trigia);
        System.out.println("Dung tích xylanh: " + dungtich);
        System.out.println("Thuế phải đóng: " + tinhThue());
    }
    
    public static void nhapDanhSachXe(Xe[] danhSachXe){
        int n = 0;
        
        while (true) {
            danhSachXe[n] = new Xe();
            System.out.println("Nhập thông tin xe " + (n+1) + ":");
            danhSachXe[n].nhap();
            if (danhSachXe[n].tenchu.isEmpty()) {
                break;
            }
            n++;
        }
    }
    
    public static void xuatThueChuXe(Xe[] danhSachXe, String tenChuXe, String loaiXe){
        boolean found = false;
        
        for (Xe danhSachXe1 : danhSachXe) {
            if (danhSachXe1 != null && danhSachXe1.tenchu.equalsIgnoreCase(tenChuXe) && danhSachXe1.loaixe.equalsIgnoreCase(loaiXe)) {
                System.out.println("Thuế phải đóng của chủ xe " + tenChuXe + " với loại xe " + loaiXe + ": " + danhSachXe1.tinhThue());
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Không tìm thấy thông tin xe của chủ xe " + tenChuXe + " với loại xe " + loaiXe);
        }
    }
}
