import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<int[]> zeros = new ArrayList();
	static int[][] board;
	static boolean[][] rowFiled = new boolean[9][10];
	static boolean[][] colFiled = new boolean[9][10];
	static boolean[][] boxFiled = new boolean[9][10];
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new int[9][9];
		
		for(int i = 0; i < 9; i++) {
			String str = br.readLine();
			
			for(int j = 0; j < 9; j++) {
				int v = (str.charAt(j)) - '0';
				board[i][j] = v;
				if(v == 0) {
					zeros.add(new int[] {i, j});
				}
				else {
					int boxIndex = getBox(i,j);
					
					boxFiled[boxIndex][v] = true;
					rowFiled[i][v] = true;
					colFiled[j][v] = true;
				}
			}
		}	
		
		sudoku(0);
		
		StringBuilder sb = new StringBuilder();
		for(int r = 0 ; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				sb.append(board[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	private static boolean sudoku(int i) {
		
		if(i == zeros.size()) return true;
		
		int[] target = zeros.get(i);
		int tR = target[0];
		int tC = target[1];
		int tBox = getBox(tR, tC);
		
		for(int v = 1; v <= 9; v++) {
			if(rowFiled[tR][v] || colFiled[tC][v] || boxFiled[tBox][v]) continue;
			
			board[tR][tC] = v;
			
			rowFiled[tR][v] = true;
			colFiled[tC][v] = true;
			boxFiled[tBox][v] = true;
			
			if(sudoku(i+1)) {
				return true;
			}
			
			rowFiled[tR][v] = false;
			colFiled[tC][v] = false;
			boxFiled[tBox][v] = false;
			
		}
		
		return false;
		
	}
	
	private static int getBox(int r, int c) {
		return 3*(r/3) + c/3;
	}

}
