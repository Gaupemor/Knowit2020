import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Solution_12 {

    private final static Map<Integer, Integer> generations = new HashMap<>();

    private static void countGenerations(String[] s) {
        countGenerationsRecursive(0, s);
    }

    private static void countGenerationsRecursive(int genIndex, String[] s) {
        if(s[0].contains("(")) {
            s[0] = s[0].replace("(", "");
            s[s.length-1] = s[s.length-1].substring(0, s[s.length-1].length()-1);
        }

        int numInThisGen = 0;

        List<int[]> nextGenIndicesList = new ArrayList<>();
        int[] nextGenIndices = new int[] { };
        int nesting = 0;

        for(int i = 0; i < s.length; i++) {
            String name = s[i];
            if(name.contains("("))  {
                if(nesting == 0) nextGenIndices = new int[] { i, -1 };
                nesting += name.chars().filter(ch -> ch == '(').count();
            } if(nesting == 0) {
                numInThisGen++;
            } if(name.contains(")")) {
                nesting = nesting - Math.toIntExact(name.chars().filter(ch -> ch == ')').count());
                if(nesting == 0)  {
                    nextGenIndices[1] = i;
                    nextGenIndicesList.add(nextGenIndices.clone());
                }
            }
        }

        generations.put(genIndex, generations.containsKey(genIndex) ?
                numInThisGen + generations.get(genIndex) :
                numInThisGen);

        if(Arrays.stream(s).anyMatch(n -> n.contains("("))) {
            nextGenIndicesList.forEach(indices -> countGenerationsRecursive(genIndex+1, Arrays.copyOfRange(s, indices[0], indices[1]+1)));
        }
    }

    public static void solve() {
        String[] tree;
        try {
            tree = Files.readAllLines(Path.of(InputPath.input_12.getPath()))
                    .get(0).split(" ");
        } catch (IOException e) { tree = new String[] {}; }

        countGenerations(tree);
        System.out.println(generations.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow());
    }
}
