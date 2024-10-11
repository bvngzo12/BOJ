import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int N,C,R;
	static int[][] map, copyMap;
	static ArrayList<int[]> perms;
	static int ans;
	static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			
			N = sc.nextInt();
			C = sc.nextInt();
			R = sc.nextInt();
			
			ans = Integer.MAX_VALUE;
			cnt = 0;
			
			map = new int[R][C];
			
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] > 0) cnt++;
				}
			}
			
			perms = new ArrayList<int[]>();
			permutation(0, new int[N]);
	
			System.out.printf("#%d %d\n",tc, ans);
			
		}
	}


	private static void fall() {
		for(int i = R-1; i >= 0; i--) {
			for(int j = 0; j < C; j++) {
				if(copyMap[i][j] > 0) {
					int k = i;
					while(k+1 < R && copyMap[k+1][j] == 0) {
						copyMap[k+1][j] = copyMap[k][j];
						copyMap[k++][j] = 0;
					}
				}
			}
		}
		
	}



	private static int crash(int[] ball, int left) {
		boolean[][] visited = new boolean[R][C];
		
		Queue<int[]> bricks = new ArrayDeque();
		bricks.offer(ball);
		visited[ball[0]][ball[1]] = true;
		
		int[] dr = {1,0,-1,0};
		int[] dc = {0,1,0,-1};
		
		while(!bricks.isEmpty()) {
			int[] target = bricks.poll();
			int r = target[0];
			int c = target[1];
			int len = copyMap[r][c];
			
			for(int d = 0; d < dr.length; d++) {
				int nr = r;
				int nc = c;
				
				for(int l = 1; l < len; l++) {
					nr += dr[d];
					nc += dc[d];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) break;
					if(copyMap[nr][nc] == 0 || visited[nr][nc]) continue;
					
					bricks.offer(new int[] {nr,nc});
					visited[nr][nc] = true;
				}
			}
			copyMap[r][c] = 0;
			left--;
			
		}
		
		return left;
		
	}



	private static void permutation(int idx, int[] sel) {
		if(idx == sel.length) {
			copyMap = new int[R][C];
			int left = cnt;
			// copy map
			for(int i = 0; i < R; i++) {
				copyMap[i] = map[i].clone();
			}

			for(int i = 0; i < sel.length; i++) {
				int row = 0;
				while(row < R && copyMap[row][sel[i]] == 0) row++;
				
				if(row == R) continue;
				
				left = crash(new int[] {row, sel[i]}, left);
				fall();
			}
			
			ans = Math.min(ans, left);
			
			return;
		}
		
		for(int i = 0; i < C; i++) {
			sel[idx] = i;
			permutation(idx+1, sel);
		}
		
	}

}
