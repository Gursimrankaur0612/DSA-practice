class Solution {
    static void rotateArr(int arr[], int d) {
        int n = arr.length;
        d = d % n; // Handle cases where d is larger than n
        
        // Step 1: Reverse the first 'd' elements
        reverse(arr, 0, d - 1);
        
        // Step 2: Reverse the remaining 'n-d' elements
        reverse(arr, d, n - 1);
        
        // Step 3: Reverse the whole array
        reverse(arr, 0, n - 1);
    }
    
    // A simple helper function to reverse an array between two indices
    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}