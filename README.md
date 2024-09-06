# ABCG Calculator

This ABCG Calculator is a simple Java Swing-based GUI application designed to calculate the rebalancing threshold for LogicMonitor collectors. It allows users to input the total number of instances in an ABCG and the number of collectors in the ABCG while choosing a predefined collector size. The program computes an ideal rebalancing threshold based on the input values.

## Features

- **Input Fields**:
  - **Total Instances**: Enter the total number of instances you want to balance.
  - **Number of Collectors**: Enter the number of collectors used for data collection.
  
- **Collector Size**:
  - Predefined collector sizes are available via a drop-down menu:
    - Small (1 GB)
    - Medium (2 GB)
    - Large (4 GB)
    - XL (8 GB)

- **Calculation**:
  - The application calculates the rebalancing threshold by dividing the total instances by the number of collectors, then scales the result based on the collector size.

- **Error Handling**:
  - Displays an error message if the user inputs invalid numbers.

## How to Use

1. Run the `ABCGCalculator` class.
2. In the GUI window:
   - Enter the **Total Instances**.
   - Enter the **Number of Collectors**.
   - Select the **Collector Size** from the drop-down list.
3. Click the **Calculate** button to see the rebalancing threshold result.
4. The rebalancing threshold will be displayed in the output field.

## How to Compile, Package, and Run

1. Ensure you have JDK installed.
2. Open your terminal and navigate to the directory where the `ABCGCalculator.java` file is located:

```cd /path/to/your/java/file```

3. Compile the Java file using the following command:

```javac ABCGCalculator.java```

4. Package the compiled `.class` files into a JAR file:

```jar cfe ABCGCalculator.jar ABCGCalculator *.class```

5. Run the JAR file using the following command:

```java -jar ABCGCalculator.jar```

This will launch the ABCG Calculator GUI application.
