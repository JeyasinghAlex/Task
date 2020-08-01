package Task.ThreadPool;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of threads - ");
        int numberOfThreads = scan.nextInt();
        System.out.print("Enter the number of files - ");
        int numberOfFiles = scan.nextInt();
        System.out.print("Enter the number of students - ");
        int numberOfStudents = scan.nextInt();
        createFile(numberOfThreads, numberOfFiles, numberOfStudents);
    }

    private static void createFile(int threads, int files, int students) {

        int index = 0;
        int limits = students / files;
        LinkedBlockingQueue<FileHandler> queue = new LinkedBlockingQueue<>();

        for (int i = 1; i <= files && students > 0; ++i) {
            FileHandler handler = new FileHandler();
            File file = new File("filename" + i + ".txt");
            handler.setFile(file);
            handler.setStartIndex(index + 1);
            if (files == i) {
                handler.setEndIndex(index + students);
            } else if (students >= limits && limits != 0) {
                handler.setEndIndex(limits * i);
            } else {
                handler.setEndIndex(students);
                limits = students;
            }
            queue.add(handler);

            index = limits * i;
            students = students - limits;
        }
        executeThread(threads, queue);
    }

    private static void executeThread(int threads, LinkedBlockingQueue<FileHandler> queue) {
        ThreadPool pool = new ThreadPool(threads, queue);
        pool.execute();
    }
}
