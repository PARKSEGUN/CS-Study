1부터 N 까지의 모든 수들을 원형태로 위치시키고 시계방향으로 진행하면서 시작했던 수를 제외한 K-1개의 수를 제거하면서 마지막 하나가 남을때까지 진행하는 문제이다
방법 1 : 살아남은 값들만 큐에 다시 넣어주면서 진행

----------코드----------

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static long answer;
	static int arr[];

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		inputValues();
		System.out.println(solve());
	}

	private static int solve() {
		// 리스트로 설정해도 같은값이 나오는지 확인해보기
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			q.add(i + 1);
		}
		while (true) {
			for (int i = 0; i < K; i++) {
				if (i == 0) {
					q.add(q.peek());
					q.remove();
				} else {
					q.remove();
					if (q.size() == 1) {
						return q.peek();
					}
				}
			}
		}
	}

	private static void inputValues() throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
	}

}
