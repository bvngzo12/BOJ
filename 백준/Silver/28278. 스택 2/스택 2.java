import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		Stack<Integer> stk = new Stack();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case 1:
				stk.push(Integer.parseInt(st.nextToken()));
				break;
			case 2:
				sb.append(stk.isEmpty() ? -1 : stk.pop()).append("\n");
				break;
			case 3:
				sb.append(stk.size()).append("\n");
				break;
			case 4:
				sb.append(stk.isEmpty() ? 1 : 0).append("\n");
				break;
			case 5:
				sb.append(stk.isEmpty() ? -1 : stk.peek()).append("\n");
				break;
			}

		}
		
		System.out.println(sb.toString());

	}

}
