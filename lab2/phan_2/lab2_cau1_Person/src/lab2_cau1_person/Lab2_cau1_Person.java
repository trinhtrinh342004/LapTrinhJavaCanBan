/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2_cau1_person;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class Lab2_cau1_Person {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhap so luong person: ");
        int p = scanner.nextInt();
        System.out.print("Nhap so luong employee: ");
        int e = scanner.nextInt();
        
        Person[] persons = new Person[p];
        Employee[] employees = new Employee[e];
        
        for(int i = 0; i < p; i++){
            System.out.print("Nhap thong tin person " + (i + 1) + ": ");
            persons[i] = new Person();
            persons[i].nhap();
        }
        
        for(int i = 0; i < e; i++){
            System.out.print("Nhap thong tin employee " + (i + 1) + ": ");
            employees[i] = new Employee(null, 0 ,0);
            employees[i].nhap();
        }
        
        // Hiển thị thông tin của mỗi Person
        System.out.println("Thong tin cac Person:");
        for (int i = 0; i < p; i++) {
            System.out.println("Person " + (i + 1) + ":");
            persons[i].show();
        }
        
        // Hiển thị thông tin của mỗi Employee
        System.out.println("Thong tin cac Employee:");
        for (int i = 0; i < e; i++) {
            System.out.println("Employee " + (i + 1) + ":");
            employees[i].show();
        }
    }
    
}
