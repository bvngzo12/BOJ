import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[301];
		
		for(int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[] dp = new int[301];
		
		dp[1] = arr[1];
		dp[2] = dp[1] + arr[2];
		

		for(int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i-3]+arr[i-1]+arr[i], dp[i-2]+arr[i]);
		}
		
		System.out.println(dp[n]);
	}

}
