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

        // Validation
        if (armPrecision < 0.0 || armPrecision > 1.0) {
            System.out.println("Error: Arm precision must be 0.0-1.0");
            scanner.close();
            return;
        }

        if (workerDensity < 1 || workerDensity > 20) {
            System.out.println("Error: Worker density must be 1-20");
            scanner.close();
            return;
        }

        if (!machineryState.equals("Worn") && !machineryState.equals("Faulty") && !machineryState.equals("Critical")) {
            System.out.println("Error: Unsupported machinery state");
            scanner.close();
            return;
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
            default:
                // Unreachable if validation passes
                return;

        }

        // Calculate Hazard Risk
        // Formula: ((1.0 - armPrecision) * 15.0) + (workerDensity * machineRiskFactor)
        double hazardRisk = ((1.0 - armPrecision) * 15.0) + (workerDensity * machineRiskFactor);

        // Output Result
        System.out.println("\n--- Hazard Analysis Result ---");
        System.out.printf("Hazard Risk Score: %.2f%n", hazardRisk);

        scanner.close();
    }
}
