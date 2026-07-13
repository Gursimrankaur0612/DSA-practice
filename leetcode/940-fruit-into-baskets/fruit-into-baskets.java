class Solution {
    public int totalFruit(int[] fruits) {
        // Track the two fruit types currently in our baskets
        int type1 = -1, type2 = -1;
        // Track the counts of the two fruit types in the current window
        int count1 = 0, count2 = 0;
        
        int left = 0;
        int maxFruits = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            int currentFruit = fruits[right];
            
            // Case 1: Match existing type1
            if (currentFruit == type1) {
                count1++;
            } 
            // Case 2: Match existing type2
            else if (currentFruit == type2) {
                count2++;
            } 
            // Case 3: Empty basket 1 available
            else if (type1 == -1) {
                type1 = currentFruit;
                count1 = 1;
            } 
            // Case 4: Empty basket 2 available
            else if (type2 == -1) {
                type2 = currentFruit;
                count2 = 1;
            } 
            // Case 5: Third fruit type introduced -> Shrink window
            else {
                while (count1 > 0 && count2 > 0) {
                    int leftFruit = fruits[left];
                    if (leftFruit == type1) count1--;
                    else if (leftFruit == type2) count2--;
                    left++;
                }
                
                // Identify which basket became empty and replace it with the new fruit
                if (count1 == 0) {
                    type1 = currentFruit;
                    count1 = 1;
                } else {
                    type2 = currentFruit;
                    count2 = 1;
                }
            }
            
            // Update maximum fruits collected
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
}
