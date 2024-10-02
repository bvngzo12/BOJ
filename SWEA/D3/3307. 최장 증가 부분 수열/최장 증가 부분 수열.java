import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int n = sc.nextInt();
			
			int[] num = new int[n];
			int[] dp = new int[n];
			
			for(int i = 0; i < n; i++) {
				num[i] = sc.nextInt();
				dp[i]=1;
			}
				
			int max = 1;
			for(int i = 1; i < n; i++) {
				for(int j = 0; j < i; j++) {
					if(num[j] < num[i]) {
						dp[i] = Math.max(dp[i], dp[j]+1);
						max = Math.max(max, dp[i]);
					}
				}
			}
			System.out.printf("#%d %d\n",t,max);
		}
	}
}