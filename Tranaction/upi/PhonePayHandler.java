package Task.Tranaction.upi;

import Task.Tranaction.enums.TransactionType;
import Task.Tranaction.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhonePayHandler implements UPIhandler {

    @Override
    public Entry transact(Transaction transaction) {

        List<TransactionEvent> event = new ArrayList<>();
        Random rn = new Random();
        int answer = rn.nextInt(10) + 1;
        Entry entry = null;
        try {
            if (transaction.getFrom().getAvailableBalance() - transaction.getAmount() < transaction.getFrom().getMinimumBalance()) {
                System.out.println("* -----   Transaction failed, Please maintain minimum balance   ----- *");
                return null;
            }
            event.add(new TransactionEvent(transaction.getFrom(), TransactionType.WITH_DRAW, transaction.getAmount()));
            transaction.getFrom().setAvailableBalance(transaction.getFrom().getAvailableBalance() - transaction.getAmount());
            event.add(new TransactionEvent(transaction.getTo(), TransactionType.DEPOSIT, transaction.getAmount()));
            transaction.getTo().setAvailableBalance(transaction.getTo().getAvailableBalance() + transaction.getAmount());
            entry = addEntry(transaction);
        } catch (Exception ex) {
            for (int i = 0; i < event.size(); ++i) {
                if (event.get(i).getType().equals(TransactionType.WITH_DRAW)) {
                    transaction.getFrom().setAvailableBalance(transaction.getFrom().getAvailableBalance() + event.get(i).getAmount());
                    String transactionId = BranchConstants.DEPOSIT_OPERATION + TransactionHandler.TRANSACTION_ID_GENERATOR.incrementAndGet();
                    Entry entry1 = new Entry(transactionId, transaction);
                    transaction.getFrom().addEntry(entry1);
                } else {
                    event.get(i).getAccount().setAvailableBalance(event.get(i).getAccount().getAvailableBalance() - event.get(i).getAmount());
                    String transactionId = BranchConstants.DEPOSIT_OPERATION + TransactionHandler.TRANSACTION_ID_GENERATOR.incrementAndGet();
                    Entry entry1 = new Entry(transactionId, transaction);
                    transaction.getFrom().addEntry(entry1);
                }
                System.out.println("Transaction failed, due to some technical issue ");
                return null;
            }
        }
        return entry;
    }

    public Entry addEntry(Transaction transaction) {
        String transactionId = BranchConstants.WITHDRAW_OPERATION + TransactionHandler.TRANSACTION_ID_GENERATOR.incrementAndGet();
        Entry entryFrom = new Entry(transactionId, transaction);
        String transactionId1 = BranchConstants.DEPOSIT_OPERATION + TransactionHandler.TRANSACTION_ID_GENERATOR.incrementAndGet();
        Entry entryTo = new Entry(transactionId1, transaction);
        transaction.getFrom().addEntry(entryFrom);
        transaction.getTo().addEntry(entryTo);
        return entryFrom;
    }
}
