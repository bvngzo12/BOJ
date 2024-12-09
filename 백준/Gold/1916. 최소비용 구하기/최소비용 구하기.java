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
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		ArrayList<int[]>[] graph = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<int[]>();
		}
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new int[] {e,w});
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int depart = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((o1,o2)->Integer.compare(o1[1], o2[1]));
		
		PQ.offer(new int[] {depart, 0});
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] selected = new boolean[N+1];
		dist[depart] = 0;
		
		while(!PQ.isEmpty()) {
			int[] curr = PQ.poll();
			if(selected[curr[0]]) continue;
			selected[curr[0]] = true;
			
			for(int[] next : graph[curr[0]]) {
				if(selected[next[0]]) continue;
				if(dist[next[0]] > dist[curr[0]] + next[1]) {
					dist[next[0]] = dist[curr[0]] + next[1];
					PQ.offer(new int[] {next[0], dist[next[0]]});
				}
			}
		}
		
		System.out.println(dist[dest]);
		
	}

}
