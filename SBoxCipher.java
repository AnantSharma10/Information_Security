import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SBoxCipher {

    // Define a simple S-box substitution map
    private static final Map<Character, Character> sBox = new HashMap<>();
    private static final Map<Character, Character> inverseSBox = new HashMap<>();

    // Initialize the S-box and its inverse
    static {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String substitution = "QWERTYUIOPASDFGHJKLZXCVBNM";
        
        // Populate S-box and inverse S-box
        for (int i = 0; i < alphabet.length(); i++) {
            sBox.put(alphabet.charAt(i), substitution.charAt(i));
            inverseSBox.put(substitution.charAt(i), alphabet.charAt(i));
        }
    }

    // Method to encrypt the text using S-box
    public static String encrypt(String plainText) {
        StringBuilder cipherText = new StringBuilder();
        
        for (char ch : plainText.toUpperCase().toCharArray()) {
            if (sBox.containsKey(ch)) {
                cipherText.append(sBox.get(ch)); // Substitute using S-box
            } else {
                cipherText.append(ch); // Keep non-alphabet characters unchanged
            }
        }
        
        return cipherText.toString();
    }

    // Method to decrypt the text using inverse S-box
    public static String decrypt(String cipherText) {
        StringBuilder plainText = new StringBuilder();
        
        for (char ch : cipherText.toUpperCase().toCharArray()) {
            if (inverseSBox.containsKey(ch)) {
                plainText.append(inverseSBox.get(ch)); // Substitute using inverse S-box
            } else {
                plainText.append(ch); // Keep non-alphabet characters unchanged
            }
        }
        
        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the plaintext
        System.out.print("Enter the plaintext: ");
        String plainText = scanner.nextLine();

        // Encrypt the plaintext
        String cipherText = encrypt(plainText);
        System.out.println("Encrypted Ciphertext: " + cipherText);

        // Decrypt the ciphertext
        String decryptedText = decrypt(cipherText);
        System.out.println("Decrypted Plaintext: " + decryptedText);

        scanner.close();
    }
}
