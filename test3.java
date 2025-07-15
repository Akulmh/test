/**
 * Class representing a basic banking system using old Java conventions
 * Compatible with Java 6
 */
public class BankAccountManager {
    private Vector accountList;
    private static final Double MINIMUM_BALANCE = new Double(100.00);
    private static final Integer MAX_ACCOUNTS = new Integer(100);
    
    public BankAccountManager() {
        accountList = new Vector();
    }
    
    /**
     * Inner class representing a bank account
     */
    private class Account {
        private String accountNumber;
        private Double balance;
        private Boolean isActive;
        
        public Account(String accountNumber) {
            this.accountNumber = accountNumber;
            this.balance = new Double(0.00);
            this.isActive = Boolean.TRUE;
        }
        
        public String getAccountNumber() {
            return this.accountNumber;
        }
        
        public Double getBalance() {
            return this.balance;
        }
        
        public void setBalance(Double balance) {
            this.balance = balance;
        }
    }
    
    /**
     * Creates a new account
     * @param accountNumber Account identifier
     * @return Boolean indicating success
     */
    public Boolean createAccount(String accountNumber) {
        if (accountList.size() >= MAX_ACCOUNTS.intValue()) {
            return Boolean.FALSE;
        }
        
        Account newAccount = new Account(accountNumber);
        accountList.add(newAccount);
        return Boolean.TRUE;
    }
    
    /**
     * Deposits money into specified account
     * @param accountNumber Account identifier
     * @param amount Amount to deposit
     * @throws Exception If account not found
     */
    public void deposit(String accountNumber, Double amount) throws Exception {
        Account account = findAccount(accountNumber);
        
        if (account == null) {
            throw new Exception("Account not found");
        }
        
        Double newBalance = new Double(account.getBalance().doubleValue() + 
                                     amount.doubleValue());
        account.setBalance(newBalance);
    }
    
    /**
     * Withdraws money from specified account
     * @param accountNumber Account identifier
     * @param amount Amount to withdraw
     * @throws Exception If insufficient funds or account not found
     */
    public void withdraw(String accountNumber, Double amount) throws Exception {
        Account account = findAccount(accountNumber);
        
        if (account == null) {
            throw new Exception("Account not found");
        }
        
        Double newBalance = new Double(account.getBalance().doubleValue() - 
                                     amount.doubleValue());
                                     
        if (newBalance.doubleValue() < MINIMUM_BALANCE.doubleValue()) {
            throw new Exception("Insufficient funds");
        }
        
        account.setBalance(newBalance);
    }
    
    /**
     * Finds account by account number
     * @param accountNumber Account to find
     * @return Account object if found, null otherwise
     */
    private Account findAccount(String accountNumber) {
        for (int i = 0; i < accountList.size(); i++) {
            Account account = (Account) accountList.elementAt(i);
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    
    /**
     * Gets current balance for specified account
     * @param accountNumber Account identifier
     * @return Current balance
     * @throws Exception If account not found
     */
    public Double getBalance(String accountNumber) throws Exception {
        Account account = findAccount(accountNumber);
        
        if (account == null) {
            throw new Exception("Account not found");
        }
        
        return new Double(account.getBalance().doubleValue());
    }
}
