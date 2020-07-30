import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadFileLast {
    public static void main(String[] args) {

        String fileName = args[0];
        String lastLines = args[1];

        File tmpFile = new File(fileName);
        ReadFileLast rf = new ReadFileLast();
        // calling method
        rf.readFromLast(tmpFile, Integer.parseInt(lastLines));
        // rf.reverseLines(file);
    }

    // Read last line of the file
    public void readFromLast(File file, int lastLines) {
        int readLines = 0;
        StringBuilder builder = new StringBuilder();
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            long fileLength = file.length() - 1;
            // Set the pointer at the last of the file
            randomAccessFile.seek(fileLength);
            for (long pointer = fileLength; pointer >= 0; pointer--) {
                randomAccessFile.seek(pointer);
                char c;
                // read from the last one char at the time
                c = (char) randomAccessFile.read();
                // break when end of the line
                if (c == '\n') {
                    readLines++;
                    if (readLines == lastLines)
                        break;
                }
                builder.append(c);
            }
            // Since line is read from the last so it
            // is in reverse so use reverse method to make it right
            builder.reverse();
            System.out.println("the last " + lastLines + " Line is:\n " + builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
