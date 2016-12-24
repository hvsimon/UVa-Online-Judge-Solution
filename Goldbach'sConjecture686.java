package UVaOnlineJudgeSolution;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Goldbach'sConjecture686 {

    public static void main(String[] args) {

        final int MAX = (int) Math.pow(2, 15);
        boolean[] isPrime = new boolean[MAX];

        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(MAX); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        ArrayList<Integer> primeList = new ArrayList<Integer>();

        for (int i = 2; i < MAX; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }

        Scanner input = new Scanner(System.in);
        int n;

        while ((n = input.nextInt()) != 0) {
            int L = 0;
            int R = primeList.size() - 1;
            int M = 0;

            while (L < R) {
                M = (L + R) / 2;
                if (n < primeList.get(M)) {
                    R = M;
                } else {
                    L = M + 1;
                }
            }
            int count = 0;

            for (int i = L - 1; i >= 0 && n - primeList.get(i) <= primeList.get(i); --i) {
                if (isPrime[n - primeList.get(i)]) {
                    ++count;
                }
            }

            System.out.println(count);
        }
    }
}
