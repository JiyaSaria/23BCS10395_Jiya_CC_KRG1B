import java.util.*;

public class mst {

    public static int solve(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        int energy = 0;
        int initial= 0;

        for (int[] task : tasks) {
            int actual = task[0];
            int min = task[1];
            if (energy < min) {
                initial += (min - energy);
                energy = min;
            }
            energy -= actual;
        }
        return initial;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   

        int[][] tasks = new int[n][2];

        for (int i = 0; i < n; i++) {
            tasks[i][0] = sc.nextInt(); 
            tasks[i][1] = sc.nextInt(); 
        }

        int result = solve(tasks);

        System.out.println(result);
    }}