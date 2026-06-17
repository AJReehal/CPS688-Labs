import java.util.Scanner;

/*
 * This program solves the N-Queens problem.
 * The goal is to place N queens on an N x N chessboard
 * so that no two queens can attack each other.
 */

public class Problem1NQueens {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter matrix size N: ");

        // Check if the user actually entered a number
        if (scanner.hasNextInt()) {

            int n = scanner.nextInt();

            // Check if the number is valid (must be 1 or greater)
            if (n <= 0) {
                System.out.println("N must be a positive integer.");
                scanner.close();
                return; // Stop the program here
            }

            // Start solving the N-Queens problem
            solveNQueens(n);

        } else {
            // If user did not enter a number, show error message
            System.out.println("Invalid input. Please enter an integer.");
        }

        scanner.close();
    }

    //This function sets up the chessboard and starts the solving process.
    public static void solveNQueens(int n) {

        // Create 2D array filled with 0s
        // 0 = empty square, 1 = queen placed
        int[][] board = new int[n][n];

        // Try to place queens starting from column 0
        if (solveNQueensUtil(board, 0, n)) {

            // If successful print the final board
            printBoard(board);

        } else {
            // If no solution exists
            System.out.println("No solution exists for N = " + n);
        }
    }

    // Main recursive function that tries to place queens column by column.
     
    private static boolean solveNQueensUtil(int[][] board, int col, int n) {

        // BASE CASE: If we placed queens in all columns, we are done
        if (col >= n) {
            return true;
        }

        // Try placing a queen in each row of the current column
        for (int row = 0; row < n; row++) {

            // Check if it's safe to place a queen here
            if (isSafe(board, row, col, n)) {

                // Place the queen 
                board[row][col] = 1;

                // Recursively try to place queens in next column
                if (solveNQueensUtil(board, col + 1, n)) {
                    return true; // solution found
                }

                // If it didn't work backtrack
                board[row][col] = 0;
            }
        }

        // If no position works in this column, return false
        return false;
    }

    /*
     * This function checks if it is safe to place a queen
     * at position (row, col)
     */
    private static boolean isSafe(int[][] board, int row, int col, int n) {

        int i, j;

        // Check left side of the same row
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false; // another queen found
            }
        }

        // Check upper-left diagonal
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower-left diagonal
        for (i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // If no queens are attacking, it's safe
        return true;
    }

    /*
     * This function prints the final board
     */
    private static void printBoard(int[][] board) {

        int n = board.length;

        // Loop through every row
        for (int i = 0; i < n; i++) {

            // Loop through every column in that row
            for (int j = 0; j < n; j++) {

                // Print value (0 or 1) with a space
                System.out.print(board[i][j] + " ");
            }

            // Move to next line after each row
            System.out.println();
        }
    }
}