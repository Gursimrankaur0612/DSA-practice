class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < n; j++) {
                int val = nums[j];
                prod *= val;
                gcd = (j == i) ? val : gcd(gcd, val);
                lcm = lcm(lcm, val);

                // Stop expanding if product exceeds maximum possible lcm * gcd bound
                if (prod > 2520L * gcd) {
                    break;
                }

                if (prod == (long) lcm * gcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}