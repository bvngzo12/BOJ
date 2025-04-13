import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Vertex implements Comparable<Vertex>{

		int e;
		int dist;
		
		Vertex(int e, int w){
			this.e = e;
			this.dist = w;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return this.dist - o.dist;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] points = new int[n][3];
		
		List<Vertex>[] graph = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Vertex>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i] = new int[] {i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		
		for(int i = 1; i < 4; i++) {
			
			int index = i;
			Arrays.sort(points, (p1, p2) -> Integer.compare(p1[index], p2[index]));
			
			for(int p = 0; p < n-1; p++) {
				int[] before = points[p];
				int[] nxt = points[p+1];
				
				int dist = getDist(before, nxt);
				
				graph[before[0]].add(new Vertex(nxt[0], dist));
				graph[nxt[0]].add(new Vertex(before[0], dist));
			}
			
		}
		
		
		int[] dist = new int[n];
		boolean[] selected = new boolean[n];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(0,0));
		
		while(!pq.isEmpty()) {
			
			Vertex curr = pq.poll();
			
			if(selected[curr.e]) continue;
			selected[curr.e] = true;
			
			for(Vertex nxt : graph[curr.e]) {
				
				if(!selected[nxt.e] && dist[nxt.e] > nxt.dist) {
					dist[nxt.e] = nxt.dist;
					pq.offer(nxt);
				}
				
			}
			
		}
		
		int total = 0;
		
		for(int i = 0; i < n; i++) {
			total += dist[i];
		}
		
		System.out.println(total);
	}

	private static int getDist(int[] before, int[] nxt) {
		return Math.min(Math.abs(before[1] - nxt[1]), Math.min(Math.abs(before[2] - nxt[2]), Math.abs(before[3] - nxt[3])));
	}

}
