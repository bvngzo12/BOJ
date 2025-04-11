import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		
		init();
		
		for(int i = 1; i <= m; i++) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(line.nextToken());
			int n2 = Integer.parseInt(line.nextToken());
			
			if(union(n1, n2)) {
				System.out.println(i);
				return;
			}
			
		}
		
		System.out.println(0);
		
	}

	private static void init() {
		
		for(int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		
	}
	
	private static int find(int child) {
		if(parent[child] == child) return child;
		else return parent[child] = find(parent[child]);
	}
	
	private static boolean union(int c1, int c2) {
		
		int p1 = find(c1);
		int p2 = find(c2);
		
		if(p1 == p2) return true;
		
		parent[p1 < p2 ? p2 : p1] = p1 < p2 ? p1 : p2 ;
		return false;
		
	}

}
