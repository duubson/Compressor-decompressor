# Compressor and decompressor
The project involves implementing a simple compressor and decompressor for text files using the Huffman algorithm and a priority queue based on a heap. The Huffman algorithm is used for efficient text encoding, and the priority queue on a heap is utilized to build the Huffman encoding tree. This solution allows for compressing text files, saving disk space, and later decompressing them. Compressor operates on files containing ASCII characters. The project includes unit tests.
## Program invocation

The program is invoked using the following command:
java -jar AiSD2023ZLab5.jar
The program requires three arguments:

### The first argument:
The flag -c if the program is to function as a compressor, or the flag -d if it is to function as a decompressor.
### The second argument:
The path to the input file.
### The third argument:
The path to the output file.
