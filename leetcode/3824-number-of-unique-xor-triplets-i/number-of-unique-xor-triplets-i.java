class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int n = nums.length;
        if (n < 3) {
            return n;
        }
        // Calculate the number of bits required to represent n
        int bitLength = 32 - Integer.numberOfLeadingZeros(n);
        return 1 << bitLength;
    }
}
