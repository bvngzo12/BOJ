import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[] visited;
	static boolean[] finished;
	static boolean[] isCycled;
	static int[] nxts;
	
	public static void main(String[] args)throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			
			int n = Integer.parseInt(br.readLine());
			nxts = new int[n+1];
			visited = new boolean[n+1];
			finished = new boolean[n+1];
			isCycled = new boolean[n+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i = 1; i <= n; i++) {
				nxts[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= n; i++) {
				if(!visited[i]) dfs(i);
			}
			
			int cnt= 0;
			for(int i = 1; i <= n; i++) {
				if(!isCycled[i]) cnt++;
			}
			System.out.println(cnt);
		}
	}
	
	static void dfs(int curr) {
		visited[curr] = true;
		
		int nxt = nxts[curr];
		
		if(!visited[nxt]) dfs(nxt);
		else if(!finished[nxt]) {
			for(int start = nxt; start != curr; start = nxts[start] ) {
				finished[start] = true;
				isCycled[start] = true;
			}
			isCycled[curr] = true;
		}
		
		finished[curr] = true;
		
	}
}
