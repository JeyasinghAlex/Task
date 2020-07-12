package Task.Tranaction.model;

public abstract class PaymentHandler {


    public Entry addEntry(Transaction transaction) {
        String transactionId = BranchConstants.WITHDRAW_OPERATION + TransactionHandler.TRANSACTION_ID_GENERATOR.incrementAndGet();
        Entry entryFrom = new Entry(transactionId, transaction);
        String transactionId1 = BranchConstants.DEPOSIT_OPERATION + TransactionHandler.TRANSACTION_ID_GENERATOR.incrementAndGet();
        Entry entryTo = new Entry(transactionId1, transaction);
        transaction.getFrom().addEntry(entryFrom);
        transaction.getTo().addEntry(entryTo);
        return entryFrom;
    }

    public abstract void transact(Account from, Account to, int amount);
    public abstract Entry transact(Transaction transaction);

}
