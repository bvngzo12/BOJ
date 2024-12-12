import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] triangle = new int[n][];
		int[][] dp = new int[n][];
		
		for(int i = 0; i < n; i++) {
			triangle[i] = new int[i+1];
			dp[i] = new int[i+1];
			for(int j = 0; j < i+1; j++) {
				triangle[i][j] = sc.nextInt();
			}
		}
		
		
		dp[0][0] = triangle[0][0];
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i+1; j++) {
				int last = dp[i].length;
				if(j==0) dp[i][j] = dp[i-1][j] + triangle[i][j];
				else if(j==i) dp[i][j] = dp[i-1][j-1] + triangle[i][j];
				else dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
			}
		}
		
		int ans = Integer.MIN_VALUE;
		for(int j = 0; j < n; j++) {
			ans = Math.max(ans, dp[n-1][j]);
		}
		System.out.println(ans);
	}

}
