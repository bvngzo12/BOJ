import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new ArrayList[v+1];
		
		for(int i = 1; i <= v; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			graph[v1].add(v2);
			graph[v2].add(v1);
			
		}
		
		for(int i = 1; i <= v; i++) {
			graph[i].sort(null);
		}
		
		dfs(graph, new boolean[v+1], s);
		System.out.println();
		bfs(graph, new boolean[v+1], s);
		
	}

	private static void bfs(List<Integer>[] graph, boolean[] visited, int s) {
		
		Queue<Integer> q = new ArrayDeque();
		q.offer(s);
		visited[s] = true;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			System.out.print(curr+" ");
			
			
			for(int nxt : graph[curr]) {
				if(visited[nxt]) continue;
				
				q.offer(nxt);
				visited[nxt] = true;
			}
		}
		
	}

	private static void dfs(List<Integer>[] graph, boolean[] visited, int s) {
		
		System.out.print(s+" ");
		visited[s] = true;
		
		for(int nxt : graph[s]) {
			if(visited[nxt]) continue;
			
			dfs(graph, visited, nxt);
			
		}
	}

}
