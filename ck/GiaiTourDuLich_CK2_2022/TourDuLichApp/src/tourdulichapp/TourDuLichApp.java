/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tourdulichapp;

import javax.swing.SwingUtilities;

/**
 *
 * @author Trinh
 */
public class TourDuLichApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//         Khởi chạy giao diện của ThemDiemDuLich
        SwingUtilities.invokeLater(() -> {
            new ThemTourDuLich().setVisible(true);
        });
        
        SwingUtilities.invokeLater(() -> {
            new ThemDiemDuLich().setVisible(true);
        });
        
        SwingUtilities.invokeLater(() -> {
            new ThemDiemDLVaoTour().setVisible(true);
        });
    }
    
}
