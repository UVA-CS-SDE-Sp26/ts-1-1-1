import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserInterface {
    private final FileHandler filehandler;

    public UserInterface(FileHandler filehandler){
        this.filehandler = filehandler;
    }

    public void run(String[] args) {
            try{
                List<String> fileList = ProgramControl.ArrayOfFiles(filehandler);
                if (args.length == 0) {
                    printFiles(ProgramControl.numberFiles(fileList));
                } else if (args.length < 3) {
                        int fileNum = ProgramControl.checkCommandArgument(args[0]);
                        String cipherKey = "key.txt"; // default key
                        if (args.length == 2) {
                            cipherKey = (args[1]);
                        }
                        Path cipherKeyPath = Paths.get("ciphers",cipherKey);
                        String content = filehandler.readFile(numToFileName(fileNum,fileList));
                        Cipher cipher = new Cipher(cipherKeyPath);

                        System.out.println(cipher.decipherStringInput(content));
                } else {
                    System.out.println("Up to 2 arguments allowed: [fileNumber] [keyFile]");
                }
            }
            catch (Exception e){
                System.out.println("error: " + e.getMessage() + "\nexiting...");
            }
    }

    public void printFiles(List<String> list) {
        for (String line : list) {
            System.out.println(line);
        }
    }

    public String numToFileName(int num, List<String> list) throws IndexOutOfBoundsException {
        //Gyeom's original code
        if (num > list.size() || num <= 0){
            throw new IndexOutOfBoundsException("file " + num + " does not exist.");
        }
        return list.get(num-1);

        /*
        potential code to help the index out of bounds issue - Ali
        try {
            return list.get(num-1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("File 0" + num + " does not exist.");
        }
        */
    }
}