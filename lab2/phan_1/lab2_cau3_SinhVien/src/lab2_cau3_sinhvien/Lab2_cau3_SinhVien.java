/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2_cau3_sinhvien;

/**
 *
 * @author Trinh
 */
public class Lab2_cau3_SinhVien {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SinhVien sv1 = new SinhVien();
        SinhVien sv2 = new SinhVien();
        SinhVien sv3 = new SinhVien();
        
        sv1.nhap();
        sv2.nhap();
        sv3.nhap();
        
        System.out.println("Xuat danh sach sinh vien");
        System.out.println("MSSV\t Ho ten\t\t Diem LT\t Diem TH\t Diem TB" );
        System.out.println(sv1.toString());
        System.out.println(sv2.toString());
        System.out.println(sv3.toString());
    }
    
}
