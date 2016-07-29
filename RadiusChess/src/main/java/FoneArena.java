import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ardatt1155
 */

public class FoneArena implements ArenaInterface
{
    private List<int[]> _traps;
    private List<int[]> _clay;

    public int dimension()
    {
        return 3;
    }

    public List<int[]> traps()
    {
        if (this._traps == null) {
            this._traps = new ArrayList<int[]>();
            this._traps.add(new int[]{0, 0});
            this._traps.add(new int[]{0, 2});
        }
        return Collections.unmodifiableList(this._traps);
    }

    public List<int[]> clay()
    {
        if (this._clay == null) {
            this._clay = new ArrayList<int[]>();
            this._clay.add(new int[]{0, 1});
        }
        return Collections.unmodifiableList(this._clay);
    }
}
