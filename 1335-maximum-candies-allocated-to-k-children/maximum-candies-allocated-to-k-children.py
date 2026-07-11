class Solution(object):
    def maximumCandies(self, candies, k):
        """
        :type candies: List[int]
        :type k: int
        :rtype: int
        """
        if k == 0:
            return 0  # No children to distribute to

        left, right = 1, max(candies)
        best = 0

        while left <= right:
            mid = (left + right) // 2
            if sum(c // mid for c in candies) >= k:
                best = mid  # Update best result
                left = mid + 1  # Try a larger size
            else:
                right = mid - 1  # Try a smaller size

        return best

