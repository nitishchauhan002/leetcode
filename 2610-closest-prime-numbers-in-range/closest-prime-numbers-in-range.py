class Solution(object):
    def closestPrimes(self, left, right):
        """
        :type left: int
        :type right: int
        :rtype: List[int]
        """
        def sieve(n):
            """Returns a list where is_prime[i] is True if i is a prime."""
            is_prime = [True] * (n + 1)
            is_prime[0] = is_prime[1] = False  # 0 and 1 are not primes

            for i in range(2, int(n ** 0.5) + 1):
                if is_prime[i]:
                    for j in range(i * i, n + 1, i):
                        is_prime[j] = False

            return is_prime

        # Step 1: Find all primes up to `right` using Sieve of Eratosthenes
        is_prime = sieve(right)
        
        # Step 2: Extract primes in the range [left, right]
        primes = [i for i in range(left, right + 1) if is_prime[i]]

        # Step 3: Find the closest prime pair
        if len(primes) < 2:
            return [-1, -1]

        min_diff = float('inf')
        closest_pair = [-1, -1]

        for i in range(len(primes) - 1):
            diff = primes[i + 1] - primes[i]
            if diff < min_diff:
                min_diff = diff
                closest_pair = [primes[i], primes[i + 1]]

        return closest_pair