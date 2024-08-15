
import java.util.Scanner;

public class Main {

	static int[][] M;
	static int[][] operations;
	static int K;
	static int R;
	static int C;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();

		M = new int[R][C];
		operations = new int[K][3];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				M[i][j] = sc.nextInt();
			}
		}

		for (int k = 0; k < K; k++) {
			operations[k] = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt() };
		}

		rotate(0, new int[K][], new boolean[K]);

		System.out.println(ans);


	}

	static void print(int[][] A) {
		for (int[] arr : A) {
			for (int a : arr) {
				System.out.print(a + " ");
			}
			System.out.println();
		}
	}

	private static void rotate(int sel_k, int[][] sel, boolean[] visited) {
		if (sel_k == sel.length) {

			int[][] copy_M = new int[R][C];
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					copy_M[i][j] = M[i][j];
				}
			}

			for (int i = 0; i < K; i++) {
				int r = sel[i][0];
				int c = sel[i][1];
				int s = sel[i][2];
				int[] start = new int[] { r - s, c - s };
				int[] end = new int[] { r + s, c + s };

				rotateM(start, end); // 배열 돌리기

			}
			

			for (int i = 0; i < R; i++) {
				int sum = 0;
				for (int j = 0; j < C; j++) {
					sum += M[i][j];
				}
				ans = Math.min(ans, sum);
			}
			
			M = copy_M;
			
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sel[sel_k] = operations[i];
				rotate(sel_k + 1, sel, visited);
				visited[i] = false;
			}
		}

	}

	private static void rotateM(int[] start, int[] end) {
		int curr_x = start[0] - 1;
		int curr_y = start[1] - 1;
		int nw_x;
		int nw_y;

		int len = (end[0] - start[0] + 1);
		int cnt = 1;
		char dir = 'R';
		int curr_v = M[curr_x][curr_y];
		int new_v = 0;

		while (len > 1) {
			switch (dir) {
			case 'R':
				nw_x = curr_x;
				nw_y = curr_y + 1;

				if (cnt >= len) {
					dir = 'D';
					cnt = 1;
					break;
				} else {
					new_v = M[nw_x][nw_y];
					M[nw_x][nw_y] = curr_v;
					cnt++;
					curr_v = new_v;
					curr_x = nw_x;
					curr_y = nw_y;
				}
				break;
			case 'D':
				nw_x = curr_x + 1;
				nw_y = curr_y;

				if (cnt >= len) {
					dir = 'L';
					cnt = 1;
					break;
				} else {
					new_v = M[nw_x][nw_y];
					M[nw_x][nw_y] = curr_v;
					cnt++;
					curr_v = new_v;
					curr_x = nw_x;
					curr_y = nw_y;
					break;
				}
			case 'L':
				nw_x = curr_x;
				nw_y = curr_y - 1;

				if (cnt >= len) {
					dir = 'U';
					cnt = 1;
					break;
				} else {
					new_v = M[nw_x][nw_y];
					M[nw_x][nw_y] = curr_v;
					cnt++;
					curr_v = new_v;
					curr_x = nw_x;
					curr_y = nw_y;
					break;
				}
			case 'U':
				nw_x = curr_x - 1;
				nw_y = curr_y;

				if (cnt >= len) {
					dir = 'R';
					cnt = 1;
					len -= 2;
					curr_x = curr_x + 1;
					curr_y = curr_y + 1;
					curr_v = M[curr_x][curr_y];
					break;
				} else {
					new_v = M[nw_x][nw_y];
					M[nw_x][nw_y] = curr_v;
					cnt++;
					curr_v = new_v;
					curr_x = nw_x;
					curr_y = nw_y;
					break;
				}

			}// end switch
		} // end while
	}// end f
	

	
}
