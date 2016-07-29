
/**
 * Created by ardatt1155
 */

import java.util.List;

public class AbacusEngine implements AbacusInterface
{
    public int jumps()
    {
        return 7;
    }

    public long compute(Roles code)
    {
        RoleInterface role = RoleFactory.spawn(code);
        ArenaInterface arena = new FoneArena();

        long[][] board = new long[arena.dimension()][arena.dimension()];
        long[][] table = new long[arena.dimension()][arena.dimension()];
        for (int x = 0; x < arena.dimension(); ++x)
            for (int y = 0; y < arena.dimension(); ++y)
            {
                Square square = new Square(x, y);
                if (square.find(arena.clays())) board[x][y] = 0;
                else if (square.find(arena.traps())) board[x][y] = -1;
                else board[x][y] = 1;
            }

        for (int jump = 1; jump < this.jumps(); jump++)
        {
            for (int x = 0; x < arena.dimension(); ++x)
                for (int y = 0; y < arena.dimension(); ++y)
                {
                    Square square = new Square(x, y);
                    table[x][y] = square.find(arena.traps()) ? -1 : 0;
                }

            for (int x = 0; x < arena.dimension(); ++x)
                for (int y = 0; y < arena.dimension(); ++y)
                {
                    if (board[x][y] == -1) continue;
                    Square square = new Square(x, y);
                    List<Square> reachables = role.nextSquares(square, arena.dimension());
                    for (Square reachable : reachables)
                    {
                        if (reachable.find(arena.traps())) continue;
                        else table[reachable.x][reachable.y] += board[x][y];
                    }
                }

            long[][] t = board; board = table; table = t;
        }

        long result = 0;
        for (int x = 0; x < arena.dimension(); ++x)
            for (int y = 0; y < arena.dimension(); ++y)
                result = result + Math.max(board[x][y], 0);

        return result;
    }
}
