package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.Enum.AccountType;
import Task.TicketBookingManagement.Enum.TransactionType;
import Task.TicketBookingManagement.Utils.BankConstants;

public class JoinAccount extends Account {

    public static final int PROCESSING_PERCENTAGE = BankConstants.JOIN_PROCESSING_PERCENTAGE;

    @Override
    public boolean withdraw(int amount) {
          return  ((TransactionHandler) amt -> {
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
    public boolean transfer(Account to, int amount) {
        return  ((TransactionHandler) amt -> {
            return amt * PROCESSING_PERCENTAGE / 100;
        }).transactionProcess(this, to, amount, TransactionType.TRANSFER);
    }

    @Override
    public AccountType getType() {
        return AccountType.CURRENT;
    }
}
