import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

public class Solution_09 {

    static class Elf {
        boolean sick;

        Elf(boolean sick) {
            this.sick = sick;
        }
    }

    // answer: 158
    public static void solve() {
        String[] input;
        try {
            input = Files.readAllLines(Path.of(InputPath.input_09.path)).toArray(String[]::new);
        } catch (IOException e) { input = new String[0]; }

        Elf[][] elves = new Elf[input.length][input[0].length()];
        for(int i = 0; i < input.length; i++) {
            String[] finalInput = input; int finalI = i;
            elves[i] = IntStream.range(0, finalInput[i].length())
                    .mapToObj(j -> new Elf(finalInput[finalI].charAt(j) == 'S'))
                    .toArray(Elf[]::new);
        }

        int days = 0;
        boolean sickness;
        do {
            sickness = false;
            List<int[]> elvesToBeSick = new ArrayList<>();
            for(int i = 0; i < elves.length; i++) {
                for(int j = 0; j < elves[i].length; j++) {
                    if(!elves[i][j].sick) {
                        int sickNeighbours = 0;
                        sickNeighbours += i > 0 && elves[i-1][j].sick                       ? 1 : 0;    //left
                        sickNeighbours += i < (elves.length - 1) && elves[i+1][j].sick      ? 1 : 0;    //right
                        sickNeighbours += j > 0 && elves[i][j-1].sick                       ? 1 : 0;    //over
                        sickNeighbours += j < (elves[i].length - 1) && elves[i][j + 1].sick ? 1 : 0;    //under
                        if(sickNeighbours > 1) {
                            elvesToBeSick.add(new int[] { i, j });
                            sickness = true;
                        }
                    }
                }
            } for(int[] e : elvesToBeSick) elves[e[0]][e[1]].sick = true;
            days++;
        } while(sickness);

        System.out.println(days);
    }
}
