
package lap1_venha1;

public class baitap_venha1 {
    public static int timUCLN(int x, int y){
        x = Math.abs(x);
        y = Math.abs(y);
        
        while (y != 0){
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
    
    public static int timBCNN(int x, int y){
        return Math.abs(x * y) / timUCLN(x, y);
    }
    
    
}
