/**
 * Created by ardatt1155
 */

import java.util.List;
import java.util.ArrayList;

public class KnightRole extends AbstractRole
{
    public KnightRole()
    {
        this.name = "Knight";
        this.role = Roles.Knight;
    }

    public List<Square> nextSquares(Square square, int dimension)
    {
        List<Square> squares = new ArrayList<Square>();
        if (square.x + 1 < dimension && square.y + 2 < dimension) squares.add(new Square(square.x + 1, square.y + 2));
        if (square.x + 1 < dimension && square.y - 2 > -1       ) squares.add(new Square(square.x + 1, square.y - 2));
        if (square.x + 2 < dimension && square.y + 1 < dimension) squares.add(new Square(square.x + 2, square.y + 1));
        if (square.x + 2 < dimension && square.y - 1 > -1       ) squares.add(new Square(square.x + 2, square.y - 1));
        if (square.x - 1 > -1        && square.y + 2 < dimension) squares.add(new Square(square.x - 1, square.y + 2));
        if (square.x - 1 > -1        && square.y - 2 > -1       ) squares.add(new Square(square.x - 1, square.y - 2));
        if (square.x - 2 > -1        && square.y + 1 < dimension) squares.add(new Square(square.x - 2, square.y + 1));
        if (square.x - 2 > -1        && square.y - 1 > -1       ) squares.add(new Square(square.x - 2, square.y - 1));
        return squares;
    }
}
