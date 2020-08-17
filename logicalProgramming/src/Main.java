import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TimeHandler handler = new TimeHandler();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the time - ");
        String time = scan.nextLine();
        String ans = "";

        if (time.contains(":")) {
            ans = handler.splitByColon(time);
        } else {
            ans = handler.splitByDot(time);
        }
        System.out.println(ans);
    }
}