import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        long MOD = 1000000007;

       
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        List<Integer> digits = new ArrayList<>();
        List<Integer> originalIndices = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            char ch = s.charAt(i);
            if (ch != '0') {
                digits.add(ch - '0');
                originalIndices.add(i);
            }
        }

        int n = digits.size();

       
        if (n == 0) {
            return new int[queries.length];
        }


        long[] prefixSum = new long[n + 1];
        long[] prefixVal = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + digits.get(i);
            prefixVal[i + 1] = (prefixVal[i] * 10 + digits.get(i)) % MOD;
        }

     
        int[] nextNonZero = new int[m + 1];
        int curr = 0;
        for (int i = 0; i <= m; i++) {
            while (curr < n && originalIndices.get(curr) < i) {
                curr++;
            }
            nextNonZero[i] = curr; 
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int L = queries[i][0];
            int R = queries[i][1];

            int idxL = nextNonZero[L];
            int idxR = nextNonZero[R + 1] - 1;

            
            if (idxL > idxR) {
                answer[i] = 0;
                continue;
            }

           
            long sum = prefixSum[idxR + 1] - prefixSum[idxL];

            
            int count = idxR - idxL + 1;
            long x = (prefixVal[idxR + 1] - (prefixVal[idxL] * pow10[count]) % MOD + MOD) % MOD;

       
            answer[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return answer;
    }
}