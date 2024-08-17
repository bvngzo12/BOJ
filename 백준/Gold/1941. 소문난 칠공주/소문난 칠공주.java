
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static ArrayList<Student> students;
	static int cnt;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Student {
		int r;
		int c;
		char v;

		public Student(int r, int c, char v) {
			super();
			this.r = r;
			this.c = c;
			this.v = v;
		}

		@Override
		public String toString() {
			return "Student [r=" + r + ", c=" + c + ", v=" + v + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		students = new ArrayList();

		for (int i = 0; i < 5; i++) {
			String str = sc.next();
			for (int j = 0; j < 5; j++) {
				students.add(new Student(i, j, str.charAt(j)));
			}
		}

		combination(0, 0, new Student[7]);
		
		System.out.println(cnt);
	}

	private static void combination(int idx, int sel_k, Student[] sel) {
		if (sel_k == sel.length) {
			
			bfs(sel,0,new boolean[7]);
			return;
		}

		if (idx == students.size()) {
			return;
		}

		sel[sel_k] = students.get(idx);
		combination(idx + 1, sel_k + 1, sel);
		combination(idx + 1, sel_k, sel);

	}

	private static void bfs(Student[] sel, int start, boolean[] visited) {
		Queue<Student> q = new ArrayDeque();
		int cntS = 0;
		int num = 1;
		
		q.offer(sel[start]);
		visited[start] = true;
		if(sel[start].v == 'S') cntS++;
		
		while(!q.isEmpty()) {
			Student student = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				int nr = student.r + dx[d];
				int nc = student.c + dy[d];
				
				if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
				
				for(int i = 0; i < sel.length; i++) {
					if(!visited[i]) {
						if(sel[i].r == nr && sel[i].c == nc) {
							q.offer(sel[i]);
							visited[i] = true;
							num++;
							if(sel[i].v == 'S') cntS++;
						}
					}
				}
			}
		}
		if(num != 7) return;
		else {
			if(cntS  >= 4) {
				cnt++;
			}
		}
		
	}


}
