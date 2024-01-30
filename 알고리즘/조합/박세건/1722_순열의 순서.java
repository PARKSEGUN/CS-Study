문제는 2가지로 나눠진다.
  첫번째는 숫자 K가 주어지고 K 번째 나올 순열을 찾는다.
  두번째는 순열이 주어지고 몇번째인지 맞추는 것이다.

  팩토리얼을 이용해서 해결하려한다.
  ex)
  N이 5일때 K 가 10이라면
  앞자리가 1일때 총 나올 수 있는 경우의 수는 24개이다
  그렇다면 K가 10으로 24이하이기때문에 첫번째 자리는 1로 결정할 수 있다.
  이 규칙을 사용해서 1번과 2번을 해결한다.
  
  ----------코드----------

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, type, resultCnt;
	static int values[], tmp[], result[], target[];
	static long[] factorial = new long[21];
	static boolean isVisited[];
	static boolean flag;
	static long K, maxCount;

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		inputValues();
		if (type == 1) {
			findAnswer1();
			for (int i = 0; i < N; i++) {
				System.out.print(result[i] + " ");
			}
		} else {
			System.out.println(findAnswer2());
		}
	}

	private static void pushBackResult() {
		int v = N;
		for (int i = 0; i < N; i++) {
			if (result[i] == 0) {
				for (int j = N; j >= 1; j--) {
					if (isVisited[j] == false) {
						result[i] = j;
						isVisited[j] = true;
						break;
					}
				}
			}
		}

	}

	private static void findAnswer1() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (K == 0) {
				pushBackResult();
				return;
			}
			long dividedValue = K / factorial[N - i - 1];
			K %= factorial[N - i - 1];
			if (K != 0) {
				dividedValue++;
			}
//			System.out.println(K);
//			System.out.println(Arrays.toString(isVisited));
//			System.out.println(i);
			for (int j = 1; j <= N; j++) {
				if (isVisited[j] == false) {
					dividedValue--;
					if (dividedValue <= 0) {
						result[i] = j;
						isVisited[j] = true;
						break;
					}
				}
			}
		}
	}

	private static int toCurCount(int cur) {
		int cnt = 0;
		for (int i = 1; i <= cur; i++) {
			if (isVisited[i] == false) {
				cnt++;
			}
		}
		return cnt - 1;
	}

	private static long findAnswer2() {
		long cnt = 0;
		for (int i = 0; i < N; i++) {
			long divideNum = factorial[N - i - 1];
			// toCurCount 대신에 크기를 이용해도되는지 생각해보기
			cnt += divideNum * toCurCount(target[i]);
			isVisited[target[i]] = true;
		}
		return cnt + 1;
	}

	private static void inputValues() throws IOException {
		N = Integer.parseInt(reader.readLine());
		values = new int[N];
		for (int i = 0; i < N; i++) {
			values[i] = i + 1;
		}
		tmp = new int[N];
		target = new int[N];
		result = new int[N];
		isVisited = new boolean[N + 1];
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		type = Integer.parseInt(tokenizer.nextToken());
		if (type == 1) {
			K = Long.parseLong(tokenizer.nextToken());
		} else {
			for (int i = 0; i < N; i++) {
				target[i] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		factorial[0] = 1;
		for (int i = 1; i <= 20; i++) {
			factorial[i] = i * factorial[i - 1];
		}
	}

}
