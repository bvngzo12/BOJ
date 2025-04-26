import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Integer>[] tree;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int root = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		tree = new ArrayList[n+1];
		
		for(int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
		
			tree[n1].add(n2);
			tree[n2].add(n1);
		}
		
		
		
		int[] dp = new int[n+1];
		boolean[] visited = new boolean[n+1];
		
		doDP(dp, root, visited);
		
		for(int i = 0; i < q; i++) {
			int query = Integer.parseInt(br.readLine());
		
			System.out.println(dp[query]);
		}

	}

	private static int doDP(int[] dp, int query, boolean[] v) {
		
		if(dp[query] > 0) return dp[query];
		
		v[query] = true;
		int sum = 0;
		for(int nxt : tree[query]) {
			
			if(v[nxt]) continue;
			sum += doDP(dp, nxt, v);
			
		}
		
		return sum == 0 ? (dp[query] = 1) : (dp[query] = sum+1);
		
	}
	

}
