/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2_cau4_xe;

import java.util.Scanner;
import static lab2_cau4_xe.Xe.nhapDanhSachXe;
import static lab2_cau4_xe.Xe.xuatThueChuXe;

/**
 *
 * @author Trinh
 */
public class Lab2_cau4_Xe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Xe[] danhSachXe = new Xe[100];
        
        nhapDanhSachXe(danhSachXe);
        
        System.out.println("Bảng kê khai tiền thuế phải đóng của các xe trong danh sách:");
        for (int i = 0; i < danhSachXe.length; i++) {
            if (danhSachXe[i] != null) {
                System.out.println("Xe " + (i+1) + ":");
                danhSachXe[i].xuat();
                System.out.println("--------------------");
            }
        }
        
        System.out.print("Nhập tên chủ xe: ");
        String tenChuXe = scanner.nextLine();
        System.out.print("Nhập loại xe: ");
        String loaiXe = scanner.nextLine();
        
        xuatThueChuXe(danhSachXe, tenChuXe, loaiXe);
    }
}
