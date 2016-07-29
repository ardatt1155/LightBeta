/**
 * Created by ardatt1155
 */

public class AbacusEngine implements AbacusInterface
{
    public int compute(Roles code)
    {
        RoleInterface role = RoleFactory.spawn(code);
        ArenaInterface arena = new FoneArena();
        return 42;
    }
}
