/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package book_cau7_amara;

/**
 *
 * @author Trinh
 */
public class Book_cau7_Amara {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KSAmara a = new KSAmara();
        a.nhap();
        a.xuat();
        int s = a.timMax();
        System.out.println("Tong chi phi: " + s);
    }
    
}
