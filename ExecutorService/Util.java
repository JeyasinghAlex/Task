package Task.ExecutorService;

import java.util.Random;

public class Util {

    public static String getRandomString() {
        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 10) {
            int index = (int) (rnd.nextFloat() * randomString.length());
            str.append(randomString.charAt(index));
        }
        return str.toString();
    }
}
