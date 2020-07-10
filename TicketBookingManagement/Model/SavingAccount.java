package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.Enum.AccountType;
import Task.TicketBookingManagement.Utils.BankConstants;

import java.util.List;

public class SavingAccount extends Account {

    private static final int MINIMUM_BALANCE = BankConstants.SAVING_ACCOUNT_MINIMUM_BALANCE;
    private static final int INTEREST_RATE = BankConstants.SAVING_ACCOUNT_INTEREST;

//    @Override
//    public int getMinimumBalance() {
//        return MINIMUM_BALANCE;
//    }
//
//    @Override
//    public int getInterestRate() {
//        return INTEREST_RATE;
//    }

    @Override
    public AccountType getType() {
        return AccountType.SAVING;
    }
}
