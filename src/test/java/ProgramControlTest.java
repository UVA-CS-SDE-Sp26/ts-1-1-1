import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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

        ProgramControl programControl = new ProgramControl();
        List<Path> emptyPaths = List.of();
        assertThrows(RuntimeException.class, () -> programControl.PathToString(emptyPaths));

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

        // Negative integer → exception
        assertThrows(RuntimeException.class, () -> programControl.checkCommandArgument("-1"));
        // Non-integer → exception
        RuntimeException exNonInt = assertThrows(RuntimeException.class, () -> TopSecret.checkCommandArgument("abc"));
    }


    @Test
    void testArrayOfFiles() {

    }

}