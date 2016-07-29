import org.junit.Test;

/**
 * Created by ardatt1155
 */

public class RoleFactoryTest
{
    @Test
    public void spawn()
    {
        RoleInterface role = null;

        role = RoleFactory.spawn(Roles.King);
        org.junit.Assert.assertTrue(role instanceof KingRole);

        role = RoleFactory.spawn(Roles.Pawn);
        org.junit.Assert.assertTrue(role instanceof PawnRole);
    }
}
