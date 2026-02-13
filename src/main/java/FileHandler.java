import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Responsible for all direct access to files
 */
public class FileHandler {
    private final Path dataDir;

    public FileHandler() {
        this(FileSystems.getDefault().getPath("data"));
    }

    public FileHandler(Path path) {
        this.dataDir = path;
    }

    public FileHandler(String path) {
        this(FileSystems.getDefault().getPath(path));
    }

    public List<Path> getFiles() {
        if (!Files.isDirectory(dataDir)) {
            throw new IllegalStateException("Data directory not found: " + dataDir);
        }

        try (Stream<Path> paths = Files.list(dataDir)) {
            return paths.filter(Files::isRegularFile).sorted().toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read data directory", e);
        }
    }

    public Stream<String> streamLines(String filename) throws IOException {
        Path file = findFile(filename);
        return Files.lines(file);
    }

    public String readFile(String filename) throws IOException {
        Path file = findFile(filename);
        return Files.readString(file);
    }

    private Path findFile(String filename) throws FileNotFoundException {
        Path file = dataDir.resolve(filename);
        if (!Files.isRegularFile(file)) {
            throw new FileNotFoundException("File not found: " + file);
        }
        return file;
    }
}

