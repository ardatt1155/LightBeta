
/**
 * Created by ardatt1155
 */

import java.util.List;
import java.util.ArrayList;

public class QueenRole extends AbstractRole
{
    QueenRole()
    {
        this.role = Roles.Queen;
    }

    public List<Square> nextSquares(Square square, Square dimension)
    {
        Square reach = null;
        List<Square> squares = new ArrayList<Square>();
        int dims = Math.max(dimension.x, dimension.y);
        for (int i = -dims; i < dims; ++i)
        {
            if (i == 0) continue;
            reach = new Square(square.x + i, square.y);
            if (reach.bounds(dimension)) squares.add(reach);
            reach = new Square(square.x, square.y + i);
            if (reach.bounds(dimension)) squares.add(reach);
            reach = new Square(square.x + i, square.y + i);
            if (reach.bounds(dimension)) squares.add(reach);
            reach = new Square(square.x + i, square.y - i);
            if (reach.bounds(dimension)) squares.add(reach);
        }
        return squares;
    }
}
