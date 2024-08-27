
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());// 건물 수
			int K = Integer.parseInt(st.nextToken());// 규칙 수
			
			
			// 인접 리스트
			ArrayList<Integer>[] graph = new ArrayList[N+1];
			for(int i = 0; i <= N; i++) {
				graph[i] = new ArrayList();
			}
			
			// 건설 시간
			int[] constructTime = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				constructTime[i] = Integer.parseInt(st.nextToken());
			}
			
			//규칙
			int inDegree[] = new int[N+1];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				inDegree[to]++;
			}
			
			int target = Integer.parseInt(br.readLine());// 타겟 건물
			
			Queue<Integer> Q = new ArrayDeque();
			
			for(int i = 1; i <= N; i++) {
				if(inDegree[i] == 0) {
					if(i==target) {
						Q.clear();
						break;
					}
					Q.offer(i);
				}
			}
			
			int[] accum= new int[N+1];
			
			L:while(!Q.isEmpty()) {
				int curr = Q.poll();
				ArrayList<Integer> building = graph[curr];
				for(int i = 0; i < building.size(); i++) {
					int next = building.get(i);
					accum[next] = Math.max(accum[next], accum[curr] + constructTime[curr]);
					if(--inDegree[next] == 0) {
						if(next == target)break L;
						Q.offer(next);
					}
				}
			}// end while
			
			System.out.println(accum[target]+constructTime[target]);
		}

	}

}
