import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> origAdj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            origAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            origAdj.get(parent[i]).add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        findNewParents(0, s, origAdj, lastSeen, newParent);

        List<List<Integer>> modAdj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            modAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            modAdj.get(newParent[i]).add(i);
        }

        int[] ans = new int[n];
        computeSizes(0, modAdj, ans);
        return ans;
    }

    private void findNewParents(
            int u,
            String s,
            List<List<Integer>> origAdj,
            int[] lastSeen,
            int[] newParent
    ) {
        int idx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[idx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[idx] = u;
        for (int v : origAdj.get(u)) {
            findNewParents(v, s, origAdj, lastSeen, newParent);
        }
        lastSeen[idx] = prevAncestor;
    }

    private int computeSizes(int u, List<List<Integer>> modAdj, int[] ans) {
        int size = 1;
        for (int v : modAdj.get(u)) {
            size += computeSizes(v, modAdj, ans);
        }
        ans[u] = size;
        return size;
    }
}