package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
//26,240 kb, 98 ms
public class SWEA_사과먹기_D3 {
	static int N;
	static int curDirection;
	static Map<Integer,int[]> map;
	static int cnt;
	static int[] direction = {0,1,2,3}; //동남서북
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;t++) {
			sb.append("#"+t).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new TreeMap<>();
			for(int r=0;r<N;r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					int number = Integer.parseInt(st.nextToken());
					if(number!=0) {
						map.put(number, new int[] {r,c});
					}
				}
			}
			cnt=0;
			curDirection = 0;
			int cr = 0;
			int cc=0;
			check(cr,cc);
			sb.append(cnt+"\n");
		}
		System.out.println(sb.toString());
	}
	static void check(int cr,int cc) {
		for(Integer num : map.keySet()) {
			int nr = map.get(num)[0]; //다음 행
			int nc = map.get(num)[1]; //다음 열
			if(nr>cr && nc>cc) { //오른쪽 대각 아래
				//0,1거치기
				if(curDirection==0) {
					cnt+=1;
					curDirection=1;
				}else if(curDirection==1) {
					cnt+=3;
					curDirection=0;
				}else if(curDirection==2) {
					cnt+=3;
					curDirection=1;
				}else if(curDirection==3) {
					cnt+=2;
					curDirection=1;
				}
			}else if(nr>cr && nc<cc) {//왼쪽 대각 아래
				//1,2거치기
				if(curDirection==0) {
					cnt+=2;
					curDirection=2;
				}else if(curDirection==1) {
					cnt+=1;
					curDirection=2;
				}else if(curDirection==2) {
					cnt+=3;
					curDirection=1;
				}else if(curDirection==3) {
					cnt+=3;
					curDirection=2;
				}
			}else if(nr<cr && nc>cc) {//오른쪽 대각 위
				//0,3 거치기
				if(curDirection==0) {
					cnt+=3;
					curDirection=3;
				}else if(curDirection==1) {
					cnt+=3;
					curDirection=0;
				}else if(curDirection==2) {
					cnt+=2;
					curDirection=0;
				}else if(curDirection==3) {
					cnt+=1;
					curDirection=0;
				}
			}else if(nr<cr && nc<cc) {//왼쪽 대각 위
				//2,3 거치기
				if(curDirection==0) {
					cnt+=3;
					curDirection=3;
				}else if(curDirection==1) {
					cnt+=2;
					curDirection=3;
				}else if(curDirection==2) {
					cnt+=1;
					curDirection=3;
				}else if(curDirection==3) {
					cnt+=3;
					curDirection=2;
				}
			}
			cr = nr;
			cc = nc;
		}
		
	}
}
