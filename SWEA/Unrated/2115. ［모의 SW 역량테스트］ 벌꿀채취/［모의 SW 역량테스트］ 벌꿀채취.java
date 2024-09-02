import java.util.Scanner;

public class Solution {

	static int N,M,C;
	static int[][] map;
	static int ans;
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			ans = 0;
			
			N = sc.nextInt();
			M = sc.nextInt();
			C = sc.nextInt();
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			getStart(0,0, new int[2]);
			System.out.printf("#%d %d\n",tc,ans);
		}
	}

	private static void getStart(int idx, int sel_k, int[]sel) {
		if(idx > N*N) return;
		if(sel_k == sel.length) {
			setHoney(sel[0], sel[1]);
			return;
		}
		sel[sel_k] = idx;
		getStart(idx+1,sel_k+1,sel);
		getStart(idx+1,sel_k,sel);
	}

	private static void setHoney(int i, int j) {
		//일꾼 A의 좌표
		int ar = i/N;
		int ac = i%N;
		
		//일꾼 B의 좌표
		int br = j/N;
		int bc = j%N;
		
		if(ac + M-1 >= N || bc + M-1 >= N) return;
		if(ar == br) {
			if((ac <= bc && bc <= ac+M-1) || (bc <= ac && ac <= bc+M-1)) return;
		}

		// 합 구하기
		int[] aHoney = new int[M];
		int[] bHoney = new int[M];
		
		for(int h = 0; h < M; h++) {
			aHoney[h] = map[ar][ac+h];
			bHoney[h] = map[br][bc+h];
		}
		
		int aProfit = getHoney(aHoney, 0, new boolean[M]);
		int bProfit = getHoney(bHoney, 0, new boolean[M]);
		
		ans = Math.max(ans, aProfit + bProfit);
	}

	private static int getHoney(int[] origin, int idx,  boolean[] selected) {
		if(idx == origin.length) {
			int sum = 0;
			int pow = 0;
			for(int i = 0; i < origin.length; i++) {
				if(selected[i]) {
					sum += origin[i];
					pow += origin[i]*origin[i];
				}
			}
			if(sum>C) return 0;
			else return pow;
			
		}
		selected[idx] = true;
		int a = getHoney(origin, idx+1, selected);

		selected[idx] = false;
		int b = getHoney(origin, idx+1, selected);
		
		return Math.max(a,b);
	}

}
