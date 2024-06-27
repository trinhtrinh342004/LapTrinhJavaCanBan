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
public class Person {
    private String name;
    private int age;
    
    public Person(){
        this.name = null;
        this.age = 0;
    }
    
    public Person(String n, int a){
        this.name = n;
        this.age = a;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public void setName(String n){
        this.name = n;
    }
    
    public void setAge(int a){
        this.age = a;
    }
    
    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap thong tin person: ");
        System.out.print("Name: ");
        name = scanner.nextLine();
        
        System.out.print("Age: ");
        age = scanner.nextInt();
    }
    public void show(){
        System.out.println("Name" + "\t" + "Age");
        System.out.println(name + "\t" + age);
    }
}
