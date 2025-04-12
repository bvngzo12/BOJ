import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new ArrayList[n+1];
		
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int[] inDegree = new int[n+1];
		
		for(int i = 0; i < m; i++) {
			 st = new StringTokenizer(br.readLine());
			 
			 int num = Integer.parseInt(st.nextToken());
			 int before = Integer.parseInt(st.nextToken());
			 
			 while(st.hasMoreTokens()) {
				 int nxt = Integer.parseInt(st.nextToken());
				 
				 graph[before].add(nxt);
				 inDegree[nxt]++;
				 
				 before = nxt;
				 
			 }
			 
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= n; i++) {
			if(inDegree[i] == 0) 
				q.offer(i);
		}
		
		List<Integer> result = new ArrayList<>();
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			result.add(curr);
			
			for(int nxt : graph[curr]) {
				
				if(--inDegree[nxt] == 0)
					q.offer(nxt);
			}
			
		}
		
		if(result.size() != n)
			System.out.println(0);
		else {
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < result.size(); i++) {
				sb.append(result.get(i)).append("\n");
			}
			System.out.println(sb.toString());
		}
	}

}
