package Task.TicketBookingManagement.Model;

import Task.TicketBookingManagement.BankException.BankException;
import Task.TicketBookingManagement.Enum.AccountType;
import Task.TicketBookingManagement.Utils.BankConstants;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String ifsc;
    private List<Account> accounts;
    private List<Customer> customers;


    private void addAccounts(Account account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        this.accounts.add(account);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomers(Customer customer) {
        if (customers == null) {
            customers = new ArrayList<>();
        }
        this.customers.add(customer);
    }

    public String createAccount(String panNumber, String accountType, int amount) {
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
        String accountNumber = BankConstants.BANK_ACCOUNT_NUMBER_PREFIX + panNumber;
        newAccount.setAccountNumber(accountNumber);
        newAccount.setAvailableBalance(amount);


        Customer alreadyCustomer = customerPresentWithPanNumber(panNumber);

        if (alreadyCustomer != null) {
            alreadyCustomer.addAccounts(newAccount);
        } else {
            Customer newCustomer = new Customer();
            newCustomer.setPanNumber(panNumber);
            newCustomer.addAccounts(newAccount);
            addCustomers(newCustomer);
        }
        addAccounts(newAccount);
        return accountNumber;
    }

    public Account getAccount(String accountNumber)  {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public Customer getCustomerByPanNumber(String panNumber)  {
        for (Customer customer : customers) {
            if (customer.getPanNumber().equals(panNumber)) {
                return customer;
            }
        }
       return null;
    }

    private Customer customerPresentWithPanNumber(String panNumber) {
        if (null != customers) {
            for (Customer customer : customers) {
                if (customer.getPanNumber().equals(panNumber)) {
                    return customer;
                }
            }
        }
        return null;
    }
}
