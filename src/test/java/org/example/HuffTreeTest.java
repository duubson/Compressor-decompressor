package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuffTreeTest {

    @Test
    public void testMakeHuffTreeFromHeap() {
        Heap heap = new Heap(5);
        heap.insert(new Node(1, 1));
        heap.insert(new Node(2, 2));
        heap.insert(new Node(3, 3));

        HuffTree huffTree = new HuffTree(heap);
        assertNotNull(huffTree.getRoot());
        assertEquals(6, huffTree.getRoot().getFrequency());
    }

    @Test
    public void testMakeHuffTreeFromString() {
        String tree = "01001011000010010110110010110110101001000000101110000101110011";

        HuffTree huffTree = new HuffTree(tree);

        assertNotNull(huffTree.getRoot());
        assertEquals(0, huffTree.getRoot().getFrequency());
        assertEquals(62, huffTree.getSkip());
    }

    @Test
    public void testMakeCodes() {
        Heap heap = new Heap(5);
        heap.insert(new Node(1, 3));
        heap.insert(new Node(2, 2));
        heap.insert(new Node(3, 4));
        heap.insert(new Node(4, 5));
        heap.insert(new Node(5, 1));

        HuffTree huffTree = new HuffTree(heap);
        String[] codes = new String[256];
        huffTree.makeCodes(codes);

        assertNotNull(codes[1]);
        assertNotNull(codes[2]);
        assertNotNull(codes[3]);

        assertEquals("010", codes[5]);
        assertEquals("11", codes[4]);
        assertEquals("10", codes[3]);
        assertEquals("011", codes[2]);
        assertEquals("00", codes[1]);
    }
}