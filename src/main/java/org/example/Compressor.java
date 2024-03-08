package org.example;

import java.io.*;

public class Compressor {
    private static char zero = '0';
    private static char one = '1';

    private static int writer = 0b00000000;

    private static int counter = 3;
    private static int filled;

    public static String treeToString(HuffTree root) {
        Node node = root.getRoot();
        StringBuilder sTree = new StringBuilder();
        String tree = treeToString(node, sTree);
        return tree;
    }

    private static String treeToString(Node node, StringBuilder tree) {
        if (node == null) {
            return tree.toString();
        }
        if (node.getLeft() != null) {
            tree.append(zero);
            treeToString(node.getLeft(), tree);
            treeToString(node.getRight(), tree);
        } else {
            tree.append(one);
            tree.append((char) node.getKey());
        }
        return tree.toString();
    }

    private static void isFilled(RandomAccessFile output) {
        if (counter % 8 == 0) {
            try {
                counter = 0;
                output.write(writer);
                writer = 0b00000000;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void treeToFile(String tree, RandomAccessFile output) throws IOException {
        for (int i = 0; i < tree.length(); i++) {
            if (tree.charAt(i) == one) {
                writer = writer << 1;
                writer += 1;
                counter++;
                isFilled(output);
                i++;
                for (int j = 7; j >= 0; j--) {
                    int bit = (tree.charAt(i) >> j) & 1;
                    writer = writer << 1;
                    if (bit == 1) {
                        writer += 1;
                    }
                    counter++;
                    isFilled(output);
                }
            } else if (tree.charAt(i) == zero) {
                writer = writer << 1;
                counter++;
                isFilled(output);
            }
        }

    }

    public static void compressedFile(RandomAccessFile output, String filePath, String[] dictionary) {
        try (FileReader reader = new FileReader(filePath)) {
            int charCode;
            while ((charCode = reader.read()) != -1) {
                String code = dictionary[charCode];
                for (int i = 0; i < code.length(); i++) {
                    writer = writer << 1;
                    if (code.charAt(i) == one) {
                        writer += 1;
                    }
                    counter++;
                    isFilled(output);
                }
            }
            if (counter % 8 != 0) {
                filled = 8 - counter;
                while (counter != 8) {
                    writer = writer << 1;
                    counter++;
                }
                output.write(writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moveToBeginning(RandomAccessFile output) {
        try {
            output.seek(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFilled(RandomAccessFile output) {
        try {
            int firstByte = output.readByte();
            filled = filled << 5;
            firstByte = firstByte + filled;
            moveToBeginning(output);
            output.write(firstByte);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}