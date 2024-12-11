import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		int[] dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			dp[i] = Integer.parseInt(st.nextToken()) + dp[i-1];
		}
		
		int[][] subs = new int[M][2];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			subs[i][0] = Integer.parseInt(st.nextToken());
			subs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int[] sub : subs) {
			System.out.println(dp[sub[1]]-dp[sub[0]-1]);
		}
		
	}

}
