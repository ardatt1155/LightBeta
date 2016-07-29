/**
 * Created by ardatt1155
 */

import java.util.List;
import java.util.ArrayList;

public class PawnRole extends AbstractRole
{
    public PawnRole()
    {
        this.role = Roles.Pawn;
    }

    public List<Square> nextSquares(Square square, Square dimension)
    {
        // moves ignored : two-squares-on-first-move, en-passant, diagonal-captures, pawn-promotions
        // pawn only moves one square up along the x-axes (this makes the axes asymmetric)
        Square reach = null;
        List<Square> squares = new ArrayList<Square>();
        reach = new Square(square.x + 1, square.y);
        if (reach.bounds(dimension)) squares.add(reach);
        return squares;
    }
}
