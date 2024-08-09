import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int r = sc.nextInt();
        int c = sc.nextInt();
        int n = sc.nextInt();

        n = (n%4 == 1) ? ((n == 1) ? 1 : 5) : (n % 4 == 0) ? 4 :n % 4;

        String[][] M = new String[r][c];

        int[][] timer = new int[r][c];

        for(int i = 0; i < r; i++){
            M[i] = sc.next().split("");
        }

        for(int i=0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(M[i][j].equals("O")) timer[i][j] = 2;
            }
        }

        for(int t = 2; t < n+1; t++){
            for(int x = 0; x < r; x++){
                for(int y = 0; y < c; y++){
                    String mark = M[x][y];
                    switch (mark){
                        case ".":
                            if(timer[x][y] == 0){
                                M[x][y] = "O";
                            }
                            break;
                        case "O":
                            if(timer[x][y] == 3){
                                M[x][y] = ".";
                                timer[x][y] = -1;

                                if(x-1 >= 0 && M[x-1][y]=="O" && timer[x-1][y] != 3){
                                    M[x-1][y] = ".";
                                    timer[x-1][y] = -1;
                                }
                                if(y-1 >= 0 && M[x][y-1]=="O" && timer[x][y-1] != 3){
                                    M[x][y-1] = ".";
                                    timer[x][y-1] = -1;
                                }
                                if(x+1 < r && M[x+1][y]=="O" && timer[x+1][y] != 3){
                                    M[x+1][y] = ".";
                                    timer[x+1][y] = -1;
                                }
                                if(y+1 < c && M[x][y+1]=="O" && timer[x][y+1] != 3){
                                    M[x][y+1] = ".";
                                    timer[x][y+1] = -1;
                                }

                            }
                    }
                }
            }

            for(int i = 0; i < r; i++){
                for(int  j = 0; j < c; j++){
                    timer[i][j]++;
                }
            }
        }

        for(int i = 0; i < r; i++){
            for(int  j = 0; j < c; j++){
                sb.append(M[i][j]);
            }sb.append('\n');
        }

        System.out.println(sb);



    }
}
