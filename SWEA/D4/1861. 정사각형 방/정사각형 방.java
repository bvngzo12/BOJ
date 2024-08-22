
import java.util.Scanner;

public class Solution {
	
	static int[] ans;
	static int[][] M;
	static int N,cnt;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			ans = new int[] {0, 0};
			
			N = sc.nextInt();
			M = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					M[i][j] = sc.nextInt();
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					cnt = 1;
					dfs(i,j);
					if(cnt > ans[1]) {
						ans[0] = M[i][j];
						ans[1] = cnt;
					}
					else if(cnt == ans[1]) {
						ans[0] = Math.min(M[i][j], ans[0]);
					}
				}
			}
			
			System.out.printf("#%d %d %d\n",t,ans[0],ans[1]);
		}

	}

	private static void dfs(int i, int j) {
		for(int d = 0; d < dx.length; d++) {
			int nx = i+dx[d];
			int ny = j+dy[d];
			
			if(nx<0 || nx >= N || ny < 0 || ny >= N) continue;
			
			if(M[nx][ny] == M[i][j]+1) {
				cnt++;
				dfs(nx,ny);
			}
		}
		
	}

}
