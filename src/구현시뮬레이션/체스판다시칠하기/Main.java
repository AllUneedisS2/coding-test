package 구현시뮬레이션.체스판다시칠하기;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 그래프 만들기
        char[][] graph = new char[n][m];
        for (int i = 0 ; i < n ; i++) {
            String line = br.readLine();
            for (int j = 0 ; j < m ; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 0 ; i < n - 7 ; i++) {
            for (int j = 0 ; j < m - 7 ; j++) {

                // 일단 White 시작으로 계산
                int startW = 0;

                for (int k = 0 ; k < 8 ; k++) {
                    for (int l = 0 ; l < 8 ; l++) {
                        // 기대치
                        char expected = ((k+l)%2==0) ? 'W' : 'B';
                        if (graph[i+k][j+l] == expected) {
                            startW++;
                        }
                    }
                }

                // (64 - startW)는 사실상 startB
                answer = Math.min(answer, Math.min(startW, 64 - startW));

            }
        }

        System.out.println(answer);

    }
    
}
