import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.PolicyQualifierInfo;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Taxi{
		int r;
		int c;
		int gas;
		
		public Taxi(int r, int c, int gas) {
			super();
			this.r = r;
			this.c = c;
			this.gas = gas;
		}

	}
	
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	
	/////////////////////////////////////// 도착지 지도 만들기////////////////////////////////////////
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());		// 지도 크기
		int M = Integer.parseInt(st.nextToken());		// 손님 수
		int gas = Integer.parseInt(st.nextToken());		// 연료
		int wall = 401;									// 벽
		
		int[][] map = new int[N][N];					// 지도 구성
		int[][] destMap = new int[N][N];
		int[][] destination = new int[M+1][2];			// 손님별 탑승지 좌표
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken())*wall;
				map[i][j] = val;
				destMap[i][j] = val;
			}
		}
		st = new StringTokenizer(br.readLine());
		
		Taxi taxi = new Taxi(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, gas);	// 택시

		for(int i = 1; i <= M; i++) {					// 손님 죄표 구성 -> >0 : 출발 <0 : 도착
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken())-1;
			int sc = Integer.parseInt(st.nextToken())-1;
			map[sr][sc] = i;
			
			int er = Integer.parseInt(st.nextToken())-1;
			int ec = Integer.parseInt(st.nextToken())-1;
			destination[i] = new int[] {er,ec};
			//map2Dest[er][ec] = -i;
		}
		//print(map);
		while(true) {
			if(taxi.gas == 0) break;		// 연료 미달
			
			int dist = 0;
			Queue<int[]> path2guest = new ArrayDeque();		// 경로
			boolean[][] toGuest = new boolean[N][N];
			// 손님 큐
			PriorityQueue<int[]> guests = new PriorityQueue<int[]>((o1,o2)->(o1[0]==o2[0]) ? o1[1]-o2[1] : o1[0]-o2[0] );
			
			if(map[taxi.r][taxi.c] > 0) {
				guests.offer(new int[] {taxi.r, taxi.c});
			}
			else {
				path2guest.offer(new int[] {taxi.r, taxi.c});
			}
			
			toGuest[taxi.r][taxi.c] = true;
			
			while(!path2guest.isEmpty()) {
				dist++;
				int numOfPath = path2guest.size();
				
				for(int i = 0; i < numOfPath; i++) {
					int[] curr = path2guest.poll();
					
					for(int d = 0; d < dr.length; d++) {
						int nr = curr[0] + dr[d];
						int nc = curr[1] + dc[d];
						
						if(nr<0||nr>=N||nc<0||nc>=N||toGuest[nr][nc]||map[nr][nc] == wall) continue;
						
						if(map[nr][nc]>0 && map[nr][nc]<wall) {
							guests.offer(new int[] {nr, nc});
							toGuest[nr][nc] = true;
						}
						else {
							path2guest.offer(new int[] {nr, nc});
							toGuest[nr][nc] = true;
						}
					}// end 4방
				}// end path 계산
				
				if(guests.size() > 0) break;
			}// end inner While
			
			if(taxi.gas < dist || (guests.size() == 0)) break;	// 손님 탐승 불가
			
			int[] guest = guests.poll();	// 손님
			int gr = guest[0];
			int gc = guest[1];
			int guestId = map[gr][gc];
			
			map[gr][gc] = 0;	// 손님 탑승
			taxi.r = gr;		// 택시 위치 초기화
			taxi.c = gc;
			taxi.gas -= dist;
			
			int[] guestDest = destination[guestId]; // 손님 목적지 세팅
			destMap[guestDest[0]][guestDest[1]] = -guestId;
			
			dist = 1;
			Queue<int[]> path2dest = new ArrayDeque();
			boolean[][] toDest = new boolean[N][N];
			
			path2dest.offer(new int[] {taxi.r, taxi.c});
			toDest[taxi.r][taxi.c] = true;
			
			L:while(!path2dest.isEmpty()) {
				int numOfPath = path2dest.size();
				
				for(int i = 0; i < numOfPath; i++) {
					int[] curr = path2dest.poll();
					
					for(int d = 0; d < dr.length; d++) {
						int nr = curr[0] + dr[d];
						int nc = curr[1] + dc[d];
						
						if(nr<0||nr>=N||nc<0||nc>=N||toDest[nr][nc]||destMap[nr][nc] == wall) continue;
						
						if(destMap[nr][nc] == -guestId) { // 도착
							if(taxi.gas >= dist) {
								destMap[nr][nc] = 0;	// 손님 하차
								M--;
								taxi.r = nr;		// 택시 위치 초기화
								taxi.c = nc;
								taxi.gas -= dist;
								taxi.gas += 2*dist;
							}
							break L;
						}
						else {
							path2dest.offer(new int[] {nr, nc});
							toDest[nr][nc] = true;
						}
					}// end 4방
				}// end path 계산
				dist++;
			}// end while

			if(M == 0) break;	// 손님 전부 처리
		}// end outter while
		int ans = (M==0) ? taxi.gas : -1;
		System.out.println(ans);
		
	}
	
	static void print(int[][] M) {
		for(int[] aa : M) {
			for(int a : aa) {
				System.out.printf("%3d ",a);
			}System.out.println();
		}
	}

}
