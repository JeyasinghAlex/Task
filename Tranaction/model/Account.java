package Task.Tranaction.model;

import Task.Tranaction.enums.AccountType;
import Task.Tranaction.enums.TransactionType;

import java.util.*;

public abstract class Account {
    private String accountNumber;
    private int minimumBalance;
    private int availableBalance;
    private int interestRate;
    private List<Entry> entries;
    private Map<TransactionType, UPIhandler> upIhandlers;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public int getMinimumBalance() {
        return minimumBalance;
    }

    protected void setMinimumBalance(int minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public int getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(int availableBalance) {
        this.availableBalance = availableBalance;
    }

    protected void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public int getInterestRate() {
        return interestRate;
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

    public void addUpi(TransactionType type, UPIhandler upIhandler) {
        if (upIhandlers == null) {
               upIhandlers = new HashMap<>();
        }
        this.upIhandlers.put(type, upIhandler);
    }

    public Map<TransactionType, UPIhandler> getUpIhandlers() {
        return upIhandlers;
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

    public Entry transact(Transaction transaction) {
        UPIhandler upIhandler = upIhandlers.get(transaction.getType());
       return upIhandler.transact(transaction);
    }
    public abstract Entry deposit(int amount);
    public abstract Entry withdraw(int withdrawAmount);
    protected abstract AccountType getType();

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
