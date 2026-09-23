import java.util.HashSet;
import java.util.Set;

class Solution {
    public int thirdMax(int[] nums) {
        Set<Integer> topThree = new HashSet<>();

        for (int num : nums) {
            topThree.add(num);

            // Keep only the 3 largest distinct elements in the set
            if (topThree.size() > 3) {
                topThree.remove(getMin(topThree));
            }
        }

        // If 3 distinct maximums exist, return the minimum (the 3rd max)
        if (topThree.size() == 3) {
            return getMin(topThree);
        }

        // Otherwise, return the maximum value overall
        return getMax(topThree);
    }

    private int getMin(Set<Integer> set) {
        int min = Integer.MAX_VALUE;
        for (int val : set) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    private int getMax(Set<Integer> set) {
        int max = Integer.MIN_VALUE;
        for (int val : set) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }
}