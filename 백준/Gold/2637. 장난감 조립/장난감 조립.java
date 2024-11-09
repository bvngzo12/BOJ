import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static class Part{
		int no;
		int cnt;
		
		public Part(int no, int cnt) {
			this.no = no;
			this.cnt = cnt;
		}
	
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] inDegree = new int[N+1];
		
		ArrayList<Part>[] toy = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			toy[i] = new ArrayList<Part>();
		}
		
		int mid = Integer.MAX_VALUE;
		
		for(int i = 0; i < M; i++) {
			int midPart =  sc.nextInt();
			int basicPart = sc.nextInt();
			int cnt = sc.nextInt();
			
			toy[midPart].add(new Part(basicPart,cnt));
			mid = Math.min(mid, midPart);
			inDegree[basicPart]++;
		}
		//System.out.println(Arrays.toString(inDegree));
		int[] numOfParts = new int[N+1];
		numOfParts[N] = 1;
		
		Queue<Integer> Q = new ArrayDeque<Integer>();
		Q.offer(N);
		
		while(!Q.isEmpty()) {
			int target = Q.poll();
			
			for (Part part : toy[target]) {
				numOfParts[part.no] += part.cnt*numOfParts[target];
				
				if(--inDegree[part.no] == 0) {
					Q.offer(part.no);
				}
				
			}
		}
		
		
		for(int i = 1; i <= N; i++) {
			if(toy[i].size() == 0)
				System.out.println(i +" "+numOfParts[i]);
		}
	}

}
