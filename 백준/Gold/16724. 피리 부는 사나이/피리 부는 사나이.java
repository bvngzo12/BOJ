import java.util.Scanner;

public class Main {
	
	static int R,C;
	static char[][] M;
	static int[][] area;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = new char[R][C];
		area = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			M[i] = sc.next().toCharArray();
		}
		
		int ans = 0;
		int v = 1;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(dfs(i,j,v))
					++v;
			}
		}
		
		
		System.out.println(v-1);
	}

	/*
	 1. 끝에 닿아서 종료
	 2. 순환
	 3. 남의 영역
	*/
	
	private static boolean dfs(int r, int c, int v) {
		if(area[r][c] != 0) {
			return area[r][c]==v ? true : false; 
		}
		
		area[r][c] = v;
		int[] next = next(new int[] {r,c});
		int nr = next[0];
		int nc = next[1];
		
		if(dfs(nr,nc,v)) {
			return true;
		}else {
			area[r][c] = area[nr][nc];
			return false;
		}
		
	}
	
	private static int[] next(int[] curr) {
		int nr = curr[0];
		int nc = curr[1];
		
		switch(M[nr][nc]) {
		case 'U' :
			nr -= 1;
			break;
		case 'D':
			nr += 1;
			break;
		case 'R':
			nc+=1;
			break;
		case 'L':
			nc-=1;
			break;
		}
		
		return new int[] {nr, nc};
	}
	
	public static void print(int[][] arr) {
		for (int[] is : arr) {
			for (int i : is) {
				System.out.print(i+"  ");
			}System.out.println();
		}
	}

}
