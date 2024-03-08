package org.example;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Bledne wywolanie programu. Wywolaj go w ten sposob: java Main <compression_flag> <input_file> <output_file>");
            System.exit(1);
        }

        String compression = args[0];
        String fileIn = args[1];
        String fileOut = args[2];

        if (compression.equals("-c")) {
            OptionHandler.compressFile(fileIn, fileOut);
        } else if (compression.equals("-d")) {
            OptionHandler.decompressFile(fileIn, fileOut);
        } else {
            System.err.println("Nieprawidlowa flaga. Uzyj -c aby skompresowac plik lub -d aby go zdekompresowac.");
            System.exit(1);
        }

    }
}