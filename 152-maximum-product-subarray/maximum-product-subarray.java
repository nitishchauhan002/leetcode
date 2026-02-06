class Solution {
    public int maxProduct(int[] nums) {
        int n=nums.length;
        int leftproduct = 1;
        int rightproduct = 1;
        int maxproduct = nums[0];

        for (int i = 0; i < nums.length; i++) {
            leftproduct=leftproduct==0?1:leftproduct;
            rightproduct=rightproduct==0?1:rightproduct;
            leftproduct = leftproduct*nums[i];
            rightproduct = rightproduct*nums[n-i-1];

           maxproduct=Math.max(maxproduct,Math.max(leftproduct,rightproduct));
        }
        return maxproduct;
    }
}