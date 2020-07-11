package Task.Tranaction.model;

import Task.Tranaction.enums.AccountType;
import Task.Tranaction.enums.TransactionType;
import Task.Tranaction.utils.BankConstants;

public class BusinessAccount extends Account {

    public static final int PROCESSING_PERCENTAGE = BankConstants.BUSINESS_PROCESSING_PERCENTAGE;

    @Override
    public boolean withdraw(int amount) {
        Transaction transaction = new Transaction();
        transaction = transaction.setFrom(this).setType(TransactionType.WITH_DRAW).setAmount(amount);
        return ((TransactionHandler) amt -> {
            return amt * PROCESSING_PERCENTAGE / 100;
        }).transactionProcess(transaction);
    }

    @Override
    public boolean deposit(int amount) {
        return ((TransactionHandler) amt -> {
            return amt * PROCESSING_PERCENTAGE / 100;
        }).transactionProcess(this, amount, TransactionType.DEPOSIT);
    }

    @Override
    public AccountType getType() {
        return AccountType.BUSINESS;
    }

    @Override
    public boolean transfer(Account to, int amount) {
        return ((TransactionHandler) amt -> {
            return amt * PROCESSING_PERCENTAGE / 100;
        }).transactionProcess(this, to, amount, TransactionType.TRANSFER);
    }
}

