
/**
 * Created by ardatt1155
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class RadiusApp
{
    public static void play()
    {
        System.out.println("Launching RadiusChess ... ");
        System.out.println("RadiusChess is ready. Let's play. Enter q to quit");
        System.out.println("Enter your character : 1 King. 2 Queen. 3 Rook. 4 Knight. 5 Bishop. 6 Pawn." +
                " (Pawns aren't playful)");

        int iterations = 50;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            AbacusInterface abacus = new AbacusEngine();
            while (iterations-- > 0) {
                String value = br.readLine().trim();
                int role = 1; //default King
                if ("q".equalsIgnoreCase(value)) break;
                try {
                    role = Integer.parseInt(value) - 1;
                    if (!Roles.isRoleOk(role)) throw new NumberFormatException("");
                } catch (NumberFormatException e) {
                    System.out.println("Your selection " + value + " was invalid. Try again");
                    continue;
                }
                long moves = abacus.compute(role);
                System.out.println(Roles.names[role] + " gives you " + moves + " moves.");

            }
        } catch (IOException e) {
            System.out.println("Radiuschess errored out because of platform failures. Sorry.");
        }
        if (iterations == 0) System.out.println("That is enough play for today. :P");
        System.out.println("RadiusChess shut down");
        return;
    }

    public static void main(String args[])
    {
        RadiusApp.play();
        return;
    }
}
