/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau1_person;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class Employee extends Person {
    private double salary;
    
    public double getSalary(){
        return this.salary;
    }
    
    public Employee(String n, int a, double sa){
        super(n, a);
        this.salary = sa;
    }
    
    public void setSalary(double s){
        this.salary = s;
    }
    
    @Override
    public void show(){
        super.show();
        System.out.println("\tSalary");
        System.out.println("\t" + salary);
    }
    
    @Override
    public void nhap(){
        super.nhap();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Salary: ");
        salary = scanner.nextDouble();
    }
    
    public double addSalary(){
        return salary*1.01;
    }
    
    public double addSalary(float x){
        return salary + x;
    }
}
