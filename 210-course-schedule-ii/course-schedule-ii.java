import java.util.*;
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {        
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];           
            graph.get(prereq).add(course);
            indegree[course]++;
        }
        Queue<Integer> queue = new LinkedList<>();      
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }        
        int[] result = new int[numCourses];
        int index = 0;        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result[index++] = curr;           
            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (index != numCourses) {
            return new int[0];
        }        
        return result;
    }
}