```java
/**
 * Class representing a basic banking system using old Java conventions
 * Compatible with Java 6
 * Using Github for version control
 */
public class BankAccountManager {
    //MODERNIZATION: Replaced Vector with the more modern List interface, using generics for type safety.
    private List<Account> accountList;
    //MODERNIZATION: Removed unnecessary boxing of primitive types.
    private static final double MINIMUM_BALANCE = 100.00;
    //MODERNIZATION: Removed unnecessary boxing of primitive types.
    private static final int MAX_ACCOUNTS = 100;
    
    public BankAccountManager() {
        //MODERNIZATION: Replaced Vector instantiation with ArrayList, using the diamond operator for concise generic instantiation.
        accountList = new ArrayList<>();
    }
    
    /**
     * Inner class representing a bank account
     */
    private class Account {
        private String accountNumber;
        //MODERNIZATION: Changed Double wrapper to primitive double for better performance.
        private double balance;
        //MODERNIZATION: Changed Boolean wrapper to primitive boolean for better performance.
        private boolean isActive;
        
        public Account(String accountNumber) {
            this.accountNumber = accountNumber;
            //MODERNIZATION: Removed unnecessary boxing of primitive types.
            this.balance = 0.00;
            //MODERNIZATION: Removed unnecessary boxing of primitive types.
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
     */
    //MODERNIZATION: Changed return type to primitive boolean for better performance.
    public boolean createAccount(String accountNumber) {
        if (accountList.size() >= MAX_ACCOUNTS) {
            //MODERNIZATION: Removed unnecessary unboxing and use of Boolean.FALSE.
            return false;
        }
        
        Account newAccount = new Account(accountNumber);
        accountList.add(newAccount);
        //MODERNIZATION: Replaced Boolean.TRUE with primitive boolean true.
        return true;
    }
    
    /**
     * Deposits money into specified account
     * @param accountNumber Account identifier
     * @param amount Amount to deposit
     */
    public void deposit(String accountNumber, double amount) {
        //MODERNIZATION: Changed parameter type to primitive double and removed unnecessary throws clause.
        Account account = findAccount(accountNumber);
        
        if (account == null) {
            //MODERNIZATION: Replaced generic Exception with more specific IllegalArgumentException.
            throw new IllegalArgumentException("Account not found");
        }
        
        //MODERNIZATION: Removed unnecessary boxing and unboxing of double values.
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
    }
    
    /**
     * Withdraws money from specified account
     * @param accountNumber Account identifier
     * @param amount Amount to withdraw
     */
    public void withdraw(String accountNumber, double amount) {
        //MODERNIZATION: Changed parameter type to primitive double and removed unnecessary throws clause.
        Account account = findAccount(accountNumber);
        
        if (account == null) {
            //MODERNIZATION: Replaced generic Exception with more specific IllegalArgumentException.
            throw new IllegalArgumentException("Account not found");
        }
        
        //MODERNIZATION: Removed unnecessary boxing and unboxing of double values, and replaced generic Exception with more specific IllegalStateException.
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
        //MODERNIZATION: Replaced traditional for loop with enhanced for loop, removing the need for casting.
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
    public double getBalance(String accountNumber) {
        //MODERNIZATION: Changed return type to primitive double and removed unnecessary throws clause.
        Account account = findAccount(accountNumber);
        
        if (account == null) {
            //MODERNIZATION: Replaced generic Exception with more specific IllegalArgumentException.
            throw new IllegalArgumentException("Account not found");
        }
        
        //MODERNIZATION: Removed unnecessary boxing of double value.
        return account.getBalance();
    }
}
```