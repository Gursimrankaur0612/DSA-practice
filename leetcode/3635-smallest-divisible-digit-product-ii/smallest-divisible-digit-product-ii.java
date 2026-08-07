class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Factorize t into prime factors 2, 3, 5, 7
        long tempT = t;
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        while (tempT % 2 == 0) { tempT /= 2; c2++; }
        while (tempT % 3 == 0) { tempT /= 3; c3++; }
        while (tempT % 5 == 0) { tempT /= 5; c5++; }
        while (tempT % 7 == 0) { tempT /= 7; c7++; }
        
        if (tempT > 1) {
            return "-1"; // t contains prime factors other than 2, 3, 5, 7
        }

        int n = num.length();
        int[] numDigits = new int[n];
        for (int i = 0; i < n; i++) {
            numDigits[i] = num.charAt(i) - '0';
        }

        // Find the first '0'
        int firstZero = -1;
        for (int i = 0; i < n; i++) {
            if (numDigits[i] == 0) {
                firstZero = i;
                break;
            }
        }

        int limit = (firstZero == -1) ? n : firstZero;

        // Prefix factors accumulated
        int[] prefixC2 = new int[n + 1];
        int[] prefixC3 = new int[n + 1];
        int[] prefixC5 = new int[n + 1];
        int[] prefixC7 = new int[n + 1];

        for (int i = 0; i < limit; i++) {
            int d = numDigits[i];
            prefixC2[i + 1] = prefixC2[i] + getFactor(d, 2);
            prefixC3[i + 1] = prefixC3[i] + getFactor(d, 3);
            prefixC5[i + 1] = prefixC5[i] + getFactor(d, 5);
            prefixC7[i + 1] = prefixC7[i] + getFactor(d, 7);
        }

        // Case 1: num has no '0's and already satisfies t
        if (firstZero == -1) {
            if (prefixC2[n] >= c2 && prefixC3[n] >= c3 && prefixC5[n] >= c5 && prefixC7[n] >= c7) {
                return num;
            }
        }

        // Try prefix lengths from limit down to 0
        for (int i = limit; i >= 0; i--) {
            int remLen = n - i;
            
            int startDigit = (i < n) ? numDigits[i] + 1 : 1;
            if (i == n) startDigit = 10;

            for (int d = startDigit; d <= 9; d++) {
                int r2 = Math.max(0, c2 - prefixC2[i] - getFactor(d, 2));
                int r3 = Math.max(0, c3 - prefixC3[i] - getFactor(d, 3));
                int r5 = Math.max(0, c5 - prefixC5[i] - getFactor(d, 5));
                int r7 = Math.max(0, c7 - prefixC7[i] - getFactor(d, 7));

                int minDigits = getMinDigits(r2, r3, r5, r7);
                
                if (minDigits <= remLen - 1) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) {
                        sb.append(numDigits[j]);
                    }
                    sb.append(d);
                    
                    int remainingPos = remLen - 1;
                    String suffix = buildSuffix(r2, r3, r5, r7, remainingPos);
                    sb.append(suffix);
                    return sb.toString();
                }
            }
        }

        // Case 2: We need to increase length
        int minDigits = getMinDigits(c2, c3, c5, c7);
        int targetLen = Math.max(n + 1, minDigits);
        
        return buildSuffix(c2, c3, c5, c7, targetLen);
    }

    private int getFactor(int d, int prime) {
        if (d <= 0) return 0;
        int count = 0;
        while (d % prime == 0) {
            count++;
            d /= prime;
        }
        return count;
    }

    // Accurately calculates the minimum number of digits needed to yield factors (r2, r3, r5, r7)
    private int getMinDigits(int r2, int r3, int r5, int r7) {
        int count = r5 + r7;
        
        int n9 = r3 / 2;
        r3 %= 2;

        int n8 = r2 / 3;
        r2 %= 3;

        count += n9 + n8;

        if (r2 == 2 && r3 == 1) {
            // Can be represented by 8 and 3 (or 4 and 6), taking 2 digits
            count += 2;
        } else if (r2 > 0 || r3 > 0) {
            // (r2=1, r3=1 -> '6'), (r2=2, r3=0 -> '4'), (r2=1, r3=0 -> '2'), (r2=0, r3=1 -> '3')
            count += 1;
        }

        return count;
    }

    private String buildSuffix(int r2, int r3, int r5, int r7, int len) {
        char[] result = new char[len];
        
        for (int i = 0; i < len; i++) {
            for (int d = 1; d <= 9; d++) {
                int nr2 = Math.max(0, r2 - getFactor(d, 2));
                int nr3 = Math.max(0, r3 - getFactor(d, 3));
                int nr5 = Math.max(0, r5 - getFactor(d, 5));
                int nr7 = Math.max(0, r7 - getFactor(d, 7));

                if (getMinDigits(nr2, nr3, nr5, nr7) <= len - 1 - i) {
                    result[i] = (char) ('0' + d);
                    r2 = nr2;
                    r3 = nr3;
                    r5 = nr5;
                    r7 = nr7;
                    break;
                }
            }
        }
        return new String(result);
    }
}