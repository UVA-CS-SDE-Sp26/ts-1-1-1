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

    public static void main(String[] args) {
        ProgramControl commandHandler = new ProgramControl();
        commandHandler.commandUtility(args);
    }
}
