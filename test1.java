```java
public class FactorialCalculator {
    
    /**
     * Calculates the factorial of a given number.
     * 
     * @param n The number to calculate factorial for
     * @return The factorial of the input number
     * @throws IllegalArgumentException if the input is negative
     * Using Github for version control
     */
    //MODERNIZATION: Removed unnecessary throws clause as IllegalArgumentException is unchecked
    public static long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }
        
        //MODERNIZATION: Replaced traditional loop with a more concise and potentially parallel stream operation
        return java.util.stream.LongStream.rangeClosed(1, n)
                .reduce(1, (long a, long b) -> a * b);
    }

    public static void main(String[] args) {
        //MODERNIZATION: Added try-catch block to handle potential exceptions, used printf for formatted output, and improved error handling
        try {
            int number = 5;
            System.out.printf("Factorial of %d is: %d%n", number, calculateFactorial(number));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```