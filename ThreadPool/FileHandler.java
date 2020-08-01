package Task.ThreadPool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements Runnable {

    private File fileName;
    private int startIndex;
    private int endIndex;

    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        System.out.println("------------  " + Thread.currentThread().getName());
        try {
            FileWriter fw = new FileWriter(this.getFileName(), true);
            for (int i = this.getStartIndex(); i <= this.getEndIndex(); ++i) {
                fw.write(i + "  " + Util.getRandomString() + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println("An error occurred.");
            ioe.printStackTrace();
        }
    }
}