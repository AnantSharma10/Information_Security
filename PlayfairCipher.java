import java.util.*;

public class PlayfairCipher {
    private char[][] matrix;
    private String key;

    // Constructor to initialize the Playfair matrix
    public PlayfairCipher(String key) {
        this.key = key;
        matrix = createMatrix(key);
    }

    // Method to create the 5x5 matrix for the Playfair cipher
    private char[][] createMatrix(String key) {
        char[][] matrix = new char[5][5];
        Set<Character> used = new HashSet<>();
        StringBuilder keyBuilder = new StringBuilder();

        // Process key and remove duplicates
        for (char c : key.toLowerCase().toCharArray()) {
            if (c == 'j') c = 'i'; // Treat 'i' and 'j' as the same
            if (c >= 'a' && c <= 'z' && !used.contains(c)) {
                keyBuilder.append(c);
                used.add(c);
            }
        }

        // Add remaining letters to the matrix
        for (char c = 'a'; c <= 'z'; c++) {
            if (c == 'j') continue; // Skip 'j' since we treat 'i' and 'j' the same
            if (!used.contains(c)) {
                keyBuilder.append(c);
                used.add(c);
            }
        }

        // Fill the matrix with the key
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = keyBuilder.charAt(index++);
            }
        }

        return matrix;
    }

    // Method to format plaintext for encryption
    private List<String> formatPlainText(String text) {
        List<String> pairs = new ArrayList<>();
        StringBuilder filteredText = new StringBuilder();

        // Remove symbols and convert to lowercase
        for (char c : text.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                if (c == 'j') c = 'i'; // Treat 'i' and 'j' as the same
                filteredText.append(c);
            }
        }

        // Form pairs of characters
        for (int i = 0; i < filteredText.length(); i++) {
            char first = filteredText.charAt(i);
            char second = (i + 1 < filteredText.length()) ? filteredText.charAt(i + 1) : 'x';

            if (first == second) { // If characters in a pair are the same, replace second with 'x'
                pairs.add("" + first + 'x');
            } else {
                pairs.add("" + first + second);
                i++; // Move to the next pair
            }
        }

        // If there's an odd character left, add 'x' to make a pair
        if (filteredText.length() % 2 != 0) {
            pairs.add("" + filteredText.charAt(filteredText.length() - 1) + 'x');
        }

        return pairs;
    }

    // Method to get position of character in matrix
    private int[] getPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // Encrypt a pair of characters
    private String encryptPair(String pair) {
        char first = pair.charAt(0);
        char second = pair.charAt(1);
        int[] pos1 = getPosition(first);
        int[] pos2 = getPosition(second);

        if (pos1[0] == pos2[0]) { // Same row
            return "" + matrix[pos1[0]][(pos1[1] + 1) % 5] + matrix[pos2[0]][(pos2[1] + 1) % 5];
        } else if (pos1[1] == pos2[1]) { // Same column
            return "" + matrix[(pos1[0] + 1) % 5][pos1[1]] + matrix[(pos2[0] + 1) % 5][pos2[1]];
        } else { // Rectangle
            return "" + matrix[pos1[0]][pos2[1]] + matrix[pos2[0]][pos1[1]];
        }
    }

    // Encrypt the plaintext
    public String encrypt(String plainText) {
        List<String> pairs = formatPlainText(plainText);
        StringBuilder cipherText = new StringBuilder();

        for (String pair : pairs) {
            cipherText.append(encryptPair(pair));
        }

        return cipherText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking the plaintext input from the user
        System.out.print("Enter the plaintext: ");
        String plainText = scanner.nextLine();

        // Taking the key input from the user
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        PlayfairCipher playfairCipher = new PlayfairCipher(key);

        // Encrypt the plaintext
        String cipherText = playfairCipher.encrypt(plainText);

        // Display the results
        System.out.println("Plain Text: " + plainText);
        System.out.println("Key: " + key);
        System.out.println("Cipher Text: " + cipherText);

        scanner.close();
    }
}
