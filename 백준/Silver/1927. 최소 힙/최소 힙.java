
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for(int i = 0; i < n; i++) {
			int v = Integer.parseInt(br.readLine());
			
			if(v == 0) {
				System.out.println(pq.isEmpty() ? 0 : pq.poll());
			}else {
				pq.offer(v);
			}
			
		}
		
	}
	
}
