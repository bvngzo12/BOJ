import java.util.Scanner;

public class Solution {

	static String[] wheels = new String[4];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			
			int N = sc.nextInt();
			
			for (int i = 0; i < 4; i++) {
				String w = "";
				for(int j = 0; j < 8; j++) {
					w += sc.nextInt();		
				}
				wheels[i] = w;
			}

			for (int i = 0; i < N; i++) {
				int idx = sc.nextInt()-1;
				int cmd = sc.nextInt();

				rotate(idx, cmd);
				chainEffect(idx, idx + 1, cmd);
				chainEffect(idx, idx - 1, cmd);
			}

			int score = 0;

			for (int n = 0; n < 4; n++) {
				if (wheels[n].charAt(0) == '1') {
					score += Math.pow(2, n);
				}
			}

			System.out.printf("#%d %d\n",t,score);
		}

	}

	static void rotate(int idx, int opt) {
		String str = wheels[idx];
		if (opt == 1) {
			wheels[idx] = str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
		} else if (opt == -1) {
			wheels[idx] = str.substring(1, str.length()) + str.charAt(0);
		}
	}

	static void chainEffect(int self, int idx, int cmd) {
		if (idx < 0 || idx >= 4) {
			return;
		}
		// 동시에 회전 하기대문에 회전 self는 이전 인덱스 값으로 비교 
		if (self < idx && wheels[self].charAt(2+cmd) != wheels[idx].charAt(6)) {	
			rotate(idx, cmd * (-1));
			chainEffect(idx, idx + 1, cmd * (-1));

		} else if (self > idx && wheels[self].charAt(6+cmd) != wheels[idx].charAt(2)) {
			rotate(idx, cmd * (-1));
			chainEffect(idx, idx - 1, cmd * (-1));
		}
	}
}