
/**
 * Created by ardatt1155
 */

import org.junit.Test;
import java.util.List;

public class KingRoleTest
{
    @Test
    public void moves()
    {
        RoleInterface king = new KingRole();
        List<int[]> moves = null;

        moves = king.nextSquares(1, 1, 3);
        moves.forEach(AbstractArena.spc); System.out.println();
        org.junit.Assert.assertTrue(moves.size() == 8);
    }
}
