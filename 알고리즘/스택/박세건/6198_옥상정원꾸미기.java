주어진 입력에서 현재 탐색하고있는 값의 오른쪽 수들중 작은 연속된 수들의 개수들의 합을 구하는 문제이다.
스택 구조를 사용해서 들어오는 값보다 작은 수를 빼주는 방식으로 구현
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static long answer;
	static int arr[];

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		inputValues();
		solve();
		System.out.println(answer);
	}

	private static void solve() {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			while (list.size() > 0 && list.get(list.size() - 1) <= arr[i]) {
				answer += list.size() - 1;
				list.remove(list.size() - 1);
			}
			list.add(arr[i]);
		}
		while (list.size() > 0) {
			answer += list.size() - 1;
			list.remove(list.size() - 1);
		}
	}

	private static void inputValues() throws IOException {
		N = Integer.parseInt(reader.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(reader.readLine());
		}

	}

}
