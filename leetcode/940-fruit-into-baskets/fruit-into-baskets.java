class Solution {
    public int totalFruit(int[] fruits) {
        HashMap<Integer,Integer> fruitmap=new HashMap<>();
        int left=0;
        int maxfruits=0;

        for(int right=0;right<fruits.length;right++)
        {
           fruitmap.put(fruits[right],fruitmap.getOrDefault(fruits[right],0)+1);
        

        while(fruitmap.size()>2)
        {
            int leftfruit=fruits[left];
            fruitmap.put(leftfruit,fruitmap.get(leftfruit)-1);

            if(fruitmap.get(leftfruit)==0)
            fruitmap.remove(leftfruit);

            left++;
        }
        maxfruits=Math.max(maxfruits,right-left+1);
        }
    return maxfruits;
        
    }
}