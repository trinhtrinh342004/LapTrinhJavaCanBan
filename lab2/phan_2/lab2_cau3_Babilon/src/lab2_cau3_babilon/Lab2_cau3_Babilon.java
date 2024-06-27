/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2_cau3_babilon;


/**
 *
 * @author Trinh
 */
public class Lab2_cau3_Babilon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KhaiPha a = new KhaiPha();
        a.nhap();
        a.xuat();
        int s = a.timMax();
        System.out.println("Tong tieu thu la: " + s);
    } 
}
