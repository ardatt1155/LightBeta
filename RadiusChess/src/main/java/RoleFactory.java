
/**
 * Created by ardatt1155
 */

import com.sun.javaws.exceptions.InvalidArgumentException;

public final class RoleFactory
{
    public static RoleInterface spawn(int role)
    {
        RoleInterface puppet = null;
        switch (role)
        {
            case Roles.King:
                puppet = new KingRole();
                break;
            case Roles.Queen:
                puppet = new QueenRole();
                break;
            case Roles.Rook:
                puppet = new RookRole();
                break;
            case Roles.Knight:
                puppet = new KnightRole();
                break;
            case Roles.Bishop:
                puppet = new BishopRole();
                break;
            case Roles.Pawn:
            default:
                throw new IllegalArgumentException("Radiuschess: role not supported.");
        }
        return puppet;
    }
}
