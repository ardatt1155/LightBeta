/**
 * Created by ardatt1155
 */

import org.junit.Test;

public class AbacusEngineTest
{
    @Test
    public void compute()
    {
        AbacusInterface engine = new AbacusEngine();
        int moves = engine.compute(Roles.King);
        org.junit.Assert.assertTrue(moves == 42);
    }
}
