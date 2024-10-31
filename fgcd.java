import java.util.Scanner;
public class fgcd {
    public static int findgcd(int a,int b){
        if(b==0){
            return a;
        }
        return findgcd(b,a%b);
    }
    
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the value of a ");
        int a = sc.nextInt();
         System.out.println("enter the value of b ");
        int b = sc.nextInt();
        
        int gcd=findgcd(a,b);
        
        System.out.println(gcd);
        
        sc.close();
    }
}

