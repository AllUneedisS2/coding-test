public class Fatigue {

    private static int result = 0;
    private static boolean[] visited;

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 30}, {30, 10}};
        Fatigue fatigue = new Fatigue();
        int result = fatigue.solution(k, dungeons);
        System.out.println(result);
    }

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return result;
    }

    // 깊이우선탐색
    static void dfs(int energy, int[][] dungeons, int count) {

        // 결과값을 현재까지 카운트와 현재 결과 값 중 큰 값으로 대체
        result = Math.max(result, count);
        // 최댓값 나왔으면 그냥 리턴
        if (result == dungeons.length)
            return;

        // 던전 순회
        for (int i = 0; i < dungeons.length; i++) {

            // 최소 요구 에너지
            int required = dungeons[i][0];
            // 에너지 소모량
            int cost = dungeons[i][1];

            // 이번 던전에 방문한적이 없고 최소 요구치보다 에너지가 같거나 높으면
            if (!visited[i] && energy >= required) {
                // 이번 던전 방문 true
                visited[i] = true;
                // 다음 던전 탐색
                dfs(energy - cost, dungeons, count + 1);
                // backtrack
                visited[i] = false;
            }

        }

    }

}
