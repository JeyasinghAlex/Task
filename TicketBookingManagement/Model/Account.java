package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.Enum.AccountType;

import java.util.List;

public abstract class Account {
    private String accountNumber;
    private int minimumBalance;
    private int availableBalance;
    private int interestRate;
    private List<Transaction> transactions;


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(int minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public int getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(int availableBalance) {
        this.availableBalance = availableBalance;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void withdraw(int withdrawAmount) {

    }

    public void deposit(int depositAmount) {

    }

    public abstract AccountType getType();

}
