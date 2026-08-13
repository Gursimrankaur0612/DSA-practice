class Solution {
    class Node {
        char prefixChar, suffixChar;
        int maxLen;
        int prefixLen;
        int suffixLen;
        int length; // length of the range represented by this node

        Node(char c) {
            this.prefixChar = c;
            this.suffixChar = c;
            this.maxLen = 1;
            this.prefixLen = 1;
            this.suffixLen = 1;
            this.length = 1;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] chars;

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.length = left.length + right.length;
        res.prefixChar = left.prefixChar;
        res.suffixChar = right.suffixChar;

        // Default prefix and suffix lengths
        res.prefixLen = left.prefixLen;
        res.suffixLen = right.suffixLen;

        // Maximum length within this range
        res.maxLen = Math.max(left.maxLen, right.maxLen);

        // If the middle characters match, we can combine suffix of left child and prefix of right child
        if (left.suffixChar == right.prefixChar) {
            int mergedLen = left.suffixLen + right.prefixLen;
            res.maxLen = Math.max(res.maxLen, mergedLen);

            // If the entire left child is one repeating character, extend prefix length
            if (left.prefixLen == left.length) {
                res.prefixLen = left.length + right.prefixLen;
            }

            // If the entire right child is one repeating character, extend suffix length
            if (right.suffixLen == right.length) {
                res.suffixLen = right.length + left.suffixLen;
            }
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char ch) {
        if (start == end) {
            tree[node] = new Node(ch);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, end, idx, ch);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            if (chars[idx] != ch) {
                chars[idx] = ch;
                update(1, 0, n - 1, idx, ch);
            }

            result[i] = tree[1].maxLen;
        }

        return result;
    }
}