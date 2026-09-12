import java.util.*;

class Solution {
    private static class Interval {
        int l, r, weight, id;

        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    private static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> inv = intervals.get(i);
            arr[i] = new Interval(inv.get(0), inv.get(1), inv.get(2), i);
        }

        // Sort by right boundary, then left boundary, then original index
        Arrays.sort(arr, (a, b) -> {
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            if (a.l != b.l) return Integer.compare(a.l, b.l);
            return Integer.compare(a.id, b.id);
        });

        // dp[k][i] stores max weight and lexicographically best index list using at most k intervals from arr[0..i-1]
        State[][] dp = new State[5][n + 1];
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dp[k][i] = new State(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {
            Interval curr = arr[i - 1];

            // Binary search to find largest j such that arr[j].r < curr.l
            int low = 0, high = i - 1, prevIdx = 0;
            while (low < high) {
                int mid = (low + high + 1) >>> 1;
                if (arr[mid - 1].r < curr.l) {
                    prevIdx = mid;
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }

            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip current interval
                State skip = dp[k][i - 1];

                // Option 2: Take current interval
                State prev = dp[k - 1][prevIdx];
                long takeWeight = prev.weight + curr.weight;
                List<Integer> takeIndices = new ArrayList<>(prev.indices);
                takeIndices.add(curr.id);
                Collections.sort(takeIndices); // ensure ascending index order within choice
                State take = new State(takeWeight, takeIndices);

                // Compare Options
                dp[k][i] = getBestState(skip, take);
            }
        }

        List<Integer> resultList = dp[4][n].indices;
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    private State getBestState(State a, State b) {
        if (a.weight > b.weight) return a;
        if (b.weight > a.weight) return b;

        // If weights are equal, choose lexicographically smaller sequence
        int len = Math.min(a.indices.size(), b.indices.size());
        for (int i = 0; i < len; i++) {
            if (!a.indices.get(i).equals(b.indices.get(i))) {
                return a.indices.get(i) < b.indices.get(i) ? a : b;
            }
        }
        return a.indices.size() <= b.indices.size() ? a : b;
    }
}