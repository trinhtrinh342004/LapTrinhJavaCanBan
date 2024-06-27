
package lab1_venha2;

import java.util.Arrays;
import java.util.Scanner;

public class baitap_venha2 {
    //Bai 1
    // Nhap mang A
    public static void nhapArrA(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Nhap so luong mang:");
        int n = scanner.nextInt();
        int[] arrA = new int[n];
        
        System.out.println("Nhap mang: ");
        for (int i = 0; i < n; i++){
            arrA[i] = scanner.nextInt();
        }
    }
    
    
    
    //Tao mang B random
    public static int[] nhapArrB(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Nhap so luong mang:");
        int m = scanner.nextInt();
        int[] arrB = new int[m];
        
        for (int i = 0; i < m; i++){
            arrB[i] = (int) (Math.random() * 100);
        }
        
        return arrB;
    }
    
    //Xuat phan tu B ra man hinh
    public static void xuatArrB(int[] arrB){
        System.out.println("Mang B: " + Arrays.toString(arrB));
    }
    
    
    public static void xuatArrC(int[] arrC){
    System.out.println("Mang C: " + Arrays.toString(arrC));
    }
    
    
    //Bai 2
    public static int[][] nhapMangArrA2Chieu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap n dong: ");
        int n = scanner.nextInt();
        System.out.print("Nhap m cot: ");
        int m = scanner.nextInt();
        int[][] arrA2 = new int[n][m];
        
        System.out.println("Nhap mang: ");
        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                arrA2[i][j] = scanner.nextInt();  
            }
        }
        
        return arrA2;
    }
    
    public static int timPhanTuLonNhat(int[][] arrA2) {
        int max = arrA2[0][0];
        int maxRow = 0;
int maxCol = 0;

        for (int i = 0; i < arrA2.length; i++) {
            for (int j = 0; j < arrA2[i].length; j++) {
                if (arrA2[i][j] > max) {
                    max = arrA2[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("Chi so phan tu lon nhat: [" + maxRow + "][" + maxCol + "]");

        return max;
    }
    
    public static boolean isNguyenTo(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
    
    public static int[][] timSoNguyenTo(int[][] arrA2) {
        int[][] arrPrime = new int[arrA2.length][arrA2[0].length];

        for (int i = 0; i < arrA2.length; i++) {
            for (int j = 0; j < arrA2[i].length; j++) {
                if (isNguyenTo(arrA2[i][j])) {
                    arrPrime[i][j] = arrA2[i][j];
                } else {
                    arrPrime[i][j] = 0;
                }
            }
        }

        return arrPrime;
    }
    
    public static void sapXepCot(int[][] arrA2) {
        for (int j = 0; j < arrA2[0].length; j++) {
            for (int i = 0; i < arrA2.length - 1; i++) {
                for (int k = i + 1; k < arrA2.length; k++) {
                    if (arrA2[i][j] > arrA2[k][j]) {
                        int temp = arrA2[i][j];
                        arrA2[i][j] = arrA2[k][j];
                        arrA2[k][j] = temp;
                    }
                }
            }
        }
    }
    
    public static void xuatMangArrA2Chieu(int[][] arrA2) {
        for (int i = 0; i < arrA2.length; i++) {
            for (int j = 0; j < arrA2[i].length; j++) {
                System.out.print(arrA2[i][j] + " ");
            }
            System.out.println();
        }
    }
}


