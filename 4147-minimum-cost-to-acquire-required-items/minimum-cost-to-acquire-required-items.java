class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long common = Math.min(need1, need2);
        long rem1 = need1 - common;
        long rem2 = need2 - common;
        
        // Cost for the overlapping portion
        long totalCost = common * Math.min((long)costBoth, (long)cost1 + cost2);
        
        // Cost for the remaining type 1 requirement
        totalCost += rem1 * Math.min((long)cost1, (long)costBoth);
        
        // Cost for the remaining type 2 requirement
        totalCost += rem2 * Math.min((long)cost2, (long)costBoth);
        
        return totalCost;
    }
}