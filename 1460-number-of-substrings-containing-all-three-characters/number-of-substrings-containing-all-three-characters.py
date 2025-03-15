class Solution(object):
    def numberOfSubstrings(self, s):
        """
        :type s: str
        :rtype: int
        """
        count = {'a': 0, 'b': 0, 'c': 0}
        left = 0
        result = 0
        n = len(s)

        for right in range(n):
            count[s[right]] += 1  # Expand window
            
            # Shrink window from left while valid
            while all(count[ch] > 0 for ch in 'abc'):
                result += (n - right)  # All substrings from [left, right] to end are valid
                count[s[left]] -= 1  # Shrink window
                left += 1

        return result