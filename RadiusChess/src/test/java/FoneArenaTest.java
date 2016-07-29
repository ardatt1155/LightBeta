
/**
 * Created by ardatt1155
 */

import org.junit.Test;
import java.util.function.Consumer;

public class FoneArenaTest
{
    @Test
    public void test()
    {
        final ArenaInterface arena = new FoneArena();

        Consumer<Square> bounds = new Consumer<Square>() {
            public void accept(Square square) {
                org.junit.Assert.assertTrue(square.bounds(new Square(arena.dimensionX(), arena.dimensionY())));
            }
        };

        arena.traps().forEach(bounds);
        arena.clays().forEach(bounds);
    }
}
