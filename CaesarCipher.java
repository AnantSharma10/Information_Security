// java code for caesar_cipher

import java.util.Scanner;

public class CaesarCipher {
    
    // Method to encrypt the plaintext
    public static String encrypt(String text, int key) {
        StringBuilder cipherText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                int originalPosition = c - 'a';
                int newPosition = (originalPosition + key) % 26;
                char newCharacter = (char) (newPosition + 'a');
                cipherText.append(newCharacter);
            }
        }
        return cipherText.toString();
    }
    
    // Method to decrypt the ciphertext
    public static String decrypt(String cipherText, int key) {
        StringBuilder plainText = new StringBuilder();
        for (char c : cipherText.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                int originalPosition = c - 'a';
                int newPosition = (originalPosition - key + 26) % 26;  // +26 to handle negative values
                char newCharacter = (char) (newPosition + 'a');
                plainText.append(newCharacter);
            }
        }
        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking plaintext input from user
        System.out.print("Enter the plaintext: ");
        String plainText = scanner.nextLine();

        // Convert text to lowercase and remove non-alphabet characters
        StringBuilder filteredText = new StringBuilder();
        for (char c : plainText.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                filteredText.append(c);
            }
        }

        // Taking key input from user
        System.out.print("Enter the key: ");
        int key = scanner.nextInt();

        // Encrypting the text
        String encryptedText = encrypt(filteredText.toString(), key);
        
        // Decrypting the text
        String decryptedText = decrypt(encryptedText, key);

        // Displaying results
       // System.out.println("Plain Text: " + plainText);
      //  System.out.println("Filtered Plain Text (no symbols): " + filteredText.toString());
        //System.out.println("Key: " + key);
        System.out.println("Cipher Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}

