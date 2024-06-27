/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_cau4_xe;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public abstract class Xe {
    protected double xang;
    protected double hang;
    
    public void doiXang() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so lit xang do:");
        double soLitXang = scanner.nextDouble();
        xang += soLitXang;
        System.out.println("Đã đổ " + soLitXang + " lít xăng vào xe.");
    }

    public abstract void choHangLenXe();

    public abstract void choHangXuongXe();

    public abstract void chay();

    public void kiemTraHetXang() {
        if (xang <= 0) {
            System.out.println("Xe đã hết xăng.");
        } else {
            System.out.println("Xe còn đủ xăng để tiếp tục chạy.");
        }
    }

    public void luongXangConLai() {
        System.out.println("Lượng xăng còn lại trong xe: " + xang + " lít.");
    }
}
