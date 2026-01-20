from typing import List

class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        ans = []

        for n in nums:
            found = -1
            for x in range(n):  # minimal x guaranteed here
                if (x | (x + 1)) == n:
                    found = x
                    break
            ans.append(found)

        return ans
