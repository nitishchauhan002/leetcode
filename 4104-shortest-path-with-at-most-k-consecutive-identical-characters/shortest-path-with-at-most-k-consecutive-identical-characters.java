import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, weight]
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[] { edge[1], edge[2] });
        }

        // Priority queue storing: [total_weight, current_node, consecutive_count]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // minWeight[u][count] stores the minimum weight to reach node u with a given count
        int[][] minWeight = new int[n][k + 1];
        for (int[] row : minWeight) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Starting at node 0 with count 1
        pq.offer(new int[] { 0, 0, 1 });
        minWeight[0][1] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int weight = curr[0];
            int u = curr[1];
            int count = curr[2];

            // If we reached the destination node
            if (u == n - 1) {
                return weight;
            }

            if (weight > minWeight[u][count]) {
                continue;
            }

            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int w = neighbor[1];

                int nextCount = 1;
                if (labels.charAt(v) == labels.charAt(u)) {
                    nextCount = count + 1;
                }

                // Skip if it exceeds the allowed consecutive identical characters
                if (nextCount > k) {
                    continue;
                }

                int newWeight = weight + w;
                if (newWeight < minWeight[v][nextCount]) {
                    minWeight[v][nextCount] = newWeight;
                    pq.offer(new int[] { newWeight, v, nextCount });
                }
            }
        }

        return -1;
    }
}