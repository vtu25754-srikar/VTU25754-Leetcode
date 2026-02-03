import java.util.Stack;
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int ast : asteroids) {
            boolean alive = true;
            while (alive && !stack.isEmpty() && stack.peek() > 0 && ast < 0) {
                int top = stack.peek();
                if (Math.abs(top) < Math.abs(ast)) {
                    stack.pop();          
                } else if (Math.abs(top) == Math.abs(ast)) {
                    stack.pop();          
                    alive = false;
                } else {
                    alive = false;       
                }
            }
            if (alive) {
                stack.push(ast);
            }
        }
        int[] result = new int[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            result[i] = stack.get(i);
        }
        return result;
    }
}
