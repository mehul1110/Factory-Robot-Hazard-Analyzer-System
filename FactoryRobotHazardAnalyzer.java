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
            RobotHazardAuditor auditor = new RobotHazardAuditor();
            double result = auditor.CalculateHazardRisk(armPrecision, workerDensity, machineryState);
            System.out.println("\n--- Hazard Analysis Result ---");
            System.out.printf("Hazard Risk Score: %.2f%n", result);
        } catch (RobotSafetyException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
