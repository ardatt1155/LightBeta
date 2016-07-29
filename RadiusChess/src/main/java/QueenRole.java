
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

    public List<Square> nextSquares(Square square, int dimension)
    {
        List<Square> squares = new ArrayList<Square>();
        for (int i = -dimension; i < dimension; ++i)
        {
            if (i == 0) continue;
            if (square.x + i < dimension && square.x + i > -1) squares.add(new Square(square.x + i, square.y));
            if (square.y + i < dimension && square.y + i > -1) squares.add(new Square(square.x, square.y + i));
            if (square.x + i < dimension && square.x + i > -1 && square.y + i < dimension && square.y + i > -1) squares.add(new Square(square.x + i, square.y + i));
            if (square.x + i < dimension && square.x + i > -1 && square.y - i < dimension && square.y - i > -1) squares.add(new Square(square.x + i, square.y - i));
        }
        return squares;
    }
}
