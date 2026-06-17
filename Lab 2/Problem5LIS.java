import java.util.ArrayList;
import java.util.Arrays;    
import java.util.Scanner;   

public class Problem5LIS {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); 

        if (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            // Make sure the line is not empty or just spaces
            if (!line.trim().isEmpty()) {

                // Split the line by spaces into individual number strings
                String[] tokens = line.trim().split("\\s+");

                int[] a = new int[tokens.length];

                // Convert each string token into an integer
                for (int i = 0; i < tokens.length; i++) {
                    a[i] = Integer.parseInt(tokens[i]);
                }

                // Call function to compute LIS
                findLIS(a);
            }
        }

        scanner.close(); 
    }

    private static void findLIS(int[] a) {

        // Step 1: Create array B
        // Remove duplicates and sort the array
        // This represents the sorted version of A
        int[] b = Arrays.stream(a).distinct().sorted().toArray();

        int m = a.length; // Length of original array A
        int n = b.length; // Length of sorted unique array B

        // Create  table for LCS
        int[][] dp = new int[m + 1][n + 1];

        // Fill  table  bottom-up 
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                // Base case: if either string is empty LCS is 0
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;

                }
                // If elements match extend previous LCS
                else if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                }
                // Otherwise take the maximum of ignoring one element
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The bottom-right cell contains the LIS length
        int lisLength = dp[m][n];

        System.out.println("LIS=" + lisLength);

        ArrayList<Integer> lcsElements = new ArrayList<>();

        // Start backtracking from bottom-right corner of DP table
        int i = m, j = n;

        // Move backwards through the table to reconstruct LIS
        while (i > 0 && j > 0) {

            // If elements match, it is part of LIS
            if (a[i - 1] == b[j - 1]) {

                // Add element to front of list (to maintain correct order)
                lcsElements.add(0, a[i - 1]);

                // Move diagonally up-left in DP table
                i--;
                j--;

            }
            // If top value is greater or equal, move up
            else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;

            }
            // Otherwise move left
            else {
                j--;
            }
        }

        // Print final LIS sequence
        System.out.print("LIS is: ");

        for (int k = 0; k < lcsElements.size(); k++) {

            System.out.print(lcsElements.get(k) + (k == lcsElements.size() - 1 ? "" : " "));
        }

        System.out.println(); 
    }
}