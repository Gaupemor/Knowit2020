import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution_01 {

    // answer: 81273
    static public void solve() {
        String path = "C:\\Users\\selma\\IdeaProjects\\Knowit2020\\input\\input_01.txt";
        int[] numbers = new int[0];
        try {
            numbers = Stream.of(Files.readString(Path.of(path))
                .split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        } catch (IOException ignored) {}

        Arrays.sort(numbers);

        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] != i+1) {
                System.out.println(i+1);
                return;
            }
        }
    }
}
