/**
 * Created by ardatt1155
 */

import org.junit.Test;
import java.util.List;

public class RookRoleTest
{
    @Test
    public void moves()
    {
        RoleInterface role = new RookRole();
        List<Square> moves = null;

        moves = role.nextSquares(new Square(1, 1), 3);
        org.junit.Assert.assertTrue(moves.size() == 4);

        moves = role.nextSquares(new Square(0, 1), 3);
        org.junit.Assert.assertTrue(moves.size() == 4);
    }
}
