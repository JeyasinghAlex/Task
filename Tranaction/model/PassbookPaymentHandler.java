package Task.Tranaction.model;

import Task.Tranaction.enums.AccountType;
import Task.Tranaction.enums.TransactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PassbookPaymentHandler extends PaymentHandler {

    @Override
    public void transact(Account from, Account to, int amount) {

//        List<TransactionEvent> event = new ArrayList<>();
//
//        try {
//            Random rn = new Random();
//            int answer = rn.nextInt(10) + 1;
//            if (from.getAvailableBalance() - amount < from.getMinimumBalance()) {
//                System.out.println("* -----   Transaction failed   ----- *");
//                return;
//            }
//            event.add(new TransactionEvent(from, TransactionType.WITH_DRAW, amount));
//            from.setAvailableBalance(from.getAvailableBalance() - amount);
//            event.add(new TransactionEvent(to, TransactionType.DEPOSIT, amount));
//            to.setAvailableBalance(to.getAvailableBalance() + amount);
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
        List<TransactionEvent> event = new ArrayList<>();
        Random rn = new Random();
        int answer = rn.nextInt(10) + 1;

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
        } catch (Exception ex) {
            for (int i = 0; i < event.size(); ++i) {
                if (event.get(i).getType().equals(TransactionType.WITH_DRAW)) {
                    transaction.getFrom().setAvailableBalance(transaction.getFrom().getAvailableBalance() + event.get(i).getAmount());

                } else {
                    event.get(i).getAccount().setAvailableBalance(event.get(i).getAccount().getAvailableBalance() - event.get(i).getAmount());
                }
            }
            Transaction reTransfer = new Transaction.Builder().from(transaction.getTo()).to(transaction.getFrom()).amount(transaction.getAmount()).build();
            addEntry(reTransfer);
            System.out.println("Transaction failed, due to some technical issue ");
        }
        return addEntry(transaction);
    }
}
