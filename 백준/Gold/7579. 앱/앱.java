import java.io.*;
import java.util.*;

public class Main {
	static int N, M, sumCost, minCost;
	static Apps[] appList;
	static int[][] arr;
	
	static class Apps implements Comparable<Apps> {
		int weight, cost;
		
		Apps(int weight, int cost) {
			this.weight = weight;
			this.cost = cost;
		}

		@Override
		public int compareTo(Apps o) {
			if (this.cost == o.cost) {
				return this.weight - o.weight;
			}
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "Apps [weight=" + weight + ", cost=" + cost + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		appList = new Apps[N+1];
		minCost = 0;
		sumCost = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int m = Integer.parseInt(st.nextToken());
			appList[i+1] = new Apps(m, 0);
		}
		 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int c = Integer.parseInt(st.nextToken());
			appList[i+1].cost = c;
			sumCost += c;
		}
		
		arr = new int[N+1][sumCost+1];
		
		appList[0] = new Apps(0, 0);
//		Arrays.sort(appList);

		run();
		
		bw.write(minCost + "\n");
		
		br.close();
		bw.close();
	}

	private static void run() {
		//mem
		for (int i = 1; i <= N; i++) {
			int curCost = appList[i].cost;
			for (int j = 0; j <= sumCost; j++) {
				
				
				if (curCost <= j) arr[i][j] = Math.max(arr[i-1][j], arr[i-1][j-curCost] + appList[i].weight);
				else {
					if (j == 0) {
						arr[i][j] = arr[i-1][j];
					}
					
					else arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
				}
			}
		}
		
//		for (int i = 1; i <= N; i++ ) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
//		System.out.println();
		
		//최소 비용
		L: for (int c = 0; c <= sumCost; c++) {
			for (int app = 1; app <= N; app++) {
				if (arr[app][c] >= M) {
					minCost = c;
					break L;
				}
			}
		}
	}

}
