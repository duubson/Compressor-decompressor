package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileReadTest {
    @Test
    public void testReadFileCharByChar() {
        String fileIn = "testFile.txt";
        int[] frequency = new int[256];

        createTestFile(fileIn, "abc");

        FileRead.readFileCharByChar(fileIn, frequency);
        assertEquals(1, frequency['a']);
        assertEquals(1, frequency['b']);
        assertEquals(1, frequency['c']);
    }

    @Test
    public void testOutOfBounds() {
        String fileIn = "testFile.txt";
        int[] frequency = new int[16];

        createTestFile(fileIn, "abc");
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                FileRead.readFileCharByChar(fileIn, frequency));
    }

    private void createTestFile(String filePath, String text) {
        try (FileWriter writer = new FileWriter(filePath);) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}