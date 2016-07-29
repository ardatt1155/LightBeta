
/**
 * Created by ardatt1155
 */

import java.util.List;
import java.util.ArrayList;

public class KingRole extends AbstractRole
{
    KingRole()
    {
        this._name = "King";
        this._role = Roles.King;
    }

    public List<int[]> nextSquares(int x, int y, int dimension)
    {
        List<int[]> squares = new ArrayList<int[]>();
        if (y+1 < dimension) squares.add(new int[]{x, y+1});
        if (y-1 > -1) squares.add(new int[]{x, y-1});
        if (x+1 < dimension) squares.add(new int[]{x+1, y});
        if (x-1 > -1) squares.add(new int[]{x-1, y});
        if (x+1 < dimension && y+1 < dimension) squares.add(new int[]{x+1, y+1});
        if (x+1 < dimension && y-1 > -1) squares.add(new int[]{x+1, y-1});
        if (x-1 > -1 && y+1 < dimension) squares.add(new int[]{x-1, y+1});
        if (x-1 > -1 && y-1 > -1) squares.add(new int[]{x-1, y-1});
        return squares;
    }
}
