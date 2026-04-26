import java.util.*;

public class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }

        for (int[] edge : redEdges) {
            redGraph[edge[0]].add(edge[1]);
        }

        for (int[] edge : blueEdges) {
            blueGraph[edge[0]].add(edge[1]);
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);

        boolean[][] visited = new boolean[n][2];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});

        visited[0][0] = true;
        visited[0][1] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0];
                int color = curr[1];

                if (result[node] == -1) {
                    result[node] = steps;
                }

                if (color == 0) {
                    for (int nei : blueGraph[node]) {
                        if (!visited[nei][1]) {
                            visited[nei][1] = true;
                            queue.offer(new int[]{nei, 1});
                        }
                    }
                } else {
                    for (int nei : redGraph[node]) {
                        if (!visited[nei][0]) {
                            visited[nei][0] = true;
                            queue.offer(new int[]{nei, 0});
                        }
                    }
                }
            }
            steps++;
        }

        return result;
    }
}