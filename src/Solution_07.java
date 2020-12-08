import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution_07 {

    // implementation from day 3
    private static String[] vertical(String[] array) {
        String[] v = new String[array[0].length()];
        for(int i = 0; i < array[0].length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (String s : array) sb.append(s.charAt(i));
            v[i] = sb.toString();
        } return v;
    }

    // answer: 5534
    public static void solve() {

        // get input
        String path = InputPath.input_07.getPath();
        String[] forest;
        try {
            forest = Files.lines(Path.of(path)).toArray(String[]::new);
        } catch (IOException e) {
            forest = new String[0];
        }

        // divide forest into trees
        String[] vertical_forest = vertical(forest);
        List<String[]> vertical_trees = new ArrayList<>();
        int old = 0;
        for(int i : IntStream.range(0, vertical_forest.length)
                .filter(i -> vertical_forest[i].isBlank()
                        && (i < vertical_forest.length-1 && vertical_forest[i+1].isBlank()))
                .toArray()) {
            vertical_trees.add(List.of(Arrays.copyOfRange(vertical_forest, old, i)).stream()
                    .filter(s -> !s.isBlank())
                    .toArray(String[]::new));
            old = i;
        } List<String[]> trees = vertical_trees.stream().map(Solution_07::vertical).collect(Collectors.toList());

        // determine how many trees are sellable
        int trees_to_sell = 0;
        for(String[] tree : trees) {
            boolean sellable = true;
            for(int i = 0; i < tree.length; i++) {
                int trunk_index = (tree[i].length() - 1) / 2;
                int tree_index = i;
                IntPredicate pre = r -> tree[tree_index].charAt(r) == '#';
                long left_branch = IntStream.range(0, trunk_index)
                        .filter(pre).count();
                long right_branch = IntStream.range(trunk_index+1, tree[i].length())
                        .filter(pre).count();

                if(left_branch != right_branch) {
                    sellable = false;
                    break;
                }
            } if(sellable) trees_to_sell++;
        }
        
        System.out.println(trees_to_sell);
    }
}
