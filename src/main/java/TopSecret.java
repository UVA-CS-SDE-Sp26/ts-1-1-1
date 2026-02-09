import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

import java.util.stream.Stream;

/**
 * Commmand Line Utility
 */
public class TopSecret {

    public static List<String> numberFiles(List<String> files) { //Given a String List of files, returns an indexed String list of files
        List<String> indexing = new ArrayList<String>();
        for (int x = 0; x < files.size(); x++) {
            indexing.add(String.format("%02d %s", x + 1, files.get(x))); //add formatted indexes to each file, i.e. 01, 02, 03
        }
        return indexing;
    }

    public static List<String> PathToString(List<Path> paths) {
        List<String> pathStrings = new ArrayList<>();
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
    public static void main(String[] args) {
        try {
            FileHandler secretFileHandler = new FileHandler();
            List<Path> filePath = secretFileHandler.getFiles();
            List<String> stringFiles = PathToString(filePath);
            if (args.length == 1) {
                int fileChoice = checkCommandArgument(args[0]);
                if (fileChoice >= stringFiles.size()) {
                    throw new RuntimeException("Choice out of range. Must be 1 to " + (stringFiles.size() - 1));
                }

                String fileText;
                try {
                    fileText = secretFileHandler.readFile(stringFiles.get(fileChoice));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("File not found: " + stringFiles.get(fileChoice), e);
                } catch (IOException e) {
                    throw new RuntimeException("Error reading file: " + stringFiles.get(fileChoice), e);
                }

                System.out.println(fileText);
            } else {
                List<String> result = numberFiles(stringFiles);
                for (String s : result) {
                    System.out.println(s);
                }
            }

        } catch (RuntimeException e) {
            System.out.println("Fatal error: " + e.getMessage());
        }
    }
}
