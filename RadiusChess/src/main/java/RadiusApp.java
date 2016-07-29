
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
        System.out.println("Enter your character : 1 King. 2 Queen. 3 Bishop. 4 Knight. 5. Rook. 6 Pawn");

        int iterations = 5;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (iterations-- > 0) {
                String value = br.readLine().trim();
                int character = 1; //default King
                if ("q".equalsIgnoreCase(value)) break;
                try {
                    character = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    System.out.println("Your selection " + value + " was invalid. Try again");
                    continue;
                }
                String name = null;
                int moves = 0;
                switch (character) {
                    default:
                        name = "King";
                        moves = 42;
                }
                System.out.println(name + " gives you " + moves + " moves.");

            }
        } catch (IOException e) {
            System.out.println("Radiuschess errored out. Sorry.");
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
