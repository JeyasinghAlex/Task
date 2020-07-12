package Task.Tranaction.model;

import Task.Tranaction.enums.AccountType;
import Task.Tranaction.utils.BankConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class Account {
    private String accountNumber;
    private int minimumBalance;
    private int availableBalance;
    private int interestRate;
    private List<Entry> entries;

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

    public void addEntry(Entry entry) {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        this.entries.add(entry);
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public List<Entry> getMiniStatement() {
        List<Entry> miniStatement = new ArrayList<>();
        int count = 0;
        ListIterator<Entry> listIter = entries.listIterator(entries.size());
        while (listIter.hasPrevious()) {
            miniStatement.add(listIter.previous());
            count++;
            if (count > BranchConstants.MINI_STATEMENT_COUNT) {
                break;
            }
        }
        return miniStatement;
    }

    public abstract Transaction deposit(int amount);
    public abstract Transaction withdraw(int withdrawAmount);
    public abstract AccountType getType();

    @Override
    public String toString() {
        return "Account{" +
                " accountNumber = '" + accountNumber +
                ", accountType = " + this.getType() +
                ", availableBalance = " + availableBalance +
                ", entries = " + entries +
                '}';
    }
}
