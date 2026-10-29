import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[] parent, depth, heavy, head, pos, revPos;
    int curPos;
    int[] tree; // Segment tree storing 26-bit masks
    char[] charArr;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        head = new int[n];
        pos = new int[n];
        revPos = new int[n];
        charArr = s.toCharArray();

        dfs1(0, -1, 0);
        curPos = 0;
        dfs2(0, 0);

        tree = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                charArr[u] = c;
                update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathMask = queryPath(u, v, n);
                // Check if pathMask has at most one bit set
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private int dfs1(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        int size = 1;
        int maxSubTree = 0;
        for (int v : adj[u]) {
            if (v != p) {
                int subSize = dfs1(v, u, d + 1);
                size += subSize;
                if (subSize > maxSubTree) {
                    maxSubTree = subSize;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    private void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = curPos;
        revPos[curPos] = u;
        curPos++;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : adj[u]) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            int u = revPos[start];
            tree[node] = 1 << (charArr[u] - 'a');
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx >= start && idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int queryPath(int u, int v, int n) {
        int resMask = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            int currHead = head[v];
            resMask ^= queryTree(1, 0, n - 1, pos[currHead], pos[v]);
            v = parent[currHead];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        resMask ^= queryTree(1, 0, n - 1, pos[u], pos[v]);
        return resMask;
    }

    private int queryTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return queryTree(2 * node, start, mid, l, r) ^ queryTree(2 * node + 1, mid + 1, end, l, r);
    }
}