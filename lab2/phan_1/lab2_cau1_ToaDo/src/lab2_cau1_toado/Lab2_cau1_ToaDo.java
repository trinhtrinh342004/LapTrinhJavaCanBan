/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2_cau1_toado;

/**
 *
 * @author Trinh
 */
public class Lab2_cau1_ToaDo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pointer p1 = new Pointer();
        Pointer p2 = new Pointer();
        
        System.out.println("Nhap toa do diem 1: ");
        p1.nhap();
        System.out.println("Nhap toa do diem 2: ");
        p2.nhap();
        
        double kc = p1.khoangCachXY(p2);
        System.out.println("Khoang cach 2 diem: " + kc);
    }
}
