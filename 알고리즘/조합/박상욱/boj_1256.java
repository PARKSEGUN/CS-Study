import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int n, m, k;
	public static long[][] dp;
	public static int INF = 1000000000;

	public static void init() throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		//n,m,k �Է�
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		n = n + m;
		dp = new long[n + 1][m + 1];
		dp[0][0] = 1;

		//���� DP�� ���
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= m; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				if (dp[i][j] >= INF) {
					dp[i][j] = INF;
				}
			}
		}

		//k�� ������ �������� ũ�ٸ�
		if (dp[n][m] < k) {
			System.out.println(-1);
		} else {
			//�� ���ڸ����� ��� 
			//dp[i][m] -> ���ڸ��� a�� �� ���¿��� ������ ������ ����
			//dp[i][m] >= k -> k���� ũ�ٸ� 
			//[a ~~~]  <= k��° ���ڿ� < [z ~~~] �� �Ǳ� ������ �ݵ�� ���ڸ��� a�� �;ߵ�
			//�۴ٸ� z �߰� �� ���ڸ��� a�� ������ ������ŭ k ����
			//z�� �߰������Ƿ� m�� 1 ����
			for (int i = n - 1; i >= 0; i--) {
				if (dp[i][m] >= k) {
					sb.append("a");
				} else {
					sb.append("z");
					k -= dp[i][m];
					m -= 1;
				}
			}

			System.out.println(sb);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		init();

	}
}