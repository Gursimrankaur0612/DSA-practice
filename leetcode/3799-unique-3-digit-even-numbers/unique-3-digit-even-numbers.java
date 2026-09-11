class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }

        int validCount = 0;

        // Iterate over all 3-digit even numbers
        for (int num = 100; num < 1000; num += 2) {
            int d1 = num / 100;        // Hundreds digit
            int d2 = (num / 10) % 10;  // Tens digit
            int d3 = num % 10;         // Units digit

            int[] tempCount = new int[10];
            tempCount[d1]++;
            tempCount[d2]++;
            tempCount[d3]++;

            if (tempCount[d1] <= count[d1] &&
                tempCount[d2] <= count[d2] &&
                tempCount[d3] <= count[d3]) {
                validCount++;
            }
        }

        return validCount;
    }
}