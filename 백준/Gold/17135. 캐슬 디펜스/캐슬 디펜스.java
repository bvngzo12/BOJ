import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private static class Enemy{
		int r;
		int c;
		boolean death;
		
		public Enemy(int r, int c, boolean death) {
			this.r = r;
			this.c = c;
			this.death = death;
		}
		
	}
	
	
	static int N,M,D;
	static int ans = Integer.MIN_VALUE;
	static ArrayList<Enemy> enemies;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		
		enemies = new ArrayList();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(sc.nextInt() == 1)
					enemies.add(new Enemy(i,j,false));
			}
		}
		
		comb(0, 0, new int[3]);
		System.out.println(ans);
	}


	private static void comb(int idx, int sel_k, int[] sel) {
		if(sel_k == sel.length) {
			
			ArrayList<int[]> archers = new ArrayList<int[]>();
			for(int i = 0; i < sel.length; i++) {
				archers.add(new int[] {N,sel[i]});
			}
			ArrayList<Enemy> death = new ArrayList<>();
			
			while(archers.get(0)[0] > 0) {
				ArrayList<Enemy> temp = new ArrayList<>();
				for(int i = 0; i < archers.size(); i++) {
					int[] archer = archers.get(i);
					Enemy target = null;
					int minDist = Integer.MAX_VALUE;
					int minIdx = Integer.MAX_VALUE;
					
					for(int j = 0; j < enemies.size(); j++) {
						Enemy enemy = enemies.get(j);

						int dist = getDist(archer, new int[] {enemy.r, enemy.c});
						if(enemy.r >= archer[0] || dist > D || enemy.death) 
							continue;
						
						// 가장 가까운!!!!!
						if(dist < minDist) {
							minDist = dist;
							minIdx = enemy.c;
							target = enemy;
						}else if(dist == minDist && minIdx > enemy.c) {
							minIdx = enemy.c;
							target = enemy;
						}
					}
					if(target != null) {
						temp.add(target);
					}
				}
				// 적 섬멸
				for(Enemy e : temp) {
					if(!e.death) {
						e.death = true;
						death.add(e);
					}
				}
				
				//궁수 위치 이동
				for(int[] ac : archers) {
					ac[0]--;
				}
			}// end while
			
			ans = Math.max(ans, death.size());
			for(Enemy e : enemies) {
				e.death=false;
			}
			
			return;
		}
		if(idx == M) return;

		
		sel[sel_k] = idx;
		comb(idx+1, sel_k+1, sel);
		comb(idx+1, sel_k, sel);

	}
	
	private static int getDist(int[] a, int[] b) {
		return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
	}
	
}
