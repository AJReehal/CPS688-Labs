import java.util.*;

public class Q1 {

    // Build bad character table
    static int[] buildBadCharTable(String pattern) {
        int[] badChar = new int[256];

        for (int i = 0; i < 256; i++) {
            badChar[i] = -1;
        }

        for (int i = 0; i < pattern.length(); i++) {
            badChar[(int) pattern.charAt(i)] = i;
        }

        return badChar;
    }

    // Boyer-Moore Search
    static void boyerMooreSearch(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int[] badChar = buildBadCharTable(pattern);

        ArrayList<Integer> positions = new ArrayList<>();

        int shift = 0;

        while (shift <= n - m) {

            int j = m - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }

            // Pattern found
            if (j < 0) {

                positions.add(shift);

                if (shift + m < n) {
                    shift += m - badChar[text.charAt(shift + m)];
                } else {
                    shift += 1;
                }

            } else {

                shift += Math.max(1,
                        j - badChar[text.charAt(shift + j)]);
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

        boyerMooreSearch(text, pattern);

        sc.close();
    }
}