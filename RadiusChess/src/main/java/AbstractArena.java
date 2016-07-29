
/**
 * Created by ardatt1155
 */

import java.util.function.Consumer;

public class AbstractArena
{
    public static Consumer<int[]> spc = new Consumer<int[]>() {
        public void accept(int[] square) {
            System.out.print(" (" + square[0] + "," + square[1] + ") ");
        }
    };
}
