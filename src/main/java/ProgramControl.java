import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handling command line arguments and the various inputs by the user
 */
public class ProgramControl {
    public static List<String> numberFiles(List<String> files) throws RuntimeException { //Given a String List of files, returns an indexed String list of files
        List<String> indexedList = new ArrayList<String>();
        if (files.isEmpty()) throw new RuntimeException("No files found to index.");
        for (int x = 0; x < files.size(); x++) {
            indexedList.add(String.format("%02d %s", x + 1, files.get(x))); //add formatted indexes to each file, i.e. 01, 02, 03
        }
        return indexedList;
    }

    public static List<String> pathToString(List<Path> paths) {
        List<String> pathStrings = new ArrayList<>();
        if (paths.isEmpty()) throw new RuntimeException("No files found to index.");
        for (Path p: paths) {
            pathStrings.add(p.toString());
        }
        return pathStrings;
    }

    public static int checkCommandArgument(String command) {
        try {
            int choice = Integer.parseInt(command);
            if (choice < 0) {
                throw new RuntimeException("Invalid choice: must be 0 or greater");
            }
            return choice;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input: must be an integer", e);
        }
    }

    public static List<String> ArrayOfFiles(FileHandler fileHandler) {
        List<Path> filePath = fileHandler.getFiles();
        List<String> finalFiles = new ArrayList<>();
        for (Path path : filePath) {
            finalFiles.add(String.valueOf(path.getFileName()));
        }
        return finalFiles;
    }
}
