class Solution {
    public ArrayList<Integer> dfs( ArrayList<ArrayList<Integer>> adj) {
        int V=adj.size();
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        
        // Start DFS from the first node (0)
        traversal(0, adj, visited, result);
        
        return result;
    }
    
    private void traversal(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> result) {
        visited[node] = true;
        result.add(node);
        
        // Visit all neighbors of the current node
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                traversal(neighbor, adj, visited, result);
            }
        }
    }
}
