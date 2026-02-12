import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramControlTest {

    @Test
    void testNumberFiles() {

        ProgramControl programControl = new ProgramControl();

        //If they are inputted correctly
        List<String> inputBasic = List.of("file1.txt", "file2.txt", "file3.txt");
        List<String> result = programControl.numberFiles(inputBasic);
        List<String> expected = List.of("01 file1.txt", "02 file2.txt", "03 file3.txt");

        assertEquals(expected, result);

        //if there are no inputs
        List<String> inputNone = List.of();
        assertThrows(RuntimeException.class, () -> programControl.numberFiles(inputNone));
    }

    @Test
    void testPathToString() {

        //empty List of Paths
        ProgramControl programControl = new ProgramControl();
        List<Path> emptyPaths = List.of();
        assertThrows(RuntimeException.class, () -> programControl.PathToString(emptyPaths));

        //Normal list of paths
        List<Path> paths = List.of(
                Paths.get("file1.txt"),
                Paths.get("file2.txt"),
                Paths.get("file3.txt")
        );

        List<String> result = ProgramControl.PathToString(paths);
        assertEquals(List.of("file1.txt", "file2.txt", "file3.txt"), result);
    }

    @Test
    void testCheckCommandArgument() {

    ProgramControl programControl = new ProgramControl();

    // Valid integers
        assertEquals(5, programControl.checkCommandArgument("5"));
        assertEquals(0, programControl.checkCommandArgument("0"));

        // Negative integer leads to exception
        assertThrows(RuntimeException.class, () -> programControl.checkCommandArgument("-1"));
        // Non-integer leads to exception
        RuntimeException exNonInt = assertThrows(RuntimeException.class, () -> programControl.checkCommandArgument("abc"));
    }


    @Test
    void testArrayOfFiles() {

        FileHandler handlerMultiple = new FileHandler();
        ProgramControl programControl = new ProgramControl();

        //Normal amount of files
        List<Path> paths = List.of(
                Paths.get("file1.txt"),
                Paths.get("file2.txt"),
                Paths.get("file3.txt")
        );

        assertEquals(List.of("file1.txt", "file2.txt", "file3.txt"), programControl.ArrayOfFiles(handlerMultiple));

            // No files given
            FileHandler handlerEmpty = new FileHandler();
            List<Path> noPaths = List.of();

            assertTrue(programControl.ArrayOfFiles(handlerEmpty).isEmpty());
        }

}