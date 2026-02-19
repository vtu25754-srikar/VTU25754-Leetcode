import java.util.*;
class Solution {   
    class Node {
        int row, col, val;
        Node(int r, int c, int v) {
            row = r;
            col = c;
            val = v;
        }
    }    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Node> list = new ArrayList<>();        
        dfs(root, 0, 0, list);
        Collections.sort(list, (a, b) -> {
            if (a.col != b.col)
                return a.col - b.col;
            if (a.row != b.row)
                return a.row - b.row;
            return a.val - b.val;
        });       
        List<List<Integer>> result = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;
        
        for (Node node : list) {
            if (node.col != prevCol) {
                result.add(new ArrayList<>());
                prevCol = node.col;
            }
            result.get(result.size() - 1).add(node.val);
        }       
        return result;
    }   
    private void dfs(TreeNode root, int row, int col, List<Node> list) {
        if (root == null) return;
        
        list.add(new Node(row, col, root.val));
        dfs(root.left, row + 1, col - 1, list);
        dfs(root.right, row + 1, col + 1, list);
    }
}
