import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new  StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] graph = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<int[]>();
		}
		
		st = new StringTokenizer(br.readLine());
		int[] center = new int[K+1];
		
		for(int i = 0; i < K; i++) {
			center[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new int[] {v,w});
			graph[v].add(new int[] {u,w});
		}
		
		PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((o1,o2)->Integer.compare(o1[1],o2[1]));
		boolean[] selected = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i = 0; i < K; i++) {
			PQ.offer(new int[] {center[i],0});
			dist[center[i]] = 0;

		}
		int ans = 0;
		while(!PQ.isEmpty()) {
			int[] target = PQ.poll();
			int e = target[0];
			
			if(selected[e]) 
				continue;
				
			selected[e] = true;
			ans += dist[e];
	
			for(int[] nxt : graph[e]) {
				if(selected[nxt[0]]) continue;
				
				if(dist[nxt[0]] > nxt[1]) {
					dist[nxt[0]] = nxt[1];
					PQ.add(nxt);
				}
				
			}
		}
		System.out.println(ans);
	}
}
