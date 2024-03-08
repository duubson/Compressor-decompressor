package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CompressorTest {

    @Test
    public void testTreeToString() {
        Heap heap = new Heap(5);
        heap.insert(new Node(97, 2));
        heap.insert(new Node(98, 6));
        heap.insert(new Node(99, 9));

        HuffTree huffTree = new HuffTree(heap);
        String tree = Compressor.treeToString(huffTree);
        assertEquals("001a1b1c", tree);
    }

    @Test
    public void testMove() throws IOException {
        String fileIn = "test.txt";
        createTestFile(fileIn, "abc");

        RandomAccessFile file = new RandomAccessFile(fileIn, "rw");
        int firstByte = file.readByte();
        Compressor.moveToBeginning(file);
        int firstByteAfterMove = file.readByte();

        assertEquals(firstByte, firstByteAfterMove);
    }

    private void createTestFile(String filePath, String text) {
        try (FileWriter writer = new FileWriter(filePath);) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}