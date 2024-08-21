
import java.util.Scanner;

public class Main {

	static String[] wheels;
	static int T;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		wheels = new String[T];
		
		for (int i = 0; i < T; i++) {
			wheels[i] = sc.next();
		}

		int N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			int idx = sc.nextInt() - 1;
			int cmd = sc.nextInt();

			rotate(idx, cmd);
			chainEffect(idx, idx + 1, cmd);
			chainEffect(idx, idx - 1, cmd);
		}

		int score = 0;

		for (int n = 0; n < T; n++) {
			if (wheels[n].charAt(0) == '1') {
				score++;
			}
		}

		System.out.println(score);

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
		if (idx < 0 || idx >= T) {
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
