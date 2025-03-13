import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Vertex{
	int end;
	int weight;
	
	Vertex(int e, int w){
		this.end = e;
		this.weight = w;
	}
}

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());

		int start = Integer.parseInt(br.readLine());
		
		List<Vertex>[] graph = new ArrayList[v+1];
		
		for(int i = 1; i <= v; i++) {
			graph[i] = new ArrayList<Vertex>();
		}
		
		for(int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Vertex(e, w));
			
		}

		// 다익스트라 초기 설정
		int[] dist = new int[v+1];					// 거리
		Arrays.fill(dist, Integer.MAX_VALUE);		// 거리를 최잿값으로 초기화
		boolean[] selected = new boolean[v+1];		// 대표 노드여부
		dist[start] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {start, 0});				// 후보 노드와 시작 노드로부터의 거리
		
		while(!pq.isEmpty()) {
			
			int[] curr = pq.poll();
			
			if(selected[curr[0]]) continue;
			
			selected[curr[0]] = true;
			for(Vertex nxt : graph[curr[0]]) {
				int nxtNode = nxt.end;
				
				if(selected[nxtNode]) continue;
				
				if(dist[nxtNode] > dist[curr[0]] + nxt.weight) {
					dist[nxtNode] = dist[curr[0]] + nxt.weight;
					pq.offer(new int[] {nxtNode, dist[nxtNode]});
				}
				
			}
		}
		
		for(int i = 1; i < dist.length; i++) {
			System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
		}
		
	}

}
