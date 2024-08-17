
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static int[] papers = {0,5,5,5,5,5};
	static ArrayList<Point> points = new ArrayList();
	static int[][] map = new int[10][10];
	static int ans = Integer.MAX_VALUE;
	
	static class Point{
		int r;
		int c;
		
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				int v= sc.nextInt();
				map[i][j] = v;
				if(v == 1) {
					points.add(new Point(i,j));
				}
			}
		}
		
		dfs(0, points.size(), 0);
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else{
			System.out.println(ans);
		}
		
	}

	private static void dfs(int idx, int uncovered, int cnt) {
		if(idx == points.size()) {
			// 전부 덮었을 때만 값 갱신 후 return
			if(uncovered == 0) {
				ans = Math.min(cnt, ans);
			}
			return;
		}
		
		Point p = points.get(idx);
		int pr = p.r;
		int pc = p.c;
		
		if(map[pr][pc] < 0) dfs(idx+1,uncovered,cnt);
		
		for(int len = 5; len > 0; len--) {
			
			// 길이 구하기
			boolean coverable = true;
			L:for(int sr = pr; sr < pr+len; sr++) {
				for(int sc = pc; sc < pc+len; sc++) {
					if(pr+len > 10 || pc+len > 10 || map[sr][sc] != 1) {
						coverable = false;
						break L;
					}
				}
			}
			
			if(coverable) {
				if(papers[len]>0) {
					for(int i = pr; i < pr+len; i++) {
						for(int j = pc; j < pc+len; j++) {
							map[i][j] = -len;			// 덮기
							uncovered--;
						}
					}
					
					papers[len]--;
					dfs(idx+1, uncovered,cnt+1);
					papers[len]++;
					
					for(int i = pr; i < pr+len; i++) {
						for(int j = pc; j < pc+len; j++) {
							map[i][j] = 1;			// 덮기
							uncovered++;
						}
					}
				}
			}	// end cover
		}//end for - len
	}

}
