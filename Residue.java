import java.util.Scanner;

class Residue {
    private int number;
    private int modulus;

    // Constructor to initialize the number and modulus
    public Residue(int number, int modulus) {
        this.number = number;
        this.modulus = modulus;
    }

    // Method to calculate the residue
    public int calculateResidue() {
        return ((number % modulus) + modulus) % modulus; // Ensures positive residue
    }

    // Method to display the result
    public void displayResidue() {
        int residue = calculateResidue();
        System.out.println("The residue of " + number + " modulo " + modulus + " is: " + residue);
    }


        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
    
            // Take input from the user
            System.out.print("Enter the number: ");
            int number = scanner.nextInt();
    
            System.out.print("Enter the modulus: ");
            int modulus = scanner.nextInt();
    
            // Create a Residue object and display the result
            Residue residue = new Residue(number, modulus);
            residue.displayResidue();
    
            scanner.close();
        }
    
}


