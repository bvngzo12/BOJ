import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int ans = 0;
			
			int N = sc.nextInt();	// 사람 수
			int M = sc.nextInt();	// 관계 수
			
			int[] parents = make(N);
			
			for(int cmd = 0; cmd < M; cmd++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				union(a,b,parents);
			}
			
			for(int i = 1; i<=N; i++) {
				if(i == parents[i]) ans++;
			}
			
			System.out.printf("#%d %d\n",t,ans);
		}
	}
	
	static int[] make(int N) {
		int[] parents = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		return parents;
	}
	
	static int findSet(int a, int[] p) {
		if(a == p[a]) return a;
		else return p[a] = findSet(p[a],p);
	}
	
	static boolean union(int a, int b, int[] p) {
		int aRoot = findSet(a, p);
		int bRoot = findSet(b, p);
		if(aRoot != bRoot) {
			p[bRoot] = aRoot;
			return true;
		}
		else return false;
	}

}
