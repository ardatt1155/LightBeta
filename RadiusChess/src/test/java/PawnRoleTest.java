/**
 * Created by ardatt1155
 */

import org.junit.Test;
import java.util.List;

public class PawnRoleTest
{
    @Test
    public void moves()
    {
        RoleInterface role = new PawnRole();
        List<Square> moves = null;

        moves = role.nextSquares(new Square(1, 1), new Square(4, 3));
        org.junit.Assert.assertTrue(moves.size() == 1);

        moves = role.nextSquares(new Square(4, 2), new Square(4, 3));
        org.junit.Assert.assertTrue(moves.size() == 0);
    }
}
