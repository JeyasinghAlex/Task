package Task.ThreadPool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements Runnable {

    private File file;
    private int startIndex;
    private int endIndex;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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
            if(this.getFile().isFile()){
                this.getFile().delete();
            }
            this.getFile().createNewFile();
            FileWriter fw = new FileWriter(this.getFile(), true);
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