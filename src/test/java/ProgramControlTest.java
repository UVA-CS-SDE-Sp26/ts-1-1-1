import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramControlTest {

    @Test
    void testNumberFiles() {

        ProgramControl programControl = new ProgramControl();

        List<String> inputBasic = List.of("file1.txt", "file2.txt", "file3.txt");
        List<String> result = programControl.numberFiles(inputBasic);
        List<String> expected = List.of("01 file1.txt", "02 file2.txt", "03 file3.txt");

        assertEquals(expected, result);

        List<String> inputOne = List.of("file1.txt");
        List<String> resultOne = programControl.numberFiles(inputOne);
        List<String> expectedOne = List.of("01 file1.txt");

        assertEquals(expectedOne, resultOne);

        List<String> inputNone = List.of();
        assertThrows(RuntimeException.class, () -> programControl.numberFiles(inputNone));
    }

    @Test
    void testPathToString() {
    }

    @Test
    void testCheckCommandArgument() {
    }

    @Test
    void testCommandUtility() {
    }
}