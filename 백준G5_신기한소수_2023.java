package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준G5_신기한소수_2023 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	static int[] firstJari = {2,3,5,7};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dfs(0,0);
		System.out.println(sb.toString());
	}
	
	static void dfs(int dept,int num) {
		if(dept==N) {
			sb.append(num+"\n");
			return;
		}
		
		if(dept==0) {
			for(int i : firstJari) {
				int temp = num*10+i;
				dfs(dept+1,temp);
			}
		}else {
			for(int i=1;i<=9;i+=2) {
				int temp = num*10+i;
				if(isPrime(temp)) {
					dfs(dept+1,temp);
				}
			}
		}
	}
	static boolean isPrime(int num) {
		for(int i=2;i*i<=num;i++) {
			if(num%i==0) return false;
		}
		return true;
	}

}
