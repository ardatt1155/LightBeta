/**
 * Created by ardatt1155
 */

import java.util.List;

interface RoleInterface
{
    public List<Square> nextSquares(Square square, int dimension);

    public Roles getRoleCode();

    public String getRoleName();
}
