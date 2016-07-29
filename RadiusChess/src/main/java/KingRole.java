
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
        List<Square> squares = new ArrayList<Square>();
        if (square.y+1 < dimension.y) squares.add(new Square(square.x, square.y+1));
        if (square.y-1 > -1) squares.add(new Square(square.x, square.y-1));
        if (square.x+1 < dimension.x) squares.add(new Square(square.x+1, square.y));
        if (square.x-1 > -1) squares.add(new Square(square.x-1, square.y));
        if (square.x+1 < dimension.x && square.y+1 < dimension.y) squares.add(new Square(square.x+1, square.y+1));
        if (square.x+1 < dimension.x && square.y-1 > -1) squares.add(new Square(square.x+1, square.y-1));
        if (square.x-1 > -1 && square.y+1 < dimension.y) squares.add(new Square(square.x-1, square.y+1));
        if (square.x-1 > -1 && square.y-1 > -1) squares.add(new Square(square.x-1, square.y-1));
        return squares;
    }
}
