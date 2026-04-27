

# Factory Robot Hazard Analyzer System


This project implements a **Robot Hazard Analyzer** using strict Object-Oriented Programming (OOP) principles and Design Patterns. It validates robot safety parameters and calculates a hazard risk score based on specified business logic.

## 🚀 Features

- **Strict Input Validation**: Ensures data integrity with custom exception handling.
- **Encapsulation**: Business logic is strictly contained within the `RobotHazardAuditor` class.
- **Separation of Concerns**: Clear distinction between the UI (Main), Logic (Auditor), and Model (Safety Data).
- **Maintainability**: Easy to extend with new machinery states or risk calculation formulas.

## 🛠️ Setup & Installation

### Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher.

### Build & Run

1.  **Clone the repository** (if applicable).
2.  **Compile**: 
    ```bash
    javac *.java
    ```
3.  **Run**: 
    ```bash
    java FactoryRobotHazardAnalyzer
    ```

## 🎯 Usage

The application will prompt you to enter robot safety parameters.

```
Factory Robot Hazard Analyzer
Enter Arm Precision (0.0-1.0): 0.85
Enter Worker Density (1-20): 5
Enter Machinery State (Worn/Faulty/Critical): Worn
```

### Sample Output

```
--- Hazard Analysis Result ---
Hazard Risk Score: 10.00
```

## 📐 Business Logic & Formulas

### 1. Hazard Risk Calculation

The core logic is implemented in `RobotHazardAuditor.calculateHazardRisk()`:

```java
return ((1.0 - armPrecision) * 15.0) + (workerDensity * machineRiskFactor);
```

### 2. Machinery Risk Factors

The risk factor depends on the machinery state:

| State | Risk Factor |
| :--- | :--- |
| `Worn` | 1.3 |
| `Faulty` | 2.0 |
| `Critical` | 3.0 |

## 🧩 Class Structure

### `FactoryRobotHazardAnalyzer.java`
- **Role**: Main entry point (UI).
- **Responsibility**: Handles user interaction and displays results.

### `RobotHazardAuditor.java`
- **Role**: The Business Logic Layer.
- **Responsibility**: Validates input and calculates the hazard score.

### `RobotSafetyException.java`
- **Role**: The Exception Layer.
- **Responsibility**: Custom exception for handling validation errors, ensuring "Fail Fast".


