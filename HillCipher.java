import java.util.Scanner;

public class HillCipher {

    private static int[][] keyMatrix = new int[2][2];
    private static int[] plaintextVector = new int[2];
    private static int[] cipherVector = new int[2];

    // Method to get the key matrix
    public static void getKeyMatrix(String key) {
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = (key.charAt(k)) % 65;
                k++;
            }
        }
    }

    // Method to perform encryption
    public static String encrypt(String plaintext, String key) {
        getKeyMatrix(key);
        int length = plaintext.length();
        StringBuilder cipherText = new StringBuilder();

        // Process each 2-character block
        for (int i = 0; i < length; i += 2) {
            plaintextVector[0] = (plaintext.charAt(i) - 'A') % 26;
            plaintextVector[1] = (plaintext.charAt(i + 1) - 'A') % 26;

            // Matrix multiplication
            for (int j = 0; j < 2; j++) {
                cipherVector[j] = 0;
                for (int k = 0; k < 2; k++) {
                    cipherVector[j] += keyMatrix[j][k] * plaintextVector[k];
                }
                cipherVector[j] %= 26;
            }

            // Convert the result to characters and append to the ciphertext
            cipherText.append((char) (cipherVector[0] + 'A'));
            cipherText.append((char) (cipherVector[1] + 'A'));
        }

        return cipherText.toString();
    }

    // Method to find the multiplicative inverse of a number modulo 26
    public static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    // Method to calculate the decryption key (inverse of the key matrix)
    public static void getDecryptionKeyMatrix() {
        int determinant = (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]) % 26;
        determinant = determinant < 0 ? determinant + 26 : determinant;
        int inverseDeterminant = modInverse(determinant, 26);

        int[][] adjoint = new int[2][2];
        adjoint[0][0] = keyMatrix[1][1];
        adjoint[1][1] = keyMatrix[0][0];
        adjoint[0][1] = -keyMatrix[0][1];
        adjoint[1][0] = -keyMatrix[1][0];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = (adjoint[i][j] * inverseDeterminant) % 26;
                keyMatrix[i][j] = keyMatrix[i][j] < 0 ? keyMatrix[i][j] + 26 : keyMatrix[i][j];
            }
        }
    }

    // Method to perform decryption
    public static String decrypt(String ciphertext, String key) {
        getKeyMatrix(key);
        getDecryptionKeyMatrix();
        int length = ciphertext.length();
        StringBuilder plainText = new StringBuilder();

        // Process each 2-character block
        for (int i = 0; i < length; i += 2) {
            plaintextVector[0] = (ciphertext.charAt(i) - 'A') % 26;
            plaintextVector[1] = (ciphertext.charAt(i + 1) - 'A') % 26;

            // Matrix multiplication
            for (int j = 0; j < 2; j++) {
                cipherVector[j] = 0;
                for (int k = 0; k < 2; k++) {
                    cipherVector[j] += keyMatrix[j][k] * plaintextVector[k];
                }
                cipherVector[j] %= 26;
            }

            // Convert the result to characters and append to the plaintext
            plainText.append((char) (cipherVector[0] + 'A'));
            plainText.append((char) (cipherVector[1] + 'A'));
        }

        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input plaintext
        System.out.print("Enter the plaintext (in uppercase, even length): ");
        String plaintext = scanner.nextLine().replaceAll("[^A-Z]", "").toUpperCase();

        // Input key for the matrix
        System.out.print("Enter the 4-letter key (in uppercase): ");
        String key = scanner.nextLine().toUpperCase();

        if (plaintext.length() % 2 != 0) {
            plaintext += "X"; // Padding with 'X' if plaintext length is odd
        }

        // Encryption
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted Ciphertext: " + ciphertext);

        // Decryption
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted Plaintext: " + decryptedText);

        scanner.close();
    }
}

