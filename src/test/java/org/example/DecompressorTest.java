package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.junit.jupiter.api.Assertions.*;

class DecompressorTest {
    @Test
    public void testCheckFilled() throws IOException {
        String fileIn = "testFile.txt";
        createTestFile(fileIn, "abc");

        RandomAccessFile file = new RandomAccessFile(fileIn, "rw");

        int filled = Decompressor.checkFilled(file);

        assertEquals(3, filled);
    }

    @Test
    public void testReadFile() throws IOException {
        String fileIn = "test.txt";
        createTestFile(fileIn, "abc");

        RandomAccessFile file = new RandomAccessFile(fileIn, "rw");

        String text = Decompressor.readFile(file);

        assertEquals("011000010110001001100011", text);
    }

    private void createTestFile(String filePath, String text) {
        try (FileWriter writer = new FileWriter(filePath);) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}