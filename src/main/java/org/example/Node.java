package org.example;

public class Node implements Comparable<Node> {
    private int key;
    private int frequency;

    private Node left, right;

    public Node(int key, int frequency) {
        this.key = key;
        this.frequency = frequency;
    }

    public Node(int key, int frequency, Node left, Node right) {
        this.key = key;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {this.key = key; }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return this.frequency - o.getFrequency();
    }
}
