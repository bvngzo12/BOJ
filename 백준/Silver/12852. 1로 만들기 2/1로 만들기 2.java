import java.util.Scanner;

public class Main {

	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		dp = new int[1000000+1];
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		
		for(int i = 4; i <= N; i++) {
			if(i % 6 == 0) {
				dp[i] = Math.min(Math.min(dp[i/2], dp[i/3]), dp[i-1])+1;
			}else if(i % 2 == 0) {
				dp[i] = Math.min(dp[i/2], dp[i-1])+1;
			}else if(i % 3 == 0) {
				dp[i] = Math.min(dp[i/3], dp[i-1])+1;
			}else {
				dp[i] = dp[i-1]+1;
			}
		}
		
		System.out.println(dp[N]);
		dp(N);
	}
	
	static void dp(int N) {
		if(N == 1)
            System.out.print(1);
        else if(N<=3) {
			System.out.print(N+" "+1);
		}else {
			System.out.print(N+" ");
			if(N % 6 == 0) {
				if(dp[N/3] > dp[N/2]) {
					if(dp[N/2]>dp[N-1]) dp(N-1);
					else dp(N/2);
				}else {
					if(dp[N/3]>dp[N-1]) dp(N-1);
					else dp(N/3);
				}
			}
			else if(N % 3 == 0) {
				if(dp[N/3] > dp[N-1]) dp(N-1);
				else dp(N/3);
			}
			else if(N % 2 == 0) {
				if(dp[N/2] > dp[N-1]) dp(N-1);
				else dp(N/2);
			}
			else dp(N-1);
		}
	}

}
