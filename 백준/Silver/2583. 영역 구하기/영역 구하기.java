import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	
	static int[][] M;
	static boolean[][] visited;
	static int R,C,cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		int K = sc.nextInt();
		
		
		M = new int[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < K; i++) {
			int startY = sc.nextInt();
			int startX = sc.nextInt();
			
			int endY = sc.nextInt();
			int endX = sc.nextInt();
			
			for(int x = startX; x < endX; x++) {
				for(int y = startY; y < endY; y++) {
					M[x][y] = 1;
				}
			}
			
		}

		ArrayList<Integer> area = new ArrayList();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(dfs(i,j)) {
					area.add(cnt);
					cnt = 0;
				}
				
			}
		}
		area.sort(null);
		
		System.out.println(area.size());
		area.forEach(i->System.out.print(i+" "));
		

	}
	
	static boolean dfs(int r, int c) {
		if(r < 0 || r >= R || c < 0 || c >= C|| visited[r][c] || M[r][c] == 1 ) {
			return false;
		}
		cnt++;
		visited[r][c] = true;
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			dfs(nr,nc);
		}
		return true;
		
		
	}
	
	static void print(int[][] M) {
		for (int[] is : M) {
			for (int i : is) {
				System.out.print(i+" ");
			}System.out.println();
		}
	}
}

