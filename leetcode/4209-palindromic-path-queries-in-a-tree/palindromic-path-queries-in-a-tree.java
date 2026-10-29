import java.util.*;

class Solution {
    private List<List<Integer>> adj;
    private char[] sChars;
    private int[] parent, depth, heavy, head, pos, sz;
    private int[] invPos; // Maps segment tree positions back to tree nodes
    private int curPos;
    private int[] tree; // Segment tree array storing 26-bit XOR masks
    private int n;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.sChars = s.toCharArray();
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        head = new int[n];
        pos = new int[n];
        invPos = new int[n];
        sz = new int[n];

        // Step 1: HLD DFS passes
        dfs1(0, -1, 0);
        curPos = 0;
        dfs2(0, 0);

        // Step 2: Initialize Segment Tree
        tree = new int[4 * n];
        build(1, 0, n - 1);

        // Step 3: Process queries
        List<Boolean> result = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                sChars[u] = c;
                update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int mask = queryPathMask(u, v);
                
                // A set of characters can form a palindrome if at most one character has an odd count
                result.add((mask & (mask - 1)) == 0);
            }
        }
        return result;
    }

    private void dfs1(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        sz[u] = 1;
        int maxSub = 0;
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs1(v, u, d + 1);
                sz[u] += sz[v];
                if (sz[v] > maxSub) {
                    maxSub = sz[v];
                    heavy[u] = v;
                }
            }
        }
    }

    private void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = curPos;
        invPos[curPos] = u;
        curPos++;
        
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : adj.get(u)) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            int originalNode = invPos[start];
            tree[node] = 1 << (sChars[originalNode] - 'a');
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) update(2 * node, start, mid, idx, val);
        else update(2 * node + 1, mid + 1, end, idx, val);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int querySegment(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return querySegment(2 * node, start, mid, l, r) ^ querySegment(2 * node + 1, mid + 1, end, l, r);
    }

    private int queryPathMask(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= querySegment(1, 0, n - 1, pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= querySegment(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }
}
