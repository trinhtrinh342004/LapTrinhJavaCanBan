/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package phongkhamapp;

import javax.swing.SwingUtilities;

/**
 *
 * @author Trinh
 */
public class PhongKhamApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        SwingUtilities.invokeLater(() -> new ThemBenhNhan().setVisible(true));

//        SwingUtilities.invokeLater(() -> new DatLichKB().setVisible(true));
        
        SwingUtilities.invokeLater(() -> {
            new ThemCTKB().setVisible(true);
        });
    }

}
