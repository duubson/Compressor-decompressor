package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Decompressor {
    private static char zero = '0';
    private static char one = '1';
    private static int reader;

    public static int checkFilled(RandomAccessFile output) throws IOException {
        int reader = output.readByte();
        int filled = 0;
        filled += 4 * ((reader >> 7) & 1);
        filled += 2 * ((reader >> 6) & 1);
        filled += (reader >> 5) & 1;
        output.seek(0);
        return filled;
    }

    public static String readFile(RandomAccessFile output) throws IOException {
        int currBit;
        StringBuilder temp = new StringBuilder();
        while ((reader = output.read()) != -1) {
            for (int i = 7; i >= 0; i--) {
                currBit = (reader >> i) & 1;
                if (currBit == 1) {
                    temp.append(one);
                } else {
                    temp.append(zero);
                }
            }
        }
        return temp.toString();
    }

    public static void decompress(String file, String[] dictionary, RandomAccessFile decompressed, int skip, int filled) throws IOException {
        StringBuilder code = new StringBuilder();
        for (int i = skip; i < file.length() - filled; i++) {
            code.append(file.charAt(i));
            for (int j = 0; j < 256; j++) {
                if (dictionary[j] != null) {
                    if (dictionary[j].equals(code.toString())) {
                        decompressed.write((char) j);
                        code = new StringBuilder();
                    }
                }
            }
        }
        decompressed.close();
    }
}
