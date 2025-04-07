import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	static int n;
	static int[] dr = {-1, 0 ,1, 0};
	static int[] dc = {0, 1 , 0, -1};
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		char[][] map = new char[n][n];
		char[][] mapRG = new char[n][n];
		
		
		for(int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			mapRG[i] = map[i].clone();
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(mapRG[i][j] == 'R')
					mapRG[i][j] = 'G';
			}
		}
		
		int a1 = 0;
		int a2 = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0;j < n; j++) {
				if(map[i][j] != 'X') {
					bfs(map, i,j);
					a1++;
				}
				if(mapRG[i][j] != 'X') {
					bfs(mapRG, i,j);
					a2++;
				}
			}
		}
		
		System.out.println(a1+" "+a2);
		
	}

	private static void bfs(char[][] map, int i, int j) {
		Queue<int[]> Q = new ArrayDeque<>();
		char target = map[i][j];
		
		Q.offer(new int[] {i,j});
		map[i][j] = 'X';
		
		while(!Q.isEmpty()) {
			
			int[] curr = Q.poll();
			
			for(int d = 0; d < dr.length; d++) {
				int nr = curr[0] +dr[d];
				int nc = curr[1] +dc[d];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] != target) continue;
				
				Q.offer(new int[] {nr, nc});
				map[nr][nc] = 'X';
				
			}
			
		}
		
	}

}
