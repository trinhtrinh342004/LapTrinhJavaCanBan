/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab2_cau4_xe;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class Lab2_Cau4_Xe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chon loai xe (1 - Xe may, 2 - Xe tai):");
        int choice = scanner.nextInt();

        Xe xe;
        switch (choice) {
            case 1 -> xe = new XeMay();
            case 2 -> xe = new XeTai();
            default -> {
                System.out.println("Lua chon khong hop le.");
                return;
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("Chon tac vu (1 - Them hang, 2 - Bot hang, 3 - Đo xang, 4 - Chay, 5 - Thoat):");
            int task = scanner.nextInt();

            switch (task) {
                case 1 -> xe.choHangLenXe();
                case 2 -> xe.choHangXuongXe();
                case 3 -> xe.doiXang();
                case 4 -> xe.chay();
                case 5 -> exit = true;
                default -> System.out.println("Lua chon khong hop le.");
            }
        }

        System.out.println("Exit.......");
    }
}
