class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                // Since the problem uses 1-indexing, the element at 
                // 1-indexed position 'i' corresponds to the 
                // 0-indexed position '(i - 1)' in Java.
                sum += nums[i - 1] * nums[i - 1];
            }
        }
        
        return sum;
    }
}