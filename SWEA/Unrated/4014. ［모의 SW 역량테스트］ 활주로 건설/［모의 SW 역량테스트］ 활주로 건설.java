import java.util.ArrayList;
import java.util.Scanner;

public class Solution{
	
	static class Info{
		int value;
		int cnt;
		
		public Info(int value, int cnt) {
			super();
			this.value = value;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Info [value=" + value + ", cnt=" + cnt + "]";
		}
		
		
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int X = sc.nextInt();
			
			int[][] map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			ArrayList<Info>[] row = new ArrayList[N];
			ArrayList<Info>[] col = new ArrayList[N];
			// 행 데이터
			for(int i = 0; i < N; i++) {
				row[i] = new ArrayList<Info>();
				
				int old = -1;
				for(int j = 0; j < N; j++) {
					if(old != map[i][j]) {
						Info info = new Info(map[i][j], 1);
						row[i].add(info);
						old = map[i][j];
					}else {
						row[i].get(row[i].size()-1).cnt++;
					}
				}
			}
			// 열 데이터
			for(int i = 0; i < N; i++) {
				col[i] = new ArrayList<Info>();
				
				int old = -1;
				for(int j = 0; j < N; j++) {
					if(old != map[j][i]) {
						Info info = new Info(map[j][i], 1);
						col[i].add(info);
						old = map[j][i];
					}else {
						col[i].get(col[i].size()-1).cnt++;
					}
				}
			}
			

			
			int ans = 0;
			
			//행 검사
			for(int i = 0; i <N; i++) {
				if(row[i].size() == 1) ans++;
				else {
					boolean flag = true;
					for(int j = 1; j < row[i].size(); j++) {
						Info r1 = row[i].get(j);
						Info r2 = row[i].get(j-1);
						
						if(Math.abs(r1.value - r2.value) > 1) {
							flag = false;
							break;
						}else if(Math.abs(r1.value - r2.value) == 1) {
							Info temp = (r1.value > r2.value) ? r2 : r1;
							if(temp.cnt < X) {
								flag = false;
								break;
							}else {
								temp.cnt -= X;
							}
						}
					}
					if(flag) ans++;

				}
			}
			
			//열 검사
			for(int i = 0; i <N; i++) {
				if(col[i].size() == 1) ans++;

				else {
					boolean flag = true;
					for(int j = 1; j < col[i].size(); j++) {
						Info c1 = col[i].get(j);
						Info c2 = col[i].get(j-1);
						
						
						if(Math.abs(c1.value - c2.value) > 1) {
							flag = false;
							break;
						}else if(Math.abs(c1.value - c2.value) == 1) {
							Info temp = (c1.value > c2.value) ? c2 : c1;
							if(temp.cnt < X) {
								flag = false;
								break;
							}else {
								temp.cnt -= X;
							}
						}
					}
					if(flag) ans++;
						
				}
			}
			
			System.out.printf("#%d %d\n",t, ans);
		}
		
	}

}
// c6 - (2,2) (1,6) (2,1)
 
 