class Solution(object):
    def maximizeSquareArea(self, m, n, hFences, vFences):
        """
        :type m: int
        :type n: int
        :type hFences: List[int]
        :type vFences: List[int]
        :rtype: int
        """
        MOD = 10**9 + 7

        # Add boundary fences
        h = hFences + [1, m]
        v = vFences + [1, n]

        h.sort()
        v.sort()

        # Calculate all possible distances between horizontal fences
        h_dist = set()
        for i in range(len(h)):
            for j in range(i + 1, len(h)):
                h_dist.add(h[j] - h[i])

        # Calculate all possible distances between vertical fences
        v_dist = set()
        for i in range(len(v)):
            for j in range(i + 1, len(v)):
                v_dist.add(v[j] - v[i])

        # Find the maximum common distance
        common = h_dist & v_dist
        if not common:
            return -1

        side = max(common)
        return (side * side) % MOD
