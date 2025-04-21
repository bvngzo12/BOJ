import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] parents = new int[n+1];
		
		for(int i = 0; i <= n; i++) {
			parents[i] = i;
		}
		
		for(int i =  0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			int pv1 = find(parents, v1);
			int pv2 = find(parents, v2);
			
			if(cmd == 0) {
				
				parents[pv2] = pv1;
				
			}else if(cmd == 1) {
				System.out.println(pv1 == pv2 ? "YES" : "NO");
			}
			
		}
		
	}
	
	private static int find(int[] parents, int v) {
		
		if(parents[v] == v) return v;
		else return parents[v] = find(parents, parents[v]); 
		
	}

}
