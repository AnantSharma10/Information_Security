import java.util.Scanner;

public class RailFenceCipher {

    // Method to encrypt using Rail Fence Cipher
    public static String encrypt(String text, int key) {
        // Create an array of strings for each row
        StringBuilder[] rail = new StringBuilder[key];
        for (int i = 0; i < key; i++) {
            rail[i] = new StringBuilder();
        }

        int row = 0;
        boolean directionDown = false;

        // Place characters in a zigzag manner
        for (int i = 0; i < text.length(); i++) {
            rail[row].append(text.charAt(i));

            // Reverse direction when reaching top or bottom
            if (row == 0 || row == key - 1) {
                directionDown = !directionDown;
            }
            row += directionDown ? 1 : -1;
        }

        // Combine all rows to form the ciphertext
        StringBuilder cipherText = new StringBuilder();
        for (StringBuilder sb : rail) {
            cipherText.append(sb);
        }
        
        return cipherText.toString();
    }

    // Method to decrypt using Rail Fence Cipher
    public static String decrypt(String cipherText, int key) {
        // Create an array to mark the position in the zigzag
        boolean[][] mark = new boolean[key][cipherText.length()];

        int row = 0;
        boolean directionDown = false;

        // Mark positions in a zigzag manner
        for (int i = 0; i < cipherText.length(); i++) {
            mark[row][i] = true;

            if (row == 0 || row == key - 1) {
                directionDown = !directionDown;
            }
            row += directionDown ? 1 : -1;
        }

        // Fill the marked positions with the cipher text
        char[] decryptedText = new char[cipherText.length()];
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipherText.length(); j++) {
                if (mark[i][j] && index < cipherText.length()) {
                    decryptedText[j] = cipherText.charAt(index++);
                }
            }
        }

        // Read characters in the zigzag order
        row = 0;
        directionDown = false;
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            plainText.append(decryptedText[i]);

            if (row == 0 || row == key - 1) {
                directionDown = !directionDown;
            }
            row += directionDown ? 1 : -1;
        }

        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the plaintext
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().replaceAll("\\s+", "").toUpperCase();

        // Input the key
        System.out.print("Enter the key (number of rails): ");
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
