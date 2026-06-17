import java.util.*;

public class Q2 {

    static final int d = 256; // number of characters
    static final int q = 101; // prime number

    static void rabinKarpSearch(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int patternHash = 0;
        int textHash = 0;
        int h = 1;

        ArrayList<Integer> positions = new ArrayList<>();

        // Calculate h = pow(d, m-1) % q
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }

        // Initial hash values
        for (int i = 0; i < m; i++) {
            patternHash = (d * patternHash + pattern.charAt(i)) % q;
            textHash = (d * textHash + text.charAt(i)) % q;
        }

        // Slide pattern over text
        for (int i = 0; i <= n - m; i++) {

            // Hashes match
            if (patternHash == textHash) {

                boolean match = true;

                // Las Vegas verification
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    positions.add(i);
                }
            }

            // Calculate next window hash
            if (i < n - m) {

                textHash = (d * (textHash - text.charAt(i) * h)
                        + text.charAt(i + m)) % q;

                if (textHash < 0) {
                    textHash += q;
                }
            }
        }

        if (positions.size() == 0) {
            System.out.println("Pattern not found");
        }
        else if (positions.size() == 1) {
            System.out.println("Pattern " + pattern +
                    " found at index " + positions.get(0));
        }
        else {
            System.out.print("Pattern " + pattern +
                    " found at indices ");

            for (int i = 0; i < positions.size(); i++) {
                System.out.print(positions.get(i));

                if (i < positions.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        System.out.print("Enter the pattern: ");
        String pattern = sc.nextLine();

        rabinKarpSearch(text, pattern);

        sc.close();
    }
}