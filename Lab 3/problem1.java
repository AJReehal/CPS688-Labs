import java.util.Scanner;

public class problem1 {

    // Function that solves the Rod Cutting problem
    // using Bottom-Up Dynamic Programming
    public static int bottomUpCutRod(int[] p, int n) {

        // array used to store  MAX revenue
        int[] r = new int[n + 1];

        // Base case:
        // Revenue of a rod with length 0 is 0
        r[0] = 0;

        // for loop used if rod length is from 1 to n
        for (int j = 1; j <= n; j++) {

            // Start with a very small number
            // so any valid revenue will replace it
            int q = Integer.MIN_VALUE;

            for (int i = 1; i <= j; i++) {

                q = Math.max(q, p[i - 1] + r[j - i]);
            }

            r[j] = q;
        }

        // Return the maximum revenue for rod length n
        return r[n];
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {

            int n = scanner.nextInt();

            int[] prices = new int[n];

            for (int i = 0; i < n; i++) {
                prices[i] = scanner.nextInt();
            }

            // Call the dynamic programming function
            int maxRevenue = bottomUpCutRod(prices, n);

            System.out.println(maxRevenue);
        }

        scanner.close();
    }
}