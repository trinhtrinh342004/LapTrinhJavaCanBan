
package lab1_phan2;

public class baitap_phan2 {
    public static boolean kiemTraSoNguyenTo(int n){
        boolean ok = true;
        
        if (n < 2) {
            return false;
        }
        
        for (int i = 2; i < n; i++){
            if (n % i == 0){
                ok = false;
                break;
            }
        }
        return ok;
    }
}
