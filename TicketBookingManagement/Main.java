package Task.TicketBookingManagement;

import Task.TicketBookingManagement.BankException.BankException;
import Task.TicketBookingManagement.Enum.AccountType;
import Task.TicketBookingManagement.Model.Account;
import Task.TicketBookingManagement.Model.Branch;

import java.util.Scanner;

public class Main {

    private Scanner scan = new Scanner(System.in);
    private Branch branch = new Branch();

    public static void main(String[] args) {
        System.out.println("Welcome to bank website :- ");
        new Main().indexPage();
    }


    public void indexPage() {
        System.out.println("1 ) User  - ");
        System.out.println("2 ) Admin - ");
    }

    public void showUserOption() throws BankException {
        System.out.println("1 ) Create Account :- ");
        System.out.println("2 ) Balance Enquiry :- ");
        System.out.println("3 ) Withdraw :- ");
        System.out.println("4 ) Deposit :- ");
        System.out.println("5 ) Transfer :- ");
        System.out.println("6 ) Exit :- ");
        System.out.print("Please enter your  option - ");
        int option = scan.nextInt();
        switch(option)  {
            case 1:
                System.out.println("Enter your adharNumber :- ");
                String adharNumber = scan.nextLine();
                String accountType = chooseAccountType();
                int amount = scan.nextInt();
                String accountNumber = branch.createAccount(adharNumber, accountType, amount);
                System.out.println("Your account number is - " + accountNumber);
                break;
            case 2:
                System.out.println("Enter your account number");
                String acNumber = scan.nextLine();
                Account account = branch.getAccount(acNumber);
                System.out.println("Your account is - " + account.getType() + "Your available balance is - " + account.getAvailableBalance());
                break;
            case 3:
                System.out.println("Enter your account number");
                String accNumber = scan.nextLine();
                Account account1 = branch.getAccount(accNumber);
                System.out.println("Enter the withdraw amount - ");
                int amt = scan.nextInt();
                account1.withdraw(amt);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
        }
        indexPage();
    }

    private String chooseAccountType() {
        boolean isEnd = true;
        do {
            System.out.println("1 ) Saving account :");
            System.out.println("2 ) Join account :");
            System.out.println("3 ) Business account :");
            System.out.print("Enter your option - ");
            int option = scan.nextInt();
            switch(option) {
                case 1:
                    return AccountType.SAVING.toString();
                case 2:
                    return AccountType.JOIN.toString();
                case 3:
                    return AccountType.BUSINESS.toString();
                default:
                    System.out.println("Please enter valid option :");
                    break;
            }
        }while(true);
    }
}