import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] nums = new int[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		
		Arrays.sort(nums);
		
		int s = 0;
		int e = 0;
		long ans = Integer.MAX_VALUE;
		
		while(true) {
			if(nums[e]-nums[s] < M) {
				if(e == N-1) break;
				e += 1;
			}else {
				ans = Math.min(ans, nums[e]-nums[s]);
				if(s == N-1) break;
				s +=1 ;
			}
		}
		
		System.out.println(ans);
	}

}
