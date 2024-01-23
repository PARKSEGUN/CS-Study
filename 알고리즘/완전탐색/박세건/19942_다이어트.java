주어진 최소의 영양성분을 지키면서 최소의 금액을 사용하는 문제이다
DFS를 사용해서 모든 경우를 확인한다
answer를 최솟값으로 유지시키면서 진행한다.
진행중에 최솟값으로 유지된 answer보다 작은 경우가 발생한다면 리턴시킨다(최적화)
최솟값을 찾았던 식재로의 조합을 오름차순으로 출력해야한다.
-> DFS 시작 범위를 조절
같은 경우가 있다면 사전순으로 출력
-> 같을때는 결과값을 초기화할 수 없게 설정
----------------------------------------------------------------------
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, nutrient[] = new int[4], foodInfo[][], result = Integer.MAX_VALUE;
	static List<Integer> tmpList = new ArrayList<>();
	static List<Integer> resultList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		inputValues(args);
		int[] startArr = { 0, 0, 0, 0 };
		findResult(0, startArr, 0);
		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(result);
		for (int i : resultList) {
			System.out.print(i + " ");
		}
	}

	public static boolean isSatisfy(int arr[]) {
		for (int i = 0; i < 4; i++) {
			if (!(arr[i] >= nutrient[i])) {
				return false;
			}
		}
		return true;
	}

	public static void findResult(int cur, int curArr[], int money) {
		if (isSatisfy(curArr)) {
			result = Math.min(result, money);
			resultList = List.copyOf(tmpList);
		}
		for (int i = cur; i < N; i++) {

			if (money + foodInfo[i][4] < result) {
				int[] tmpArr = { curArr[0] + foodInfo[i][0], curArr[1] + foodInfo[i][1], curArr[2] + foodInfo[i][2],
						curArr[3] + foodInfo[i][3] };
				tmpList.add(i + 1);
				findResult(i + 1, tmpArr, money + foodInfo[i][4]);
				tmpList.remove(tmpList.size() - 1);
			}
		}
	}

	private static void inputValues(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
		StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer1.nextToken());
		for (int i = 0; i < 4; i++) {
			nutrient[i] = Integer.parseInt(tokenizer2.nextToken());
		}
		foodInfo = new int[N][5];
		for (int i = 0; i < N; i++) {
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < 5; j++) {
				foodInfo[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
	}
}

