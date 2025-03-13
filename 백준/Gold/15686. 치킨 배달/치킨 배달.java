import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[][] map;
	static List<int[]> homes;
	static List<int[]> chickens;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		homes = new ArrayList<>();
		chickens = new ArrayList<>();
		
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) homes.add(new int[] {i,j});
				else if(map[i][j] == 2) chickens.add(new int[] {i,j});
			}
		}
		
		delivery(new int[m], 0, 0);
		System.out.println(ans);
	}

	private static void delivery(int[] sel, int selIdx, int idx) {
		
		if(selIdx == sel.length) {
			int total = 0;
			
			for(int j = 0; j < homes.size(); j++) {
				int[] home = homes.get(j);
				int dist = Integer.MAX_VALUE;
				
				for(int i = 0; i < sel.length; i++) {
					int[] chicken = chickens.get(sel[i]);
					
					dist = Math.min(dist, Math.abs(chicken[0] - home[0]) + Math.abs(chicken[1] - home[1]));
					
				}
				
				total += dist;
			}
			
			ans = Math.min(total, ans);
			
			return;
		}
		if(idx == chickens.size()) return;
		
		sel[selIdx] = idx;
		delivery(sel, selIdx+1, idx+1);
		delivery(sel, selIdx, idx+1);
		
	}
}
