import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ABCGCalculator {
    public static void main(String[] args) {
        // Create a new frame for the calculator
        JFrame frame = new JFrame("ABCG Calculator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the input fields and labels
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        // Create and place input fields and labels
        JLabel instancesLabel = new JLabel("Total Instances:");
        instancesLabel.setBounds(10, 20, 120, 25);
        panel.add(instancesLabel);

        JTextField instancesText = new JTextField(20);
        instancesText.setBounds(150, 20, 165, 25);
        panel.add(instancesText);

        JLabel collectorsLabel = new JLabel("Number of Collectors:");
        collectorsLabel.setBounds(10, 50, 150, 25);
        panel.add(collectorsLabel);

        JTextField collectorsText = new JTextField(20);
        collectorsText.setBounds(150, 50, 165, 25);
        panel.add(collectorsText);

        // ComboBox for predefined collector sizes
        JLabel memoryLabel = new JLabel("Collector Size (S, M, L, XL):");
        memoryLabel.setBounds(10, 80, 180, 25);
        panel.add(memoryLabel);

        String[] collectorSizes = {"Small (1 GB)", "Medium (2 GB)", "Large (4 GB)", "XL (8 GB)"};
        JComboBox<String> sizeComboBox = new JComboBox<>(collectorSizes);
        sizeComboBox.setBounds(200, 80, 165, 25);
        panel.add(sizeComboBox);

        // Button to trigger the calculation
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 120, 150, 25);
        panel.add(calculateButton);

        // Output fields
        JLabel resultLabel = new JLabel("Rebalancing Threshold:");
        resultLabel.setBounds(10, 150, 200, 25);
        panel.add(resultLabel);

        JTextField resultText = new JTextField(20);
        resultText.setBounds(200, 150, 165, 25);
        resultText.setEditable(false);
        panel.add(resultText);

        // Action listener for button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double totalInstances = Double.parseDouble(instancesText.getText());
                    double numCollectors = Double.parseDouble(collectorsText.getText());

                    // Get the selected JVM memory size from the dropdown
                    String selectedSize = (String) sizeComboBox.getSelectedItem();
                    double collectorMemory;

                    switch (selectedSize) {
                        case "Small (1 GB)":
                            collectorMemory = 1.0;
                            break;
                        case "Medium (2 GB)":
                            collectorMemory = 2.0;
                            break;
                        case "Large (4 GB)":
                            collectorMemory = 4.0;
                            break;
                        case "XL (8 GB)":
                            collectorMemory = 8.0;
                            break;
                        default:
                            collectorMemory = 2.0;  // Default to Medium if unknown
                    }

                    // Perform the calculations
                    double idealSplit = totalInstances / numCollectors;
                    double memoryScaling = Math.sqrt(collectorMemory / 2.0);

                    // Format memory scaling to 8 decimal places
                    String formattedMemoryScaling = String.format("%.8f", memoryScaling);

                    // Calculate and round rebalancing threshold
                    double rebalancingThreshold = idealSplit / memoryScaling;
                    long roundedThreshold = Math.round(rebalancingThreshold);

                    // Display the result (rebalancing threshold as a whole number)
                    resultText.setText(Long.toString(roundedThreshold));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.");
                }
            }
        });

        // Set the frame to be visible
        frame.setVisible(true);
    }
}
