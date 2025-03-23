import heapq

class Solution(object):
    def countPaths(self, n, roads):
        """
        :type n: int
        :type roads: List[List[int]]
        :rtype: int
        """
        MOD = 10**9 + 7
        
        # Create adjacency list
        graph = {i: [] for i in range(n)}
        for u, v, time in roads:
            graph[u].append((v, time))
            graph[v].append((u, time))
        
        # Min-heap for Dijkstra's Algorithm
        heap = [(0, 0)]  # (time, node)
        min_time = {i: float('inf') for i in range(n)}
        min_time[0] = 0
        ways = {i: 0 for i in range(n)}
        ways[0] = 1
        
        while heap:
            curr_time, node = heapq.heappop(heap)
            
            if curr_time > min_time[node]:
                continue
            
            for neighbor, travel_time in graph[node]:
                new_time = curr_time + travel_time
                
                if new_time < min_time[neighbor]:
                    min_time[neighbor] = new_time
                    ways[neighbor] = ways[node]
                    heapq.heappush(heap, (new_time, neighbor))
                elif new_time == min_time[neighbor]:
                    ways[neighbor] = (ways[neighbor] + ways[node]) % MOD
        
        return ways[n - 1]
