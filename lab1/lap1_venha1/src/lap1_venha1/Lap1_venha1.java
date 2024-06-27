
package lap1_venha1;

import java.util.Scanner;
import static lap1_venha1.baitap_venha1.timBCNN;
import static lap1_venha1.baitap_venha1.timUCLN;

public class Lap1_venha1 {

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
                    bai3();
                    break;
                case 4:
                    bai4();
                    break;
                case 5:
                    System.out.println("Exit.....");
                    break;
                default:
                    System.out.println("Invalid. Try again!!!");
            }
            System.out.println("");
        } while (choice != 5);
    }
    public static void displayMenu(){
        System.out.println("############################################");
        System.out.println("    1. Bai 1");
        System.out.println("    2. Bai 2.");
        System.out.println("    3. Bai 3.");
        System.out.println("    4. Bai 4.");
        System.out.println("    5. Thoat chuong trinh.");
        System.out.println("############################################");
        System.out.print("Chon chuc nang: ");
    }
    
    public static void bai1(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap x: ");
        int x = scanner.nextInt();
        System.out.print("Nhap y: ");
        int y = scanner.nextInt();
        
        int ucln = timUCLN(x,y);
        System.out.println("UCLN cua "+ x + " va " + y +" :" + ucln);
    }
    
    public static void bai2(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap x: ");
        int x = scanner.nextInt();
        System.out.print("Nhap y: ");
        int y = scanner.nextInt();
        
        int bcnn = timBCNN(x,y);
        System.out.println("BCNN cua "+ x + " va " + y +" :" + bcnn);
    }
    
    public static void bai3(){
        PhanSo ps = new PhanSo(0, 1);
        ps.nhapPhanSo();
        ps.rutGonPhanSo();
        System.out.print("Phan so rut gon: ");
        ps.inPhanSo();
    }
    
    public static void bai4(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhap so nguyen duong n: ");
        int n = scanner.nextInt();
        
        System.out.print("Cac uoc so cua " + n + " la: ");
        for (int i = 1; i <= n; i++){
            if (n % i == 0){
                System.out.print(i + " ");
            }
        }
    }
}
