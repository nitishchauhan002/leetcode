from collections import defaultdict

class Solution(object):
    def countCompleteComponents(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: int
        """
        # Build the adjacency list
        graph = defaultdict(set)
        for u, v in edges:
            graph[u].add(v)
            graph[v].add(u)
        
        visited = set()
        def dfs(node, component):
            stack = [node]
            while stack:
                curr = stack.pop()
                for neighbor in graph[curr]:
                    if neighbor not in visited:
                        visited.add(neighbor)
                        component.add(neighbor)
                        stack.append(neighbor)
        
        complete_components = 0
        
        for node in range(n):
            if node not in visited:
                visited.add(node)
                component = {node}
                dfs(node, component)
                
                # Check if the component is complete
                size = len(component)
                is_complete = all(len(graph[v]) == size - 1 for v in component)
                
                if is_complete:
                    complete_components += 1
        
        return complete_components
