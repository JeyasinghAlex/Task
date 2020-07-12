package Task.Tranaction.model;

import Task.Tranaction.enums.TransactionType;

public class TransactionEvent {

    private int amount;
    private Account account;
    private TransactionType type;

    public TransactionEvent(Account account, TransactionType type, int amount) {
        this.amount = amount;
        this.account = account;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
