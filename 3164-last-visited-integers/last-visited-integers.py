class Solution(object):
    def lastVisitedIntegers(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        seen = []
        ans = []
        k = 0  # Counter for consecutive -1s

        for num in nums:
            if num > 0:
                seen.insert(0, num)  # Prepend the number to seen
                k = 0  # Reset -1 count
            elif num == -1:
                k += 1
                if k <= len(seen):
                    ans.append(seen[k - 1])  # Get the k-th last seen number
                else:
                    ans.append(-1)  # Not enough numbers in seen

        return ans
