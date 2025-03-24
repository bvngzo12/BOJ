import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[][] maze = new int[r][c];
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		
		for(int i = 0; i < r; i++) {
			String str = br.readLine();
			
			for(int j = 0; j < c; j++) {
				maze[i][j] = str.charAt(j) - '0';
			}
			
		}
		
		boolean[][] visited = new boolean[r][c];
		int[][] path = new int[r][c];
		
		path[0][0] = 1;
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.offer(new int[] {0,0});
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			
			for(int d = 0; d < dr.length; d++) {
				
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				
				if(nr < 0 || nr >= r || nc < 0 || nc >= c || visited[nr][nc] || maze[nr][nc] == 0) continue;
				
				path[nr][nc] = path[curr[0]][curr[1]] + 1;
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				
			}
			
		}
		
		System.out.println(path[r-1][c-1]);
		
	}

}
