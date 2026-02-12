//Ali Khokhar - Team 1-1-1
//Team Member D

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class Cipher {

    //instance variables
    String keyFileName;
    File keyFile;
    String normalLetters;
    String cipheredLetters;
    ArrayList<String> linesOfKeyFile = new ArrayList<>();
    HashMap<Character, Character> keyLettersByIndex = new HashMap<>();

    //constructor
    public Cipher(Path keyFilePath) {
        //keyFileName = keyFileNameInput;
        //keyFile = new File(this.keyFileName);
        //this.keyFile = keyFile;
        keyFile = keyFilePath.toFile();

        //resetting if this object was used before
        normalLetters = "";
        cipheredLetters = "";
        linesOfKeyFile.clear();
        keyLettersByIndex.clear();

        //build the Cipher object so that it's ready for deciphering
        this.getCipherLines();
        this.createHashMap();
    }

    //methods

    //method to get the lines from the key file
    public void getCipherLines() throws IllegalArgumentException {

        try
        {
            Scanner scan = new Scanner(keyFile);

            while (scan.hasNextLine())
            {
                linesOfKeyFile.add(scan.nextLine());
            }

            //validating the key: making sure there are two lines
            if(linesOfKeyFile.size() != 2) {
                throw new IllegalArgumentException("There are more or less than 2 lines");
            }
            else
            {
                normalLetters = linesOfKeyFile.get(0);
                cipheredLetters = linesOfKeyFile.get(1);
            }

            scan.close();
        }
        catch (FileNotFoundException e)
        {
            throw new IllegalArgumentException("Cipher file not found");
        }

        //validating the key: making sure the two lines are equal (one to one)
        if(normalLetters.length() != cipheredLetters.length()) {
            throw new IllegalArgumentException("The lines in the key do not match length");
        }

        //validating the key: making sure no letter gets mapped to two different places
        if(cipheredLetters.length() != cipheredLetters.chars().distinct().count()) {
            throw new IllegalArgumentException("One letter is being mapped/ciphered to more than one letter");
        }

    }

    //method to create HashMap
    public void createHashMap() {
        for(int i = 0; i < normalLetters.length(); i++) {
            keyLettersByIndex.put(cipheredLetters.charAt(i), normalLetters.charAt(i));
        }
    }

    //method to finally decipher an inputted String using the HashMap
    public String decipherStringInput(String input) {
        StringBuilder decipheredString = new StringBuilder();

        for(int i = 0; i < input.length(); i++)
        {
            if(keyLettersByIndex.containsKey(input.charAt(i)))
            {
                decipheredString.append(keyLettersByIndex.get(input.charAt(i)));
            }
            else
            {
                decipheredString.append(input.charAt(i));
            }
        }

        return decipheredString.toString();
    }

}
