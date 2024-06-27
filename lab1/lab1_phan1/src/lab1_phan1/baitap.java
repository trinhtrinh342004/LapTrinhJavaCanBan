/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_phan1;
/**
 *
 * @author Trinh
 */
public class baitap {
    //bai5
    public static int tinhTienDien(int soDien){
        int tienDien;
        
        if (soDien <= 50) {
            tienDien = 2000 * soDien;
        }
        else if(soDien > 50 && soDien <= 100){
            tienDien = 2000 * 50 + 2500 * (soDien - 50);
        }
        else{
            tienDien = 2000 * 50 + 2500 * 50 + 3500 * (soDien - 50);
        }
        
        
        return tienDien;
    }
}


