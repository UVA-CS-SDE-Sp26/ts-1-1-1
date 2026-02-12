//Gyeom Park  - Team 1-1-1
//Team Member A

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class UserInterfaceTest {

    //helper: redirects and captures System.out for testing
    private String captureStdout(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try {
            action.run();
            return output.toString();
        } finally {
            System.setOut(originalOut);
        }
    }

    //test printFiles if printing correctly
    @Test
    void testPrintFiles() {
        UserInterface ui = new UserInterface(null);
        List<String> files = List.of("a.txt", "b.txt");

        String out = captureStdout(() -> ui.printFiles(files));

        assertTrue(out.contains("a.txt"));
        assertTrue(out.contains("b.txt"));
    }

    //test numToFile for converting file number to corresponding file name
    @Test
    void testNumToFileName() {
        UserInterface ui = new UserInterface(null);
        List<String> files = List.of("file1.txt", "file2.txt");

        assertEquals("file1.txt", ui.numToFileName(1, files));
        assertEquals("file2.txt", ui.numToFileName(2, files));
    }

    //check that numToFile throws an IndexOutOfBoundsException for file numbers above range, and displays correct message
    @Test
    void testNumToFileNameForExceptionMessage1() {
        UserInterface ui = new UserInterface(null);
        List<String> files = List.of("a.txt", "b.txt");

        IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class,
                () -> ui.numToFileName(3, files));

        assertEquals("file 3 does not exist.", ex.getMessage());
    }

    //check that numToFile throws an IndexOutOfBoundsException for file number = 0, and displays correct message
    @Test
    void testNumToFileNameForExceptionMessage2() {
        UserInterface ui = new UserInterface(null);
        List<String> files = List.of("a.txt", "b.txt");

        IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class,
                () -> ui.numToFileName(0, files));

        assertEquals("file 0 does not exist.", ex.getMessage());
    }


    //check that numToFile throws an IndexOutOfBoundsException for negative file number, and displays correct message
    @Test
    void numToFileName_throwsExceptionWithCorrectMessage() {
        UserInterface ui = new UserInterface(null);
        List<String> files = List.of("a.txt", "b.txt");

        IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class,
                () -> ui.numToFileName(-5, files));

        assertEquals("file -5 does not exist.", ex.getMessage());
    }

    //test run() for being able to successfully print to output
    @Test
    void testRunWithNoArgsPrintsSomething() {
        FileHandler filehandler = new FileHandler() {
            @Override
            public String readFile(String name) { return "dummy"; }
        };

        UserInterface ui = new UserInterface(filehandler);

        String out = captureStdout(() -> ui.run(new String[]{}));

        assertFalse(out.isBlank());
    }
}