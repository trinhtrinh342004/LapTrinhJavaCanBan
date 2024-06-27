/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau2_sieuthiabc;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class QLHH {
    private HangHoa array[];
    private int n;
   
    public void nhap(){
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap so luong hang hoa");
        n = in.nextInt();
        array = new HangHoa[n];
        
        
    }
    
}
