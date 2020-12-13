import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution_11 {

    private static final String passwordToFind = "eamqia";

    private static List<String> passwords(String hint) {
        List<String> passwordsIntermediate = new ArrayList<>();
        passwordsIntermediate.add(hint);

        String prevHint = hint;
        char[] passArr;
        int offset = 97;
        do { passArr = prevHint.substring(1).toCharArray();
            for(int i = 0; i < passArr.length; i++) {
                passArr[i] = (char)
                        ((((int) passArr[i] - offset + 1 + (int) prevHint.charAt(i) - offset) % 26) + offset);
            }
            String passStr = String.valueOf(passArr);
            passwordsIntermediate.add(passStr); prevHint = passStr;
        } while(passArr.length > 1); //0?

        return IntStream.range(0, hint.length())
                .mapToObj(i ->
                        passwordsIntermediate.stream()
                                .filter(s -> s.length() > i)
                                .map(s -> s.charAt(i))
                                .map(Object::toString)
                                .collect(Collectors.joining("")))
                .collect(Collectors.toList());
    }

    // metropolitan
    public static void solve() {
        String[] hints;
        try {
            hints = Files.readAllLines(Path.of(InputPath.input_11.getPath())).toArray(String[]::new);
        } catch (IOException e) {
            hints = new String[0];
        }

        String correctHint = Arrays.stream(hints).filter(h ->
                passwords(h).stream().anyMatch(s -> s.contains(passwordToFind))).findAny().orElseThrow();
        System.out.println(correctHint);
    }
}
