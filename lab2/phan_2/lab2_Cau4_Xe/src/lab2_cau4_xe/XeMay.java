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

public class XeMay extends Xe {
    private static final double TI_LE_XANG_KM = 2;
    private static final double TI_LE_XANG_HANG = 0.1;

    private final Scanner scanner;

    public XeMay() {
        scanner = new Scanner(System.in);
    }

    
    @Override
    public void choHangLenXe() {
        System.out.print("Nhập số lượng hàng cần chở lên xe (kg): ");
        double soKgHang = scanner.nextDouble();
        hang += soKgHang;
        System.out.println("Đã chở lên xe " + soKgHang + " kg hàng.");
    }

    
    @Override
    public void choHangXuongXe() {
        System.out.print("Nhập số lượng hàng cần bớt xuống xe (kg): ");
        double soKgHang = scanner.nextDouble();
        if (soKgHang <= hang) {
            hang -= soKgHang;
            System.out.println("Đã bớt xuống xe " + soKgHang + " kg hàng.");
        } else {
            System.out.println("Không đủ hàng để bớt xuống xe.");
        }
    }

    
    public void doiXang() {
        System.out.print("Nhập số lượng xăng cần đổ vào xe (lit): ");
        double soLitXang = scanner.nextDouble();
        xang += soLitXang;
        System.out.println("Đã đổ " + soLitXang + " lít xăng vào xe.");
    }

    
    @Override
    public void chay() {
        System.out.print("Nhập số km cần chạy: ");
        double soKm = scanner.nextDouble();
        double xangTieuHao = soKm * TI_LE_XANG_KM + hang * TI_LE_XANG_HANG;
        if (xangTieuHao <= xang) {
            xang -= xangTieuHao;
            System.out.println("Xe máy đã chạy " + soKm + " km.");
        } else {
            System.out.println("Không đủ xăng để chạy " + soKm + " km.");
        }
    }
}
