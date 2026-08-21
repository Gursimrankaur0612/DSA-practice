import java.util.Arrays;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;

        // Binary search bounds
        long left = 1;
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        long right = minCoin * k;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (countAmountsLessOrEqual(mid, coins, n) >= k) {
                ans = mid;
                right = mid - 1; // Try to find a smaller valid value
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private long countAmountsLessOrEqual(long m, int[] coins, int n) {
        long totalCount = 0;

        // Iterate through all non-empty subsets using bit manipulation
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcmVal = 1;
            int bitsSet = 0;

            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    bitsSet++;
                    lcmVal = lcm(lcmVal, coins[i]);
                    // Optimization: if LCM exceeds m, this subset contributes 0
                    if (lcmVal > m) {
                        break;
                    }
                }
            }

            if (lcmVal <= m) {
                if (bitsSet % 2 == 1) {
                    totalCount += m / lcmVal;
                } else {
                    totalCount -= m / lcmVal;
                }
            }
        }

        return totalCount;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}