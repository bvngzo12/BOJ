import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class Shark{
		int r;
		int c;
		int speed;
		int dir;
		int size;
		boolean alive;
		
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.speed = s;
			this.dir = d;
			this.size = z;
			this.alive = true;
		}
		
	}
	
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,1,-1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Shark> sharks = new ArrayList<Shark>();
		Shark[][] pool = new Shark[R+1][C+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			Shark shark = new Shark(r,c,s,d,z);
			
			sharks.add(shark);
			pool[r][c] = shark;
		}
		
		int loc = 1;
		int total = 0;
		
		while(loc <= C && sharks.size() > 0) {
			// 상어 잡기
			for(int i = 1; i<= R; i++) {
				Shark target = pool[i][loc];
				if(target != null) {
					total += target.size;
					sharks.remove(target);
					break;
				}
			}
			
			// 상어 move
			for(Shark shark : sharks) {
				move(shark, R,C);
			}
			
			pool = new Shark[R+1][C+1];
			ArrayList<Shark> dead = new ArrayList<Shark>();
			for(Shark shark : sharks) {
				if(!shark.alive) continue;
				if(pool[shark.r][shark.c] == null)
					pool[shark.r][shark.c] = shark;
				else {
					Shark opp = pool[shark.r][shark.c];
					if(shark.size > opp.size) {
						pool[shark.r][shark.c] = shark;
						opp.alive = false;
						dead.add(opp);
					}else {
						shark.alive = false;
						dead.add(shark);
					}
				}
			}
			sharks.removeAll(dead);
			
			// 낚시왕 이동
			loc++;
		}// end while
		
		System.out.println(total);
		
	}

	private static void move(Shark shark, int R, int C) {
		int gap = -1;
		switch(shark.dir) {
		case 1:
			gap = shark.r - 1;
			if(gap >= shark.speed)
				shark.r -= shark.speed;
			else {
				shark.r -= gap;
				int leftDist = shark.speed - gap;
				int lap = leftDist/(R-1);
				int mod = leftDist % (R-1);
				
				// lap이 홀수 = 아래쪽, lap이 짝수 = 위쪽
				
				if(lap % 2 == 0) {
					if(mod != 0) {
						shark.dir = 2;
						shark.r = 1+mod;
					}
				}else {
					shark.r = R-mod;
				}
			}
			return;
		case 2:
			gap = R - shark.r;
			if(gap >= shark.speed)
				shark.r += shark.speed;
			else {
				shark.r += gap;
				int leftDist = shark.speed - gap;
				int lap = leftDist/(R-1);
				int mod = leftDist % (R-1);
				
				// lap이 홀수 = 위쪽, lap이 짝수 = 아래쪽
				
				if(lap % 2 == 0) {
					if(mod != 0) {
						shark.dir = 1;
						shark.r = R-mod;
					}
				}else {
					shark.r = 1+mod;
				}
			}
			return;
		
		case 3:
			gap = C - shark.c;
			if(gap >= shark.speed)
				shark.c += shark.speed;
			else {
				shark.c += gap;
				int leftDist = shark.speed - gap;
				int lap = leftDist/(C-1);
				int mod = leftDist % (C-1);
				
				// lap이 홀수 = 왼쪽, lap이 짝수 = 오른쪽
				
				if(lap % 2 == 0) {
					if(mod != 0) {
						shark.dir = 4;
						shark.c = C-mod;
					}
				}else {
					shark.c = 1+mod;
				}
			}
			return;
		case 4:
			gap = shark.c-1;
			if(gap >= shark.speed)
				shark.c -= shark.speed;
			else {
				shark.c -= gap;
				int leftDist = shark.speed - gap;
				int lap = leftDist/(C-1);
				int mod = leftDist % (C-1);
				
				// lap이 홀수 = 오른쪽, lap이 짝수 = 왼쪽
				
				if(lap % 2 == 0) {
					if(mod != 0) {
						shark.dir = 3;
						shark.c = 1+mod;
					}
				}else {
					shark.c = C-mod;
				}
			}
			return;
		}
		
	}

}
