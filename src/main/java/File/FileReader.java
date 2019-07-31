package File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public static byte[] ReadByteFile(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(fileName));
    }
}
