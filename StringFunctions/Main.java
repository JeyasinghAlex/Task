package Task.StringFunctions;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        chooseOperations();
    }

    private static void chooseOperations() {
        Scanner scan = new Scanner(System.in);
//            System.out.println("Enter the String1 :- ");
//            String str = scan.nextLine();
//            System.out.println("Enter the String2 :- ");
//            String pattern = scan.nextLine();
        String str = "Hi this is test @Test test #Test test";
        String pattern = "tester";
        StringOperations string = new StringOperations(str, pattern);
        boolean end = true;
        do {
            System.out.println("\n*-----------------------------------------------------------------------*");
            System.out.println("1 ) Find Number of Occurrence : -");
            System.out.println("2 ) String Reverse :- ");
            System.out.println("3 ) String Replace :- ");
            System.out.println("4 ) String Remove :- ");
            System.out.println("5 ) String Append :- ");
            System.out.println("6 ) Exit :- ");
            System.out.println();
            System.out.print("Enter your option - ");
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    List<Integer> list = string.getNumberOfPatternCount();
                    if (list != null) {
                        System.out.println("Number of matching pattern - " + list.size());
                        for (int i = 0; i < list.size(); ++i) {
                            System.out.println("[" + list.get(i) + ", " + (list.get(i) + pattern.length() - 1) + "]");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Please enter the position to reverse :- ");
                    int revPosition = scan.nextInt();
                    String s = string.reverse(revPosition);
                    System.out.println(s);
                    break;
                case 3:
                    System.out.print("Please enter the replace position - ");
                    int rePosition = scan.nextInt();
                    System.out.print("Please enter the replace string - ");
                    scan.nextLine();
                    String reStr = scan.nextLine();
                    String s1 = string.replace(rePosition, reStr);
                    System.out.println(s1);
                    break;
                case 4:
                    System.out.print("Please enter the remove position - ");
                    String s3 = string.remove(scan.nextInt());
                    System.out.println(s3);
                    break;
                case 5:
                    System.out.print("Please enter the append index - ");
                    int apdIndex = scan.nextInt();
                    System.out.print("Please enter the append string - ");
                    scan.nextLine();
                    String apdStr = scan.nextLine();
                    String s4 = string.append(apdIndex, apdStr);
                    System.out.println(s4);
                    break;
                case 6:
                    end = false;
                default:
                    System.out.println("Please enter the correct option :- ");
                    break;
            }
        } while (end);
    }
}
