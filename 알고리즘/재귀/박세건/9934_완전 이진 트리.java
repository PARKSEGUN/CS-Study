주어진 트리를 읽는 방법은 전체의 배열의 가운데 숫자가 루트가된다는 규칙이 있다.
이는 또 작은 서브트리로 나눠서 적용시킬 수 있기에 재귀를 사용해서 해결한다.


----------코드----------
package com.ssafy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, len;
	static int arr[];
	static List<Integer> answer[];

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(reader.readLine());
		len = (int) (Math.pow(2, N)) - 1;
		arr = new int[len];
		answer = new List[N];
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < len; i++) {

			arr[i] = Integer.parseInt(tokenizer.nextToken());
		}
		for (int i = 0; i < N; i++) {
			answer[i] = new ArrayList<>();
		}
		answer[0].add(arr[arr.length / 2]);
		findAnswer(1, arr);
		for (int i = 0; i < N; i++) {
			for (int x : answer[i]) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}

	private static int findAnswer(int cur, int[] tmpArr) {
//		System.out.println(Arrays.toString(tmpArr));
		if (tmpArr.length == 1) {
			return tmpArr[0];
		}
		answer[cur].add(findAnswer(cur + 1, Arrays.copyOfRange(tmpArr, 0, tmpArr.length / 2)));
		answer[cur].add(findAnswer(cur + 1, Arrays.copyOfRange(tmpArr, tmpArr.length / 2 + 1, tmpArr.length)));
		return tmpArr[tmpArr.length / 2];
	}

}
