import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		Queue<Long> q = new ArrayDeque<>();
		boolean[] visited = new boolean[(int) (b+1)];
		
		q.offer(a);
		visited[(int) a] = true;
		
		int cnt = 0;
		boolean flag = false;
		L:while(!q.isEmpty()) {
			
			cnt++;
			
			int qSize = q.size();
			
			for(int qs = 0; qs < qSize; qs++) {
				long curr = q.poll();
				
				for(int i = 0; i < 2; i++) {
					long nxt = -1;
					if(i == 0) nxt = 2 * curr;	
					else nxt = 10 * curr + 1;
					
					if(nxt > b || visited[(int) nxt]) continue;
					
					if(nxt == b) {
						flag = true;
						break L;
					}
					
					q.offer(nxt);
					visited[(int) nxt] = true;
				}
			}

		}
		
		
		System.out.println(flag ? cnt + 1 : -1);
	}

}
