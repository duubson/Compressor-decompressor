package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;

public class OptionHandler {
    private static final int SIZE = 256;

    public static void compressFile(String fileIn, String fileOut) {
        int[] frequency = new int[SIZE];
        FileRead.readFileCharByChar(fileIn, frequency);

        Heap heap = new Heap(SIZE);
        for (int i = 0; i < SIZE; i++) {
            if (frequency[i] != 0) {
                Node tmp = new Node(i, frequency[i]);
                heap.insert(tmp);
            }
        }

        HuffTree root = new HuffTree(heap);

        String[] codes = new String[SIZE];
        root.makeCodes(codes);

        String treeAsString = Compressor.treeToString(root);
        try {
            RandomAccessFile output = new RandomAccessFile(fileOut, "rw");
            Compressor.treeToFile(treeAsString, output);
            Compressor.compressedFile(output, fileIn, codes);
            Compressor.moveToBeginning(output);
            Compressor.setFilled(output);
        } catch (IOException e) {
            handleIOException("kompresji", e);
        }
    }

    public static void decompressFile(String fileIn, String fileOut) {
        String treeDict;
        int filled;
        try {
            RandomAccessFile input = new RandomAccessFile(fileIn, "r");
            filled = Decompressor.checkFilled(input);
            treeDict = Decompressor.readFile(input);
        } catch (IOException e) {
            handleIOException("dekompresji", e);
            return;
        }

        HuffTree rootDec = new HuffTree(treeDict);
        int skip = rootDec.getSkip();

        String[] codesNew = new String[SIZE];
        rootDec.makeCodes(codesNew);

        try {
            RandomAccessFile decompressed = new RandomAccessFile(fileOut, "rw");
            Decompressor.decompress(treeDict, codesNew, decompressed, skip, filled);
        } catch (IOException e) {
            handleIOException("dekompresji", e);
        }
    }

    private static void handleIOException(String operation, IOException e) {
        System.err.println("Błąd otworzenia pliku dla " + operation + ": " + e.getMessage());
        throw new RuntimeException(e);
    }
}
