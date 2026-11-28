import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, long budget) {
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count frequency of each factor
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Compute count of items j where factor_i divides factor_j (excluding self)
        int[] multCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] > 0) {
                int totalMultiples = 0;
                for (int m = f; m <= maxFactor; m += f) {
                    totalMultiples += freq[m];
                }
                multCount[f] = totalMultiples - 1;
            }
        }

        // Collect (price, count) batches of value-2 marginal units
        // Only include batches where 1 deal (2 items) costs less than 2 single cheapest items
        List<long[]> batches = new ArrayList<>();
        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];
            int gain = multCount[factor];
            
            if (gain > 0 && price <= budget && price < 2 * minPrice) {
                batches.add(new long[]{price, gain});
            }
        }

        batches.sort(Comparator.comparingLong(a -> a[0]));

        long remBudget = budget;
        long totalItems = 0;

        for (long[] batch : batches) {
            long price = batch[0];
            long count = batch[1];
            
            if (remBudget < price) {
                break;
            }
            
            long take = Math.min(count, remBudget / price);
            totalItems += take * 2;
            remBudget -= take * price;
        }

        // Spend remaining budget on the cheapest item (1 item per unit price)
        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}
