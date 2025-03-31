import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		List<Integer> nums = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			int v = Integer.parseInt(br.readLine());
			int idx = Collections.binarySearch(nums, v);
			nums.add(idx < 0 ? -idx-1 : idx,v);
			int sel = nums.size() % 2 == 0 ? nums.size()/2-1 : nums.size()/2;
			sb.append(nums.get(sel)).append("\n");
		}
		System.out.println(sb.toString());
	}
}