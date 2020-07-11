package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.Enum.TransactionType;
import Task.TicketBookingManagement.Utils.BankConstants;

import java.util.concurrent.atomic.AtomicInteger;

public interface TransactionHandler {

    AtomicInteger TRANSACTION_ID_GENERATOR = new AtomicInteger(0);

    default boolean transactionProcess(Account from, int transferAmount, TransactionType transactionType) {

        int balance = from.getAvailableBalance();
        try {
            String description = "";
            if (transactionType.equals(TransactionType.WITH_DRAW)) {
                if (from.getAvailableBalance() - transferAmount < from.getMinimumBalance()) {
                    System.out.println("Transaction failed, please maintain minimum balance");
                    return false;
                }
                int availableBalance = from.getAvailableBalance() - transferAmount;
                int transactionCharge = calculateProcessingAmount(transferAmount);
                from.setAvailableBalance(availableBalance - transactionCharge);

                description = "TransactionId - " + BankConstants.WITHDRAW_OPERATION + TRANSACTION_ID_GENERATOR.incrementAndGet() +
                        "Transaction charge - " + transactionCharge + "your new balance is - " + from.getAvailableBalance();

            } else if (transactionType.equals(TransactionType.DEPOSIT)) {
                if (transferAmount < 0) {
                    System.out.println("Deposit amount should be greater than 0, deposit amount: " + transferAmount);
                    return false;
                }
                int availableBalance = from.getAvailableBalance() + transferAmount;
                int transactionCharge = calculateProcessingAmount(transferAmount);
                from.setAvailableBalance(availableBalance - transactionCharge);
                description = "TransactionId - " + BankConstants.DEPOSIT_OPERATION + TRANSACTION_ID_GENERATOR.incrementAndGet() +
                        ", Transaction charge - " + transactionCharge  + ", your new balance is - " + from.getAvailableBalance();
            }

            Transaction transaction = new Transaction(from, transferAmount, transactionType, description);
            from.setTransactions(transaction);
        } catch (Exception ex) {
            from.setAvailableBalance(balance);
            System.out.println("Transaction failed due to technical issue ");
            ex.printStackTrace();
        }
        return true;
    }

    default boolean transactionProcess(Account from, Account to, int transferAmount, TransactionType transactionType) {
        int balanceFrom = from.getAvailableBalance();
        int balanceTo = to.getAvailableBalance();
        try {
            String description = "";

            if (from.getAvailableBalance() - transferAmount < from.getMinimumBalance()) {
                System.out.println("Transaction failed, please maintain minimum balance");
                return false;
            }
            int availableBalance = from.getAvailableBalance() - transferAmount;
            int transactionCharge = calculateProcessingAmount(transferAmount);
            int balance = availableBalance - transactionCharge;
            from.setAvailableBalance(balance);
            availableBalance = to.getAvailableBalance();
            balance = availableBalance + transferAmount;
            to.setAvailableBalance(balance);
            description = "TransactionId - " + BankConstants.TRANSFER_OPERATION + TRANSACTION_ID_GENERATOR.incrementAndGet() +
                    ", Transaction charge - " + transactionCharge + ", your new balance is - " + from.getAvailableBalance();
            Transaction transaction = new Transaction(from, to, transferAmount, transactionType, description);
            from.setTransactions(transaction);
            description = "TransactionId - " + BankConstants.TRANSFER_OPERATION + TRANSACTION_ID_GENERATOR.incrementAndGet() +
                    ", Transaction charge - " + 0 + ", your new balance is - " + to.getAvailableBalance();
            transaction = new Transaction(from, to, transferAmount, transactionType, description);
            to.setTransactions(transaction);

        } catch (Exception ex) {
            from.setAvailableBalance(balanceFrom);
            to.setAvailableBalance(balanceTo);
            System.out.println("Transaction failed due to technical issue ");
            ex.printStackTrace();
        }
        return true;
    }

    public int calculateProcessingAmount(int amount);
}


