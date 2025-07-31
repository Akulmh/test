```java
/**
 * Class representing a basic banking system using modern Java conventions
 * Compatible with Java 8 and above
 */
public class BankAccountManager {
    //MODERNIZATION: Replaced Vector with List<Account> for type safety and better performance.
    private List<Account> accountList;
    //MODERNIZATION: Used primitive double instead of Double object for MINIMUM_BALANCE.
    private static final double MINIMUM_BALANCE = 100.00;
    //MODERNIZATION: Used primitive int instead of Integer object for MAX_ACCOUNTS.
    private static final int MAX_ACCOUNTS = 100;
    
    public BankAccountManager() {
        //MODERNIZATION: Replaced Vector with ArrayList and used the diamond operator for better performance and readability.
        accountList = new ArrayList<>();
    }
    
    /**
     * Inner class representing a bank account
     */
    private class Account {
        private String accountNumber;
        //MODERNIZATION: Used primitive types instead of wrapper classes for better performance.
        private double balance;
        //MODERNIZATION: Used primitive types instead of wrapper classes for better performance.
        private boolean isActive;
        
        public Account(String accountNumber) {
            this.accountNumber = accountNumber;
            //MODERNIZATION: Used primitive values instead of wrapper objects for better performance.
            this.balance = 0.00;
            //MODERNIZATION: Used primitive values instead of wrapper objects for better performance.
            this.isActive = true;
        }
        
        public String getAccountNumber() {
            return this.accountNumber;
        }
        
        //MODERNIZATION: Changed return type to primitive double for better performance.
        public double getBalance() {
            return this.balance;
        }
        
        //MODERNIZATION: Changed parameter type to primitive double for better performance.
        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
    
    /**
     * Creates a new account
     * @param accountNumber Account identifier
     * @return Boolean indicating success
     * @MODERNIZATION: Changed return type to primitive boolean for better performance.
     */
    public boolean createAccount(String accountNumber) {
        //MODERNIZATION: Removed unnecessary .intValue() call and used primitive boolean value.
        if (accountList.size() >= MAX_ACCOUNTS) {
            return false;
        }
        
        Account newAccount = new Account(accountNumber);
        accountList.add(newAccount);
        //MODERNIZATION: Used primitive boolean value instead of Boolean object.
        return true;
    }
    
    /**
     * Deposits money into specified account
     * @param accountNumber Account identifier
     * @param amount Amount to deposit
     * @throws AccountNotFoundException If account not found
     * @MODERNIZATION: Changed parameter type to primitive double and used a more specific exception.
     */
    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account account = findAccount(accountNumber);
        
        //MODERNIZATION: Used a more specific exception for better error handling.
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        
        //MODERNIZATION: Simplified balance calculation using primitive doubles.
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
    }
    
    /**
     * Withdraws money from specified account
     * @param accountNumber Account identifier
     * @param amount Amount to withdraw
     * @throws AccountNotFoundException, InsufficientFundsException If account not found or insufficient funds
     * @MODERNIZATION: Changed parameter type to primitive double and used more specific exceptions.
     */
    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientFundsException {
        Account account = findAccount(accountNumber);
        
        //MODERNIZATION: Used a more specific exception for better error handling.
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        
        //MODERNIZATION: Simplified balance calculation using primitive doubles and removed unnecessary .doubleValue() calls.
        double newBalance = account.getBalance() - amount;
        
        if (newBalance < MINIMUM_BALANCE) {
            //MODERNIZATION: Used a more specific exception for better error handling.
            throw new InsufficientFundsException("Insufficient funds");
        }
        
        account.setBalance(newBalance);
    }
    
    /**
     * Finds account by account number
     * @param accountNumber Account to find
     * @return Account object if found, null otherwise
     * @MODERNIZATION: Replaced traditional for loop with enhanced for loop and removed unnecessary casting.
     */
    private Account findAccount(String accountNumber) {
        for (Account account : accountList) {
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
     * @throws AccountNotFoundException If account not found
     * @MODERNIZATION: Changed return type to primitive double and used a more specific exception.
     */
    public double getBalance(String accountNumber) throws AccountNotFoundException {
        Account account = findAccount(accountNumber);
        
        //MODERNIZATION: Used a more specific exception for better error handling.
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        
        //MODERNIZATION: Simplified return statement by directly returning the primitive double value.
        return account.getBalance();
    }
}
```