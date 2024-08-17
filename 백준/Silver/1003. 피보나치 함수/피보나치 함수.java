
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int t = 0; t < T; t++){
            int n = sc.nextInt();
            int[][] arr = new int[n+2][2];

            arr[0] = new int[]{1,0};
            arr[1] = new int[]{0,1};

            for(int i = 2; i <n+1; i++){
                arr[i][0] = arr[i-1][0] + arr[i-2][0];
                arr[i][1] = arr[i-1][1] + arr[i-2][1];
            }

            System.out.println(arr[n][0]+" "+arr[n][1]);

        }

    }
}
