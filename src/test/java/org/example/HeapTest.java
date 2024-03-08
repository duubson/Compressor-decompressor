package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    @Test
    public void testInsert() {
        Heap heap = new Heap(5);
        assertEquals(0, heap.getSize());

        heap.insert(new Node(5, 5));
        assertEquals(1, heap.getSize());

        heap.insert(new Node(3, 3));
        assertEquals(2, heap.getSize());
    }

    @Test
    public void testExtractMin() {
        Heap heap = new Heap(5);
        assertEquals(null, heap.extractMin());

        heap.insert(new Node(3, 5));
        heap.insert(new Node(6, 3));
        heap.insert(new Node(5, 7));

        assertEquals(3, heap.extractMin().getFrequency());
        assertEquals(5, heap.extractMin().getFrequency());
        assertEquals(7, heap.extractMin().getFrequency());
    }
}