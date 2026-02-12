//Ali Khokhar - Team 1-1-1
//Team Member D

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {

    //helper method to create test files
    private void createTestFiles(String fileName, String line1, String line2) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(line1);
        writer.write("\n" + line2);
        writer.close();
    }
    //helper method to create test files with only one line
    private void createTestFiles(String fileName, String line1) throws IOException {
        FileWriter writer = new FileWriter(fileName);
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

        Cipher c = new Cipher("testfile1.txt");

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

        assertThrows(IllegalArgumentException.class, () -> {new Cipher("testfile2.txt");}, "Should throw illegal argument error");
    }

    @Test
    void getCipherLinesMissingSecondLine() {
        try {
            createTestFiles("testfile3.txt", "ABC");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        assertThrows(IllegalArgumentException.class, () -> {new Cipher("testfile3.txt");}, "Should throw illegal argument error");
    }

    @Test
    void getCipherLinesDuplicateCipherLetters() {
        try {
            createTestFiles("testfile4.txt", "ABC", "CBB");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        assertThrows(IllegalArgumentException.class, () -> {new Cipher("testfile4.txt");}, "Should throw illegal argument error");
    }

    @Test
    void getCipherLinesFileNotFound() {
        try {
            createTestFiles("testfile4.txt", "ABC", "CBB");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        assertThrows(IllegalArgumentException.class, () -> {new Cipher("fileNotFound.txt");}, "Should throw illegal argument error");
    }


    //tests for the createHashMap method
    @Test
    void createHashMapTypicalUsage() {
        try {
            createTestFiles("testfile5.txt", "ABC", "CBA");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Cipher c = new Cipher("testfile5.txt");

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
            createTestFiles("testfile6.txt", "ABC", "CBA");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Cipher c = new Cipher("testfile6.txt");

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
            createTestFiles("testfile7.txt", "NOO", "YES");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

            Cipher c = new Cipher("testfile7.txt");

            assertEquals("NOO", c.decipherStringInput("YES"), "Should output NOO");
    }

    @Test
    void decipherStringInputWithSpaces() {
        try {
            createTestFiles("testfile7.txt", "NOO", "YES");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        Cipher c = new Cipher("testfile7.txt");

        assertEquals("NOO ONOO", c.decipherStringInput("YES EYES"), "Should output NOO");
    }

}