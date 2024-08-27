
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] inDegree = new int[N+1];
		
		ArrayList<Integer>[] adjList = new ArrayList[N+1];
		
		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			inDegree[to]++;
		}
		
		Queue<Integer> Q = new ArrayDeque();
		
		// 진입 차수 0인 노드 넣기
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) {
				Q.offer(i);
			}
		}
		
		ArrayList<Integer> result = new ArrayList();
		StringBuilder sb= new StringBuilder();
		
		while(!Q.isEmpty()) {
			int current = Q.poll();
			sb.append(current+" ");
			for(int i = 0; i < adjList[current].size();i++) {
				int target = adjList[current].get(i);
				inDegree[target]--;
				if(inDegree[target] == 0) {
					Q.offer(target);
				}
			}
		}
		System.out.println(sb);
		//result.forEach(el->System.out.print(el+" "));
		
	}

}
