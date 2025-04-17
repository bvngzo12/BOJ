import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] nums = new long[n];
		
		for(int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(nums);
		long[] ans = new long[3];
		long val = Long.MAX_VALUE;
		
		for(int s = 0; s < n-2; s++) {
			
			int mid = s+1;
			int e = n-1;
			
			while(mid<e) {
				long temp = Math.abs(nums[s] + nums[mid] + nums[e]);
				if(temp < val) {
					val =  temp;
					
					ans[0] = nums[s];
					ans[1] = nums[mid];
					ans[2] = nums[e];
				}
				
				if(Math.abs(nums[s] + nums[mid+1]  + nums[e]) < Math.abs(nums[s] + nums[mid]  + nums[e-1])) mid++;
				else e--;
				
			}
		}
		
		System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
		
	}


}
