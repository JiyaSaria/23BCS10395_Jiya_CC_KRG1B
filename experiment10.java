import java.util.*;

public class Main {

    static class Pair {
        int val, idx;
        Pair(int v, int i) {
            val = v;
            idx = i;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] arr = new Pair[n];
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        mergeSort(arr, 0, n - 1, result);

        List<Integer> res = new ArrayList<>();
        for (int x : result) res.add(x);
        return res;
    }

    private void mergeSort(Pair[] arr, int left, int right, int[] result) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid, result);
        mergeSort(arr, mid + 1, right, result);

        merge(arr, left, mid, right, result);
    }

    private void merge(Pair[] arr, int left, int mid, int right, int[] result) {
        List<Pair> temp = new ArrayList<>();

        int i = left, j = mid + 1;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (arr[j].val < arr[i].val) {
                temp.add(arr[j]);
                rightCount++;
                j++;
            } else {
                result[arr[i].idx] += rightCount;
                temp.add(arr[i]);
                i++;
            }
        }

        while (i <= mid) {
            result[arr[i].idx] += rightCount;
            temp.add(arr[i]);
            i++;
        }

        while (j <= right) {
            temp.add(arr[j]);
            j++;
        }

        for (int k = left; k <= right; k++) {
            arr[k] = temp.get(k - left);
        }
    }

    public static void main(String[] args) {
        Main obj = new Main();
        int[] nums = {5, 2, 6, 1};
        System.out.println(obj.countSmaller(nums));
    }
}