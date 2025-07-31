```java
/**
 * Class representing a basic banking system using old Java conventions
 * Compatible with Java 6
 * Using Github for version control
 */
public class BankAccountManager {
    //MODERNIZATION: Replaced Vector with List interface and added generics for type safety
    private List<Account> accountList;
    //MODERNIZATION: Replaced wrapper classes with primitive types for constants
    private static final double MINIMUM_BALANCE = 100.00;
    //MODERNIZATION: Replaced wrapper classes with primitive types for constants
    private static final int MAX_ACCOUNTS = 100;
    
    public BankAccountManager() {
        //MODERNIZATION: Replaced Vector with ArrayList and used diamond operator
        accountList = new ArrayList<>();
    }
    
    /**
     * Inner class representing a bank account
     */
    private class Account {
        private String accountNumber;
        //MODERNIZATION: Replaced wrapper classes with primitive types for better performance
        private double balance;
        //MODERNIZATION: Replaced wrapper classes with primitive types for better performance
        private boolean isActive;
        
        public Account(String accountNumber) {
            this.accountNumber = accountNumber;
            //MODERNIZATION: Simplified initialization of primitive types
            this.balance = 0.00;
            this.isActive = true;
        }
        
        public String getAccountNumber() {
            return this.accountNumber;
        }
        
        //MODERNIZATION: Changed return type to primitive double
        public double getBalance() {
            return this.balance;
        }
        
        //MODERNIZATION: Changed parameter type to primitive double
        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
    
    /**
     * Creates a new account
     * @param accountNumber Account identifier
     * @return Boolean indicating success
     */
    //MODERNIZATION: Changed return type to primitive boolean
    public boolean createAccount(String accountNumber) {
        //MODERNIZATION: Removed unnecessary .intValue() call and used primitive boolean
        if (accountList.size() >= MAX_ACCOUNTS) {
            return false;
        }
        
        Account newAccount = new Account(accountNumber);
        accountList.add(newAccount);
        //MODERNIZATION: Used primitive boolean instead of Boolean object
        return true;
    }
    
    /**
     * Deposits money into specified account
     * @param accountNumber Account identifier
     * @param amount Amount to deposit
     */
    //MODERNIZATION: Changed parameter type to primitive double and removed throws clause
    public void deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        
        //MODERNIZATION: Replaced generic Exception with more specific IllegalArgumentException
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        
        //MODERNIZATION: Simplified balance calculation using primitive doubles
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
    }
    
    /**
     * Withdraws money from specified account
     * @param accountNumber Account identifier
     * @param amount Amount to withdraw
     */
    //MODERNIZATION: Changed parameter type to primitive double and removed throws clause
    public void withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        
        //MODERNIZATION: Replaced generic Exception with more specific IllegalArgumentException
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        
        //MODERNIZATION: Simplified balance calculation and comparison, and used more specific exception
        double newBalance = account.getBalance() - amount;
        
        if (newBalance < MINIMUM_BALANCE) {
            throw new IllegalStateException("Insufficient funds");
        }
        
        account.setBalance(newBalance);
    }
    
    /**
     * Finds account by account number
     * @param accountNumber Account to find
     * @return Account object if found, null otherwise
     */
    private Account findAccount(String accountNumber) {
        //MODERNIZATION: Replaced traditional for loop with enhanced for loop and removed casting
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
     */
    //MODERNIZATION: Changed return type to primitive double and removed throws clause
    public double getBalance(String accountNumber) {
        Account account = findAccount(accountNumber);
        
        //MODERNIZATION: Replaced generic Exception with more specific IllegalArgumentException
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        
        //MODERNIZATION: Simplified return statement by directly returning the primitive double balance
        return account.getBalance();
    }
}
```