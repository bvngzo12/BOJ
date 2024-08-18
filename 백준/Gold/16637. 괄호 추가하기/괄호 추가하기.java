
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	static int N;
	static String str;
	static int ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		str = sc.next();	
		
		powerset(0,new boolean[N/2]);
		
		System.out.println(ans);

		
	}

	private static void powerset(int idx, boolean[] visited) {
		if(idx == N/2) {
			
			ArrayList<Integer> ops = new ArrayList();
			
			for(int i = 0; i < N/2; i++) {
				if(visited[i]) {
					if(i>0 && visited[i-1]) return;
					
					ops.add(2*i+1);
				}
			}
			parethesis(ops);
			
			return;
		}
		
		visited[idx] = true;
		powerset(idx+1,visited);
		
		visited[idx] = false;
		powerset(idx+1,visited);
		
	}

	private static void parethesis(ArrayList<Integer> ops) {
		String temp = "";
		
		for(int i = 0; i < str.length();) {
			if(ops.contains(i+1)) {
				int target = ops.indexOf(i+1);
				temp += '('+str.substring(ops.get(target)-1, ops.get(target)+2)+')';
				i+=3;
			}else {
				temp += str.charAt(i);
				i+=1;
			}
		}

		
		ans = Math.max(evaluate(temp), ans);
		
	}

	private static int calculate(String sub) {
		
		switch(sub.charAt(1)) {
		case '+':
			return (sub.charAt(0)-'0') + (sub.charAt(2)-'0');
		case '-':
			return (sub.charAt(0)-'0') - (sub.charAt(2)-'0');
		case '*':
			return (sub.charAt(0)-'0') * (sub.charAt(2)-'0');
		}
		return -1;
		
	}
	
	private static int evaluate(String ss) {
		Stack<Integer> vals = new Stack();
		char op = '.';
		for(int i = 0; i < ss.length();) {
			char c = ss.charAt(i);
			
			if(Character.isDigit(c)) {
				vals.add(c-'0');
				i++;
			}else if(c == '('){
				String temp = ss.substring(i+1,i+4);
				vals.add(calculate(temp));
				i+=5;
			}else {
				op = c;
				i++;
			}
			
			if(vals.size() == 2) {
				int v2 = vals.pop();
				int v1 = vals.pop();
				
				switch(op) {
				case '+':
					vals.push(v1+v2);
					break;
				case '-':
					vals.push(v1-v2);
					break;
				case '*':
					vals.push(v1*v2);
					break;
				}
			}
				
		}// end for
		return vals.pop();
	}// end f

}



