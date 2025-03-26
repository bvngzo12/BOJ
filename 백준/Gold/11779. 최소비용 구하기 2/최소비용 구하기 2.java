import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Vertex{
		
		private int e;
		private int w;
		
		public Vertex(int e, int w) {
			this.e = e;
			this.w = w;
		}
		
	}
	
	static class Info implements Comparable<Info>{
		
		private int loc;
		private int length;
		private String path;
		
		public Info(int n, int l, String p) {
			this.loc = n;
			this.length = l;
			this.path = p;
		}

		@Override
		public int compareTo(Info o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.length, o.length);
		}
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		ArrayList<Vertex>[] graph = new ArrayList[n+1];
		
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Vertex(e,w));
			
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		
		int[] dist = new int[n+1];
		boolean[] selected = new boolean[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(start,0, start+" "));
		
		Info ans = null;
		
		while(!pq.isEmpty()) {
			Info curr = pq.poll();
			
			if(selected[curr.loc]) continue;
			selected[curr.loc] = true;
			
			if(curr.loc == end) {
				ans = curr;
				break;
			}
			
			for(Vertex nxt : graph[curr.loc]) {
				if(selected[nxt.e]) continue;
				
				if(dist[nxt.e] > dist[curr.loc] + nxt.w) {
					dist[nxt.e] = dist[curr.loc] + nxt.w;
					pq.offer(new Info(nxt.e, dist[nxt.e], curr.path+nxt.e+" "));
				}
				
			}
			
		}
		
		System.out.println(ans.length);
		System.out.println(ans.path.split(" ").length);
		System.out.println(ans.path);
		
	}

}
