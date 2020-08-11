import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class TimeHandler {

    private static Map<Integer, String> map;

    static {
        map = new HashMap<>();
        map.put(0, "zero");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
        map.put(13, "thirteen");
        map.put(14, "fourteen");
        map.put(15, "fifteen");
        map.put(16, "sixteen");
        map.put(17, "seventeen");
        map.put(18, "eighteen");
        map.put(19, "nineteen");
        map.put(20, "twenty");
        map.put(30, "thirty");
        map.put(40, "forty");
        map.put(50, "fifty");
    }

    public String splitByColon(String time) {
        String hours = "";
        String mins = "";
        String[] arr = time.split(":");
        if (!arr[0].isEmpty() && !isValid(arr[0])) {
            return "Invalid hours";
        }
        if (!arr[0].isEmpty()) {
            hours += getTime(arr[0]);
        }
        if (!arr[1].isEmpty()) {
            mins += getTime(arr[1]);
        }
        if (hours.isEmpty() && mins.isEmpty()) {
            return "Invalid hours";
        }
        return getResult(hours, mins);
    }

    public String splitByDot(String time) {
        String hours = "";
        String mins = "";
        String[] arr = time.split("\\.");
        if (!arr[0].isEmpty() && !isValid(arr[0])) {
            return "Invalid hours";
        }
        if (!arr[0].isEmpty()) {
            hours += getTime(arr[0]);
        }
        if (!arr[1].isEmpty()) {
            float a = Float.parseFloat("0." + arr[1]) * 60;
            String str = (int) a + "";
            mins += getTime(str);
        }
        if (hours.isEmpty() && mins.isEmpty()) {
            return " Invalid hours";
        }
        return getResult(hours, mins);
    }

    private String getResult(String hours, String mins) {
        String ans = "";
        if (!hours.isEmpty()) {
            ans = hours + " hours";
        }
        if (!mins.isEmpty()) {
            ans = mins + " mins";
        }
        if (!hours.isEmpty() && !mins.isEmpty()) {
            ans = hours + " hours and " + mins + " mins";
        }
        return ans;
    }

    public String getHours(String time) {
        String ans = "";
        ans = getTime(time);
        if (!ans.isEmpty() && !isValid(time)) {
            return "Invalid Time";
        }
        return getTime(time) + " hours";
    }

    private boolean isValid(String time) {
        int num = Integer.parseInt(time);
        if (num >= 24) {
            return false;
        }
        return true;
    }

    private String getTime(String time) {
        String t = "";
        int num = Integer.parseInt(time);
        if (num == 0) {
            return "";
        }
        if (num <= 20) {
            return map.get(num);
        }
        int a = num / 10;
        t = map.get(a * 10) + " ";
        a = num % 10;
        if (a != 0) {
            t += map.get(a);
        }
        return t;
    }
}