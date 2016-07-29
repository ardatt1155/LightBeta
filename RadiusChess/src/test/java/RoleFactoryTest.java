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

        boolean flag = false;
        try {
            role = RoleFactory.spawn(Roles.Pawn);
        } catch (IllegalArgumentException e) {
            flag = true;
        } catch (Exception e) { }
        org.junit.Assert.assertTrue(flag);
    }
}
