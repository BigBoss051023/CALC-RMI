import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        String rmiUrl = "rmi://127.0.0.1:1200/CalculatorService";

        try (Scanner scanner = new Scanner(System.in)) { 
            System.out.println("Attempting to connect to RMI service...");
            Calculator calc = (Calculator) Naming.lookup(rmiUrl);
            System.out.println("Connected to RMI service.");

            // Get valid numbers from the user
            double num1 = getValidNumber(scanner, "Enter the first number: ");
            double num2 = getValidNumber(scanner, "Enter the second number: ");

            // Perform calculations and display results
            System.out.println("Addition result: " + calc.add(num1, num2));
            System.out.println("Subtraction result: " + calc.subtract(num1, num2));
            System.out.println("Multiplication result: " + calc.multiply(num1, num2));

            if (num2 != 0) {
                System.out.println("Division result: " + calc.divide(num1, num2));
            } else {
                System.out.println("Division by zero is not allowed.");
            }
        } catch (MalformedURLException e) {
            System.err.println("The RMI URL is malformed: " + e.getMessage());
        } catch (NotBoundException e) {
            System.err.println("The RMI service is not bound to the specified URL: " + e.getMessage());
        } catch (RemoteException e) {
            System.err.println("A remote exception occurred: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    private static double getValidNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double number = scanner.nextDouble();
                scanner.nextLine();
                return number;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
    }
}