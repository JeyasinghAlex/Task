package Task.Tranaction.model;

import Task.Tranaction.enums.TransactionType;
import Task.Tranaction.utils.BankConstants;

import java.util.concurrent.atomic.AtomicInteger;

public interface TransactionHandler {

    AtomicInteger TRANSACTION_ID_GENERATOR = new AtomicInteger(0);
    default Transaction processTransaction(Transaction transaction) {
        int balance = transaction.getFrom().getAvailableBalance();

        try {
            if (transaction.getType().equals(TransactionType.WITH_DRAW)) {
                if (transaction.getFrom().getAvailableBalance() - transaction.getAmount() < transaction.getFrom().getMinimumBalance()) {
                    System.out.println("* -----   Transaction failed, please maintain minimum balance   ----- *");
                    return null;
                }
                int availableBalance = transaction.getFrom().getAvailableBalance() - transaction.getAmount();
                int transactionCharge = calculateProcessingAmount(transaction.getAmount());

                /*Added Bank Charge*/
                HeadOffice.getInstance().setAmount(transactionCharge);
                transaction.getFrom().setAvailableBalance(availableBalance - transactionCharge);
                String transactionId = BranchConstants.WITHDRAW_OPERATION + TRANSACTION_ID_GENERATOR.incrementAndGet();
                Entry entry = new Entry(transactionId, transaction, transactionCharge);
                transaction.getFrom().addEntry(entry);
            } else if (transaction.getType().equals(TransactionType.DEPOSIT)) {
                if (transaction.getAmount() < 0) {
                    System.out.println("* ------  Deposit amount should be greater than 0, deposit amount:    ----- *");
                    return null;
                }
                int availableBalance = transaction.getFrom().getAvailableBalance() + transaction.getAmount();
                int transactionCharge = calculateProcessingAmount(transaction.getAmount());
                transaction.getFrom().setAvailableBalance(availableBalance - transactionCharge);
                String transactionId = BranchConstants.DEPOSIT_OPERATION + TRANSACTION_ID_GENERATOR.incrementAndGet();
                Entry entry = new Entry(transactionId, transaction, transactionCharge);
                transaction.getFrom().addEntry(entry);
            }
        } catch (Exception ex) {
            transaction.getFrom().setAvailableBalance(balance);
            System.out.println("* -----  Transaction failed due to technical issue :  ----- *");
            ex.printStackTrace();
        }
        return transaction;
    }

//    default boolean processTransaction(Account from, Account to, int transferAmount, TransactionType transactionType) {
//        int balanceFrom = from.getAvailableBalance();
//        int balanceTo = to.getAvailableBalance();
//        try {
//            String description = "";
//
//            if (from.getAvailableBalance() - transferAmount < from.getMinimumBalance()) {
//                System.out.println("Transaction failed, please maintain minimum balance");
//                return false;
//            }
//            int availableBalance = from.getAvailableBalance() - transferAmount;
//            int transactionCharge = calculateProcessingAmount(transferAmount);
//            int balance = availableBalance - transactionCharge;
//            from.setAvailableBalance(balance);
//            availableBalance = to.getAvailableBalance();
//            balance = availableBalance + transferAmount;
//            to.setAvailableBalance(balance);
//            description = "TransactionId - " + BranchConstants.TRANSFER_OPERATION + TRANSACTION_ID_GENERATOR.incrementAndGet() +
//                    ", Transaction charge - " + transactionCharge + ", your new balance is - " + from.getAvailableBalance();
//            Transaction transaction = new Transaction(from, to, transferAmount, transactionType, description);
//            from.addTransactions(transaction);
//            description = "TransactionId - " + BranchConstants.TRANSFER_OPERATION + TRANSACTION_ID_GENERATOR.incrementAndGet() +
//                    ", Transaction charge - " + 0 + ", your new balance is - " + to.getAvailableBalance();
//            transaction = new Transaction(from, to, transferAmount, transactionType, description);
//            to.addTransactions(transaction);
//
//        } catch (Exception ex) {
//            from.setAvailableBalance(balanceFrom);
//            to.setAvailableBalance(balanceTo);
//            System.out.println("Transaction failed due to technical issue ");
//            ex.printStackTrace();
//        }
//        return true;
//    }

    public int calculateProcessingAmount(int amount);
}


