class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        Integer[] dp = new Integer[n];
        return solve(0, arr, k, dp);
    }

    int solve(int i, int[] arr, int k, Integer[] dp) {
        if (i == arr.length) return 0;

        if (dp[i] != null) return dp[i];

        int max = 0;
        int maxi = 0;

        for (int j = i; j < Math.min(arr.length, i + k); j++) {
            maxi = Math.max(maxi, arr[j]);
            int len = j - i + 1;

            int sum = len * maxi + solve(j + 1, arr, k, dp);
            max = Math.max(max, sum);
        }

        return dp[i] = max;
    }
}