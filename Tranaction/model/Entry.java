package Task.Tranaction.model;

public class Entry {

    private String transactionId;
    private Transaction transaction;
    private int transactionCharge;

    public Entry(String transactionId, Transaction transaction) {
        this.transactionId = transactionId;
        this.transaction = transaction;
    }

    public Entry(String transactionId, Transaction transaction, int transactionCharge) {
        this.transactionId = transactionId;
        this.transaction = transaction;
        this.transactionCharge = transactionCharge;
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionCharge() {
        return transactionCharge;
    }

    public void setTransactionCharge(int transactionCharge) {
        this.transactionCharge = transactionCharge;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "transactionId='" + transactionId + '\'' +
                ", transaction=" + transaction +
                '}';
    }
}
