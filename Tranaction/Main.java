package Task.Tranaction;

import Task.Tranaction.enums.AccountType;
import Task.Tranaction.enums.TransactionType;
import Task.Tranaction.model.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    private Scanner scan = new Scanner(System.in);
    private Branch branch = new Branch();

    public static void main(String[] args) {
        System.out.println("Welcome to bank website :- ");
        new Main().indexPage();
    }


    public void indexPage() {
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
            } else if (option == 2) {
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
                for (Customer customer1 : customers) {
                    System.out.println(customer1);
                }
                break;
            default:
                break;
        }
    }

    public void showUserOption() {
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
        switch (option) {
            case 1:
                createAccount();
                break;
            case 2:
                balanceEnquiry();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                deposit();
                break;
            case 5:
                transfer();
                break;
            case 6:
                getMiniStatement();
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

    private String getAccountNumber() {
        System.out.print("Enter your account number - ");
        return scan.nextLine();
    }

    private void createAccount() {
        System.out.print("Enter your panNumber - ");
        String panNumber = scan.nextLine();
        String accountType = chooseAccountType();
        System.out.print("Enter your open amount - ");
        int amount = scan.nextInt();
        String accountNumber = branch.createAccount(panNumber, accountType, amount);
        System.out.print("=> Your account number is - " + accountNumber);
    }

    private void balanceEnquiry() {
        System.out.print("Enter your account number - ");
        String accountNumber = scan.nextLine();
        Account account = branch.getAccount(accountNumber);
        if (account != null) {
            System.out.print("Your account is - " + account.getType() + " and your available balance is - " + account.getAvailableBalance());
        } else {
            System.out.println("* -----  Account not found  ----- *");
        }
    }

    private void withdraw() {
        System.out.print("Enter your account number - ");
        String accountNumber = scan.nextLine();
        Account account1 = branch.getAccount(accountNumber);
        System.out.print("Enter the withdraw amount - ");
        int amount = scan.nextInt();
        if (account1 != null) {
            Transaction transaction = account1.withdraw(amount);
            if (transaction != null)
                System.out.print("Transaction receipt - " + transaction);
        } else {
            System.out.println("* -----  Account not found ----  *");
        }
    }

    private void deposit() {
        System.out.print("Enter your account number - ");
        String accountNumber = scan.nextLine();
        Account account2 = branch.getAccount(accountNumber);
        System.out.print("Enter the deposit amount - ");
        int amount = scan.nextInt();
        if (account2 != null) {
            Transaction transaction = account2.deposit(amount);
            if (transaction != null)
                System.out.print("Transaction receipt - " + transaction);
        } else {
            System.out.println("Account not found");
        }
    }

    private void getMiniStatement() {
        System.out.print("Enter the account number -  ");
        String accountNumber = scan.nextLine();
        Account ac = branch.getAccount(accountNumber);
        if (ac != null) {
            List<Entry> entries = ac.getMiniStatement();
            for (Entry tr : entries) {
                System.out.println(tr.toString());
            }
        } else {
            System.out.println("* -----  Account not found  ----- *");
        }
    }

    private void transfer() {
        System.out.println("1 ) Passbook transaction :- ");
        System.out.println("2 ) Google pay transaction :- ");
        System.out.print("Enter your option - ");
        int option = scan.nextInt();
        switch (option) {
            case 1:
                PassbookPaymentHandler paymentHandler = new PassbookPaymentHandler();
                makeTransfer(paymentHandler);
                break;
            case 2:
                GooglePayHandler googlePayHandler = new GooglePayHandler();
                makeTransfer(googlePayHandler);
                break;
            default:
                break;
        }
    }

    private void makeTransfer(PaymentHandler paymentHandler) {
        scan.nextLine();
        System.out.print("Enter the FROM account number - ");
        String acc1 = scan.nextLine();
        Account from = branch.getAccount(acc1);
        if (from == null) {
            System.out.println("* -----   Account not fount   ----- *");
            return;
        }
        System.out.print("Enter the TO account number - ");
        String acc2 = scan.nextLine();
        Account to = branch.getAccount(acc2);
        if (to == null) {
            System.out.println("* -----   Account not fount   ----- *");
            return;
        }
        System.out.print("Enter the transaction amount - ");
        int amount = scan.nextInt();
        Transaction transaction = new Transaction.Builder().from(from).to(to).amount(amount).type(TransactionType.GOOGLE_PAY).build();
//        paymentHandler.transact(from, to, amount);
        Entry entry = paymentHandler.transact(transaction);
        if (entry != null)
            System.out.println(entry);
    }
}