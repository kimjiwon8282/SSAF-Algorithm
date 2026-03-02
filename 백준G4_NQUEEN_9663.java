package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class น้มุG4_NQUEEN_9663 {
	static int N;
	static boolean[] row;
	static boolean[] col;
	static boolean[] slash;
	static boolean[] bSlash;
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		count=0;
		row = new boolean[N];
		col = new boolean[N];
		slash = new boolean[2*N-1];
		bSlash = new boolean[2*N-1];
		
		nq(0);
		
		System.out.println(count);
	}
	
	static void nq(int r) {
		if(r == N) {
			count++;
			return;
		}
		for(int c = 0;c<=N-1;c++) {
			if(row[r] || col[c] || slash[r+c] || bSlash[r-c+N-1]) continue;
			row[r] = true;
			col[c] = true;
			slash[r+c] = true;
			bSlash[r-c+N-1] = true;
			nq(r+1);
			row[r] = false;
			col[c] = false;
			slash[r+c] = false;
			bSlash[r-c+N-1] = false;
		}
	}

}
