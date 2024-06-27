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
public class KSAmara {
    private DichVu array[];
    private int n;
    public void nhap(){
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap vao so luong dich vu: ");
        n = in.nextInt();
        array = new DichVu[n];
        
        for(int i = 0; i< n; i++){
            System.out.println("Chon loai dich vu: " + i);
            int flag;
            System.out.println("Nhap vao lua chon 1. Thue xe 2.Dien thoa 3.Tang hoa");
            flag = in.nextInt();
            switch(flag){
                case 1 ->  {
                    array[i] = new ThueXe();
                    array[i].nhap();
                    array[i].tinhChiPhi();
                }
                case 2 ->  {
                    array[i] = new DienThoai();
                    array[i].nhap();
                    array[i].tinhChiPhi();
                }
                case 3 ->  {
                    array[i] = new TangHoa();
                    array[i].nhap();
                    array[i].tinhChiPhi();
                }
            }
        }
    }
    
    
    public void xuat(){
        for(int i =0; i<n;i++){
            array[i].xuat();
        }
    }
    
    public int timMax(){
        int temp[] = {0,0,0};
        for (int i = 0; i < n; i++){
            switch(array[i].getLoai()){
                case 1 -> temp[0] += array[i].getChiPhi();
                case 2 -> temp[1] += array[i].getChiPhi();
                case 3 -> temp[2] += array[i].getChiPhi();
            }
        }
        
        int max = -1;
        int maxAt = 0;
        for (int i = 0; i < temp.length; i++){
            if(max < temp[i]){
                max = temp[i];
                maxAt = i;
            }
        }
        
        switch(maxAt){
            case 0 -> System.out.println("Loai dich vu thue xe ton nhieu chi phi nhat: " + temp[0]);
            case 1 -> System.out.println("Loai dich vu dien thoai ton nhieu chi phi nhat: " + temp[1]);
            case 2 -> System.out.println("Loai dich vu tang hoa ton nhieu chi phi nhat: " + temp[2]);
        }
        
        return (temp[0] + temp[1] + temp[2]);
    }
}
