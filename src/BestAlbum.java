import java.util.*;
import java.util.stream.Collectors;

public class BestAlbum {

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        BestAlbum bestAlbum = new BestAlbum();
        int[] result = bestAlbum.solution(genres, plays);

        // 4 1 3 0
        System.out.println(Arrays.toString(result));
    }

    class Album {
        private int firstSong = Integer.MIN_VALUE;
        private int firstPlays = Integer.MIN_VALUE;
        private int secondSong = Integer.MIN_VALUE;
        private int secondPlays = Integer.MIN_VALUE;
        int plays;

        public int getPlays() {
            return plays;
        }

        public int getFirstSong() {
            return firstSong;
        }

        public int getSecondSong() {
            return secondSong;
        }

        public void putSong(int name, int plays) {

            this.plays += plays;

            if (plays > firstPlays) {
                secondSong = firstSong;
                secondPlays = firstPlays;
                firstSong = name;
                firstPlays = plays;
                return;
            }

            if (plays == firstPlays) {
                secondSong = name;
                secondPlays = plays;
                return;
            }

            if (plays > secondPlays) {
                secondSong = name;
                secondPlays = plays;
            }

        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Album> genreAlbum = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreAlbum.computeIfAbsent(genres[i], k -> new Album()).putSong(i, plays[i]);
        }

        List<Album> mostPlays = genreAlbum.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted(Comparator.comparingInt(Album::getPlays).reversed())
                .collect(Collectors.toList());

        List<Integer> tmp = new ArrayList<>();

        for (Album album : mostPlays) {
            tmp.add(album.getFirstSong());
            if (album.getSecondSong() > -1) tmp.add(album.getSecondSong());
        }

        return tmp.stream().mapToInt(Integer::intValue).toArray();
    }

}