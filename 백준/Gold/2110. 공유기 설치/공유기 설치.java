import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int C = sc.nextInt();
		long[] home = new long[N];
		
		for(int i = 0; i < N; i++) {
			home[i] = sc.nextLong();
		}
		
		Arrays.sort(home);
		
		long end = home[home.length-1] - home[0];
		long start = 1;
		long len = (start + end) / 2;
		
		long ans = -1;
		
		while(start <= end) {
			int cnt = 1;
			long last = home[0];
			boolean flag = false;
			
			for(int i = 1; i < N; i++) {
				if(home[i] >= last + len) {
					cnt++;
					last = home[i];
				}
				
				if(cnt == C) {
					flag = true;
					break;
				}
			}
			
			if(flag) {
				ans = Math.max(ans, len);
				start = len+1;
			}
			else {
				end = len-1;
			}
			
			len = (start+end)/2;
			
		}
		System.out.println(ans);
		
	}

}
