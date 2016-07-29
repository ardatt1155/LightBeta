/**
 * Created by ardatt1155
 */

import org.junit.Test;
import org.mockito.Mockito;

public class AbacusEngineTest
{
    @Test
    public void compute()
    {
        long moves = -1;
        AbacusInterface spy = Mockito.spy(new AbacusEngine());

        Mockito.when(spy.jumps()).thenReturn(1);
        moves = spy.compute(Roles.King);
        System.out.println("Role = King, Jumps = " + spy.jumps() + ", Moves = " + moves);
        org.junit.Assert.assertTrue(moves == 8);

        Mockito.when(spy.jumps()).thenReturn(2);
        moves = spy.compute(Roles.King);
        System.out.println("Role = King, Jumps = " + spy.jumps() + ", Moves = " + moves);
        org.junit.Assert.assertTrue(moves == 40);

        Mockito.when(spy.jumps()).thenReturn(7);
        moves = spy.compute(Roles.King);
        System.out.println("Role = King, Jumps = " + spy.jumps() + ", Moves = " + moves);
        org.junit.Assert.assertTrue(moves == 124908); //verify?
    }
}
