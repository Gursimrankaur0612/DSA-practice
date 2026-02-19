class Solution {
    public int findNumbers(int[] nums) {
        int evenDigitCount = 0;
        
        for (int num : nums) {
            if (isEven(num)) {
                evenDigitCount++;
            }
        }
        
        return evenDigitCount;
    }

    private boolean isEven(int num) {
        int numberOfDigits = getDigits(num);
        return numberOfDigits % 2 == 0;
    }

    private int getDigits(int num) {
        // Handle negative numbers
        if (num < 0) num *= -1;
        // Handle zero (log10 of 0 is undefined)
        if (num == 0) return 1;
        
        // Mathematical way to find number of digits
        return (int)(Math.log10(num)) + 1;
    }
}
