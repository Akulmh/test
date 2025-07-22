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
        
        //MODERNIZATION: Replaced traditional for loop with IntStream for better readability and potential performance improvement
        return IntStream.rangeClosed(2, n)
                .mapToLong(Long::valueOf)
                .reduce(1, (a, b) -> a * b);
    }

    public static void main(String[] args) {
        int number = 5;
        System.out.println("Factorial of " + number + " is: " + calculateFactorial(number));
    }
}
```