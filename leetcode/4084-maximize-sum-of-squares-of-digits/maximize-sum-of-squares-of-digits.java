class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the total sum is too large or less than 0
        if ((long) num * 9 < sum || sum < 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int remSum = sum;
        
        for (int i = 0; i < num; i++) {
            int remDigits = num - 1 - i;
            
            // Try digits from 9 down to 0 to keep the number as large as possible
            for (int d = 9; d >= 0; d--) {
                // Leading digit cannot be 0 (unless num == 1)
                if (i == 0 && d == 0 && num > 1) continue;
                
                // Check if the remaining sum can be formed using the remaining positions
                if (remSum - d >= 0 && remSum - d <= (long) remDigits * 9) {
                    sb.append(d);
                    remSum -= d;
                    break;
                }
            }
        }
        
        // Final validation to ensure exact sum match and no invalid leading zeros
        if (remSum != 0 || (sb.length() > 0 && sb.charAt(0) == '0' && num > 1)) {
            return "";
        }
        
        return sb.toString();
    }
}