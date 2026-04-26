import java.util.*;

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n];
        int[] indexes = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        mergeSort(nums, indexes, temp, counts, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int c : counts) {
            result.add(c);
        }
        return result;
    }

    private void mergeSort(int[] nums, int[] indexes, int[] temp, int[] counts, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(nums, indexes, temp, counts, left, mid);
        mergeSort(nums, indexes, temp, counts, mid + 1, right);
        merge(nums, indexes, temp, counts, left, mid, right);
    }

    private void merge(int[] nums, int[] indexes, int[] temp, int[] counts, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (nums[indexes[j]] < nums[indexes[i]]) {
                temp[k++] = indexes[j++];
                rightCount++;
            } else {
                counts[indexes[i]] += rightCount;
                temp[k++] = indexes[i++];
            }
        }

        while (i <= mid) {
            counts[indexes[i]] += rightCount;
            temp[k++] = indexes[i++];
        }

        while (j <= right) {
            temp[k++] = indexes[j++];
        }

        for (int p = left; p <= right; p++) {
            indexes[p] = temp[p];
        }
    }
}