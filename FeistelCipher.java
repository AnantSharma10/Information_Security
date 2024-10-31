import java.util.Scanner;

public class FeistelCipher {

    // Number of rounds in the Feistel Cipher
    private static final int NUM_ROUNDS = 4;

    // Simple round function (for demonstration purposes)
    private static int roundFunction(int halfBlock, int roundKey) {
        return (halfBlock ^ roundKey); // XOR operation with the round key
    }

    // Feistel encryption function
    public static String encrypt(String plaintext, int key) {
        // Convert plaintext into two halves (L0 and R0)
        int blockSize = plaintext.length() / 2;
        String left = plaintext.substring(0, blockSize);
        String right = plaintext.substring(blockSize);

        int leftBlock = left.hashCode();
        int rightBlock = right.hashCode();

        // Perform Feistel rounds
        for (int i = 0; i < NUM_ROUNDS; i++) {
            int roundKey = (key + i) & 0xFF; // Simple round key generation
            int temp = rightBlock;
            rightBlock = leftBlock ^ roundFunction(rightBlock, roundKey);
            leftBlock = temp;
        }

        // Combine the two halves
        return Integer.toHexString(leftBlock) + Integer.toHexString(rightBlock);
    }

    // Feistel decryption function
    public static String decrypt(String ciphertext, int key) {
        // Split the ciphertext into two halves
        int blockSize = ciphertext.length() / 2;
        int leftBlock = Integer.parseInt(ciphertext.substring(0, blockSize), 16);
        int rightBlock = Integer.parseInt(ciphertext.substring(blockSize), 16);

        // Perform Feistel rounds in reverse
        for (int i = NUM_ROUNDS - 1; i >= 0; i--) {
            int roundKey = (key + i) & 0xFF; // Reverse round key generation
            int temp = leftBlock;
            leftBlock = rightBlock ^ roundFunction(leftBlock, roundKey);
            rightBlock = temp;
        }

        // Combine the two halves into the original plaintext
        return new String(new int[]{rightBlock, leftBlock}, 0, 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the plaintext
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        // Input the key
        System.out.print("Enter the key (integer): ");
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
