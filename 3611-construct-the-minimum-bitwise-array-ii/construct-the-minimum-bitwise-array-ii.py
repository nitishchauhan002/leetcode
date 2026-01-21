class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        ans = []

        for p in nums:
            # Even prime -> impossible
            if p % 2 == 0:
                ans.append(-1)
                continue

            # Count trailing 1s
            temp = p
            k = 0
            while temp & 1:
                k += 1
                temp >>= 1

            ans.append(p - (1 << (k - 1)))

        return ans
