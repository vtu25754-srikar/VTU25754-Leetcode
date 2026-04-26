import java.util.*;

public class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if (original == color) return image;
        dfs(image, sr, sc, original, color);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int original, int color) {
        int m = image.length;
        int n = image[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || image[i][j] != original) {
            return;
        }

        image[i][j] = color;

        dfs(image, i + 1, j, original, color);
        dfs(image, i - 1, j, original, color);
        dfs(image, i, j + 1, original, color);
        dfs(image, i, j - 1, original, color);
    }
}