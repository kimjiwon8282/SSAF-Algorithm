package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1759_암호만들기 {
	static int L,C;
	static char[] arr;
	static char[] result;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		
		arr =new char[C];
		result = new char[L];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<C;i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		combination(0,0);
		System.out.println(sb.toString());
				
	}
	static void combination(int idx,int start) {
		if(idx==L) {
			if(check()) {
				for(char c : result) {
					sb.append(c);
				}
				sb.append("\n");
			}
			return;
		}
		for(int i=start;i<C;i++) {
			result[idx] = arr[i];
			combination(idx+1,i+1);
		}
	}
	static boolean check() {
		int conCount=0;
		int vowCount=0;
		for(char c : result) {
			if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u') {
				vowCount++;
			}else {
				conCount++;
			}
			if(vowCount>=1 && conCount>=2) {
				return true;
			}
		}
		return false;
	}

}
