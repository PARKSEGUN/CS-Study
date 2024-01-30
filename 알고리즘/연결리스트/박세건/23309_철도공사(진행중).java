주어진 배열의 추가, 삭제하면서 탐색
방법 1 : 리스트를 이용하는 것이 시간초과가 발생하는지 확인
결과 : 시간초과\
----------코드-----------
package com.ssafy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static List<Integer> list=new ArrayList<>();
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer=new StringTokenizer(reader.readLine());
		N=Integer.parseInt(tokenizer.nextToken());
		M=Integer.parseInt(tokenizer.nextToken());
		tokenizer=new StringTokenizer(reader.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(tokenizer.nextToken()));
		}
		StringBuilder builder=new StringBuilder();
		for (int i = 0; i < M; i++) {
			tokenizer=new StringTokenizer(reader.readLine());
			String type=tokenizer.nextToken();
			if(type.equals("BN")) {
				
				int x=Integer.parseInt(tokenizer.nextToken());
				int y=Integer.parseInt(tokenizer.nextToken());
				int idx=list.indexOf(x);
				if(++idx==list.size()) {
					idx=0;
				}
				builder.append(list.get(idx)).append("\n");
				list.add(idx,y);
				//idx는 다음칸의 인덱스 이 칸과 탐색했던 칸 사이에 y대입
			}else if(type.equals("BP")) {
				int x=Integer.parseInt(tokenizer.nextToken());
				int y=Integer.parseInt(tokenizer.nextToken());
				int idx=list.indexOf(x);
				if(idx-1<0) {
					idx=list.size();
				}
				builder.append(list.get(idx-1)).append("\n");
				if(idx==list.size()) {
					idx=0;
				}
				list.add(idx,y);
				//idx는 다음칸의 인덱스 이 칸과 탐색했던 칸 사이에 y대입
			}else if(type.equals("CN")) {
				int x=Integer.parseInt(tokenizer.nextToken());
				int idx=list.indexOf(x);
				if(++idx==list.size()) {
					idx=0;
				}
				builder.append(list.get(idx)).append("\n");
				list.remove(idx);
				//idx는 다음칸의 인덱스 이 칸과 탐색했던 칸 사이에 y대입
			}else if(type.equals("CP")) {
				
				int x=Integer.parseInt(tokenizer.nextToken());
				int idx=list.indexOf(x);
				if(--idx<0) {
					idx=list.size()-1;
				}
				builder.append(list.get(idx)).append("\n");
				list.remove(idx);
			}
			
		}
		 System.out.println(builder);
	}
}

방법 2 : 링크드 리스트 구현체 사용
결과 : 시간 초과


방법 3 : 배열을 이용해서 전 노드와 후 노드를 탐색
결과 : 정답


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int arr[], before[] = new int[1000001], after[] = new int[1000001];

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		tokenizer = new StringTokenizer(reader.readLine());
		StringBuilder builder = new StringBuilder();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = (Integer.parseInt(tokenizer.nextToken()));
		}
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				before[arr[i]] = arr[N - 1];
				after[arr[i]] = arr[i + 1];
			} else if (i == N - 1) {
				before[arr[i]] = arr[i - 1];
				after[arr[i]] = arr[0];
			} else {
				before[arr[i]] = arr[i - 1];
				after[arr[i]] = arr[i + 1];
			}
		}

		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			String type = tokenizer.nextToken();
			if (type.equals("BN")) {
				int x = Integer.parseInt(tokenizer.nextToken());
				int y = Integer.parseInt(tokenizer.nextToken());
				builder.append(after[x]).append("\n");
				int tmp = after[x];
				after[x] = y;
				after[y] = tmp;
				before[tmp] = y;
				before[y] = x;
			} else if (type.equals("BP")) {
				int x = Integer.parseInt(tokenizer.nextToken());
				int y = Integer.parseInt(tokenizer.nextToken());
				builder.append(before[x]).append("\n");
				int tmp = before[x];
				before[x] = y;
				before[y] = tmp;
				after[tmp] = y;
				after[y] = x;
			} else if (type.equals("CN")) {
				int x = Integer.parseInt(tokenizer.nextToken());
				builder.append(after[x]).append("\n");
				int tmp = after[after[x]];
				after[x] = tmp;
				before[tmp] = x;

			} else if (type.equals("CP")) {
				int x = Integer.parseInt(tokenizer.nextToken());
				builder.append(before[x]).append("\n");
				int tmp = before[before[x]];
				before[x] = tmp;
				after[tmp] = x;
			}
		}
		System.out.println(builder);
	}

}
