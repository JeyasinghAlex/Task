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

    public void addTransactions(Transaction transaction) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        this.transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public boolean transactionProcess(Account account, int transferAmount) {
        if (this.withdraw(transferAmount)) {
            if (account.deposit(transferAmount)) {
                return true;
            }
        }
        return false;
    }

    public List<Transaction> getMiniStatement() {
        List<Transaction> miniStatement = new ArrayList<>();
        int count = 0;
        ListIterator<Transaction> listIter = transactions.listIterator(transactions.size());
        while (listIter.hasPrevious()) {
            miniStatement.add(listIter.previous());
            count++;
            if (count > BankConstants.MINI_STATEMENT_COUNT) {
                break;
            }
        }
        return miniStatement;
    }

    public boolean transfer(Account to, int amount) {
        return  transactionProcess(to, amount);
    }

    public abstract boolean deposit(int amount);
    public abstract boolean withdraw(int withdrawAmount);
    public abstract AccountType getType();

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", minimumBalance=" + minimumBalance +
                ", availableBalance=" + availableBalance +
                ", interestRate=" + interestRate +
                ", transactions=" + transactions +
                ", accountType=" + this.getType() +
                '}';
    }
}
