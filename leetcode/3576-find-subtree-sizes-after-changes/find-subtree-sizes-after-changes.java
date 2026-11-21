import java.util.*;

class Solution {
    private int[] newParent;
    private int[] lastSeen;
    private List<Integer>[] origAdj;
    private List<Integer>[] finalAdj;
    private int[] answer;

    @SuppressWarnings("unchecked")
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        newParent = new int[n];
        lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        origAdj = new ArrayList[n];
        finalAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origAdj[i] = new ArrayList<>();
            finalAdj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            origAdj[parent[i]].add(i);
        }

        newParent[0] = -1;
        findNewParents(0, parent, s);

        for (int i = 1; i < n; i++) {
            if (newParent[i] != -1) {
                finalAdj[newParent[i]].add(i);
            }
        }

        answer = new int[n];
        computeSubtreeSizes(0);
        return answer;
    }

    private void findNewParents(int u, int[] parent, String s) {
        int idx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[idx];

        if (u != 0) {
            newParent[u] = (prevAncestor != -1) ? prevAncestor : parent[u];
        }

        lastSeen[idx] = u;
        for (int v : origAdj[u]) {
            findNewParents(v, parent, s);
        }
        lastSeen[idx] = prevAncestor;
    }

    private int computeSubtreeSizes(int u) {
        int size = 1;
        for (int v : finalAdj[u]) {
            size += computeSubtreeSizes(v);
        }
        answer[u] = size;
        return size;
    }
}