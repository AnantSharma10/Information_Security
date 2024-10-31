import java.util.Scanner;

public class MatrixOperations {

    // Method to find the determinant of a matrix
    public static int determinant(int[][] matrix, int n) {
        int det = 0;
        if (n == 1) {
            return matrix[0][0];
        }
        
        int[][] temp = new int[n][n];
        int sign = 1;
        
        for (int f = 0; f < n; f++) {
            getCofactor(matrix, temp, 0, f, n);
            det += sign * matrix[0][f] * determinant(temp, n - 1);
            sign = -sign;
        }
        return det;
    }

    // Method to get cofactor of matrix
    public static void getCofactor(int[][] matrix, int[][] temp, int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = matrix[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    // Method to calculate and return the transpose of a matrix
    public static int[][] transpose(int[][] matrix, int n) {
        int[][] transposeMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposeMatrix[i][j] = matrix[j][i];
            }
        }
        return transposeMatrix;
    }

    // Method to get adjoint of the matrix
    public static int[][] adjoint(int[][] matrix, int n) {
        int[][] adjointMatrix = new int[n][n];
        if (n == 1) {
            adjointMatrix[0][0] = 1;
            return adjointMatrix;
        }
        
        int sign;
        int[][] temp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                getCofactor(matrix, temp, i, j, n);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adjointMatrix[j][i] = sign * determinant(temp, n - 1);
            }
        }
        return adjointMatrix;
    }

    // Method to find the inverse of the matrix
    public static float[][] inverse(int[][] matrix, int n) {
        int det = determinant(matrix, n);
        if (det == 0) {
            throw new ArithmeticException("Matrix is singular and cannot have an inverse.");
        }

        int[][] adj = adjoint(matrix, n);
        float[][] inverseMatrix = new float[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseMatrix[i][j] = adj[i][j] / (float) det;
            }
        }
        return inverseMatrix;
    }

    // Displaying a matrix
    public static void displayMatrix(float[][] matrix) {
        for (float[] row : matrix) {
            for (float value : row) {
                System.out.printf("%.2f ", value);
            }
            System.out.println();
        }
    }
    
    public static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input matrix size
        System.out.print("Enter the size of the matrix (n x n): ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("\nOriginal Matrix:");
        displayMatrix(matrix);

        // Determinant
        int det = determinant(matrix, n);
        System.out.println("\nDeterminant of the matrix: " + det);

        // Cofactor
        int[][] cofactorMatrix = adjoint(matrix, n);
        System.out.println("\nCofactor Matrix:");
        displayMatrix(cofactorMatrix);

        // Transpose
        int[][] transposeMatrix = transpose(matrix, n);
        System.out.println("\nTranspose of the matrix:");
        displayMatrix(transposeMatrix);

        // Adjoint
        System.out.println("\nAdjoint of the matrix:");
        displayMatrix(cofactorMatrix);

        // Inverse
        if (det != 0) {
            System.out.println("\nInverse of the matrix:");
            float[][] inverseMatrix = inverse(matrix, n);
            displayMatrix(inverseMatrix);
        } else {
            System.out.println("\nInverse does not exist as the determinant is 0.");
        }

        scanner.close();
    }
}
