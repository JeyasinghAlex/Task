package Task.Tranaction.model;

import Task.Tranaction.enums.TransactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GooglePayHandler extends PaymentHandler implements UPIhandler{

    @Override
    public void transact(Account from, Account to, int amount) {
//        if (!validateUserAccount(from)) {
//           return;
//        }
//        if (!validateUserAccount(to)) {
//            return;
//        }
//
//        List<TransactionEvent> event = new ArrayList<>();
//        try {
//            Random rn = new Random();
//            int answer = rn.nextInt(10) + 1;
//            if (from.getAvailableBalance() - amount < from.getMinimumBalance()) {
//                return;
//            }
//            event.add(new TransactionEvent(from, TransactionType.WITH_DRAW, amount));
//            from.setAvailableBalance(from.getAvailableBalance() - amount);
//            event.add(new TransactionEvent(to, TransactionType.DEPOSIT, amount));
//            to.setAvailableBalance(to.getAvailableBalance() + amount);
//            if (answer == 5)
//                answer = answer / 0;
//        } catch (Exception ex) {
//            for (int i = 0; i < event.size(); ++i) {
//                if (event.get(i).getType().equals(TransactionType.WITH_DRAW)) {
//                    from.setAvailableBalance(from.getAvailableBalance() + event.get(i).getAmount());
//                } else {
//                    event.get(i).getAccount().setAvailableBalance(event.get(i).getAccount().getAvailableBalance() - event.get(i).getAmount());
//                }
//            }
//        }
    }

    @Override
    public Entry transact(Transaction transaction) {

        if (!validateUserAccount(transaction.getFrom())) {
            System.out.println("Account not registered");
           return null;
        }
        if (!validateUserAccount(transaction.getTo())) {
            System.out.println("Account not registered");
            return null;
        }


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
            if (answer == 5)
                answer = answer / 0;
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

    @Override
    public boolean validateUserAccount(Account from) {
        Random rn = new Random();
        int answer = rn.nextInt(10) + 1;
        if  (answer == 5)
            return false;
        return true;
    }
}
