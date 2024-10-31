
import java.util.Scanner;

public class PBoxCipher {

    // Sample permutation pattern (for simplicity, should match text length)
    private static final int[] PERMUTATION_PATTERN = {3, 1, 4, 0, 2}; // Example permutation

    // Method to encrypt the text using P-Box
    public static String encrypt(String plaintext) {
        int length = PERMUTATION_PATTERN.length;
        char[] encrypted = new char[length];
        
        // Apply permutation pattern to encrypt
        for (int i = 0; i < length; i++) {
            encrypted[PERMUTATION_PATTERN[i]] = plaintext.charAt(i);
        }
        
        return new String(encrypted);
    }

    // Method to decrypt the text using P-Box
    public static String decrypt(String ciphertext) {
        int length = PERMUTATION_PATTERN.length;
        char[] decrypted = new char[length];
        
        // Reverse the permutation to decrypt
        for (int i = 0; i < length; i++) {
            decrypted[i] = ciphertext.charAt(PERMUTATION_PATTERN[i]);
        }
        
        return new String(decrypted);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the plaintext (should be of the same length as permutation pattern for simplicity)
        System.out.print("Enter the plaintext (5 characters): ");
        String plaintext = scanner.nextLine();

        // Validate length
        if (plaintext.length() != PERMUTATION_PATTERN.length) {
            System.out.println("Error: Plaintext length must be exactly " + PERMUTATION_PATTERN.length + " characters.");
            return;
        }

        // Encrypt the plaintext
        String cipherText = encrypt(plaintext);
        System.out.println("Encrypted Ciphertext: " + cipherText);

        // Decrypt the ciphertext
        String decryptedText = decrypt(cipherText);
        System.out.println("Decrypted Plaintext: " + decryptedText);

        scanner.close();
    }
}
