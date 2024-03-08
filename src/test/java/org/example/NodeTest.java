package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    public void testNodesCreation() {
        Node nodeLeft = new Node(1, 10);
        Node nodeRight = new Node(3, 7);
        Node nodeParent = new Node(4, 12, nodeLeft, nodeRight);

        assertEquals(1, nodeLeft.getKey());
        assertEquals(10, nodeLeft.getFrequency());
        assertNull(nodeLeft.getLeft());
        assertNull(nodeLeft.getRight());

        assertEquals(4, nodeParent.getKey());
        assertEquals(12, nodeParent.getFrequency());
        assertEquals(nodeLeft, nodeParent.getLeft());
        assertEquals(nodeRight, nodeParent.getRight());
    }

    @Test
    public void testNodesSetters() {
        Node nodeLeft = new Node(1, 10);
        Node nodeRight = new Node(3, 7);
        Node nodeParent = new Node(4, 12);

        nodeParent.setKey(6);
        nodeParent.setLeft(nodeLeft);
        nodeParent.setRight(nodeRight);
        assertEquals(6, nodeParent.getKey());
        assertEquals(nodeLeft, nodeParent.getLeft());
        assertEquals(nodeRight, nodeParent.getRight());
    }
}