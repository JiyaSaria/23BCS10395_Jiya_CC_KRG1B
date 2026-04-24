import java.util.*;

public class SegmentTree {

    static int[] tree;
    static int n;

    // Build the segment tree
    static void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node, start, mid);
            build(arr, 2 * node + 1, mid + 1, end);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    // Range Query
    static int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0; // no overlap

        if (l <= start && end <= r) return tree[node]; // complete overlap

        int mid = (start + end) / 2;
        int left = query(2 * node, start, mid, l, r);
        int right = query(2 * node + 1, mid + 1, end, l, r);

        return left + right;
    }

    // Update a value
    static void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;

            if (idx <= mid)
                update(2 * node, start, mid, idx, val);
            else
                update(2 * node + 1, mid + 1, end, idx, val);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        tree = new int[4 * n];

        build(arr, 1, 0, n - 1);

        int q = sc.nextInt(); // number of queries

        while (q-- > 0) {
            int type = sc.nextInt();

            if (type == 1) {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(query(1, 0, n - 1, l, r));
            } else {
                int idx = sc.nextInt();
                int val = sc.nextInt();
                update(1, 0, n - 1, idx, val);
            }
        }
    }
}