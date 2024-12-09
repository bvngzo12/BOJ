import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Vertex implements Comparable<Vertex>{
		private int end;
		private int time;
		
		public Vertex(int end, int time) {
			super();
			this.end = end;
			this.time = time;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.time, o.time);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		ArrayList<Vertex>[] graph = new ArrayList[N+1];
		
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Vertex>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Vertex(e, w));
		}
		
		int[] totalTime = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
			pq.add(new Vertex(i,0));	// 기준점
			
			//초기화
			boolean[] selected = new boolean[N+1];
			int[] dist = new int[N+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[i] = 0;
			
			while(!pq.isEmpty()) {
				Vertex curr = pq.poll();
				
				if(selected[curr.end]) continue;
				selected[curr.end] = true;
				
				for(Vertex next : graph[curr.end]) {
					if(selected[next.end]) continue;
					
					if(dist[next.end] > dist[curr.end] + next.time) {
						dist[next.end] = dist[curr.end] + next.time;
						pq.offer(new Vertex(next.end, dist[next.end]));
					}
				}
			}
			
			if(i == X) {
				for(int j = 1; j<= N; j++) {
					totalTime[j] += dist[j];
				}
			}else {
				totalTime[i] += dist[X];
			}
		}// end
		
		int max = Integer.MIN_VALUE;
		
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, totalTime[i]);
		}
		
		System.out.println(max);
	}

}
