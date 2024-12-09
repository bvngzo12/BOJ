import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static ArrayList<int[]> virus = new ArrayList<int[]>();
	static ArrayList<int[]> blank = new ArrayList<int[]>();
	static int N, M, ans;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MIN_VALUE;

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int v = Integer.parseInt(st.nextToken());
				map[i][j] = v;
				if (v == 0)
					blank.add(new int[] { i, j });
				else if (v == 2)
					virus.add(new int[] { i, j });
			}
		}

		combination(0, 0, new int[3]);
		System.out.println(ans);
	}

	private static void combination(int idx, int selIdx, int[] sel) {
		if (selIdx == sel.length) {

			int[][] copyMap = new int[N][M];
			for (int i = 0; i < map.length; i++) {
				copyMap[i] = map[i].clone();
			}

			for (int i = 0; i < sel.length; i++) {
				int[] target = blank.get(sel[i]);
				copyMap[target[0]][target[1]] = 1;
			}
			
			int safe = blank.size()-3;

			Queue<int[]> Q = new ArrayDeque<int[]>();
			Q.addAll(virus);
			while (!Q.isEmpty()) {
				int[] curr = Q.poll();
				// System.out.println(Arrays.toString(curr));
				for (int d = 0; d < dr.length; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];

					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;
					if(copyMap[nr][nc] == 0) {
						copyMap[nr][nc] = 2;
						Q.offer(new int[] { nr, nc });
						safe--;
					}
				}
			}
			ans = Math.max(safe, ans);
			return;
		}
		if (idx == blank.size())
			return;

		sel[selIdx] = idx;
		combination(idx + 1, selIdx + 1, sel);
		combination(idx + 1, selIdx, sel);
	}

}
