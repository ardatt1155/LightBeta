/**
 * Created by ardatt1155
 */

import java.util.List;

public interface ArenaInterface
{
    public int dimensionX();

    public int dimensionY();

    public List<Square> traps();

    public List<Square> clays();
}
