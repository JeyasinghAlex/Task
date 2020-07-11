package Task.Tranaction.model;

import Task.Tranaction.enums.AccountType;
import Task.Tranaction.enums.TransactionType;
import Task.Tranaction.utils.BankConstants;

public class SavingAccount extends Account {

    public static final int PROCESSING_PERCENTAGE = BankConstants.SAVING_PROCESSING_PERCENTAGE;

    @Override
    public boolean withdraw(int amount) {
        return ((TransactionHandler) amt -> {
            return amt * PROCESSING_PERCENTAGE / 100;
        }).transactionProcess(this, amount, TransactionType.WITH_DRAW);
    }

    @Override
    public boolean deposit(int amount) {
        return  ((TransactionHandler) amt -> {
            return amt * PROCESSING_PERCENTAGE / 100;
        }).transactionProcess(this, amount, TransactionType.DEPOSIT);
    }

    @Override
    public AccountType getType() {
        return AccountType.SAVING;
    }

    @Override
    public boolean transfer(Account to, int amount) {
        return ((TransactionHandler) amt -> {
            return amt * PROCESSING_PERCENTAGE / 100;
        }).transactionProcess(this, to, amount, TransactionType.TRANSFER);
    }
}


