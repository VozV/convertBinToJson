package File;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    public static void writeStringToFile(String fileName, String str) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();
    }
}
