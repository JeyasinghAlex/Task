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
        int minutes = 0;
        String[] arr = time.split(":");
        if (!arr[0].isEmpty()) {
            minutes += Integer.parseInt(arr[0]) * 60;
        }
        if (!arr[1].isEmpty()) {
            minutes += Integer.parseInt(arr[1]);
        }
        return getResult(minutes);
    }

    public String splitByDot(String time) {
        int minutes = 0;
        float ti = Float.parseFloat(time);
        minutes = (int) (ti * 60);
        return getResult(minutes);
    }

    private String getResult(int minutes) {
        int m = minutes % 60;
        int h = minutes / 60;
        if (h >= 24) {
            return "Invalid Time";
        }
        String hours = getTime(h);
        String mins = getTime(m);
        return getResult(hours, mins);
    }

    private String getTime(int num) {
        String t = "";
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

    private String getResult(String hours, String mins) {
        if (!hours.isEmpty() && !mins.isEmpty()) {
            return hours + " hours and " + mins + " mins";
        } else if (!hours.isEmpty()) {
            return hours + " hours";
        } else if (!mins.isEmpty()) {
            return mins + " mints";
        }
        return "Invalid Time";
    }
}