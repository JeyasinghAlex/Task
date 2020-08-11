import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        TimeHandler handler = new TimeHandler();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the time - ");
        String time = scan.nextLine();
        String ans = "";
//        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(time);
//        boolean isFind = matcher.find();
        if (time.contains(":")) {
            ans = handler.splitByColon(time);
        } else if (time.contains(".")) {
            ans = handler.splitByDot(time);
        } else {
            ans = handler.getHours(time);
        }
        System.out.println(ans);
    }
}