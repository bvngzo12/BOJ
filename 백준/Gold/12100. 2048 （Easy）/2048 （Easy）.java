import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static char[] cmd = {'U', 'R', 'D', 'L'};
	static int[][] M;
	static int N;
	static int ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				M[i][j] = sc.nextInt();
			}
		}
		
		permutation(0, new char[5]);
		System.out.println(ans);
	}

	private static void permutation(int sel_k, char[] sel) {
		if(sel_k == sel.length) {
			int[][] copy = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					copy[i][j] = M[i][j];
				}
			}
			
			Queue<Integer> Q = new ArrayDeque();
			
			for(int i = 0; i < sel.length; i++) {
				char cmd = sel[i];
				
				switch (cmd) {
				case 'U':
					for (int c = 0; c < N; c++) {
						for (int r = 0; r < N; r++) {
							if (M[r][c] != 0) {
								Q.offer(M[r][c]);
							}
								
						}
						int seq = 0;
						
						while (!Q.isEmpty()) {
							
							int a = Q.poll();
							int b = 0;

							if (!Q.isEmpty()) {
								b = Q.peek();
							}

							if (a == b) {
								M[seq][c] = a + Q.poll();
							} else {
								M[seq][c] = a;
							}
							
							ans = Math.max(ans, M[seq][c]);
							seq++;
						}
						for(;seq<N;seq++) {
							M[seq][c] = 0;
						}
					}
					break;
				case 'R':
					for (int r = 0; r < N; r++) {
						for (int c = N - 1; c >= 0; c--) {
							if (M[r][c] != 0)
								Q.offer(M[r][c]);
						}
						int seq = N-1;

						while (!Q.isEmpty()) {
							int a = Q.poll();
							int b = 0;

							if (!Q.isEmpty()) {
								b = Q.peek();
							}

							if (a == b) {
								M[r][seq] = a + Q.poll();
							} else {
								M[r][seq] = a;
							}
							ans = Math.max(ans, M[r][seq]);
							seq--;
						}
						for(;seq>=0;seq--) {
							M[r][seq] = 0;
						}
					}

					break;
				case 'D':
					for (int c = 0; c < N; c++) {
						for (int r = N - 1; r >= 0; r--) {
							if (M[r][c] != 0)
								Q.offer(M[r][c]);
						}
						int seq = N-1;

						while (!Q.isEmpty()) {
							int a = Q.poll();
							int b = 0;

							if (!Q.isEmpty()) {
								b = Q.peek();
							}

							if (a == b) {
								M[seq][c] = a + Q.poll();
							} else {
								M[seq][c] = a;
							}
							ans = Math.max(ans, M[seq][c]);
							seq--;
						}
						for(;seq>=0;seq--) {
							M[seq][c] = 0;
						}
					}
					break;
				case 'L':
					for (int r = 0; r < N; r++) {
						for (int c = 0; c < N; c++) {
							if (M[r][c] != 0)
								Q.offer(M[r][c]);
						}
						int seq = 0;

						while (!Q.isEmpty()) {
							int a = Q.poll();
							int b = 0;

							if (!Q.isEmpty()) {
								b = Q.peek();
							}

							if (a == b) {
								M[r][seq] = a + Q.poll();
							} else {
								M[r][seq] = a;
							}
							ans = Math.max(ans, M[r][seq]);
							seq++;
						}
						for(;seq<N;seq++) {
							M[r][seq] = 0;
						}
					}
					break;
				}// end switch
			}
			M = copy;
			return;
		}
		
		for(int i = 0; i < cmd.length; i++) {
			sel[sel_k] = cmd[i];
			permutation(sel_k+1,sel);
		}
		
	}

}
