import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			pq.offer(Long.parseLong(st.nextToken()));
		}
		
		for(int i = 0; i < m; i++) {
			
			long n1 = pq.poll();
			long n2 = pq.poll();
			
			pq.offer(n1+n2);
			pq.offer(n1+n2);
		}
		
		long sum = 0;
		while(!pq.isEmpty()) {
			sum += pq.poll();
		}
	
		System.out.println(sum);
		
	}

}
