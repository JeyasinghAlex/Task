package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.Enum.TransactionType;

public class Transaction {

    private Account from;
    private Account to;
    private int amount;
    private TransactionType type;
    private String description;

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

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
