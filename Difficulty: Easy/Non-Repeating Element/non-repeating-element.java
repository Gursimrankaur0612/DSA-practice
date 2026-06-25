import java.util.HashMap;

class Solution {
    public int firstNonRepeating(int[] arr) {
        int n = arr.length;
        
        // Use a HashMap to store the frequencies of each number
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        
        // Pass 1: Count how many times each number appears
        for (int i = 0; i < n; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }
        
        // Pass 2: Scan left-to-right to find the first element with a count of 1
        for (int i = 0; i < n; i++) {
            if (freqMap.get(arr[i]) == 1) {
                return arr[i]; // Found it!
            }
        }
        
        // Return 0 if all elements are repeating
        return 0; 
    }
}