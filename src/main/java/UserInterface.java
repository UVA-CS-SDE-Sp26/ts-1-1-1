import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserInterface {

    public void run(String[] args) {
            //ProgramControl program = new ProgramControl();
            FileHandler filehandler = new FileHandler();
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
                        System.out.println("Up to 2 arguments allowed: fileNumber keyFile");
                }
            }
            catch (Exception e){
                System.out.println("error: " + e.getMessage());
            }

    }

    private void printFiles(List<String> list) {
        for (String line : list) {
            System.out.println(line);
        }
    }

    private String numToFileName(int num, List<String> list){
        return list.get(num-1);
    }

}
