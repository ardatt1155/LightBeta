/**
 * Created by ardatt1155
 */

public abstract class AbstractRole implements RoleInterface
{
    protected String name;

    protected Roles role;

    public String getRoleName()
    {
        return this.name;
    }

    public Roles getRoleCode()
    {
        return this.role;
    }
}
