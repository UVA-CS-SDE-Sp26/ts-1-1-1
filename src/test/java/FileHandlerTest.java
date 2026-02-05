import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @TempDir
    Path testDir;

    @Test
    void testConstructorsWithSameDirectoryBehaveSame() throws IOException {
        Path file = testDir.resolve("test.txt");
        Files.writeString(file, "Some test text.");

        FileHandler fh1 = new FileHandler(testDir);
        FileHandler fh2 = new FileHandler(testDir.toString());
        FileHandler fh3 = new FileHandler(testDir.toString());

        assertEquals(fh1.getFiles(), fh2.getFiles());
        assertEquals(fh1.getFiles(), fh3.getFiles());
    }

    @Test
    void testGetFilesInDirectory() throws IOException {
       Path file1 = testDir.resolve("message.txt");
       Files.writeString(file1, "Hello world");
       Path file2 = testDir.resolve("random.txt");
       Files.writeString(file2, "Random text");
       Path file3 = testDir.resolve("abcdefg");
       Files.writeString(file3, "Random information.");

       FileHandler fileHandler = new FileHandler(testDir);

       List<Path> files = fileHandler.getFiles();
       List<String> expected = new ArrayList<>(List.of("abcdefg", "message.txt", "random.txt"));
       List<String> actual = new ArrayList<>();

       for (Path path : files) actual.add(String.valueOf(path.getFileName()));

       assertEquals(expected, actual);
    }

    @Test
    void testGetFilesThrowsExceptionWhenDirectoryDoesNotExist() throws IOException {
        FileHandler fileHandler = new FileHandler(testDir);
        Files.delete(testDir);
        assertThrows(IllegalStateException.class, fileHandler::getFiles);
    }

    @Test
    void testGetFilesThrowsExceptionWhenPathIsNotADirectory() {
        FileHandler fileHandler = new FileHandler("This is literally not a correct path sequence");
        assertThrows(IllegalStateException.class, fileHandler::getFiles);
    }

    @Test
    void testStreamLinesThrowsExceptionWhenDirectoryDoesNotExist() throws IOException {
        FileHandler fileHandler = new FileHandler(testDir);
        Files.delete(testDir);

        assertThrows(FileNotFoundException.class,
                () -> fileHandler.streamLines("test.txt"));
    }

    @Test
    void testStreamLinesThrowsExceptionWhenFileDoesNotExist() {
        FileHandler fileHandler = new FileHandler(testDir);

        assertThrows(FileNotFoundException.class,
                () -> fileHandler.streamLines("test.txt"));
    }

    @Test
    void testStreamLinesReturnsLinesInOrder() throws IOException {
        Path file = testDir.resolve("lines.txt");
        Files.writeString(file, "one\ntwo\nthree");

        FileHandler fileHandler = new FileHandler(testDir);

        List<String> lines;
        try (Stream<String> stream = fileHandler.streamLines("lines.txt")) {
            lines = stream.toList();
        }

        assertEquals(List.of("one", "two", "three"), lines);
    }

    @Test
    void readFileReturnsCorrectFileContents() throws IOException {
        Path file = testDir.resolve("text.txt");
        Files.writeString(file, "Today is the day.\nOctober is tomorrow.");

        FileHandler fileHandler = new FileHandler(testDir);

        assertEquals("Today is the day.\nOctober is tomorrow.", fileHandler.readFile("text.txt"));
    }
}