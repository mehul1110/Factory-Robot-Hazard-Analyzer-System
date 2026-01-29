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

        try {
            double result = CalculateHazardRisk(armPrecision, workerDensity, machineryState);
            System.out.println("\n--- Hazard Analysis Result ---");
            System.out.printf("Hazard Risk Score: %.2f%n", result);
        } catch (RobotSafetyException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    public static double CalculateHazardRisk(double armPrecision, int workerDensity, String machineryState)
            throws RobotSafetyException {
        // Validation
        if (armPrecision < 0.0 || armPrecision > 1.0) {
            throw new RobotSafetyException("Error: Arm precision must be 0.0-1.0");
        }

        if (workerDensity < 1 || workerDensity > 20) {
            throw new RobotSafetyException("Error: Worker density must be 1-20");
        }

        if (!machineryState.equals("Worn") && !machineryState.equals("Faulty") && !machineryState.equals("Critical")) {
            throw new RobotSafetyException("Error: Unsupported machinery state");
        }

        // Determine Risk Factor
        double machineRiskFactor = getMachineryRiskFactor(machineryState);

        // Calculate Hazard Risk
        return ((1.0 - armPrecision) * 15.0) + (workerDensity * machineRiskFactor);
    }

    private static double getMachineryRiskFactor(String machineryState) {
        switch (machineryState) {
            case "Worn":
                return 1.3;
            case "Faulty":
                return 2.0;
            case "Critical":
                return 3.0;
            default:
                return 0.0; // Should be unreachable due to validation
        }

    
    }
}


    
    
