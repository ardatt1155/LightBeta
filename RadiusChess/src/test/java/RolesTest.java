import org.junit.Test;

/**
 * Created by ardatt1155
 */

public class RolesTest
{
    @Test
    public void isRoleOk()
    {
        org.junit.Assert.assertTrue(Roles.isRoleOk(1));
        org.junit.Assert.assertTrue(Roles.isRoleOk(5));
        org.junit.Assert.assertTrue(Roles.isRoleOk(3));
        org.junit.Assert.assertTrue(!Roles.isRoleOk(10));
        org.junit.Assert.assertTrue(!Roles.isRoleOk(-1));
    }
}
