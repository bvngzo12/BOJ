import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int R, C, blind;
	static int ans = Integer.MAX_VALUE;
	static int[][] M;
	static ArrayList<int[]> cctv;
	static int[][] Type =
		{
		 {0,0,0,0},
		 {1,0,0,0},
		 {1,0,1,0},
		 {1,1,0,0},
		 {1,1,1,0},
		 {1,1,1,1},
		};
	
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		M = new int[R][C];
		
		cctv = new ArrayList();
		
		
		// 자료 입력 + cctv 좌표 수집
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				M[i][j] = Integer.parseInt(st.nextToken());
				if(M[i][j]>=1 && M[i][j]<=5) {
					cctv.add(new int[] {i,j});
				}
				else if(M[i][j] == 0) blind++;
			}
		}
		
		cctvOn(0);
		System.out.println(ans);
	}
	
	static void cctvOn(int idx) {
		if(idx == cctv.size()) {
			ans = Math.min(ans, blind);
			return;
		}
		
		int[] temp = cctv.get(idx);
		int type = M[temp[0]][temp[1]];
		
		for(int d = 0; d < dr.length; d++) {	
			monitorOn(idx,d,type,-(idx+1));		// 마킹
			cctvOn(idx+1);
			monitorOn(idx,d,type,0);			// 언마킹
		}
	}
	
	static void monitorOn(int idx, int d, int type, int value) {
		int[] curr = cctv.get(idx);
		int r = curr[0];
		int c = curr[1];
		int id = -(idx+1);
		
		int[] cmd = Type[type];
		for(int i = 0; i < cmd.length; i++) {
			if(cmd[i]==1) {
				marking(r,c,(d+i)%4,value,id);
			}
		}
		return;
	}
	
	static void marking(int r, int c, int d, int val, int id) {
		int nr = r+dr[d];
		int nc = c+dc[d];
		if(nr < 0 || nr >= R|| nc < 0 || nc >= C || M[nr][nc] == 6)return;
		
		if(val == 0 && M[nr][nc] == id) {		// 언마킹
			M[nr][nc] = 0;
			blind++;
		}
		else if(val != 0 && M[nr][nc] == 0) {						// 마킹
			M[nr][nc] = val;
			blind--;
		}
		
		marking(nr,nc,d,val,id);
		
	}
	

}
