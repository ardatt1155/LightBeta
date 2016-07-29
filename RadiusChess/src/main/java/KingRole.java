
/**
 * Created by ardatt1155
 */

import java.util.List;
import java.util.ArrayList;

public class KingRole extends AbstractRole
{
    KingRole()
    {
        this.role = Roles.King;
    }

    public List<Square> nextSquares(Square square, Square dimension)
    {
        Square reach = null;
        List<Square> squares = new ArrayList<Square>();
        reach = new Square(square.x, square.y+1);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x, square.y-1);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x+1, square.y);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x-1, square.y);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x+1, square.y+1);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x+1, square.y-1);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x-1, square.y+1);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x-1, square.y-1);
        if (reach.bounds(dimension)) squares.add(reach);
        return squares;
    }
}
