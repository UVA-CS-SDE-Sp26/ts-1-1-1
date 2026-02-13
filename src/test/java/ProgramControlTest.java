import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ProgramControlTest {

    // Create a temporary directory
    @TempDir
    Path testDir;

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
    void testArrayOfFiles() throws IOException {

        //Normal filehandlers
        Files.createFile(testDir.resolve("file1.txt"));
        Files.createFile(testDir.resolve("file2.txt"));
        Files.createFile(testDir.resolve("file3.txt"));

        FileHandler fileHandler = new FileHandler(testDir);
        List<String> result = ProgramControl.ArrayOfFiles(fileHandler);
        assertEquals(List.of("file1.txt", "file2.txt", "file3.txt"),  result);

        //Empty Filehandlers
        Path emptyDir = Files.createTempDirectory("emptyTestDir");
        FileHandler emptyHandler = new FileHandler(emptyDir);
        List<String> resultEmpty = ProgramControl.ArrayOfFiles(emptyHandler);

        assertTrue(resultEmpty.isEmpty());
    }

}