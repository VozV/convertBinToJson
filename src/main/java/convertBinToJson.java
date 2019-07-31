import Parser.DataParser;
import Types.Json;

import java.io.*;
import File.FileWriter;
import File.FileReader;
import java.nio.file.Paths;


public class convertBinToJson {
    public static void main(String[] args) throws IOException {
        FileWriter.writeStringToFile(args[1], Json.toJson(new DataParser(FileReader.ReadByteFile(args[0])).parseData()));

    }
}
