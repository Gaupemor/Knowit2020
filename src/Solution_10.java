import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution_10 {

    // answer: ti-7776
    public static void solve() {
        Map<String, Integer> results;
        try {
            Map<String, Integer> finalResults = new HashMap<>();
            Files.lines(Path.of(InputPath.input_10.getPath()))
                    .forEach(line -> {
                        String[] contest = line.split(",");
                        for(int i = 0; i < contest.length; i++) {
                            finalResults.put(
                                    contest[i],
                                    finalResults.getOrDefault(contest[i], 0)
                                            + Integer.max(contest.length - (i + 1), 0)
                            );
                        }
                    });
            results = finalResults;
        } catch (IOException e) { results = new HashMap<>(); }



        Map.Entry<String, Integer> winner =
                results.entrySet().stream()
                        .max(Comparator.comparingInt(Map.Entry::getValue))
                        .orElseThrow();
        System.out.println(winner.getKey() + "-" + winner.getValue());
    }
}
