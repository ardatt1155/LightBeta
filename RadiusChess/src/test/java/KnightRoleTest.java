/**
 * Created by ardatt1155
 */

import org.junit.Test;
import java.util.List;

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
        org.junit.Assert.assertTrue(moves.size() == 2);
    }
}
