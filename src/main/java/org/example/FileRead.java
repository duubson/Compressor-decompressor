package org.example;

import java.io.FileReader;
import java.io.IOException;

public class FileRead {
    public static void readFileCharByChar(String filePath, int[] Array) {
        try (FileReader reader = new FileReader(filePath)) {
            int charCode;

            while ((charCode = reader.read()) != -1) {
                if (charCode < 0 || charCode >= Array.length) {
                    throw new ArrayIndexOutOfBoundsException("W pliku występuje znak spoza ASCII: " + charCode);
                }
                Array[charCode]++;
            }
        } catch (IOException e) {
            System.err.println("Błąd otworzenia pliku dla kompresji: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
