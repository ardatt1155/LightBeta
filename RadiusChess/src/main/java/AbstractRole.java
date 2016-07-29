/**
 * Created by ardatt1155
 */

public abstract class AbstractRole implements RoleInterface
{
    protected String _name;

    protected Roles _role;

    public String getRoleName()
    {
        return this._name;
    }

    public Roles getRoleCode()
    {
        return this._role;
    }
}
