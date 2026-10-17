import java.util.Arrays;
import java.util.Comparator;

class Solution {

    private int getDigitSum(int n) {
        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int sumA = getDigitSum(nums[a]);
                int sumB = getDigitSum(nums[b]);

                if (sumA != sumB) {
                    return Integer.compare(sumA, sumB);
                }

                return Integer.compare(nums[a], nums[b]);
            }
        });

        // Count minimum swaps using cycle detection
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int j = i;
            int cycleLength = 0;

            while (!visited[j]) {
                visited[j] = true;
                cycleLength++;

                // Move to the original index of the element
                // that belongs at the current sorted position.
                j = indices[j];
            }

            if (cycleLength > 1) {
                swaps += cycleLength - 1;
            }
        }

        return swaps;
    }
}