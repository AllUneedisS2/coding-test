import java.util.*;

public class GridSectioning {

    public static void main(String[] args) {
        GridSectioning gs = new GridSectioning();
        int n = 9;
        int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        int result = gs.solution(n, wires);
        System.out.println(result);
    }

    public int solution(int n, int[][] wires) {

        int answer = Integer.MAX_VALUE;

        // 와이어 갯수 만큼 돌리기 (하나씩 없앨거니까)
        for (int i = 0; i < wires.length; i++) {

            // 그래프 초기화 (1번부터 n번 송전탑까지 활용) (0은 미사용)
            List<List<Integer>> graph = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }

            // i번째 와이어는 빼고 나머지로 그래프 구성
            for (int j = 0; j < wires.length; j++) {
                if (i == j)
                    continue; // 해당 와이어 스킵
                int a = wires[j][0];
                int b = wires[j][1];
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            int count = bfs(1, graph, n);
            int diff = Math.abs((n - count) - count);
            answer = Math.min(answer, diff);

        }

        return answer;
    }

    // BFS를 통해 연결된 노드 수 세기
    private int bfs(int start, List<List<Integer>> graph, int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    count++;
                }
            }
        }
        return count;
    }

}
