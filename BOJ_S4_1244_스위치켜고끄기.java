package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S4_1244_스위치켜고끄기{
	static int N; //LED개수
	static int[] status;
	static int S; //학생 수
	static int[][] students;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		status = new int[N];
		for(int i=0;i<N;i++) {
			status[i] = Integer.parseInt(st.nextToken());
		}
		S = Integer.parseInt(br.readLine());
		students = new int[S][2];
		for(int s=0;s<S;s++) {
			st = new StringTokenizer(br.readLine());
			students[s][0] = Integer.parseInt(st.nextToken());//성별
			students[s][1] = Integer.parseInt(st.nextToken());//LED번호
			
		}
		for(int s=0;s<S;s++) {
			int gender = students[s][0];
			int ledNum = students[s][1];
			if(gender==1) {//남자일때
				for(int n=ledNum;n<=N;n+=ledNum) {
					swap(n-1);
				}
			}else if(gender==2) {//여자일때
				int left = ledNum-1;
				int right = ledNum+1;
				swap(ledNum-1);
				while(left>=1 && right<=N) {
					if(status[left-1]==status[right-1]) {
						swap(left-1);
						swap(right-1);
						left-=1;
						right+=1;
					}else {
						break;
					}
				}
			}
		}

		for(int i=0;i<N;i++) {
			sb.append(status[i]).append(" ");
			if((i+1)%20==0) sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	static void swap(int index) {
		if(status[index]==0) {
			status[index]=1;
		}else {
			status[index]=0;
		}
	}

}
