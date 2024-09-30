import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int S = sc.nextInt();
		
		int[] num = new int[N];
		for(int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		
		int s = 0;
		int e = 0;
		int ans = Integer.MAX_VALUE;
		
		int sum = num[0];
		
		while(true) {
			if(sum < S) {
				if(e == N-1)break;
				sum += num[++e];
			}else {
				ans = Math.min(ans, e-s);
				
				if(s == N-1)break;
				sum -= num[s++];
			}
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? 0 : ans+1);
		
	}

}
