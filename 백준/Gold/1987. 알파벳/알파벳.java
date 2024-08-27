
import java.util.Scanner;

public class Main {

	static char[][] M;
	static int R;
	static int C;
	static boolean[] V;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int cnt;
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();

		M = new char[R][C];
		V = new boolean['Z' - 'A' + 1];

		cnt = 0;
		ans = Integer.MIN_VALUE;

		for (int i = 0; i < R; i++) {
			M[i] = sc.next().toCharArray();
		}

		V[M[0][0] - 'A'] = true;
		cnt++;
		ans = Math.max(ans, cnt);

		dfs(0, 0);

		System.out.println(ans);

	}

	static void dfs(int x, int y) {
		for (int d = 0; d < dx.length; d++) {
			int nw_x = x + dx[d];
			int nw_y = y + dy[d];

			if (nw_x < 0 || nw_x >= R || nw_y < 0 || nw_y >= C)
				continue;

			if (!V[M[nw_x][nw_y] - 'A']) {
				V[M[nw_x][nw_y] - 'A'] = true;
				cnt++;
				ans = Math.max(ans, cnt);
				dfs(nw_x, nw_y);
				cnt--;
				V[M[nw_x][nw_y] - 'A'] = false;
			}
		}
	}

}