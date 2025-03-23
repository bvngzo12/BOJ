import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] maxDP = new int[n][3];
		int[][] minDP = new int[n][3];
		
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 3; j++) {
				maxDP[i][j] = Integer.parseInt(st.nextToken());
				minDP[i][j] = maxDP[i][j];
			}
			
		}
		
		for(int i = 1; i < n; i++) {
			for(int j= 0; j < 3; j++) {
				maxDP[i][j] = Math.max(maxDP[i][j] + maxDP[i-1][j], 
									Math.max((j+1) < 3 ? maxDP[i][j] + maxDP[i-1][j+1] : -1, 
											 (j-1) >= 0 ? maxDP[i][j] + maxDP[i-1][j-1] : -1));
				
				minDP[i][j] = Math.min(minDP[i][j] + minDP[i-1][j], 
						Math.min((j+1) < 3 ? minDP[i][j] + minDP[i-1][j+1] : 1000000, 
								 (j-1) >= 0 ? minDP[i][j] + minDP[i-1][j-1] : 1000000));
			}
		}
		
		int min = Math.min(minDP[n-1][0], Math.min(minDP[n-1][1], minDP[n-1][2]));
		int max = Math.max(maxDP[n-1][0], Math.max(maxDP[n-1][1], maxDP[n-1][2]));
		
		
		System.out.println(max+" "+min);
	}

}
