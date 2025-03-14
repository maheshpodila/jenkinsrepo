import     // Output the sum of the numbers
        System.out.println("The sum of the numbers is: " + sum);
    } 
}java.util.Scanner;

public class SumNumbers {
    public static void main(String[] args) {
        int sum = 0;

        // Using try-with-resources to automatically close the scanner
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter numbers (enter 0 to calculate the sum):");

            while (true) {
                // Read user input
                int number = scanner.nextInt();
                
                if (number == 0) {
                    break; // Exit the loop if the user enters 0
                }
                
                // Add the number to sum
                sum += number;
            }
        } catch (Exception e) {
            System.out.println("Invalid input, please enter valid integers.");
        }

    