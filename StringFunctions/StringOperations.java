package Task.StringFunctions;

import java.util.ArrayList;
import java.util.List;

public class StringOperations {

    private String str;
    private String pattern;
    private List<Integer> patternIndexes;
    private static final String EMPTY_STATEMENT = "PATTERN NOT FOUND";

    public StringOperations(String str, String pattern) {
        this.str = str;
        this.pattern = pattern;
        updateMatchingPattern();
    }

    private void updateMatchingPattern() {
        if (pattern.length() == 0)
            return;

        int[] lps = computeLspTable(pattern);
        this.patternIndexes = new ArrayList<>();

        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            while (index > 0 && str.charAt(i) != pattern.charAt(index)) {
                index = lps[index - 1];
            }

            if (str.charAt(i) == pattern.charAt(index)) {
                index++;
            }

            if (index == pattern.length()) {
                int start = i - pattern.length() + 1;
                int end = i;
                patternIndexes.add(start);
                index = lps[index - 1];
            }
        }
    }

    private int[] computeLspTable(String pattern) {
        int[] lps = new int[pattern.length()];
        int prev = 0;
        int curr = 1;
        lps[0] = 0;

        while (curr < pattern.length()) {
            if (pattern.charAt(curr) == pattern.charAt(prev)) {
                prev++;
                lps[curr] = prev;
                curr++;
            } else {
                if (prev != 0) {
                    prev = lps[prev - 1];
                } else { // if (prev == 0)
                    lps[curr] = prev;
                    curr++;
                }
            }
        }
        return lps;
    }

    public String reverse(int position) {
        if (pattern.length() == 0 || patternIndexes.size() < position)
            return EMPTY_STATEMENT;

//        int start = patternIndexes.get(position - 1)[0];
//        int end = patternIndexes.get(position - 1)[1];
        int start = patternIndexes.get(position - 1);
        int end = patternIndexes.get(position - 1) + pattern.length() - 1;
        char[] strArray = str.toCharArray();
        char temp;
        while (start < end) {
            temp = strArray[start];
            strArray[start] = strArray[end];
            strArray[end] = temp;
            start++;
            end--;
        }
        str = String.valueOf(strArray);
        updateMatchingPattern();
        return str;
    }

    public String replace(int position, String subStr) {
        if (pattern.length() == 0 || patternIndexes.size() < position)
            return EMPTY_STATEMENT;

//        String start = getSubString(0, patternIndexes.get(position - 1)[0]);
//        String end = getSubString(patternIndexes.get(position - 1)[1] + 1, str.length());
        String start = getSubString(0, patternIndexes.get(position - 1));
        String end = getSubString(patternIndexes.get(position - 1) + pattern.length(), str.length());
        str = start + subStr + end;
        updateMatchingPattern();
        return str;
    }

    public String remove(int position) {
        if (pattern.length() == 0 || patternIndexes.size() < position)
            return EMPTY_STATEMENT;

//        String start = getSubString(0, patternIndexes.get(position - 1)[0]);
//        String end = getSubString(patternIndexes.get(position - 1)[1] + 1, str.length());
        String start = getSubString(0, patternIndexes.get(position - 1));
        String end = getSubString(patternIndexes.get(position - 1) + pattern.length(), str.length());
        str = start + end;
        updateMatchingPattern();
        return str;
    }

    public String append(int index, String subStr) {
        if (str.length() <= index || index < 0)
            return "Please select index is less then String length";

        String start = getSubString(0, index);
        String end = getSubString(index, str.length());
        str = start + subStr + end;
        updateMatchingPattern();
        return str;
    }

    public List<Integer> getNumberOfPatternCount() {
        updateMatchingPattern();
        return patternIndexes;
    }

    private String getSubString(int st, int ed) {
        String s = "";
        for (int i = st; i < ed; ++i) {
            s += str.charAt(i);
        }
        return s;
    }
}