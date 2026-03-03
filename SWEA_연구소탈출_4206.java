package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_연구소탈출_4206 {
    static int N, M;
    static int[][] graph;
    
    // 4방향 탐색 (우, 좌, 하, 상)
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder();
    
    // 큐에 담을 상태 객체
    static class Node {
        int r, c, time, status; // status: 2(바이러스), 3(사람)
        public Node(int r, int c, int time, int status) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.status = status;
        }
    }
    
    // ⭐️ static으로 선언된 글로벌 큐
    static Queue<Node> q = new ArrayDeque<>();
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            
            q.clear(); 
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new int[N][M];
            Node human = null;
            
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    graph[r][c] = Integer.parseInt(st.nextToken());
                    
                    // 💡 핵심 1. 바이러스(2)는 발견 즉시 큐에 먼저 넣습니다.
                    if (graph[r][c] == 2) {
                        q.offer(new Node(r, c, 0, 2));
                    } 
                    // 사람은 위치만 따로 기억해둡니다.
                    else if (graph[r][c] == 3) {
                        human = new Node(r, c, 0, 3);
                    }
                }
            }
            
            // 💡 핵심 2. 사람(3)은 바이러스가 다 들어간 후 마지막에 큐에 넣습니다.
            // 이렇게 하면 동시간대(time)일 때 바이러스의 이동 경로가 사람보다 무조건 먼저 처리됩니다.
            q.offer(human);
            bfs();
        }
        System.out.println(sb.toString());
    }
    
    static void bfs() {
        // 좀비가 될 가능성 (사망 플래그)
        boolean canZombie = false;
        
        while (!q.isEmpty()) {
            Node curr = q.poll();
            int cr = curr.r;
            int cc = curr.c;
            int t = curr.time;
            int status = curr.status; // 현재 탐색 주체 (2: 바이러스, 3: 사람)
            
            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                
                // 1. 맵 밖으로 나가는 경우 (탈출 시나리오)
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    if (status == 3) { // 💡 핵심 3. 탐색 주체가 사람이라면 탈출 성공!
                        sb.append(t + 1).append("\n");
                        return; // Early Return: 가장 먼저 탈출한 시간이 최소 시간이므로 즉시 함수 종료
                    }
                    continue; // 바이러스가 맵 밖으로 나가려는 경우는 무시
                }
                
                // 2. 이동하려는 다음 칸이 이미 바이러스(2)가 덮친 곳인 경우
                if (graph[nr][nc] == 2) {
                    // 🚨 치명적 버그 수정 2: 로직 반전 (사람일 때 플래그를 세워야 합니다)
                    if (status == 3) { 
                        // 💡 탐색 주체가 '사람'인데 바이러스와 만났다면?
                        // 당장 이 칸으로 이동하진 않겠지만, 막힌 공간에서 바이러스와 맞닿았으니 
                        // 탈출구를 못 찾으면 결국 감염될 운명임을 메모합니다.
                        canZombie = true; 
                    }
                    continue; // 바이러스든 사람이든 바이러스 칸으로는 더 전진하지 않음
                }
                
                // 3. 빈 칸(0)이 아니면 갈 수 없음 (벽(1)이거나 누군가 이미 지나간 자리)
                // 💡 핵심 4. map 자체를 visited 배열로 활용하여 무한루프를 차단합니다.
                if (graph[nr][nc] != 0) continue;
                
                // 4. 빈 칸(0)인 경우 다음 탐색을 위해 큐에 삽입
                q.offer(new Node(nr, nc, t + 1, status));
                
                // 💡 핵심 5. 지나간 자리에 자신의 흔적(2 또는 3)을 남겨서 재방문(무한루프)을 막습니다.
                graph[nr][nc] = status;
            }
        }
        
        // 큐가 다 빌 때까지 return(조기 탈출)을 못 했다면 갇혀버린 것!
        // 갇힌 상태에서 사망 플래그가 true면 시간이 지나 ZOMBIE가 되고, 아니면 평생 CANNOT ESCAPE
        if (canZombie) {
            sb.append("ZOMBIE\n");
        } else {
            sb.append("CANNOT ESCAPE\n");
        }
    }
}