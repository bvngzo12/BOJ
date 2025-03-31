import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Star{
		
		double x;
		double y;
		
		Star(double x, double y){
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Star[] stars = new Star[n];
		double[][] graph = new double[n][n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stars[i] = new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) graph[i][j] = 0;
				else graph[i][j] = dist(stars[i], stars[j]);
			}
		}
		
		double[] dist = new double[n];
		boolean[] selected = new boolean[n];
		
		Arrays.fill(dist, Double.MAX_VALUE);
		PriorityQueue<double[]> pq = new PriorityQueue<>((d1,d2)->Double.compare(d1[1], d2[1]));
		
		dist[0] = 0;
		pq.offer(new double[] {0,0});
		
		while(!pq.isEmpty()) {
			
			double[] curr = pq.poll();
			int currIdx = (int)curr[0];
			
			if(selected[currIdx]) continue;
			selected[currIdx] = true;
			
			for(int i = 0; i < n; i++) {
				if(!selected[i] && dist[i] > graph[currIdx][i]) {
					dist[i] = graph[currIdx][i];
					pq.offer(new double[] {i, dist[i]});
				}
			}
			
		}
		
		double ans = 0;
		for(int i = 0; i < n; i++)
			ans += dist[i];
		
		System.out.println(Math.floor(ans*100)/100.0);
		
	}
	
	private static double dist(Star s1, Star s2) {
		return Math.sqrt(Math.pow(s1.x-s2.x, 2) + Math.pow(s1.y-s2.y, 2));
	}
	

}
