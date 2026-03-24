package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준G4_20056_마법사상어와파이어볼 {
	
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	
	static class Ball implements Comparable<Ball>{
		int r,c,m,s,d;
		boolean combined = false;
		int oddCount=0;
		int evenCount=0;
		public Ball(int r,int c,int m,int s,int d) {
			super();
			this.r=r;
			this.c=c;
			this.m=m;
			this.s=s;
			this.d=d;
		}
		@Override
		public int compareTo(Ball o) {	      
			// TODO Auto-generated method stub
			if(this.r!=o.r) {
				return Integer.compare(this.r, o.r);
			}else if(this.c!=o.c) {
				return Integer.compare(this.c, o.c);
			}else {
				return 0;
			}
		}
	}
	

	public static void main(String[] args) throws IOException {
		int N,M,K;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		List<Ball> list = new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r,c,m,s,d;
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			list.add(new Ball(r,c,m,s,d));
		}
		while(K>0) {
			//이동 연산
			for(Ball b : list) {
				int moveR= (dr[b.d]*b.s)%N;
				int moveC= (dc[b.d]*b.s)%N;
				
				int nr = (b.r + moveR + N)%N;
				int nc = (b.c + moveC + N)%N;
				b.r = nr;
				b.c = nc;
			}
			//합치기 연산
			list.sort(null);
			for(int i=0;i<list.size()-1;) {
				Ball curr = list.get(i);
				Ball next = list.get(i+1);
				
				if(curr.r==next.r && curr.c==next.c) {
					curr.m+=next.m;
					curr.s+=next.s;
					if(curr.evenCount==0 && curr.d%2==0) {
						curr.evenCount++;
					}else if(curr.oddCount==0 && curr.d%2!=0) {
						curr.oddCount++;
					}
					if(next.d%2==0) {
						curr.evenCount++;
					}else {
						curr.oddCount++;
					}
					
					list.remove(i+1);
					if(curr.combined==false) curr.combined=true;
				}else {
					i++;
				}
			}
			//합쳐진 볼은 4개로 나누기
			for(int i=list.size()-1;i>=0;i--) {
				Ball curr = list.get(i);
				if(curr.combined) {
					int new_m = curr.m/5;
					int new_s = curr.s/(curr.oddCount+curr.evenCount);
					if(new_m<=0) {
						list.remove(i);
						continue;
					}else {
						if(curr.oddCount==0 || curr.evenCount==0) {
							list.add(new Ball(curr.r,curr.c,new_m,new_s,0));
							list.add(new Ball(curr.r,curr.c,new_m,new_s,2));
							list.add(new Ball(curr.r,curr.c,new_m,new_s,4));
							list.add(new Ball(curr.r,curr.c,new_m,new_s,6));
						}else {
							list.add(new Ball(curr.r,curr.c,new_m,new_s,1));
							list.add(new Ball(curr.r,curr.c,new_m,new_s,3));
							list.add(new Ball(curr.r,curr.c,new_m,new_s,5));
							list.add(new Ball(curr.r,curr.c,new_m,new_s,7));
						}
						list.remove(i);
					}
					
				}
			}
			K--;
		}
		int result=0;
		for(Ball b : list) {
			result+=b.m;
		}
		System.out.println(result);
	}

}
