import java.util.Scanner;

public class Problem4Knap {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {

            // Number of items
            int n = scanner.nextInt();

            // Array to store  values of each item
            int[] values = new int[n];

            // Read all values into the values array
            for (int i = 0; i < n; i++) {
                values[i] = scanner.nextInt();
            }

            // Array to store weights of each item
            int[] weights = new int[n];

            // Read all weights into the weights array
            for (int i = 0; i < n; i++) {
                weights[i] = scanner.nextInt();
            }

            // Maximum weight capacity of the bag
            int w = scanner.nextInt();

            // Call knapsack function and print the maximum sentimental value
            System.out.println(knapsack(w, weights, values, n));
        }

        scanner.close();
    }

    /**
     * This function solves the 0/1 Knapsack problem using Dynamic Programming. Returns the maximum sentimental value that can be put in a bag of capacity W.
     *
     * W maximum weight capacity of the bag
     *  wt array of item weights
     * val array of item values (sentimental value)
     * n number of items
     */

    private static int knapsack(int W, int[] wt, int[] val, int n) {

       
        int[][] dp = new int[n + 1][W + 1];

        // Build the table row by row
        for (int i = 0; i <= n; i++) {

            // Build each capacity column
            for (int w = 0; w <= W; w++) {

                // Base case:
                // If no items OR no capacity, value is 0
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }

                // If current item weight is less than or equal to capacity
                else if (wt[i - 1] <= w) {

                    // We have 2 choices:
                    // 1. Take the item,  add its value + remaining capacity result
                    // 2. Don't take it, keep previous best value

                    dp[i][w] = Math.max(
                        val[i - 1] + dp[i - 1][w - wt[i - 1]],
                        dp[i - 1][w]
                    );
                }

                // If item is too heavy, we cannot take it
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }
}