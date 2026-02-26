package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//22,272 kb, 75 ms
public class SWEA_숫자만들기1_D2 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;t++) {
			sb.append("#"+t).append("\n");
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(j==i) continue;
					for(int k=0;k<N;k++) {
						if(k==i || k==j) continue;
						int first = arr[i];
						int second = arr[j];
						int third = arr[k];
						int number = first*100+second*10+third;
						sb.append(number+"\n");
					}
				}
			}
		}
		System.out.println(sb.toString());
	}

}
