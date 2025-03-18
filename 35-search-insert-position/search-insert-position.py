class Solution(object):
    def longestNiceSubarray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        max_length = 0
        left = 0
        current_and = 0
        
        for right in range(len(nums)):
            while (current_and & nums[right]) != 0:
                current_and ^= nums[left]
                left += 1
            
            current_and |= nums[right]
            max_length = max(max_length, right - left + 1)
        
        return max_length
    
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        left, right = 0, len(nums) - 1
        
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1
        
        return left
