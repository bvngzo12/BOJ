import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Vertex implements Comparable<Vertex>{
		int nxt;
		int weight;
		
		Vertex(int nxt, int w){
			this.nxt = nxt;
			this.weight = w;
		}
				
		public int compareTo(Vertex v1) {
			return Integer.compare(this.weight, v1.weight);
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
	
		List<Vertex>[] graph = new ArrayList[V+1];
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Vertex(e, w));
			graph[e].add(new Vertex(s, w));
			
		}
		
		int[] dist = new int[V+1];
		boolean[] selected = new boolean[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[1] = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(1,0));
		
		while(!pq.isEmpty()) {
			Vertex v = pq.poll();
			
			if(selected[v.nxt]) continue;
			selected[v.nxt] = true;
			
			for(Vertex nxt : graph[v.nxt]) {
				if(selected[nxt.nxt]) continue;
				
				if(dist[nxt.nxt] > nxt.weight) {
					dist[nxt.nxt] = nxt.weight;
					pq.add(nxt);
				}
			}
		}
		
		int ans = 0;
		for(int i = 1; i < dist.length; i++) {
			ans += dist[i];
		}
		System.out.println(ans);
		
	}
}
