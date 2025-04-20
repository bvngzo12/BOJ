import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] colors = new int[n][3];
		
		final int INF = 1000000;
		
		for(int i = 0; i < n; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			colors[i][0] = Integer.parseInt(st.nextToken());
			colors[i][1] = Integer.parseInt(st.nextToken());
			colors[i][2] = Integer.parseInt(st.nextToken());
			
		}
		
		int ans = Integer.MAX_VALUE;
		
		for(int color = 0; color < 3; color++) {
			int[][] dp = new int[n][3];
			Arrays.fill(dp[0], INF);
			
			dp[0][color] = colors[0][color];
			
			for(int i = 1; i < n; i++) {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + colors[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + colors[i][1];
				dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + colors[i][2];
			}
			
			ans = Math.min(ans, Math.min(dp[n-1][(color + 1)%3], dp[n-1][(color + 2)%3]));
			
		}
		
		System.out.println(ans);
		
	}
	
	
}
