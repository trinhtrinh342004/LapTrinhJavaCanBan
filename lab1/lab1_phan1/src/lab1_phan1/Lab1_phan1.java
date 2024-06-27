
package lab1_phan1;

import java.util.Scanner;
import static lab1_phan1.baitap.tinhTienDien;

public class Lab1_phan1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            displayMenu();
            choice = scanner.nextInt();
            
            switch (choice){
                case 1: 
                    bai1();
                    break;
                case 2:
                    bai2();
                    break;
                case 3:
                    bai3();
                    break;
                case 4:
                    bai4();
                    break;
                case 5:
                    bai5();
                    break;
                case 6:
                    System.out.println("Exit!!!!.....");
                    break;
                default:
                    System.out.println("Invalid! Try again!");
            }
            System.out.println("");
        }
        while(choice !=6);
    }
    
    public static void displayMenu(){
        System.out.println("###################################");
        System.out.println("    1. Bai 1");
        System.out.println("    2. Bai 2");
        System.out.println("    3. Bai 3");
        System.out.println("    4. Bai 4");
        System.out.println("    5. Bai 5");
        System.out.println("    6. Thoat.");
        System.out.println("###################################");
        System.out.println("Chon chuc nang: ");
    }
    public static void bai1(){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a number for radius: ");
        double radius = input.nextDouble();
        double area = radius * radius * 3.14159;
        
        System.out.println("The area for the circle of radius " + radius + " is " + area);
    }
    
    public static void bai2(){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a: ");
        double a = input.nextDouble();
        
        System.out.print("Enter b: ");
        double b = input.nextDouble();
        
        double x = a/b;
        System.out.printf("%.3f", x);
        
        System.out.println(" ");
    }
    
    public static void bai3(){
        PhanSo ps1 = new PhanSo();
        PhanSo ps2 = new PhanSo();
        
        System.out.println("Nhap phan so thu nhat: ");
        ps1.nhapPhanSo();
        System.out.println("Nhap phan so thu hai: ");
        ps2.nhapPhanSo();
        
        PhanSo tongPS = ps1.cong(ps2);
        PhanSo hieuPS = ps1.tru(ps2);
        PhanSo tichPS = ps1.nhan(ps2);
        PhanSo thuongPS = ps1.chia(ps2);
        
        ps1.inPhanSo();
        System.out.print(" + ");
        ps2.inPhanSo();
        tongPS.rutGonPhanSo();
        System.out.print(" = ");
        tongPS.inPhanSo();
        System.out.println("");
        
        ps1.inPhanSo();
        System.out.print(" - ");
        ps2.inPhanSo();
        hieuPS.rutGonPhanSo();
        System.out.print(" = ");
        hieuPS.inPhanSo();
        System.out.println("");
        
        ps1.inPhanSo();
        System.out.print(" * ");
        ps2.inPhanSo();        
        tichPS.rutGonPhanSo();
        System.out.print(" = ");
        tichPS.inPhanSo();
        System.out.println("");
        
        ps1.inPhanSo();
        System.out.print(" : ");
        ps2.inPhanSo();        
        thuongPS.rutGonPhanSo();
        System.out.print(" = ");
        thuongPS.inPhanSo();
        System.out.println("");
    }
    
    public static void bai4(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Nhap chuoi X: ");
        String x = scanner.nextLine();
        
        System.out.println("Nhap chuoi Y: ");
        String y = scanner.nextLine();
        
        //Tong chieu dai chuoi x
        int lengthX = x.length();
        int lengthY = y.length();
        System.out.println("Tong chieu dai chuoi X " + lengthX);
        
        //3 ki tu dau tien chuoi x
        if (lengthX >= 3) {
            System.out.println("3 ky tu dau tien cua X: " + x.substring(0, 3));
        }
        else {
            System.out.println("Chuoi khong du 3 ki tu.");
        }
        
        //3 ki tu cuoi cung chuoi x
        if (lengthX >= 3) {
            System.out.println("3 ky tu dau tien cua X: " + x.substring(lengthX - 3));
        }
        else {
            System.out.println("Chuoi khong du 3 ki tu.");
        }
        
        //Ki tu thu 6 chuoi x
        if (lengthX >= 6) {
            System.out.println("Ki tu thu 6 chuoi X: " + x.charAt(5));;
        }
        else {
            System.out.println("Chuoi khong du 6 ki tu.");
        }
        
        //Tao chuoi moi tu X va Y
        if (lengthX >= 3 && lengthY >= 3){
            String newString = x.substring(0, 3) + y.substring(lengthY - 3);
            System.out.println("Chuoi moi: " + newString);
        }
        else {
            System.out.println("Chuoi X hoac Y khong du 3 ki tu.");
        }
        
        //Kiem tra hai chuoi bang nhau bang equals
        if (x.equals(y)) {
            System.out.println("X = Y");
        }
        else {
            System.out.println("X != Y");
        }
        
        //Kiem tra hai chuoi bang nhau khong phan biet hoa thuong
        if (x.equalsIgnoreCase(y)) {
            System.out.println("X = Y");
        }
        else {
            System.out.println("X != Y");
        }
        
        //Kiem tra y co xuat hien trong x khong
        int index = x.indexOf(y);
        if (index != -1) {
            System.out.println("Y xuat hien trong X." + index);
        }
        else {
            System.out.println("Y khong xuat hien tron X tai vi tri ");
        }
        
        //Tat ca vi tri cua y trong x
        int currentIndex = 0;
        while ((currentIndex = x.indexOf(y, currentIndex)) != -1){             
            System.out.println("Cac vi tri y xuat hien trong x: " + currentIndex);
            currentIndex ++;
        }
    }
    
    public static void bai5(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Nhap so dien: ");
        int soDien = scanner.nextInt();
        
        int tienDien = tinhTienDien(soDien);
        System.out.println("Tien dien phai tra: " + tienDien + " dong.");
    }
}
