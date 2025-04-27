import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<int[]> points = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points.add(new int[] {x,y});
		}
		
		long sumX = 0;
		long sumY = 0;
		
		for(int i = 0; i < n; i++) {
			int[] p1 = points.get(i);
			int[] p2 = points.get((i+1)%n);
			
			sumX += (long)p1[0]*p2[1];
			sumY += (long)p1[1]*p2[0];

		}
		
		double ans = (double) Math.abs(sumX - sumY) * 0.5;
		
		System.out.printf("%.1f\n", ans);
		
	}


	
}
