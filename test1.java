public class FactorialCalculator {
    
    /**
     * Calculates the factorial of a given number.
     * 
     * @param n The number to calculate factorial for
     * @return The factorial of the input number
     * @throws IllegalArgumentException if the input is negative
     * Using Github for version control
     */
    public static long calculateFactorial(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        
        return result;
    }

    public static void main(String[] args) {
        int number = 5;
        System.out.println("Factorial of " + number + " is: " + calculateFactorial(number));
    }
}
