package Task.Tranaction.model;

import Task.Tranaction.enums.AccountType;
import Task.Tranaction.enums.TransactionType;

public class JointAccount extends Account {

    private static int debitProcessingPercentage = BranchConstants.JOIN_DEBIT_PROCESSING_PERCENTAGE;
    private static int creditProcessingPercentage = BranchConstants.JOIN_CREDIT_PROCESSING_PERCENTAGE;

    @Override
    public Transaction withdraw(int amount) {
        Transaction transaction = new Transaction.Builder().from(this).amount(amount).type(TransactionType.WITH_DRAW).build();
        return ((TransactionHandler) amt -> {
            int count = 0;
            for (int i = 0; i < this.getEntries().size(); ++i) {
                if (this.getEntries().get(i).getTransaction().getType().equals(TransactionType.WITH_DRAW))
                    count++;
            }
            if (count >= 5)
                debitProcessingPercentage = (count + debitProcessingPercentage) / 2;
            return amt * debitProcessingPercentage / 100;
        }).processTransaction(transaction);
    }

    @Override
    public Transaction deposit(int amount) {
        Transaction transaction = new Transaction.Builder().from(this).amount(amount).type(TransactionType.DEPOSIT).build();
        return ((TransactionHandler) amt -> {
            int count = 0;
            for (int i = 0; i < this.getEntries().size(); ++i) {
                if (this.getEntries().get(i).getTransaction().getType().equals(TransactionType.DEPOSIT))
                    count++;
            }
            if (count >= 5)
                creditProcessingPercentage = (count + creditProcessingPercentage / 2) / 2;
            return amt * creditProcessingPercentage / 100;
        }).processTransaction(transaction);
    }

    @Override
    public AccountType getType() {
        return AccountType.JOIN;
    }
}
