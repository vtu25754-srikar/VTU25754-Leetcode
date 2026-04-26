import java.util.*;

public class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);

        Map<String, Integer> emailToIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (emailToIndex.containsKey(email)) {
                    uf.union(i, emailToIndex.get(email));
                } else {
                    emailToIndex.put(email, i);
                }
            }
        }

        Map<Integer, Set<String>> map = new HashMap<>();

        for (String email : emailToIndex.keySet()) {
            int root = uf.find(emailToIndex.get(email));
            map.computeIfAbsent(root, k -> new HashSet<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();

        for (int root : map.keySet()) {
            List<String> list = new ArrayList<>(map.get(root));
            Collections.sort(list);
            list.add(0, accounts.get(root).get(0));
            result.add(list);
        }

        return result;
    }

    class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px != py) {
                parent[py] = px;
            }
        }
    }
}