package 구현시뮬레이션.수열;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0 ; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 비감소 길이 계산
        int[] inc = new int[n];
        // 비증가 길이 계산
        int[] dec = new int[n];
        inc[0] = dec[0] = 1;

        int answer = 1;

        for (int i = 1 ; i < n; i++) {

            // 비감소
            inc[i] = (arr[i] >= arr[i-1]) ? inc[i-1] + 1 : 1;
            // 비증가
            dec[i] = (arr[i] <= arr[i-1]) ? dec[i-1] + 1 : 1;

            answer = Math.max(answer, Math.max(inc[i], dec[i]));

        }

        System.out.println(answer);

    }

}
