import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution_06 {

    // answer: 952
    public static void solve() {
        String path = InputPath.input_06.getPath();

        int[] godteri;
        try {
            godteri =   Stream.of(Files.readString(Path.of(path)).split(","))
                        .mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            godteri = new int[0];
        }

        int alver = 127;
        int sum = IntStream.of(godteri).sum();

        int i = godteri.length - 1;
        while(sum % alver != 0) {
            sum = sum - godteri[i];
            i--;
        }

        System.out.println(sum / alver);
    }

}
