import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] prices = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			prices[i][0] = Integer.parseInt(st.nextToken());
			prices[i][1] = Integer.parseInt(st.nextToken());
			prices[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[3][N];
		for(int c = 0; c < 3; c++) {
			dp[c][0] = prices[0][c];
		}
		
		for(int i = 1; i < N; i++) {
			dp[0][i] = Math.min(dp[1][i-1], dp[2][i-1]) + prices[i][0];
			dp[1][i] = Math.min(dp[0][i-1], dp[2][i-1]) + prices[i][1];
			dp[2][i] = Math.min(dp[0][i-1], dp[1][i-1]) + prices[i][2];
		}
		
		System.out.println(Math.min(dp[0][N-1], Math.min(dp[1][N-1], dp[2][N-1])));
		
	}

}
