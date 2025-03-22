import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            System.out.println("Starting RMI Registry...");
            LocateRegistry.createRegistry(1200); // Start RMI registry on port 1200
            System.out.println("RMI Registry started.");

            CalculatorImpl calc = new CalculatorImpl();
            System.out.println("Binding Calculator Service...");
            String rmiUrl = "rmi://127.0.0.1:1200/CalculatorService";
            Naming.rebind(rmiUrl, calc); // Bind the service
            System.out.println("Calculator Service bound.");
            System.out.println("Calculator Service is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}