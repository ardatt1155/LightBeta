
/**
 * Created by ardatt1155
 */

import com.sun.javaws.exceptions.InvalidArgumentException;

public final class RoleFactory
{
    public static RoleInterface spawn(Roles role)
    {
        RoleInterface puppet = null;
        switch (role)
        {
            case King:
                puppet = new KingRole();
                break;
            case Queen:
            case Rook:
            case Knight:
            case Bishop:
            default:
                throw new IllegalArgumentException("Radiuschess: role not supported.");
        }
        return puppet;
    }
}
