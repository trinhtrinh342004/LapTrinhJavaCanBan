/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau1_toado;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class Pointer {
    private int x;
    private int y;
    
    public Pointer(){
        this.x = 0;
        this.y = 0;
    }
    
    public Pointer(Pointer p){
        this.x = p.x;
        this.y = p.y;
    }
    
    public int getX(){
        return x;
    }    
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap hoanh do x:");
        x = scanner.nextInt();
        System.out.print("Nhap tung do y:");
        y = scanner.nextInt();
    }
    
    public void xuat(){
        System.out.println("Toan do diem (" + x + "," + y + ")");
    }
    
    public double khoangCachXY(Pointer other){
        double khoangCach = Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
        return khoangCach;
    }
}
