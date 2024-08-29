
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static double tax;
	static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 정점의 개수

			ArrayList<Integer>[] node = new ArrayList[2];
			
			for(int i = 0; i < 2; i++) {
				node[i] = new ArrayList<Integer>();
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					node[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			tax = Double.parseDouble(br.readLine());

			ArrayList<Edge> edges = new ArrayList<Edge>();
			
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
					double dist = getLen(node[0].get(i), node[1].get(i), node[0].get(j), node[1].get(j));
					edges.add(new Edge(i, j, dist));
					edges.add(new Edge(j, i, dist));
				}
			}

			edges.sort(null);
			
			
			double sum = 0;
			
			make();
			int cnt = 0;
			for(Edge e : edges) {
				if(findSet(e.s) == findSet(e.e)) continue;
				
				union(e.s, e.e);
				cnt++;
				sum += e.dist;
				
				if(cnt == N-1) break;
				
			}
			
			long ans = (long) Math.round((sum*10)/10);
			
			System.out.printf("#%d %d\n", tc, ans);
		}
		


	}

	static void make() {
		parents = new int[N];
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		else return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		else {
			parents[bRoot] = aRoot;
			return true;
		}
	}

	static double getLen(int r1, int c1, int r2, int c2) {
		return Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(c1 - c2, 2));
	}
	
	static class Edge implements Comparable<Edge>{
		int s;
		int e;
		double dist;
		
		public Edge(int s, int e, double dist) {
			super();
			this.s = s;
			this.e = e;
			this.dist = dist*dist*tax;
		}

		@Override
		public int compareTo(Edge o) {
			
			return Double.compare(this.dist, o.dist);
		}

	}
	
}
