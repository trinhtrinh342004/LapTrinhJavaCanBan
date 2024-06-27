
package lab1_phan2;

import java.util.Arrays;
import java.util.Scanner;

public class Lab1_phan2 {

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
        System.out.println("    5. Thoat.");
        System.out.println("###################################");
        System.out.print("Chon chuc nang: ");
    }
    
    public static void bai1(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap mot so nguyen duong: ");
        int n = scanner.nextInt();
        
        boolean laSoNguyenTo = baitap_phan2.kiemTraSoNguyenTo(n);
        
        if (laSoNguyenTo){
            System.out.println(n + " la so nguyen to.");
        }
        else {
            System.out.println(n + " khong la so nguyen to.");
        }
    }
    
    public static void bai2(){
        System.out.println("Bang tinh Pythagoras");
        System.out.println("  | 1  2  3  4  5  6  7  8  9");
        System.out.println("--+---------------------------");
        
        for (int i = 1; i <= 9; i++){
            System.out.print(i + "|");
            for (int j = 1; j <=9;j++){
                int result = i * j;
                System.out.printf("%3d", result);
            }
            System.out.println("");
        }
    }
    
    public static void bai3(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong mang: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        
        System.out.println("Nhap mang:");
        for (int i = 0; i < n; i++){
            arr[i] = scanner.nextInt();
        }
        
        //Xuat mang
        System.out.println("Mang: " + Arrays.toString(arr));
        
        //Tim phan tu max, min
        int max = arr[0];
        int min = arr[0];
        
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        
        //Kiem tra ton tai x
        System.out.println("Nhap x: ");
        int x = scanner.nextInt();
        int index = Arrays.binarySearch(arr, x);
        if (index >= 0){
            System.out.println(x + " nam trong mang.");
        }
        else {
            System.out.println(x + "khong nam trong mang.");
        }
        
        //Dem so y trong mang
        System.out.println("Nhap y: ");
        int y = scanner.nextInt();
        int count = 0;
        for (int num : arr){
            if (num == y){
                count++;
            }
        }
        System.out.println("So luong " + y + ": " + count);
        
        //Sap xep tang dan
        Arrays.sort(arr);
        System.out.println("Xep mang: " + Arrays.toString(arr));
    }
    
    public static void bai4(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong mang: ");
        int n = scanner.nextInt();
        int[][] arr = new int[n][n];
        
        System.out.println("Nhap mang: ");
        for(int i = 0; i < n;i++){
            for (int j = 0; j < n;j++){
                arr[n][n] = scanner.nextInt();
            }
        }
        
        //Xuat day hai chieu
        System.out.println("Xuat day hai chieu!");
        for(int[] row : arr){
            System.out.println(Arrays.toString(row));
        }
        
        //Tim gia tri nho nhat cua mang hai chieu
        int min = arr[0][0];
        for(int i = 0; i< n;i++){
            for(int j = 0; j < n;j++){
                if (arr[i][j] < min);
            }
        }
        System.out.println("Min: " + min);
        
        //Sap xep tren tung dong tang dan
        for(int i = 0; i < n; i++){
            Arrays.sort(arr[i]);
        }
        System.out.println("Sap xep tang dan: ");
        for (int[] row: arr){
            System.out.println(Arrays.toString(row));
        }
        
        //Sap xep cac phan tu tren duong cheo tang dan
        int[] cheo = new int[n];
        for(int i = 0; i < n; i++){
            cheo[i] = arr[i][i];
        }
        Arrays.sort(cheo);
        for(int i = 0; i < n; i++){
            arr[i][i] = cheo[i];
        }
        System.out.println("Sap xep cac phan tu tren duong cheo tang dan");
        for(int[] row: arr){
            System.out.println(Arrays.toString(row));
        }
    }
}
