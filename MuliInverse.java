//Write a program (WAP) in Java for finding the multiplicative inverse of n numbers.

import java.util.Scanner;

public class MuliInverse {
    public static int multiplicativeInverse(int a, int n) {
        int p = -1;
        for (int b = 1; b < n; b++) {
            if ((a * b) % n == 1) {
                p = b;
                return p;
            }
        }
        return p;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Taking inputs for 'a' and 'n' from the user
        System.out.print("Enter the value of a: ");
        int a = scanner.nextInt();
        
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        
        int result = multiplicativeInverse(a, n);

        if (result == -1) {
            System.out.println("Multiplicative inverse does not exist for " + a + " modulo " + n);
        } else {
            System.out.println("The multiplicative inverse of " + a + " modulo " + n + " is: " + result);
        }
        
        scanner.close();
    }
}
