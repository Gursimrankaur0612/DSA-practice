class Solution {
    static class Node {
        int prod;
        int[] count;

        Node(int k) {
            this.prod = 1;
            this.count = new int[k];
        }
    }

    private Node[] tree;
    private int n;
    private int k;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(nums, 1, 0, n - 1);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val);
            Node resNode = query(1, 0, n - 1, start, n - 1);
            result[i] = resNode.count[x];
        }

        return result;
    }

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node res = new Node(k);
        res.prod = (left.prod * right.prod) % k;

        for (int r = 0; r < k; r++) {
            res.count[r] = left.count[r];
        }

        for (int r = 0; r < k; r++) {
            if (right.count[r] > 0) {
                int newRem = (left.prod * r) % k;
                res.count[newRem] += right.count[r];
            }
        }

        return res;
    }

    private void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(k);
            int rem = nums[start] % k;
            tree[node].prod = rem;
            tree[node].count[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        build(nums, 2 * node, start, mid);
        build(nums, 2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = new Node(k);
            int rem = val % k;
            tree[node].prod = rem;
            tree[node].count[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return null;
        }

        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        Node leftRes = query(2 * node, start, mid, l, r);
        Node rightRes = query(2 * node + 1, mid + 1, end, l, r);

        return merge(leftRes, rightRes);
    }
}