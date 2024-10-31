import java.util.Scanner;

public class TranspositionCipher {

    // Method to encrypt using Transposition Cipher
    public static String encrypt(String text, int key) {
        // Calculate number of rows needed
        int numRows = (int) Math.ceil((double) text.length() / key);
        char[][] grid = new char[numRows][key];
        
        // Fill the grid with characters of the text
        int index = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key; col++) {
                if (index < text.length()) {
                    grid[row][col] = text.charAt(index++);
                } else {
                    grid[row][col] = 'X'; // Padding with 'X' if needed
                }
            }
        }

        // Read the grid column by column to create the ciphertext
        StringBuilder cipherText = new StringBuilder();
        for (int col = 0; col < key; col++) {
            for (int row = 0; row < numRows; row++) {
                cipherText.append(grid[row][col]);
            }
        }

        return cipherText.toString();
    }

    // Method to decrypt using Transposition Cipher
    public static String decrypt(String cipherText, int key) {
        // Calculate number of rows
        int numRows = (int) Math.ceil((double) cipherText.length() / key);
        char[][] grid = new char[numRows][key];
        
        // Fill the grid column by column with the cipher text
        int index = 0;
        for (int col = 0; col < key; col++) {
            for (int row = 0; row < numRows; row++) {
                if (index < cipherText.length()) {
                    grid[row][col] = cipherText.charAt(index++);
                }
            }
        }

        // Read the grid row by row to create the decrypted text
        StringBuilder plainText = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key; col++) {
                plainText.append(grid[row][col]);
            }
        }

        // Remove padding 'X' if present at the end
        while (plainText.charAt(plainText.length() - 1) == 'X') {
            plainText.deleteCharAt(plainText.length() - 1);
        }

        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the plaintext
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().replaceAll("\\s+", "").toUpperCase();

        // Input the key
        System.out.print("Enter the key (number of columns): ");
        int key = scanner.nextInt();

        // Encrypt the plaintext
        String cipherText = encrypt(plaintext, key);
        System.out.println("Encrypted Ciphertext: " + cipherText);

        // Decrypt the ciphertext
        String decryptedText = decrypt(cipherText, key);
        System.out.println("Decrypted Plaintext: " + decryptedText);

        scanner.close();
    }
}
