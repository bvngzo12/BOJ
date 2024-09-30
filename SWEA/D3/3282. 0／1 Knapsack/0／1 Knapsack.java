import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, K;
	static int[] value, weight;
	static int[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			maps = new int[N+1][K+1];
			weight = new int[N+1];
			value = new int[N+1];
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				weight[n] = v;
				value[n] = c;
			}
			
			run();
			
			bw.write("#" + tc + " " + maps[N][K] + "\n");
		}
		
		bw.close();
		br.close();
	}

	private static void run() {
		for (int i = 1; i < N+1; i++) {
			int curWeight = weight[i];
			int curValue = value[i];
			
			for (int j = 1; j < K+1; j++) {
				if (curWeight <= j) { //넣을 수 있음
					maps[i][j] = Math.max(curValue + maps[i-1][j-curWeight], maps[i-1][j]); 
				} else {
					maps[i][j] = Math.max(maps[i-1][j], maps[i][j-1]);
				}
			}
		}
	}

}
