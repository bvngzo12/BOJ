import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = 1;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			
			if(N == 0) break;
			
			int[][] map = new int[N][N];
			int[][] dist = new int[N][N];
			boolean[][] selected = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((o1,o2)->Integer.compare(o1[2], o2[2]));
			dist[0][0] = map[0][0];
			PQ.offer(new int[] {0,0,dist[0][0]});
			
			while(!PQ.isEmpty()) {
				int[] curr = PQ.poll();
				int r = curr[0];
				int c = curr[1];
				int time = curr[2];
				
				if(selected[r][c]) continue;
				
				selected[r][c] = true;
				
				for(int d = 0; d < dr.length; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || selected[nr][nc]) continue;
					
					if(dist[nr][nc] > time + map[nr][nc]) {
						dist[nr][nc] = time + map[nr][nc];
						PQ.offer(new int[] {nr, nc, dist[nr][nc]});
					}
					
				}
				
			}// end pq
			System.out.printf("Problem %d: %d\n", tc, dist[N-1][N-1]);
			tc++;
		}// end while

	}

}
