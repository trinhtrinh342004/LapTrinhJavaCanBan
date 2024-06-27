/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_phan1;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class PhanSo {
    private int tuSo;
    private int mauSo;
    
    public PhanSo(){
     
    }
    
    public PhanSo(int tuSo, int mauSo) {
        this.tuSo = tuSo;
        this.mauSo = mauSo;
    }
    
    public void nhapPhanSo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu so: ");
        tuSo = sc.nextInt();
        
        do {
            System.out.print("Nhap mau so (khac 0): ");
            mauSo = sc.nextInt();
        } while (mauSo == 0);
    }
    
        public void inPhanSo() {
        if (mauSo == 0 && tuSo == 0) {
            System.out.print("Khong xac dinh");
        } else if (tuSo == 0) {
            System.out.print(0);
        } else if (mauSo == 1) {
            System.out.print(tuSo);
        } else {
            System.out.print(tuSo + "/" + mauSo);
        }
    }
    
    public int timUCLN(int a, int b){
        while (a != b){
            if(a > b){
                a -= b;
            }
            else{
                b -=a;
            }
        }
        return a;
    }
    
    public void rutGonPhanSo() {
        int ucln = timUCLN(Math.abs(tuSo), Math.abs(mauSo));
        tuSo /= ucln;
        mauSo /= ucln;
    }
    
    public PhanSo cong(PhanSo ps){
        int ts = tuSo * ps.mauSo + ps.tuSo * mauSo;
        int ms = mauSo + ps.mauSo;
        return new PhanSo(ts, ms);
    }
    
    public PhanSo tru(PhanSo ps){
        int ts = tuSo * ps.mauSo - ps.tuSo * mauSo;
        int ms = mauSo + ps.mauSo;
        return new PhanSo(ts, ms);
    }
    
    public PhanSo nhan(PhanSo ps){
        int ts = tuSo * ps.tuSo;
        int ms = mauSo * ps.mauSo;
        return new PhanSo(ts, ms);
    }
    
    public PhanSo chia(PhanSo ps){
        int ts = tuSo * ps.mauSo;
        int ms = mauSo * ps.tuSo;
        return new PhanSo(ts, ms);
    }
}
