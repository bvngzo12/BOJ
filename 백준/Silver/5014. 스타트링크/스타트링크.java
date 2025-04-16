import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int f = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		int u = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		Queue<int[]> q = new ArrayDeque<>();
		
		boolean[] visited = new boolean[f+1];
		
		q.offer(new int[] {s,0});
		visited[s] = true;
		int ans = -1;
		
		while(!q.isEmpty()) {
			
			int[] curr =q.poll();
			
			if(curr[0] == g) {
				ans = curr[1];
				break;
			}
			
			for(int k = 0; k < 2; k++) {
				int ns = curr[0];
				
				if(k == 0)	ns += u;		// u	
				else if(k == 1)  ns -= d;	// d
				
				if(ns < 1 || ns > f || visited[ns]) continue;
				
				q.offer(new int[] {ns, curr[1]+1});
				visited[ns] = true;
			}
		}
		
		System.out.println(ans == -1 ? "use the stairs" : ans);
		
	}

}
