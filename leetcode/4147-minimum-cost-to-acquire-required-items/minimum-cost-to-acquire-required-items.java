class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cBoth = costBoth;
        long n1 = need1;
        long n2 = need2;
        
        // Step 1: Find how many units can be satisfied simultaneously by both needs
        long common = Math.min(n1, n2);
        long totalCost = common * Math.min(cBoth, c1 + c2);
        
        // Step 2: Handle remaining requirements
        n1 -= common;
        n2 -= common;
        
        if (n1 > 0) {
            // Remaining units for requirement 1
            totalCost += n1 * Math.min(c1, cBoth);
        } else if (n2 > 0) {
            // Remaining units for requirement 2
            totalCost += n2 * Math.min(c2, cBoth);
        }
        
        return totalCost;
    }
}