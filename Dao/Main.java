package Task.Dao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DbConnection db = DbConnection.getInstance();
        Scanner scan = new Scanner(System.in);
        boolean isEnd = false;
        do {
            db.insert("alex", 22);
            System.out.println("Do you want another insertion ?");
            System.out.println("1 ) Yes");
            System.out.println("2 ) No");
            System.out.println("Enter your option - ");
            int option = scan.nextInt();
            if (option == 1)
                isEnd = false;
            else
                isEnd = true;
        } while (!isEnd);
    }
}
