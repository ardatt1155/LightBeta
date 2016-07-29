/**
 * Created by ardatt1155
 */

public class AbacusEngine implements AbacusInterface
{
    public int jumps()
    {
        return 7;
    }

    public int compute(Roles code)
    {
        RoleInterface role = RoleFactory.spawn(code);
        ArenaInterface arena = new FoneArena();

        int[][] board = new int[arena.dimension()][arena.dimension()];
        for (int x = 0; x < arena.dimension(); ++x)
            for (int y = 0; y < arena.dimension(); ++y)
                board[x][y] = 0;


        return 42;
    }
}
