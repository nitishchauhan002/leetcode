class Solution(object):
    def checkPowersOfThree(self, n):
        """
        :type n: int
        :rtype: bool
        """
        while n > 0:
            if n % 3 == 2:  # If any ternary digit is 2, return False
                return False
            n //= 3  # Move to the next ternary digit
        return True