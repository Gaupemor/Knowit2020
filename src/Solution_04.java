import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_04 {

    // answer: 1458014
    public static void solve() {
        String path = InputPath.input_04.getPath();
        Map<String, Integer> map = new HashMap<>()
                {{
                    put("sukker", 0);
                    put("mel", 0);
                    put("melk", 0);
                    put("egg", 0);
                }};
        try {
            Files.lines(Path.of(path))
                    .forEach(line -> Arrays.stream(line.split(", "))
                            .forEach(str -> {
                                String[] order = str.split(": ");
                                map.put(order[0], map.get(order[0]) + Integer.parseInt(order[1]));
                            }));
        } catch(IOException ignored) {}

        int m = Integer.MAX_VALUE;
        m = Math.min(Math.floorDiv(map.get("sukker"), 2), m);
        m = Math.min(Math.floorDiv(map.get("mel"), 3), m);
        m = Math.min(Math.floorDiv(map.get("melk"), 3), m);
        m = Math.min(Math.floorDiv(map.get("egg"), 1), m);

        System.out.println(m);
    }
}
