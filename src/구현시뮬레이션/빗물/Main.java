package 구현시뮬레이션.빗물;

import java.util.*;
import java.io.*;

// 가장 양 옆의 기둥은 제외하고..
// 모든 기둥을 루프로 돌면서..
// 현재 기둥 C의 양옆으로 가장 높은 기둥 두 개 A, B의 높이를 계산
// A, B 중에서 작은 값 - 현재 기둥 C 값이
// C위로 고일 수 있는 빗물의 크기
public class Main {

    static int result = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] heights = new int[w];
        for (int i = 0 ; i < w ; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        for (int now = 1 ; now < w - 1 ; now++) {
            
            // 왼쪽의 가장 높은 기둥 구하기
            int leftHigh = 0;
            for (int left = now - 1 ; left >= 0 ; left--) {
                leftHigh = Math.max(heights[left], leftHigh);
            }
            
            // 오른쪽 가장 높은 기둥 구하기
            int rightHigh = 0;
            for (int right = now + 1 ; right < w ; right++) {
                rightHigh = Math.max(heights[right], rightHigh);
            }

            int size = Math.min(leftHigh, rightHigh) - heights[now];
            if (size <= 0) continue;
            result += size;
            
        }

        System.out.println(result);

    }
    
}
