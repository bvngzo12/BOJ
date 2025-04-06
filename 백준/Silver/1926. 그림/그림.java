import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		
		int maxArea = 0;
		int cnt = 0;
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				
				if(map[i][j] == 1) {
					int area = bfs(i, j);
					maxArea = Math.max(maxArea, area);
					cnt++;
				}
				
			}
		}
		
		System.out.println(cnt);
		System.out.println(maxArea);
		
	}

	private static int bfs(int i, int j) {
		
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {i,j});
		map[i][j] = -1;
		int area = 1;
		
		int[] dr = {0, -1, 0, 1};
		int[] dc = {1, 0, -1, 0};
		
		while(!q.isEmpty()) {
			
			int[] curr = q.poll();
			
			for(int d = 0; d < dr.length; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				
				if(nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length || map[nr][nc] != 1) continue;
				q.offer(new int[] {nr, nc});
				map[nr][nc] = -1;
				area++;
				
			}
			
		}
		
		return area;
		
	}

}
