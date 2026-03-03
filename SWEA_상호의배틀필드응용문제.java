package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_상호의배틀필드응용문제 {
	static int H;
	static int W;
	static char[][] graph;
	static int N;
	static char[] orders;
	static char[] directions = {'^','v', '<', '>'};
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int currR;
	static int currC;
	static int currentDirection;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;t++) {
			boolean first = false;
			sb.append("#"+t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			graph = new char[H][W];
			for(int h = 0; h<H;h++) {
				graph[h] = br.readLine().toCharArray();
				if(!first) {
					for(int w=0;w<W;w++) {
						if(graph[h][w] =='^') {
							currentDirection = 0;
							currR = h;
							currC = w;
							first = true;
						}
						if(graph[h][w] =='v') {
							currentDirection = 1;
							currR = h;
							currC = w;
							first = true;
						}
						if(graph[h][w] =='<') {
							currentDirection = 2;
							currR = h;
							currC = w;
							first = true;
						}
						if(graph[h][w] =='>') {
							currentDirection = 3;
							currR = h;
							currC = w;
							first = true;
						}
					}
				}
			}
			N = Integer.parseInt(br.readLine());
			orders = new char[N];
			orders = br.readLine().toCharArray();
			for(char c : orders) {
				if(c=='S') { //숫
					shoot();
				}else { //move
					move(c);
				}
			}
			for(int h=0;h<H;h++) {
				for(int w=0;w<W;w++) {
					sb.append(graph[h][w]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	static void shoot() {
	    int cr = currR;
	    int cc = currC;
	    
	    while(true) {
	        int nr = cr + dr[currentDirection];
	        int nc = cc + dc[currentDirection];
	        
	        // nr >= W 가 아니라 nc >= W 로 수정!
	        if(nr < 0 || nr >= H || nc < 0 || nc >= W) break; 
	        
	        if(graph[nr][nc] == '*') { //허수아비라면
	            graph[nr][nc] = '.';
	            break;
	        }
	        if(graph[nr][nc] == '#') { //돌벽이라면
	            break;
	        }
	        cr = nr;
	        cc = nc;
	    }
	}
	static void move(char command) {
	    // 1. 입력받은 명령어(U, D, L, R)에 따라 방향 인덱스 설정
	    if (command == 'U') currentDirection = 0;
	    else if (command == 'D') currentDirection = 1;
	    else if (command == 'L') currentDirection = 2;
	    else if (command == 'R') currentDirection = 3;
	    
	    // 2. 일단 제자리에서 탱크 고개(방향)부터 돌리기
	    graph[currR][currC] = directions[currentDirection];
	    
	    // 3. 바라보는 방향으로 한 칸 앞 좌표 계산
	    int nr = currR + dr[currentDirection];
	    int nc = currC + dc[currentDirection];
	    
	    // 4. 범위 밖이면 종료
	    if (nr < 0 || nr >= H || nc < 0 || nc >= W) return;
	    
	    // 5. 앞이 평지('.')라면 이동!
	    if (graph[nr][nc] == '.') {
	        graph[currR][currC] = '.'; // 원래 자리는 평지로
	        graph[nr][nc] = directions[currentDirection]; // 새 자리에 탱크 안착
	        
	        // ⭐️ 가장 중요한 '현재 좌표' 갱신!
	        currR = nr;
	        currC = nc;
	    }
	}
}
