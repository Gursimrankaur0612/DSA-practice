import java.util.*;

class Solution {
    private int[] tree;

    private void build(int node, int start, int end, int[] gain) {
        if (start == end) {
            tree[node] = gain[start];
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid, gain);
        build(2 * node + 1, mid + 1, end, gain);
        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return Math.max(
            query(2 * node, start, mid, l, r),
            query(2 * node + 1, mid + 1, end, l, r)
        );
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') totalOnes++;
        }

        // Decompose s into alternating 0 and 1 segments
        List<Integer> segStart = new ArrayList<>();
        List<Integer> segEnd = new ArrayList<>();
        List<Integer> segType = new ArrayList<>();

        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            segStart.add(i);
            segEnd.add(j - 1);
            segType.add(s.charAt(i) - '0');
            i = j;
        }

        int m = segStart.size();
        int[] segIdx = new int[n];
        for (int k = 0; k < m; k++) {
            for (int p = segStart.get(k); p <= segEnd.get(k); p++) {
                segIdx[p] = k;
            }
        }

        // Precompute full gains for internal '1'-segments
        int[] gain = new int[m];
        for (int k = 1; k < m - 1; k++) {
            if (segType.get(k) == 1) {
                int lenPrev = segEnd.get(k - 1) - segStart.get(k - 1) + 1;
                int lenNext = segEnd.get(k + 1) - segStart.get(k + 1) + 1;
                gain[k] = lenPrev + lenNext;
            }
        }

        if (m > 0) {
            tree = new int[4 * m];
            build(1, 0, m - 1, gain);
        }

        List<Integer> ans = new ArrayList<>(queries.length);

        for (int[] q : queries) {
            int ql = q[0], qr = q[1];
            int firstSeg = segIdx[ql];
            int lastSeg = segIdx[qr];
            int maxGain = 0;

            // 1. Safe internal range query using Segment Tree
            int leftLimit = firstSeg + 2;
            int rightLimit = lastSeg - 2;
            if (leftLimit <= rightLimit) {
                maxGain = Math.max(maxGain, query(1, 0, m - 1, leftLimit, rightLimit));
            }

            // 2. O(1) Boundary checks for affected elements near ql and qr
            int[] candidates = {firstSeg, firstSeg + 1, lastSeg - 1, lastSeg};
            for (int k : candidates) {
                if (k >= firstSeg && k <= lastSeg) {
                    maxGain = Math.max(maxGain, calculateLocalGain(k, ql, qr, m, segType, segStart, segEnd));
                }
            }

            ans.add(totalOnes + maxGain);
        }

        return ans;
    }

    private int calculateLocalGain(int k, int ql, int qr, int m, List<Integer> segType, List<Integer> segStart, List<Integer> segEnd) {
        if (k < 0 || k >= m || segType.get(k) != 1) return 0;
        
        // The '1'-segment must be fully inside the query window [ql, qr]
        if (segStart.get(k) < ql || segEnd.get(k) > qr) return 0;

        // Must have a valid left '0' neighbor within the query window
        int leftZeros = 0;
        if (k - 1 >= 0 && segType.get(k - 1) == 0) {
            leftZeros = Math.max(0, segEnd.get(k - 1) - Math.max(ql, segStart.get(k - 1)) + 1);
        }

        // Must have a valid right '0' neighbor within the query window
        int rightZeros = 0;
        if (k + 1 < m && segType.get(k + 1) == 0) {
            rightZeros = Math.max(0, Math.min(qr, segEnd.get(k + 1)) - segStart.get(k + 1) + 1);
        }

        // A trade requires non-zero '0' padding on BOTH sides
        if (leftZeros > 0 && rightZeros > 0) {
            return leftZeros + rightZeros;
        }
        return 0;
    }
}
