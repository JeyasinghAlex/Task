package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.Enum.TransactionType;

public class Transaction {

    private Account from;
    private Account to;
    private int amount;
    private TransactionType type;
    private String description;

    private Transaction(int amount) {
        this.amount = amount;
    }

    public Transaction(Account from, Account to, int amount, TransactionType type, String description) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }
}
