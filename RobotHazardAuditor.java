/**
 * RobotHazardAuditor
 *
 * This class handles the business logic for analyzing robot hazards.
 * It strictly implements the CalculateHazardRisk method as per specifications.
 */
public class RobotHazardAuditor {

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
