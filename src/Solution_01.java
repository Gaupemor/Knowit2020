import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution_01 {

    static public void solve() throws IOException {
        String path = "C:\\Users\\selma\\IdeaProjects\\Knowit2020\\input\\input_01.txt";
        int[] numbers = Stream.of(Files.readString(Path.of(path))
                .split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(numbers);

        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] != i+1) {
                System.out.println(i+1);
                return;
            }
        }
    }
}
