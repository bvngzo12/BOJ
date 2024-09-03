import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int[][] map;
	static int N, M, cnt;
	static int ans = -1;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			ans = -1;

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			cnt = 0;

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						cnt++;
				}
			}

			int k = 1;
			while (cnt * M >= k * k + (k - 1) * (k - 1)) {
				//System.out.println(k);
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						bfs(i, j, k);
					}
				}
				k++;
			}

			System.out.printf("#%d %d\n", tc, ans);
		}

	}

	private static void bfs(int i, int j, int k) {
		Queue<int[]> Q = new ArrayDeque();
		boolean[][] visited = new boolean[N][N];

		Q.offer(new int[] { i, j });
		visited[i][j] = true;

		int ppl = map[i][j];

		for (int q = 1; q < k; q++) {
			
			int qSize = Q.size();
			
			for(int p = 0; p < qSize; p++) {
				int[] curr = Q.poll();

				for (int d = 0; d < dr.length; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
						continue;

					ppl+=map[nr][nc];
					Q.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}

		}

		if (ppl * M >= k * k + (k - 1) * (k - 1)) {
			ans = Math.max(ans, ppl);
		}

	}

}
