
/**
 * Created by ardatt1155
 */

import org.junit.Test;
import java.io.*;

public class RadiusAppTest
{
    @Test
    public void testMain() {
        String data = "1\n 2\n m\n 9\n -2\n 6\n 5\n 4\n 3\n q\n";
        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        RadiusApp.play();

        System.setIn(stdin);
        System.setOut(stdout);

        String output = baos.toString();
        System.out.println("Radiusapp test run commences ... ");
        System.out.print(output);
        System.out.println(".... test run finished");
        org.junit.Assert.assertTrue(output.startsWith("Launching RadiusChess ..."));
    }
}
