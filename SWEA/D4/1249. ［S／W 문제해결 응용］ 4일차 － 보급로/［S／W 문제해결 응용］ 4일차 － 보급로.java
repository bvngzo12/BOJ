import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			int[][] dist = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				String str = br.readLine();
				for(int j = 0; j < n; j++) {
					map[i][j] = str.charAt(j)-'0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			boolean[][] selected = new boolean[n][n];
			
			PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((o1,o2)->Integer.compare(o1[2], o2[2]));
			PQ.offer(new int[] {0,0,0});
			dist[0][0] = 0;
			while(!PQ.isEmpty()) {
				int[] target = PQ.poll();
				int r = target[0];
				int c = target[1];
				int d = target[2];
				
				if(selected[r][c]) continue;
				
				selected[r][c] = true;
				
				for(int k = 0; k < dr.length; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					
					if(nr < 0 || nr >= n|| nc < 0 || nc >= n || selected[nr][nc]) continue;
					
					if(dist[nr][nc] > d+map[nr][nc]) {
						dist[nr][nc] = d+map[nr][nc];
						PQ.add(new int[] {nr,nc,dist[nr][nc]});
					}
				}
			}
			
			System.out.printf("#%d %d\n",t,dist[n-1][n-1]);
		}
		
	}

}
