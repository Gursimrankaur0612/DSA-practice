import java.util.Scanner;
public class xybinarysearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();    
        int result = 1;
        int base = x;
        int exponent = y;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base;
            }
            base = base * base;
            exponent = exponent >> 1;
        }
        System.out.println(result);
        sc.close();

        
    }
}
