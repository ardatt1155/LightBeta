/**
 * Created by ardatt1155
 */

import org.junit.Test;
import org.mockito.Mockito;

public class AbacusEngineTest
{
    private long agent(int role, int jumps)
    {
        long moves = -1;
        AbacusInterface spy = Mockito.spy(new AbacusEngine());
        Mockito.when(spy.jumps()).thenReturn(jumps);
        moves = spy.compute(role);
        System.out.println("Role = " + Roles.names[role] + ", Jumps = " + spy.jumps() + ", Moves = " + moves);
        return moves;
    }

    @Test
    public void compute()
    {
        long moves = -1;
        int role = Roles.King;

        moves = this.agent(Roles.King, 1);
        org.junit.Assert.assertTrue(moves == 8);

        moves = this.agent(Roles.King, 2);
        org.junit.Assert.assertTrue(moves == 40);

        moves = this.agent(Roles.Queen, 2);
        org.junit.Assert.assertTrue(moves == 55);

        moves = this.agent(Roles.Rook, 2);
        org.junit.Assert.assertTrue(moves == 35);

        moves = this.agent(Roles.Bishop, 2);
        org.junit.Assert.assertTrue(moves == 20);

        moves = this.agent(Roles.Knight, 2);
        org.junit.Assert.assertTrue(moves == 16);

        moves = this.agent(Roles.Knight, 3);
        org.junit.Assert.assertTrue(moves == 35);

        moves = this.agent(Roles.Pawn, 7);
        org.junit.Assert.assertTrue(moves == 0);

        moves = this.agent(Roles.King, 7);
        org.junit.Assert.assertTrue(moves == 124908); //unvalidated

    }
}
