class Solution(object):
    def minZeroArray(self, nums, queries):
        """
        :type nums: List[int]
        :type queries: List[List[int]]
        :rtype: int
        """
        n = len(nums)
        
        def can_zero_with_k(k):
            """Checks if nums can become a Zero Array using the first k queries."""
            arr = nums[:]  # Create a copy of nums
            diff = [0] * (n + 1)  # Difference array

            # Apply the first k queries using a difference array
            for i in range(k):
                li, ri, vali = queries[i]
                diff[li] -= vali
                if ri + 1 < n:
                    diff[ri + 1] += vali

            # Apply the difference array to simulate decrements
            curr_decrement = 0
            for i in range(n):
                curr_decrement += diff[i]
                arr[i] += curr_decrement  # Apply accumulated decrements
                if arr[i] > 0:  # If any element remains positive, return False
                    return False

            return True  # If all elements are 0 or negative, it's a valid solution

        # Binary search for the minimum k
        left, right = 0, len(queries)
        while left < right:
            mid = (left + right) // 2
            if can_zero_with_k(mid):
                right = mid  # Try a smaller k
            else:
                left = mid + 1  # Increase k

        # Final check
        return left if left <= len(queries) and can_zero_with_k(left) else -1
