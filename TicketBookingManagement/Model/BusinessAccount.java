package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.Enum.AccountType;

public class BusinessAccount extends Account{

    @Override
    public AccountType getType() {
        return AccountType.BUSINESS;
    }
}
