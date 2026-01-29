import java.util.Scanner;

/**
 * FactoryRobotHazardAnalyzer
 *
 * Main entry point for the Factory Robot Hazard Analyzer application.
 * Accepts user inputs and checks for hazards.
 */
public class FactoryRobotHazardAnalyzer {
    public static void main(String[] args) {
        System.out.println("Factory Robot Hazard Analyzer");

        Scanner scanner = new Scanner(System.in);

        // Input: Arm Precision
        System.out.print("Enter Arm Precision (0.0-1.0): ");
        double armPrecision = scanner.nextDouble();

        // Input: Worker Density
        System.out.print("Enter Worker Density (1-20): ");
        int workerDensity = scanner.nextInt();

        // Input: Machinery State (consume newline first)
        scanner.nextLine();
        System.out.print("Enter Machinery State (Worn/Faulty/Critical): ");
        String machineryState = scanner.nextLine();

        double result = CalculateHazardRisk(armPrecision, workerDensity, machineryState);

        // Output Result (only if valid, but for UC5 we rely on internal printing of
        // errors)
        // Since we return a double, we need a way to know if it failed.
        // For this temporary step, checking if result is distinct or just letting it
        // facilitate the flow.
        // The prompt says "main() should only call the method".
        // The validation inside prints errors.
        // If valid, print score.
        // Let's assume we print Score inside main if no error?
        // Or better: print Score inside the method?
        // "Must implement EXACTLY the method below: public double
        // CalculateHazardRisk..."
        // It returns double.
        // If I print error, I still return something.

        if (result != -1.0) {
            System.out.println("\n--- Hazard Analysis Result ---");
            System.out.printf("Hazard Risk Score: %.2f%n", result);
        }

        scanner.close();
    }

    public static double CalculateHazardRisk(double armPrecision, int workerDensity, String machineryState) {
        // Validation
        if (armPrecision < 0.0 || armPrecision > 1.0) {
            System.out.println("Error: Arm precision must be 0.0-1.0");
            return -1.0;
        }

        if (workerDensity < 1 || workerDensity > 20) {
            System.out.println("Error: Worker density must be 1-20");
            return -1.0;
        }

        if (!machineryState.equals("Worn") && !machineryState.equals("Faulty") && !machineryState.equals("Critical")) {
            System.out.println("Error: Unsupported machinery state");
            return -1.0;
        }

        // Determine Risk Factor
        double machineRiskFactor = 0.0;
        switch (machineryState) {
            case "Worn":
                machineRiskFactor = 1.3;
                break;
            case "Faulty":
                machineRiskFactor = 2.0;
                break;
            case "Critical":
                machineRiskFactor = 3.0;
                break;
        }

        // Calculate Hazard Risk
        return ((1.0 - armPrecision) * 15.0) + (workerDensity * machineRiskFactor);
    }
}
