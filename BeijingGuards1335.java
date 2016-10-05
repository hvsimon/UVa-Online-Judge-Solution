
import java.util.Arrays;
import java.util.Scanner;

public class BeijingGuards1335 {

    static int n;
    static int[] a;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int i, minTypeNum, maxNeighborAdd;

        while ((n = input.nextInt()) != 0) {

            a = new int[n + 1];

            for (i = 0; i < n; i++) {
                a[i] = input.nextInt();
            }

            a[n] = a[0];
            maxNeighborAdd = a[0] + a[1];

            for (i = 1; i < n; i++) {
                maxNeighborAdd = AddMax(maxNeighborAdd, a[i], a[i + 1]);
            }

            if (n % 2 == 0) {

                minTypeNum = maxNeighborAdd;
                System.out.println(minTypeNum);

            } else {
                int L, R, M, maxRequir;

                maxRequir = a[0];

                for (i = 1; i < a.length; i++) {
                    if (a[i] > maxRequir) {
                        maxRequir = a[i];
                    }
                }

                L = maxNeighborAdd;
                R = 3 * maxRequir;

                while (L < R) {
                    M = (L + R) / 2;
                    if (Test(M)) {
                        R = M;
                    } else {
                        L = M + 1;
                    }
                }
                System.out.println(L);
            }
        }
    }

    public static int AddMax(int max, int a, int b) {
        
        if (max < a + b) {
            max = a + b;
        }
        return max;
    }

    public static boolean Test(int val) {
        
        int[] left, right;
        int rNum, lNum;
        left = new int[n + 1];
        right = new int[n + 1];

        lNum = left[1] = a[1];
        rNum = val - left[1];

        for (int i = 2; i <= n; i++) {
            if (i % 2 != 0) {
                right[i] = Math.min(rNum - right[i - 1], a[i]);
                left[i] = a[i] - right[i];
            } else {
                left[i] = Math.min(lNum - left[i - 1], a[i]);
                right[i] = a[i] - left[i];
            }
        }
        return left[n] == 0;
    }
}
