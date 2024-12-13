import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] stickers = new int[2][N];
			int[][] dp = new int[2][N];
			
			for(int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][0] = stickers[0][0];
			dp[1][0] = stickers[1][0];
			
			for(int d = 1; d < N; d++) {
				dp[0][d] = Math.max(dp[1][d-1] + stickers[0][d], dp[0][d-1]); 
				dp[1][d] = Math.max(dp[0][d-1] + stickers[1][d], dp[1][d-1]); 
			}
			System.out.println(Math.max(dp[0][N-1], dp[1][N-1]));
			
		}
		
	}

}
