package exercises;

public class ex2 {
    public static void main(String[] args)
    {
        // initialize the 2 matrixes of type int
        int[][] matrix1 = {{2, 3, 1},{7, 1, 6},{9, 2, 4}};
        int[][] matrix2 = {{8, 5, 3}, {3, 9, 2}, {2, 7, 3}};

        // sum of matrix 1 and 2
        int[][] sum = matrixSum(matrix1, matrix2);
        // product of matrix 1 and 2
        int[][] product = matrixProduct(matrix1, matrix2);

        System.out.println("Sum of matrices:");
        printMatrix(sum);

        System.out.println("\nProduct of matrices:");
        printMatrix(product);
    }

    //method for calculation
    public static int[][] matrixSum(int[][] matrix1, int[][] matrix2)
    {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    //method to calculate product
    public static int[][] matrixProduct(int[][] matrix1, int[][] matrix2)
    {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < cols; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    // method to print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

