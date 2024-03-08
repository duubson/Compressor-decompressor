package org.example;

public class Heap {
    private Node[] heapArray;
    private int size;

    public Heap(int maxSize) {
        this.size = 0;
        this.heapArray = new Node[maxSize];
    }

    public void insert(Node node) {
        heapArray[size] = node;
        heapUp(size);
        size++;
    }

    public Node extractMin() {
        if (size == 0) {
            System.out.println("Kopiec pusty");
            return null;
        }

        Node root = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        size--;
        heapDown(0);

        return root;
    }

    public void heapUp(int index) {
        int parent = (index - 1) / 2;

        while (index > 0 && heapArray[index].compareTo(heapArray[parent]) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    public void heapDown(int index) {
        int left;
        int right;
        int smallestChild;

        while (true) {
            left = 2 * index + 1;
            right = 2 * index + 2;
            smallestChild = index;

            if (left < size && heapArray[left].compareTo(heapArray[smallestChild]) < 0) {
                smallestChild = left;
            }

            if (right < size && heapArray[right].compareTo(heapArray[smallestChild]) < 0) {
                smallestChild = right;
            }

            if (smallestChild == index) {
                break;
            }

            swap(index, smallestChild);
            index = smallestChild;
        }
    }

    public void swap(int i, int j) {
        Node temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    public int getSize() {
        return this.size;
    }
}