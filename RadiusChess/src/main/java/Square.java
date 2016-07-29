
/**
 * Created by ardatt1155
 */

import java.io.PrintStream;
import java.util.List;

public class Square
{
    public final int x;
    public final int y;

    public Square(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Square s)
    {
        return (this.x == s.x && this.y == s.y);
    }

    public boolean find(List<Square> squares)
    {
        for (Square square: squares) {
            if (this.equals(square)) return true;
        }
        return false;
    }

    public boolean bounds(int dimension)
    {
        return (this.x > -1 && this.x < dimension && this.y > -1 && this.y < dimension);
    }

    public void print(PrintStream os) {
        os.print(" (" + this.x + "," + this.y + ") ");
    };

}
