//Ali Khokhar - Team 1-1-1
//Team Member D

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {

    Path dirPath = FileSystems.getDefault().getPath("src", "test", "cipher_test_files");
    //helper method to create test files
    private void createTestFiles(String fileName, String line1, String line2) throws IOException {
        Path path = dirPath.resolve(fileName);
        Files.createFile(path);
        FileWriter writer = new FileWriter(path.toFile());
        writer.write(line1);
        writer.write("\n" + line2);
        writer.close();
    }
    //helper method to create test files with only one line
    private void createTestFiles(String fileName, String line1) throws IOException {
        Path path = dirPath.resolve(fileName);
        Files.createFile(path);
        FileWriter writer = new FileWriter(path.toFile());
        writer.write(line1);
        writer.close();
    }

    //Tests for the method getCipherLines
    @Test
    void getCipherLinesTypicalUsage() {
        try {
            createTestFiles("testfile1.txt", "ABC", "CBA");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        //Creating a path object variable
        Path path = dirPath.resolve("testfile1.txt");
        Cipher c = new Cipher(path);

        assertEquals('A', c.keyLettersByIndex.get('C'), "Should output A as the matching letter for C");
    }

    @Test
    void getCipherLinesShortSecondLine() {
        try {
            createTestFiles("testfile2.txt", "ABC", "CB");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Path path = dirPath.resolve("testfile2.txt");
        assertThrows(IllegalArgumentException.class, () -> {new Cipher(path);}, "Should throw illegal argument error");
    }

    @Test
    void getCipherLinesMissingSecondLine() {
        try {
            createTestFiles("testfile3.txt", "ABC");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Path path = dirPath.resolve("testfile3.txt");
        assertThrows(IllegalArgumentException.class, () -> {new Cipher(path);}, "Should throw illegal argument error");
    }

    @Test
    void getCipherLinesDuplicateCipherLetters() {
        try {
            createTestFiles("testfile4.txt", "ABC", "CBB");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Path path = dirPath.resolve("testfile4.txt");
        assertThrows(IllegalArgumentException.class, () -> {new Cipher(path);}, "Should throw illegal argument error");
    }

    @Test
    void getCipherLinesFileNotFound() {
        try {
            createTestFiles("testfile5.txt", "ABC", "CBB");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Path path = dirPath.resolve("testfile5.txt");
        assertThrows(IllegalArgumentException.class, () -> {new Cipher(path);}, "Should throw illegal argument error");
    }


    //tests for the createHashMap method
    @Test
    void createHashMapTypicalUsage() {
        try {
            createTestFiles("testfile6.txt", "ABC", "CBA");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Path path = dirPath.resolve("testfile6.txt");
        Cipher c = new Cipher(path);

        assertTrue(c.keyLettersByIndex.containsValue('A'), "Should say True");
        assertTrue(c.keyLettersByIndex.containsValue('B'), "Should say True");
        assertTrue(c.keyLettersByIndex.containsValue('C'), "Should say True");
        assertTrue(c.keyLettersByIndex.containsValue('C'), "Should say True");
        assertTrue(c.keyLettersByIndex.containsValue('B'), "Should say True");
        assertTrue(c.keyLettersByIndex.containsValue('A'), "Should say True");
    }

    @Test
    void createHashMapWrong() {
        try {
            createTestFiles("testfile7.txt", "ABC", "CBA");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Path path = dirPath.resolve("testfile7.txt");
        Cipher c = new Cipher(path);

        assertFalse(c.keyLettersByIndex.containsValue('D'), "Should say True");
        assertFalse(c.keyLettersByIndex.containsValue('E'), "Should say True");
        assertFalse(c.keyLettersByIndex.containsValue('F'), "Should say True");
        assertFalse(c.keyLettersByIndex.containsValue('F'), "Should say True");
        assertFalse(c.keyLettersByIndex.containsValue('E'), "Should say True");
        assertFalse(c.keyLettersByIndex.containsValue('G'), "Should say True");
    }

    //Tests for deciphering method
    @Test
    void decipherStringInputTypicalUsage() {
        try {
            createTestFiles("testfile8.txt", "NOO", "YES");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

            Path path = dirPath.resolve("testfile8.txt");
            Cipher c = new Cipher(path);

            assertEquals("NOO", c.decipherStringInput("YES"), "Should output NOO");
    }

    @Test
    void decipherStringInputWithSpaces() {
        try {
            createTestFiles("testfile9.txt", "NOO", "YES");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Path path = dirPath.resolve("testfile9.txt");
        Cipher c = new Cipher(path);

        assertEquals("NOO ONOO", c.decipherStringInput("YES EYES"), "Should output NOO");
    }

}