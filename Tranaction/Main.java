package Task.Tranaction;

import Task.Tranaction.enums.AccountType;
import Task.Tranaction.model.Account;
import Task.Tranaction.model.Branch;
import Task.Tranaction.model.Customer;
import Task.Tranaction.model.Transaction;

import java.util.List;
import java.util.Scanner;

public class Main {

    private Scanner scan = new Scanner(System.in);
    private Branch branch = new Branch();

    public static void main(String[] args)  {
        System.out.println("Welcome to bank website :- ");
        new Main().indexPage();
    }


    public void indexPage()  {
        boolean isEnd = true;
        do {
            System.out.println();
            System.out.println("1 ) User  - ");
            System.out.println("2 ) Admin - ");
            System.out.println(" Otherwise Exit program");
            System.out.print("Enter your option - ");
            int option = scan.nextInt();
            if (option == 1) {
                showUserOption();
            }else  if (option == 2) {
                showAdminOption();
            } else {
                isEnd = false;
            }
        } while (isEnd);
    }

    public void showAdminOption() {
        System.out.println();
        System.out.println("1 ) Search account by account number: ");
        System.out.println("2 ) Search account by pan number - ");
        System.out.println("3 ) See all accounts : ");
        System.out.print("Enter your option - ");
        int option = scan.nextInt();
        scan.nextLine();
        String accountNumber = "";
        switch (option) {
            case 1:
                System.out.print("Enter the account number - ");
                accountNumber = scan.nextLine();
                Account user = branch.getAccount(accountNumber);
                if (user != null) {
                    System.out.println(user.toString());
                } else {
                    System.out.println("Account not found ");
                }
                break;
            case 2:
                System.out.print("Enter the pan number - ");
                String panNumber = scan.nextLine();
                Customer customer = branch.getCustomerByPanNumber(panNumber);
                if (customer != null) {
                    System.out.println(customer.toString());
                } else {
                    System.out.println("Customer not found ");
                }
                break;
            case 3:
                List<Customer> customers = branch.getCustomers();
                for (Customer customer1: customers) {
                    System.out.println(customer1);
                }
                break;
            default:
                break;
        }
    }

    public void showUserOption()  {
        System.out.println();
        System.out.println("1 ) Create Account :- ");
        System.out.println("2 ) Balance Enquiry :- ");
        System.out.println("3 ) Withdraw :- ");
        System.out.println("4 ) Deposit :- ");
        System.out.println("5 ) Transfer :- ");
        System.out.println("6 ) Show mini statement :- ");
        System.out.println("7 ) Exit :- ");
        System.out.print("Please enter your  option - ");
        int option = scan.nextInt();
        scan.nextLine();
        String accountNumber = "";
        int amount = 0;
        switch (option) {
            case 1:
                System.out.print("Enter your panNumber :- ");
                String panNumber = scan.nextLine();
                String accountType = chooseAccountType();
                System.out.print("Enter your open amount - ");
                amount = scan.nextInt();
                accountNumber = branch.createAccount(panNumber, accountType, amount);
                System.out.print("Your account number is - " + accountNumber);
                break;
            case 2:
                System.out.print("Enter your account number - ");
                accountNumber = scan.nextLine();
                Account account = branch.getAccount(accountNumber);
                System.out.print("Your account is - " + account.getType() + " and your available balance is - " + account.getAvailableBalance());
                break;
            case 3:
                System.out.println("Enter your account number");
                accountNumber = scan.nextLine();
                Account account1 = branch.getAccount(accountNumber);
                System.out.print("Enter the withdraw amount - ");
                amount = scan.nextInt();
                if (account1 != null) {
                    account1.withdraw(amount);
                    System.out.print("available balance -" + account1.getAvailableBalance());
                } else {
                    System.out.println("Account not found");
                }
                break;
            case 4:
                System.out.print("Enter your account number - ");
                accountNumber = scan.nextLine();
                Account account2 = branch.getAccount(accountNumber);
                System.out.print("Enter the deposit amount - ");
                amount = scan.nextInt();
                if (account2 != null) {
                    account2.deposit(amount);
                    System.out.print("available balance -" + account2.getAvailableBalance());
                } else {
                    System.out.println("Account not found");
                }
                break;
            case 5:
                System.out.print("Enter the FROM account number - ");
                accountNumber = scan.nextLine();
                System.out.print("Enter the TO account number - ");
                String accNum = scan.nextLine();
                System.out.print("Enter the transfer amount - ");
                amount = scan.nextInt();
                Account acc = branch.getAccount(accountNumber);
                Account acc1 = branch.getAccount(accNum);
                if (acc != null && acc1 != null) {
                    acc.transfer(acc1, amount);
                } else {
                    System.out.println("Account not found ");
                }
                break;
            case 6:
                System.out.print("Enter the account number -  ");
                accountNumber = scan.nextLine();
                Account ac = branch.getAccount(accountNumber);
                List<Transaction> transactions = ac.getMiniStatement();
                for (Transaction tr : transactions) {
                    System.out.println(tr.toString());
                }
                break;
            default:
                System.out.println("Please enter the valid input :");
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
            switch (option) {
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
        } while (true);
    }
}