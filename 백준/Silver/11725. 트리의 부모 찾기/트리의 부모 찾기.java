import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		List<Integer>[] graph = new ArrayList[n+1];
		
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
		
			graph[s].add(e);
			graph[e].add(s);
		}
	
		int[] parents = new int[n+1];
		
		
		int[] dr = {0,-1,0,1};
		int[] dc = {1,0,-1,0};
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[n+1];
		
		q.offer(1);
		
		while(!q.isEmpty()) {
			
			int curr = q.poll();
			visited[curr] = true;
			
			for(int nxt : graph[curr]) {
				if(visited[nxt]) continue;
				
				parents[nxt] = curr;
				q.offer(nxt);
				
			}	
		}
		
		for(int i = 2; i <= n; i++) {
			System.out.println(parents[i]);
		}
		
	}

}
