import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		printSeq(nums, new int[l], 0, new boolean[n]);
		
	}

	private static void printSeq(int[] arr, int[] sel, int selIdx, boolean[] selected) {
		if(selIdx == sel.length) {
			for(int i = 0; i < sel.length; i++) {
				System.out.print(sel[i]+" ");
			}
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(selected[i]) continue;
			
			sel[selIdx] = arr[i];
			selected[i] = true;
			printSeq(arr, sel, selIdx+1, selected);
			selected[i] = false;
		}
		
	}
	
	

}
