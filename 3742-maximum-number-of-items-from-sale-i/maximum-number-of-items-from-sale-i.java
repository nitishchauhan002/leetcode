class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeCount = new int[n];

        // Count how many other items j != i have factor[j] divisible by factor[i]
        for (int i = 0; i < n; i++) {
            int fi = items[i][0];
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % fi == 0) {
                    cnt++;
                }
            }
            freeCount[i] = cnt;
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int cost = items[i][1];
            int firstGain = 1 + freeCount[i];
            if (cost > budget) {
                continue;
            }

            int[] next = dp.clone();

            // 1. Buy the first copy of item i (1 purchased + freeCount[i] free)
            for (int b = budget; b >= cost; b--) {
                next[b] = Math.max(next[b], dp[b - cost] + firstGain);
            }

            // 2. Buy additional copies of item i (+1 copy each, no extra freebies)
            for (int b = cost; b <= budget; b++) {
                next[b] = Math.max(next[b], next[b - cost] + 1);
            }

            dp = next;
        }

        int ans = 0;
        for (int val : dp) {
            ans = Math.max(ans, val);
        }
        return ans;
    }
}