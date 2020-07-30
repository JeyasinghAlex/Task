package Task.ExecutorService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        List<FileHandler> fileHandlers = new ArrayList<>();

        for (int i = 1; i <= files && students > 0; ++i) {
            FileHandler handler = new FileHandler();
            File file = new File("filename" + i + ".txt");
            handler.setFileName(file);
            handler.setStartIndex(index + 1);
            if (files == i) {
                handler.setEndIndex(index + students);
            } else if (students >= limits && limits != 0) {
                handler.setEndIndex(limits * i);
            } else {
                handler.setEndIndex(students);
                limits = students;
            }
            fileHandlers.add(handler);
            index = limits * i;
            students = students - limits;
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        executeThread(threads, fileHandlers);
    }

    private static void executeThread(int threads, List<FileHandler> fileHandlers) {

        ExecutorService service = Executors.newFixedThreadPool(threads);
        for (FileHandler handler : fileHandlers) {
            service.submit(handler);
        }
        service.shutdown();
    }
}
