
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	//static int[] currA, currB;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int[] currA = new int[] { 1, 1 };
			int[] currB = new int[] { 10, 10 };

			int M = sc.nextInt(); // 이동 시간
			int A = sc.nextInt(); // 충전기 개수

			int[] a_mov = new int[M + 1];
			int[] b_mov = new int[M + 1];

			for (int i = 1; i <= M; i++) {
				a_mov[i] = sc.nextInt();
			}
			for (int i = 1; i <= M; i++) {
				b_mov[i] = sc.nextInt();
			}

			int[][] chargers = new int[A][4];

			for (int c = 0; c < A; c++) {
				chargers[c] = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() };
			}

			Arrays.sort(chargers, (c1, c2) -> Integer.compare(c1[3], c2[3])); // 파워의 오름차순으로 정렬

			int sumA = 0;
			int sumB = 0;

			for (int i = 0; i <= M; i++) {
				move(currA, a_mov[i]);
				move(currB, b_mov[i]);

				Stack<int[]> stA = new Stack();
				Stack<int[]> stB = new Stack();

				for (int c = 0; c < A; c++) {
					int[] charger = chargers[c];

					if (getDist(currA, new int[] { charger[0], charger[1] }) <= charger[2]) {
						stA.push(new int[] {c,charger[3]});	// 충전기 id, 파워
					}
					if (getDist(currB, new int[] { charger[0], charger[1] }) <= charger[2]) {
						stB.push(new int[] {c,charger[3]});
					}
				}
				
				int[] tempA = stA.isEmpty() ? null : stA.pop();
				int[] tempB = stB.isEmpty() ? null : stB.pop();
				
				int valA = tempA == null ? 0 : tempA[1];
				int valB = tempB == null ? 0 : tempB[1];
					
				if(tempA != null && tempB != null && tempA[0] == tempB[0]) {
					int[] tempA2 = stA.isEmpty() ? null : stA.pop();
					int[] tempB2 = stB.isEmpty() ? null : stB.pop();
					
					int valA2 = tempA2 == null ? 0 : tempA2[1];
					int valB2 = tempB2 == null ? 0 : tempB2[1];
					
					if(valA2 > 0 || valB2 > 0) {
						if(valA2 >= valB2) {
							valA = valA2;
						}
						else if(valA2 < valB2) {
							valB = valB2;
						}
					}else {
						valA /= 2;
						valB /= 2;
					}
				}
				
				sumA += valA;
				sumB += valB;

			}// end for
			System.out.printf("#%d %d\n",t,sumA+sumB);
		}
	}

	static int getDist(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}

	static void move(int[] loc, int cmd) {
		switch (cmd) {
		case 1: // 상
			loc[1] -= 1;
			break;
		case 2: // 우
			loc[0] += 1;
			break;
		case 3: // 하
			loc[1] += 1;
			break;
		case 4: // 좌
			loc[0] -= 1;
			break;
		default:
			break;
		}
	}

}
