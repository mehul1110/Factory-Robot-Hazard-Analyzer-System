/**
 * RobotSafetyException
 *
 * Custom exception to handle validation errors in the Factory Robot Hazard
 * Analyzer.
 */
public class RobotSafetyException extends Exception {
    public RobotSafetyException(String message) {
        super(message);
    }
}
