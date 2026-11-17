class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // Precompute proper divisors for each length from 1 to n
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            divisors[i] = new ArrayList<>();
            for (int d = 1; d < i; d++) {
                if (i % d == 0) {
                    divisors[i].add(d);
                }
            }
        }
        
        // cost[i][j] stores the min changes to make s[i...j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                int minChanges = Integer.MAX_VALUE;
                
                for (int d : divisors[len]) {
                    int currentChanges = 0;
                    for (int offset = 0; offset < d; offset++) {
                        int left = i + offset;
                        int right = j - (d - 1 - offset);
                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                currentChanges++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    minChanges = Math.min(minChanges, currentChanges);
                }
                cost[i][j] = minChanges;
            }
        }
        
        // dp[i][parts] = min changes to partition prefix s[0...i-1] into 'parts' semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], (int) 1e9); // Fixed: cast double to int
        }
        dp[0][0] = 0;
        
        for (int parts = 1; parts <= k; parts++) {
            for (int i = 0; i < n; i++) {
                if (dp[i][parts - 1] >= (int) 1e9) continue;
                // Fixed: x starts at i + 1 because semi-palindrome length must be >= 2
                for (int x = i + 1; x < n; x++) {
                    if (n - (x + 1) >= (k - parts) * 2) {
                        dp[x + 1][parts] = Math.min(dp[x + 1][parts], dp[i][parts - 1] + cost[i][x]);
                    }
                }
            }
        }
        
        return dp[n][k];
    }
}