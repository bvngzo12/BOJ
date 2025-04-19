import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Vertex{
		
		int e;
		int w;
		
		Vertex(int e, int w){
			this.e  = e;
			this.w = w;
		}
		
	}

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[] items = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
	
		List<Vertex>[] graph = new ArrayList[n+1];
		
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Vertex>();
		}
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Vertex(e,w));
			graph[e].add(new Vertex(s,w));
			
		}
		
		int ans = -1;
		
		for(int i = 1; i <= n; i++) {
			
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[1], o2[1]));
			
			boolean[] selected = new boolean[n+1];
			int[] dist = new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			pq.offer(new int[] {i, 0});		// 위치, 현재 아이템 수, 거리
			dist[i] = 0;
						
			while(!pq.isEmpty()) {
				
				int[] curr = pq.poll();
				
				if(selected[curr[0]]) continue;
				selected[curr[0]] = true;
				
				for(Vertex nxt : graph[curr[0]]) {
					
					if(!selected[nxt.e] && dist[nxt.e] > dist[curr[0]] + nxt.w) {
						
						dist[nxt.e] = dist[curr[0]] + nxt.w;
						pq.offer(new int[] {nxt.e, dist[nxt.e]});
						
					}
				}
			}
			
			int total = 0;
			for(int k = 1; k <= n; k++) {
				if(dist[k] <= m) total += items[k];
			}
			
			ans = Math.max(ans, total);
			
		}
		
		System.out.println(ans);
		
	}

}
