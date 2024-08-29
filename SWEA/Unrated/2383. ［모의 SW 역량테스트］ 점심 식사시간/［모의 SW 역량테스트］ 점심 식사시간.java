import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] M;
	static ArrayList<int[]> stairs;
	static ArrayList<Person> ppl;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = new int[N][N];
			ans = Integer.MAX_VALUE;
			
			ppl = new ArrayList<>();			// 사람 좌표
			stairs = new ArrayList<>();			// 계단 좌표
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					M[i][j] = Integer.parseInt(st.nextToken());
					if(M[i][j] == 1) {
						ppl.add(new Person(i,j));
					}
					else if(M[i][j] > 1) {
						stairs.add(new int[] {i,j});
					}
				}
			}
			
			powerSet(0, new boolean[ppl.size()]);
			System.out.printf("#%d %d\n",tc,ans);
		}
		

	}
	
	static void powerSet(int sel_k, boolean[] visited) {
		if(sel_k == ppl.size()) {
			
			ArrayList<Person> stairA = new ArrayList<Person>();
			ArrayList<Person> stairB = new ArrayList<Person>();
			
			for(int i = 0; i <ppl.size(); i++) {
				if(visited[i]) stairA.add(ppl.get(i));
				else stairB.add(ppl.get(i));
			}

			int timeA = calcTime(stairA, stairs.get(0));
			int timeB = calcTime(stairB, stairs.get(1));
			ans = Math.min(ans, Math.max(timeA, timeB));
//			System.out.println("======================================");
//			System.out.println("결과: "+ ans);
//			System.out.println(stairA.toString());
//			System.out.println(stairB.toString());
			return;
		}
		
		visited[sel_k] = true;
		powerSet(sel_k+1, visited);
		
		visited[sel_k] = false;
		powerSet(sel_k+1, visited);
		
	}
	
	static int calcTime(ArrayList<Person> stair, int[] stair_loc) {
		if(stair.size() == 0) return 0;
		
		int time = 0;
		int A_len = M[stair_loc[0]][stair_loc[1]];
		
		for(int i = 0; i < stair.size(); i++) {
			Person p = stair.get(i);
			p.dist = Math.abs(p.r - stair_loc[0]) + Math.abs(p.c - stair_loc[1]);
			p.timeOnStair = 0;
		}
		
		stair.sort((o1,o2)-> Integer.compare(o1.dist, o2.dist));
	
		int timer = stair.get(0).dist;				// 타이머
		
		Queue<Person> onEntrance = new ArrayDeque();	// 계단 입구 사람 수
		Queue<Person> onStair = new ArrayDeque();	// 계단 입구 사람 수
		//onEntrance.add(stair.get(0));
		
		int idx = 0;							// 인덱스
		
		while(true) {
			
			// 큐에 계단 입구 인원 담기
			for(;idx < stair.size();idx++) {	
				Person p = stair.get(idx);
				if(p.dist == timer) {
					onEntrance.offer(p);
				}else if(p.dist > timer) {
					break;
				}
			}

			timer++;	// 타이머 증가
			
			while(!onStair.isEmpty()) {
				if((onStair.peek().timeOnStair + A_len) == timer) {
					onStair.poll();
				}
				else break;
			}
			
			// 계단 행
			while(!onEntrance.isEmpty()) {
				if(onStair.size()<3) {
					Person p =onEntrance.poll();
					p.timeOnStair = timer;
					onStair.offer(p);
					time = timer;
				}
				else break;
			}
			
			
			
			if(onStair.isEmpty() && onEntrance.isEmpty() && idx == stair.size())break;
			
		}
		
		
		return timer;
	}

	static class Person{
		int r;
		int c;
		int dist;
		int timeOnStair;
		
		public Person(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.dist = 0;
			this.timeOnStair = 0;
		}
	}
	
}
