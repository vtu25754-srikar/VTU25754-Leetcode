import java.util.*;

class Solution {

    static List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int nei : graph.get(node)) {
                if (--indegree[nei] == 0) {
                    queue.add(nei);
                }
            }
        }

        if (result.size() != size) return new ArrayList<>();
        return result;
    }

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {

                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);

        if (itemOrder.isEmpty() || groupOrder.isEmpty()) return new int[0];

        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int item : itemOrder) {
            groupToItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        List<Integer> result = new ArrayList<>();
        for (int grp : groupOrder) {
            if (groupToItems.containsKey(grp)) {
                result.addAll(groupToItems.get(grp));
            }
        }

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }
}
