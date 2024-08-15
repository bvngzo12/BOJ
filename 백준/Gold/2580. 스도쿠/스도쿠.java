

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[][] M = new int[9][9];
    static boolean[][] visited_row = new boolean[9][10];
    static boolean[][] visited_col = new boolean[9][10];
    static boolean[][] visited_box = new boolean[9][10];

    static ArrayList<int[]> blanks = new ArrayList();
   
    static boolean isValid(int r, int c, int v){
        return !(visited_row[r][v] || visited_col[c][v] || visited_box[(r/3)*3 +c/3][v]);
    }

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
            	int val = sc.nextInt();
                M[i][j] = val;
                visited_row[i][val] = true;
                visited_col[j][val] = true;
                visited_box[(i/3)*3 +j/3][val] = true;
                if(val == 0) {
                	blanks.add(new int[] {i,j});
                }
            }
        }
        
        sudoku(0);
        
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sb.append(M[i][j]+" ");
            }sb.append("\n");
        }

        System.out.println(sb);
	}

	private static boolean sudoku(int i) {
		if(i == blanks.size()) return true;
		
		int r = blanks.get(i)[0];
		int c = blanks.get(i)[1];
		
		for(int v = 1; v <= 9; v++) {
			if(isValid(r,c,v)) {
				M[r][c] = v;
                visited_row[r][v] = true;
                visited_col[c][v] = true;
                visited_box[(r/3)*3 +c/3][v] = true;
				if(sudoku(i+1)) return true;
				M[r][c] = 0;
                visited_row[r][v] = false;
                visited_col[c][v] = false;
                visited_box[(r/3)*3 +c/3][v] = false;
			}
		}
		return false;
	}

}
