
import java.util.*;

public class Main {

    static class Coord{
        int x;
        int y;

        Coord(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    static boolean checked(int x, int y, int r, int c){
        return 0 <= x && x < r && 0 <=  y && y < c;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int cnt = 0;

        sc.nextLine();
        int map[][] = new int[M][N];
        boolean visited[][] = new boolean[M][N];

        Queue<Coord> q = new ArrayDeque();

        for(int i =0; i < M; i++){
            for(int j =0; j < N; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1){
                    q.add(new Coord(i,j));
                    visited[i][j] = true;
                    cnt++;
                }
                if(map[i][j] == -1) cnt++;
            }
        }

        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        int day= 0;

        while(!q.isEmpty()){
            Coord p = q.poll();

            for(int i =0; i < dx.length; i++){
                int nw_x = p.x + dx[i];
                int nw_y = p.y + dy[i];

                if(checked(nw_x, nw_y, M, N) && map[nw_x][nw_y] != -1){
                    if(!visited[nw_x][nw_y]){
                        q.add(new Coord(nw_x, nw_y));
                        visited[nw_x][nw_y] = true;
                        map[nw_x][nw_y] = map[p.x][p.y]+1;
                        day = map[nw_x][nw_y];
                        cnt++;
                    }else{
                        map[nw_x][nw_y] = Math.min(map[nw_x][nw_y], map[p.x][p.y]+1);
                    }

                }
            }

        }

        if (cnt != N*M) day = -1;
        System.out.println(day <= 0 ? day : day-1);

    }
}
