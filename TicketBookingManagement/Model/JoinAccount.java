package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.Enum.AccountType;
import Task.TicketBookingManagement.Utils.BankConstants;

public class JoinAccount extends Account {

    @Override
    public AccountType getType() {
        return AccountType.CURRENT;
    }
}
