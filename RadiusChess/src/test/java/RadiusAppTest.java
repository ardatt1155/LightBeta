
/**
 * Created by ardatt1155
 */

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;

public class RadiusAppTest
{
    @Test
    public void testMain() {
        String data = "1\n 2\n q\n";
        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        RadiusApp.play();

        System.setIn(stdin);
        System.setOut(stdout);

        String output = baos.toString();
        //System.out.println(output);
        org.junit.Assert.assertTrue(output.startsWith("Launching RadiusChess ..."));
    }
}
