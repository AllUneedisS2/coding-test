import java.util.*;

public class DiskController {

    private static int answer;
    private static int count;
    private static int time;

    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {3, 5}};
        DiskController diskController = new DiskController();
        int result = diskController.solution(jobs);
        System.out.println(result);
    }

    public int solution(int[][] jobs) {
        List<Task> disk = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            disk.add(new Task(i, jobs[i][0], jobs[i][1]));
        }
        while (count != jobs.length) {
            findTask(disk);
        }
        return answer / jobs.length;
    }

    private static void findTask(List<Task> disk) {
        Optional<Task> tmp = disk.stream()
                .filter(d -> d.getRequestTime() <= time)
                .min(Comparator.comparingInt(Task::getRunTime));
        tmp.ifPresentOrElse(
                task -> {
                    time += task.getRunTime();
                    answer += time - task.getRequestTime();
                    count++;
                    disk.remove(task);
                },
                () -> {
                    time++;
                });
    }

    static class Task {
        private int seq;
        private int requestTime;
        private int runTime;

        public Task(int seq, int requestTime, int runTime) {
            this.seq = seq;
            this.requestTime = requestTime;
            this.runTime = runTime;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public int getRequestTime() {
            return requestTime;
        }

        public void setRequestTime(int requestTime) {
            this.requestTime = requestTime;
        }

        public int getRunTime() {
            return runTime;
        }

        public void setRunTime(int runTime) {
            this.runTime = runTime;
        }
    }

}
