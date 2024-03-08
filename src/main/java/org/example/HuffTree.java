package org.example;

public class HuffTree {
    private Node root;
    private int toSkip;
    private char zero = '0';
    private char one = '1';

    public HuffTree(Heap heap) {
        this.root = makeHuffTree(heap);
    }

    public HuffTree(String tree) {
        Node root = new Node(-1, 0);
        this.toSkip = makeHuffTree(tree, root, 3);
        this.root = root;
    }

    public int getSkip() {
        return this.toSkip;
    }

    public Node getRoot() {
        return this.root;
    }

    public int makeHuffTree(String tree, Node root, int index) {
        char currChar = tree.charAt(index);
        if (currChar == zero) {
            root.setLeft(new Node(-1, 0));
            root.setRight(new Node(-1, 0));
            index = makeHuffTree(tree, root.getLeft(), index + 1);
            index = makeHuffTree(tree, root.getRight(), index);
        } else if (currChar == one) {
            String key = tree.substring(index + 1, index + 9);
            index = index + 9;
            int parsedKey = Integer.parseInt(key, 2);
            root.setKey(parsedKey);
            return index;
        }
        return index;
    }

    public Node makeHuffTree(Heap heap) {
        while (heap.getSize() > 1) {
            Node min1 = heap.extractMin();
            Node min2 = heap.extractMin();
            Node newNode = new Node(-1, min1.getFrequency() + min2.getFrequency(), min1, min2);
            heap.insert(newNode);
        }
        return heap.extractMin();
    }

    private void makeCodes(Node node, String code, char num, String[] codes) {
        String newCode;
        if (code == null) {
            newCode = String.valueOf(num);
        } else {
            newCode = code + num;
        }
        if (node.getLeft() == null) {
            codes[node.getKey()] = newCode;
        } else {
            makeCodes(node.getLeft(), newCode, zero, codes);
            makeCodes(node.getRight(), newCode, one, codes);
        }
    }

    public void makeCodes(String[] codes) {
        makeCodes(root.getLeft(), null, zero, codes);
        makeCodes(root.getRight(), null, one, codes);
    }
}
