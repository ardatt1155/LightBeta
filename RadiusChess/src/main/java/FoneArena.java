/**
 * Created by ardatt1155
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoneArena extends AbstractArena
{
    public int dimensionX()
    {
        return 4;
    }

    public int dimensionY()
    {
        return 3;
    }

    public List<Square> traps()
    {
        if (this.trapList == null) {
            this.trapList = new ArrayList<Square>();
            this.trapList.add(new Square(0, 0));
            this.trapList.add(new Square(0, 2));
        }
        return Collections.unmodifiableList(this.trapList);
    }

    public List<Square> clays()
    {
        if (this.clayList == null) {
            this.clayList = new ArrayList<Square>();
            this.clayList.add(new Square(0, 1));
            this.clayList.add(new Square(3, 0));
        }
        return Collections.unmodifiableList(this.clayList);
    }
}
