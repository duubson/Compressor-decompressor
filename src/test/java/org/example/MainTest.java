package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void testCompressionAndDecompression() {
        String inputFile = "inputFile.txt";
        String compressedFile = "compressedFile";
        String decompressedFile = "decompressedFile.txt";

        createTestFile(inputFile, "Plik testowy na kompresje i dekompresje");

        String[] compress = {"-c", inputFile, compressedFile};
        String[] decompress = {"-d", compressedFile, decompressedFile};

        Main.main(compress);
        Main.main(decompress);

        String expected = readFile(inputFile);
        String decompressed = readFile(decompressedFile);

        assertEquals(expected, decompressed);
    }

    private void createTestFile(String filePath, String text) {
        try (FileWriter writer = new FileWriter(filePath);) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile(String file) {
        BufferedReader reader;
        StringBuilder text = new StringBuilder();
        String line;
        try {
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            while (line != null) {
                text.append(line);
                line = reader.readLine();
            }
            return text.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}