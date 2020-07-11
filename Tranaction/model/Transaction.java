package Task.Tranaction.model;

import Task.Tranaction.enums.TransactionType;

public class Transaction {

    private Account from;
    private Account to;
    private int amount;
    private TransactionType type;
    private String description;

    public Transaction(){

    }
    public Transaction(Account from, Account to, int amount, TransactionType type, String description) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public Transaction(Account from, int amount, TransactionType type, String description) {
        this.from = from;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public Account getFrom() {
        return from;
    }

    public Transaction setFrom(Account from) {
        this.from = from;
        return this;
    }

    public Account getTo() {
        return to;
    }

    public Transaction setTo(Account to) {
        this.to = to;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Transaction setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public TransactionType getType() {
        return type;
    }

    public Transaction setType(TransactionType type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Transaction setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {

        if (to != null)
            return "Transaction{" +
                    "from=" + from.getAccountNumber() +
                    ", to=" + to.getAccountNumber() +
                    ", amount=" + amount +
                    ", type=" + type +
                    ", description='" + description + '\'' +
                    '}';

        return "Transaction{" +
                "from=" + from.getAccountNumber() +
                ", amount=" + amount +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
