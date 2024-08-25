import java.util.Scanner;
import java.math.BigInteger;

public class Main {
	static int cnt;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		BigInteger count = new BigInteger("2");
		System.out.println(count.pow(N).subtract(new BigInteger("1")));
		if(N<=20) {
			hanoi(N,1,2,3);
			System.out.println(sb);
		}
	}
	
	static void hanoi(int n, int first, int second, int third) {
		if(n == 0) return;
		
		hanoi(n-1,first,third,second);
		sb.append(first+" "+third+"\n");
		hanoi(n-1,second, first ,third);
	}

}
