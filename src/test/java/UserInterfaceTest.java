import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class UserInterfaceTest {

    @Test
    void run() {
    }

    @Test
    void testPrintFiles() {
        List<String> files = List.of("a.txt", "b.txt");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //printFiles(files);

        String result = output.toString();

        assertTrue(result.contains("a.txt"));
        assertTrue(result.contains("b.txt"));
    }

    @Test
    void testNumToFileName() {
    }

}