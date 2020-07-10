package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.BankException.BankException;
import Task.TicketBookingManagement.Enum.AccountType;
import Task.TicketBookingManagement.Utils.BankConstants;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String ifsc;
    private List<Account> accounts;
    // add Customer


    private void setAccounts(Account account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        this.accounts.add(account);
    }

    public String createAccount(String adharNumber, String accountType, int amount) {
        Account newAccount = null;
        if (accountType.equals(AccountType.SAVING.toString())) {
            newAccount = new SavingAccount();
            newAccount.setMinimumBalance(BankConstants.SAVING_ACCOUNT_MINIMUM_BALANCE);
            newAccount.setInterestRate(BankConstants.SAVING_ACCOUNT_INTEREST);
        } else if(accountType.equals(AccountType.JOIN.toString())) {
            newAccount = new JoinAccount();
            newAccount.setMinimumBalance(BankConstants.JOIN_ACCOUNT_MINIMUM_BALANCE);
            newAccount.setInterestRate(BankConstants.JOIN_ACCOUNT_INTEREST);
        }  else {
            newAccount = new BusinessAccount();
            newAccount.setMinimumBalance(BankConstants.BUSINESS_ACCOUNT_MINIMUM_BALANCE);
            newAccount.setInterestRate(BankConstants.BUSINESS_ACCOUNT_INTEREST);
        }
        String accountNumber = BankConstants.BANK_ACCOUNT_NUMBER_PREFIX + adharNumber;
        newAccount.setAccountNumber(accountNumber);
        newAccount.setAvailableBalance(amount);

        setAccounts(newAccount);
        return accountNumber;
    }

    public Account getAccount(String accountNumber) throws BankException {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw  new BankException("No account mapped with this account number - " + accountNumber);
    }
}
