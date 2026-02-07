class Solution {
    public void sortColors(int[] nums) {
        int start = 0;
        int middle = 0;
        int end = nums.length - 1;

        while (middle <= end) {
            if (nums[middle] == 0) {
                // swap nums[low] and nums[mid]
                int temp = nums[start];
                nums[start] = nums[middle];
                nums[middle] = temp;

                start++;
                middle++;
            } 
            else if (nums[middle] == 1) {
                middle++;
            } 
            else { // nums[mid] == 2
                // swap nums[mid] and nums[high]
                int temp = nums[middle];
                nums[middle] = nums[end];
                nums[end] = temp;

                end--;
            }
        }
    }
}
