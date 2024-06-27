/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lap1_venha1;

/**
 *
 * @author Trinh
 */
import java.util.Scanner;

public class PhanSo {
    private int tuSo;
    private int mauSo;

    public PhanSo(int tuSo, int mauSo) {
        this.tuSo = tuSo;
        this.mauSo = mauSo;
    }

    public void nhapPhanSo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu so: ");
        tuSo = scanner.nextInt();
        do {
            System.out.print("Nhap mau so(khac 0): ");
            mauSo = scanner.nextInt();
        } while (mauSo == 0);
    }

    public void rutGonPhanSo() {
        int ucln = timUCLN(tuSo, mauSo);
        tuSo /= ucln;
        mauSo /= ucln;
    }

    private int timUCLN(int a, int b) {
        if (b == 0) {
            return a;
        }
        return timUCLN(b, a % b);
    }

    public void inPhanSo() {
        if (mauSo == 1) {
            System.out.println(tuSo);
        } else {
            System.out.println(tuSo + "/" + mauSo);
        }
    }
}
