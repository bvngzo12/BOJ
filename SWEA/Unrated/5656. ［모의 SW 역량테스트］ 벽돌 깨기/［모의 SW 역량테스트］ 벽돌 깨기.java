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
	//static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			
			N = sc.nextInt();
			C = sc.nextInt();
			R = sc.nextInt();
			
			ans = Integer.MAX_VALUE;
			//cnt = 0;
			
			map = new int[R][C];
			
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					map[i][j] = sc.nextInt();
					//if(map[i][j] > 0) cnt++;
				}
			}
			
			perms = new ArrayList<int[]>();
			permutation(0, new int[N]);
			
			for(int[] perm : perms) {
				//int[] perm = {2,2,6};
				copyMap = new int[R][C];
				//int left = cnt;
				// copy map
				for(int i = 0; i < R; i++) {
					copyMap[i] = map[i].clone();
				}

				for(int i = 0; i < perm.length; i++) {
					int row = 0;
					while(row < R && copyMap[row][perm[i]] == 0) row++;
					
					if(row == R) continue;
					
					crash(new int[] {row, perm[i]});
					fall();
					//System.out.println(left);
				}
				
				int cnt = 0;
				
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(copyMap[i][j] > 0)cnt++;
					}
				}
				
//				System.out.println("***************************");
//				for(int i = 0; i < R; i++) {
//					for(int j = 0; j < C; j++) {
//						System.out.print(copyMap[i][j]+" ");
//					}System.out.println();
//				}
				
				ans = Math.min(ans, cnt);
			}
			
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



	private static void crash(int[] ball) {
		Queue<int[]> bricks = new ArrayDeque();
		bricks.offer(ball);
		
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
					if(copyMap[nr][nc] == 0) continue;
					
					bricks.offer(new int[] {nr,nc});
				}
			}
			copyMap[r][c] = 0;
			
		}
		
		//return left;
		
	}



	private static void permutation(int idx, int[] sel) {
		if(idx == sel.length) {
			//System.out.println(Arrays.toString(sel));
			perms.add(sel.clone());
			return;
		}
		
		for(int i = 0; i < C; i++) {
			sel[idx] = i;
			permutation(idx+1, sel);
		}
		
	}

}
