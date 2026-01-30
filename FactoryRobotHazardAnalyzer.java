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

    /**
     * RobotHazardAuditor
     *
     * This class handles the business logic for analyzing robot hazards.
     * It strictly implements the CalculateHazardRisk method as per specifications.
     */
    public static class RobotHazardAuditor {

        /**
         * Calculates the hazard risk based on precision, density, and machinery state.
         *
         * @param armPrecision   The precision of the robot arm (0.0 to 1.0).
         * @param workerDensity  The density of workers in the area (1 to 20).
         * @param machineryState The state of the machinery (Worn, Faulty, Critical).
         * @return The calculated hazard risk score.
         * @throws RobotSafetyException If any input is invalid.
         */
        public double CalculateHazardRisk(double armPrecision, int workerDensity, String machineryState)
                throws RobotSafetyException {
            // Validation
            if (armPrecision < 0.0 || armPrecision > 1.0) {
                throw new RobotSafetyException("Error: Arm precision must be 0.0-1.0");
            }

            if (workerDensity < 1 || workerDensity > 20) {
                throw new RobotSafetyException("Error: Worker density must be 1-20");
            }

            double machineRiskFactor = getMachineryRiskFactor(machineryState);

            // Calculate Hazard Risk
            return ((1.0 - armPrecision) * 15.0) + (workerDensity * machineRiskFactor);
        }

        /**
         * Determines the risk factor based on the machinery state.
         *
         * @param machineryState The state string (Worn, Faulty, Critical).
         * @return The corresponding risk factor.
         * @throws RobotSafetyException If the state is unsupported.
         */
        private double getMachineryRiskFactor(String machineryState) throws RobotSafetyException {
            switch (machineryState) {
                case "Worn":
                    return 1.3;
                case "Faulty":
                    return 2.0;
                case "Critical":
                    return 3.0;
                default:
                    throw new RobotSafetyException("Error: Unsupported machinery state");
            }
        }
    }

    /**
     * RobotSafetyException
     *
     * Custom exception to handle validation errors in the Factory Robot Hazard
     * Analyzer.
     */
    public static class RobotSafetyException extends Exception {
        public RobotSafetyException(String message) {
            super(message);
        }
    }
}
