import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int V = sc.nextInt();

		int items[][] = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			items[i][0] = sc.nextInt();
			items[i][1] = sc.nextInt();
		}

		int[][] dp = new int[N + 1][V + 1];

		for (int w = 1; w <= V; w++) {
			for (int i = 1; i <= N; i++) {
				if (items[i][0] > w) {
					dp[i][w] = dp[i - 1][w];
				} else {
					int vi = items[i][1];
					int wi = items[i][0];
					dp[i][w] = Math.max(dp[i - 1][w], vi + dp[i - 1][w - wi]);
				}
			}
		}

		System.out.println(dp[N][V]);

	}

}
