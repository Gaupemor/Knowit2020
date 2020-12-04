import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution_03 {

    // answer: askepott,marsipangris,pinnekjøtt
    public static void solve() {
        String path_matrix = InputPath.input_03_matrix.getPath();
        String path_words = InputPath.input_03_words.getPath();

        List<String> words = new ArrayList<>();
        String[] matrix = new String[0];
        try {
            words = Files.readAllLines(Path.of(path_words));
            matrix = Files.lines(Path.of(path_matrix)).toArray(String[]::new);
        } catch (IOException ignored) {}

        List<String> found = Stream.of(
                Stream.of(occurences(words, matrix)),
                Stream.of(occurences(words, reverse(matrix))),
                Stream.of(occurences(words, vertical(matrix))),
                Stream.of(occurences(words, reverse(vertical(matrix)))),
                Stream.of(occurences(words, diagonal_right(matrix))),
                Stream.of(occurences(words, reverse(diagonal_right(matrix)))),
                Stream.of(occurences(words, diagonal_left(matrix))),
                Stream.of(occurences(words, reverse(diagonal_left(matrix)))))
                .flatMap(s -> s)
                .collect(Collectors.toList());

        words.removeAll(found);
        words.sort(String::compareTo);

        StringBuilder result = new StringBuilder();
        for(String w : words) result.append(w).append(" ");
        System.out.println(result);
    }

    private static String[] occurences(List<String> from, String[] in) {
        return from.stream()
                .filter(w -> Stream.of(in)
                        .anyMatch(s -> s.contains(w)))
                .toArray(String[]::new);
    }

    private static String[] reverse(String[] array) {
        return Stream.of(array)
                .map(s -> new StringBuilder()
                        .append(s)
                        .reverse()
                        .toString())
                .toArray(String[]::new);
    }

    private static String[] vertical(String[] array) {
        String[] v = new String[array.length];
        for(int i = 0; i < array[0].length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (String s : array) sb.append(s.charAt(i));
            v[i] = sb.toString();
        } return v;
    }

    private static String[] diagonal_right(String[] array) {
        String[] d = new String[array.length];
        for(int i = 0; i < array[0].length(); i++) {
           StringBuilder s = new StringBuilder();
           for(int j = 0; j < array[0].length(); j++) {
               int di = i + j < array[0].length() ? i + j : (i + j) - array[0].length();
               s.append(array[di].charAt(j));
           } d[i] = s.toString();
        } return d;
    }

    private static String[] diagonal_left(String[] array) {
        String[] d = new String[array.length];
        for(int i = array[0].length() - 1; i >= 0; i--) {
            StringBuilder s = new StringBuilder();
            for(int j = 0; j < array[0].length(); j++) {
                int di = i - j >= 0 ? i - j : (i - j) + array[0].length();
                s.append(array[di].charAt(j));
            } d[i] = s.toString();
        } return d;
    }
}
