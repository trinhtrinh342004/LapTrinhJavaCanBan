
package lab1_venha2;

import java.util.Arrays;
import java.util.Scanner;
import static lab1_venha2.baitap_venha2.nhapArrA;
import static lab1_venha2.baitap_venha2.nhapArrB;
import static lab1_venha2.baitap_venha2.nhapMangArrA2Chieu;
import static lab1_venha2.baitap_venha2.sapXepCot;
import static lab1_venha2.baitap_venha2.timPhanTuLonNhat;
import static lab1_venha2.baitap_venha2.timSoNguyenTo;
import static lab1_venha2.baitap_venha2.xuatArrB;
import static lab1_venha2.baitap_venha2.xuatArrC;
import static lab1_venha2.baitap_venha2.xuatMangArrA2Chieu;

public class Lab1_venha2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bai1();
                    break;
                case 2:
                    bai2();
                    break;                
                case 3:
                    System.out.println("Exit.....");
                    break;
                default:
                    System.out.println("Invalid. Try again!!!");
            }
            System.out.println("");
        } while (choice != 3);
    }
    
    public static void displayMenu(){
        System.out.println("############################################");
        System.out.println("    1. Bai 1");
        System.out.println("    2. Bai 2.");
        System.out.println("    3. Thoat chuong trinh.");
        System.out.println("############################################");
        System.out.print("Chon chuc nang: ");
    }
    
    public static void bai1(){
        nhapArrA();
        int[] myArrB = nhapArrB();
        xuatArrB(myArrB);
         int[] myArrC = Arrays.copyOf(myArrB, myArrB.length);
        System.arraycopy(myArrB, myArrB.length - 3, myArrC, 1, 3);
        Arrays.sort(myArrC);
        xuatArrC(myArrC);
    }
    
    public static void bai2(){
        int[][] arrA2 = nhapMangArrA2Chieu();
        
        int max = timPhanTuLonNhat(arrA2);
        System.out.println("Phan tu lon nhat: " + max);
        
        int[][] arrPrime = timSoNguyenTo(arrA2);
        System.out.println("Cac phan tu la so nguyen to:");
        xuatMangArrA2Chieu(arrPrime);
        
        sapXepCot(arrA2);
        System.out.println("Mang sau khi sap xep cac cot:");
        xuatMangArrA2Chieu(arrA2);
    }
}
