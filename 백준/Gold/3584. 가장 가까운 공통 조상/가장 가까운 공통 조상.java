import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int[] parents = new int[n+1];
			
			for(int i = 1; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int p = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				parents[s] = p;
				
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[n+1];
			Queue<Integer> q = new ArrayDeque<>();
			
			q.offer(n1);
			q.offer(n2);
			
			while(!q.isEmpty()) {
				int curr = q.poll();
				
				if(visited[curr]) {
					System.out.println(curr);
					break;
				}
				
				visited[curr] = true;
				if(parents[curr] != 0)
					q.offer(parents[curr]);
				
			}
		}
		
	}

}
