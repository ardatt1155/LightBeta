/**
 * Created by ardatt1155
 */

public abstract class AbstractRole implements RoleInterface
{
    protected int role;

    public String getRoleName()
    {
        return Roles.names[this.role];
    }

    public int getRoleCode()
    {
        return this.role;
    }
}
