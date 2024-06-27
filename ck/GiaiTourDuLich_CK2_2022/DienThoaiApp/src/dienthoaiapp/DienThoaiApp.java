/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dienthoaiapp;

import javax.swing.SwingUtilities;

/**
 *
 * @author Trinh
 */
public class DienThoaiApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Khởi chạy giao diện của ThemDiemDuLich
        SwingUtilities.invokeLater(() -> {
            new ThemDienThoai().setVisible(true);
        });
        
//        SwingUtilities.invokeLater(() -> {
//            new ThemPSC().setVisible(true);
//        });
////        
//        SwingUtilities.invokeLater(() -> {
//            new ThemSCDT().setVisible(true);
//        });
    }
    
}
