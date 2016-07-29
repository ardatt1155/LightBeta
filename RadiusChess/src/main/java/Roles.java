/**
 * Created by ardatt1155
 */

public final class Roles
{
    public static final int King   = 0;
    public static final int Queen  = 1;
    public static final int Rook   = 2;
    public static final int Knight = 3;
    public static final int Bishop = 4;
    public static final int Pawn   = 5;

    public static final String[] names = {"King", "Queen", "Rook", "Knight", "Bishop", "Pawn"};

    public static boolean isRoleOk(int role)
    {
        return (role < Roles.Pawn && role > -1);
    }
}

