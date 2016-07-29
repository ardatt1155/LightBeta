/**
 * Created by ardatt1155
 */

import java.util.List;

interface RoleInterface
{
    public List<Square> nextSquares(Square square, Square dimension);

    public int getRoleCode();

    public String getRoleName();
}
