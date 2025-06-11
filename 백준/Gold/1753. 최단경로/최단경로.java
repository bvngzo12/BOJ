import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Vertex{
		private int e;
		private int w;
		
		Vertex(int e, int w){
			this.e = e;
			this.w = w;
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int vertex = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());
		
		List<Vertex>[] graph = new ArrayList[vertex+1];
		
		for(int i = 1; i <= vertex; i++) {
			graph[i] = new ArrayList<Vertex>();
		}
		
		for(int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Vertex(e,v));
			
		}
		
		int[] dist = new int[vertex+1];
		boolean[] selected = new boolean[vertex+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2) -> Integer.compare(o1[1], o2[1]));
		
		pq.offer(new int[] {start,0});
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			if(selected[curr[0]]) continue;
			selected[curr[0]] = true;
			
			for(Vertex nxt : graph[curr[0]]) {
				if(selected[nxt.e]) continue;
				
				if(dist[nxt.e] > dist[curr[0]] + nxt.w) {
					dist[nxt.e] = dist[curr[0]] + nxt.w;
					pq.offer(new int[] {nxt.e, dist[nxt.e]});
				}
				
			}
			
		}
		
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= vertex; i++) {
			sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}
