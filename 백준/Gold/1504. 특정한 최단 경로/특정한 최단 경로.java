import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Vertex {

		int end;
		int weight;

		Vertex(int e, int w) {
			this.end = e;
			this.weight = w;
		}

	}
	
	static List<Vertex>[] graph;
	static final int INF = 200000*3000;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		graph = new List[v + 1];

		for (int i = 1; i <= v; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int j = 0; j < e; j++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[start].add(new Vertex(end, weight));
			graph[end].add(new Vertex(start, weight));

		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int a1 = dijkstra(1, v1, v);
		int mid = dijkstra(v1, v2, v);
		int a2 = dijkstra(v, v2, v);
		
		int b1 = dijkstra(1, v2, v);
		int b2 = dijkstra(v, v1, v);
		
		int ans1 = a1 + mid + a2;
		int ans2 = b1 + mid + b2;
		int ans = Math.min(ans1, ans2);
		System.out.println(ans >= INF ? -1 : ans);
		
	}
	
	public static int dijkstra(int s, int e, int size) {
		int[] dist = new int[size+1];
		Arrays.fill(dist, INF);
		boolean[] selected = new boolean[size + 1];
		dist[s] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] { s, 0 });

		while (!pq.isEmpty()) {

			int[] curr = pq.poll();

			if (selected[curr[0]])
				continue;
			selected[curr[0]] = true;

			for (Vertex nxt : graph[curr[0]]) {
				if (selected[nxt.end])
					continue;
				if (dist[nxt.end] > dist[curr[0]] + nxt.weight) {
					dist[nxt.end] = dist[curr[0]] + nxt.weight;
					pq.offer(new int[] { nxt.end, dist[nxt.end] });
				}
			}
		}
		
		return dist[e];
	}

}
