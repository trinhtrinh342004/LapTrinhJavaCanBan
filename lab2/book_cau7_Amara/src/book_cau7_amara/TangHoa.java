/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book_cau7_amara;

import java.util.Scanner;

/**
 *
 * @author Trinh
 */
public class TangHoa extends DichVu {
    private int dongiahoa;
    private int soluong;
    public TangHoa(){
        super.loaiDV = 3;
    }
    
    @Override
    public void nhap(){
        Scanner in = new Scanner (System.in);
        System.out.println("Nhap vao so luong hoa: ");
        soluong = in.nextInt();
        System.out.println("Nhap vao don gia hoa: ");
        dongiahoa = in.nextInt();
    }
    
    @Override
    public void tinhChiPhi(){
        super.chiphi = soluong * dongiahoa;
    }
}
