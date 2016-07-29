
/**
 * Created by ardatt1155
 */

import org.junit.Test;
import java.util.List;
import java.util.function.Consumer;

public class FoneArenaTest
{
    @Test
    public void test()
    {
        final ArenaInterface arena = new FoneArena();

        Consumer<int[]> bounds = new Consumer<int[]>() {
            public void accept(int[] square) {
                org.junit.Assert.assertTrue(square[0] > -1 && square[0] < arena.dimension() && square[1] > -1 && square[1] < arena.dimension());
            }
        };

        arena.traps().forEach(bounds);
        arena.clay().forEach(bounds);
    }
}
