import java.util.*;

public class test {

    int[][] dp = new int[30][30];	// 최대입력값이 29이므로 
 
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
 
	int[][] dp = new int[30][30];
 
	// 2번 성질 (n == r, r == 0)
	for (int i = 0; i < 30; i++) {
		dp[i][i] = 1;
		dp[i][0] = 1;
	}
			
 
	for (int i = 2; i < 30; i++) {
		for (int j = 1; j < 30; j++) {
			// 1번 성질 
			dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
		}
	}
        		
	int T = sc.nextInt();	// 테스트케이스
 
	for(int i = 0; i < T; i++) {
 
		// M개중 N개를 뽑는 경우이므로 nCr 에서 n = M, r = N이다.
		int N = sc.nextInt();	// N = r
		int M = sc.nextInt();	// M = n
			
		System.out.print(dp[M][N]);
	}
    }
}
