/**
 * Created by ardatt1155
 */

import java.util.List;
import java.util.ArrayList;

public class KnightRole extends AbstractRole
{
    public KnightRole()
    {
        this.role = Roles.Knight;
    }

    public List<Square> nextSquares(Square square, Square dimension)
    {
        Square reach = null;
        List<Square> squares = new ArrayList<Square>();
        reach = new Square(square.x + 1, square.y + 2);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x + 1, square.y - 2);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x + 2, square.y + 1);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x + 2, square.y - 1);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x - 1, square.y + 2);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x - 1, square.y - 2);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x - 2, square.y + 1);
        if (reach.bounds(dimension)) squares.add(reach);
        reach = new Square(square.x - 2, square.y - 1);
        if (reach.bounds(dimension)) squares.add(reach);
        return squares;
    }
}
