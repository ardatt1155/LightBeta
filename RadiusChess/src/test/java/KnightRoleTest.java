/**
 * Created by ardatt1155
 */

import org.junit.Test;
import java.util.List;
import java.util.function.Consumer;

public class KnightRoleTest
{
    @Test
    public void moves()
    {
        RoleInterface role = new KnightRole();
        List<Square> moves = null;

        moves = role.nextSquares(new Square(1, 1), 3);
        org.junit.Assert.assertTrue(moves.size() == 0);

        moves = role.nextSquares(new Square(0, 1), 3);
        moves.forEach(new Consumer<Square>() {
            public void accept(Square square) {
                square.print(System.out);
            }
        }); System.out.println();
        org.junit.Assert.assertTrue(moves.size() == 2);
    }
}
