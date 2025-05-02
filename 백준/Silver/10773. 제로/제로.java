import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int k = Integer.parseInt(br.readLine());
		LinkedList<Integer> stack = new LinkedList<>();
		
		while(--k >= 0) {
			int n = Integer.parseInt(br.readLine());
			
			if(n == 0) stack.pop();
			else stack.push(n);
			
		}
		int sum = 0;
		while(!stack.isEmpty())
			sum += stack.pop();
		System.out.println(sum);
	}

}
