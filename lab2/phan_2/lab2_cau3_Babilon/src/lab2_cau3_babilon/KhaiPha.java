/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau3_babilon;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */

public class KhaiPha{
    private Robot array[];
    private int n;
    
    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        array = new Robot[n];
        
        for(int i = 0;i< n;i++){
            System.out.println("Chon loai robot " + i);
            int flag;
            System.out.println("Nhap 1(Pedion), 2(Zattacker), 3(Carrier): ");
            flag = scanner.nextInt();
            switch(flag){
                case 1 -> {
                    array[i] = new Pedion(10);
                    array[i].tinhNangLuong();
                }
                case 2 -> {
                    array[i] = new Zattacker(10);
                    array[i].tinhNangLuong();
                }
                case 3 -> {
                    array[i] = new Carrier(10);
                    array[i].tinhNangLuong();
                }
            }
        }  
    }
    
    public void xuat(){
        for(int i =0; i < n;i++){
            array[i].xuat();
        }
    }
    
    public int timMax(){
        int temp[] = {0,0,0};
        for(int i = 0; i<n;i++){
            switch(array[i].getLoai()){
                case 1 -> temp[0] += array[i].getNangluongtieuthu();
                case 2 -> temp[1] += array[i].getNangluongtieuthu();
                case 3 -> temp[2] += array[i].getNangluongtieuthu();
            }
        }
        int max = -1;
        int maxAt = 0;
        for(int i = 0; i < temp.length;i++){
            if(max < temp[i]){
                max = temp[i];
                maxAt = i;
            }
        }
        
        switch(maxAt){
            case 0 -> { 
                System.out.println("Loai Pedion tieu thu nhieu nang luong nhat");
            }
            case 1 -> { 
                System.out.println("Loai Zattacker tieu thu nhieu nang luong nhat");
            }
            case 2 -> { 
                System.out.println("Loai Carrier tieu thu nhieu nang luong nhat");
            }
        }
        
        return (temp[0] + temp[1] + temp[2]);
    }
}

