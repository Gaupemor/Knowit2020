import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution_13 {

    private static int nthOccurence(char c, int n, String s) {
        int occur = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c) occur++;
            if(n == occur) return i;
        } return -1;
    }

    public static void solve() {
        String str;
        try {
            str = String.join("", Files.readAllLines(Path.of(InputPath.input_13.getPath())));
        } catch (IOException e) {
            str = "";
        }

        int a_val = 'a';
        int z_val = 'z';
        int offset = 97;

        Map<Character, Integer> map = new HashMap<>();
        for(int i = a_val; i <= z_val; i++) {
            char letter = (char) i;
            int index = nthOccurence(letter, i-offset+1, str);
            map.put(letter, index);
        }

        System.out.println(map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .filter(e -> e.getValue() >= 0)
                .map(Map.Entry::getKey)
                .map(String::valueOf)
                .collect(Collectors.joining()));
    }
}
