import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ABCGCalculator {
    public static void main(String[] args) {
        // Create a new frame for the calculator
        JFrame frame = new JFrame("ABCG Calculator");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the input fields and labels
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        // Section 1: Base Balancing
        JLabel baseBalancingLabel = new JLabel("Base Balancing");
        baseBalancingLabel.setBounds(10, 10, 200, 25);
        panel.add(baseBalancingLabel);

        // Create and place input fields and labels for Base Balancing
        JLabel instancesLabel = new JLabel("Total Instances:");
        instancesLabel.setBounds(10, 40, 120, 25);
        panel.add(instancesLabel);

        JTextField instancesText = new JTextField(20);
        instancesText.setBounds(150, 40, 165, 25);
        panel.add(instancesText);

        JLabel collectorsLabel = new JLabel("Number of Collectors:");
        collectorsLabel.setBounds(10, 70, 150, 25);
        panel.add(collectorsLabel);

        JTextField collectorsText = new JTextField(20);
        collectorsText.setBounds(150, 70, 165, 25);
        panel.add(collectorsText);

        JLabel memoryLabel = new JLabel("Collector Size (S, M, L, XL, XXL):");
        memoryLabel.setBounds(10, 100, 180, 25);
        panel.add(memoryLabel);

        String[] collectorSizes = {"Small (1 GB)", "Medium (2 GB)", "Large (4 GB)", "XL (8 GB)", "XXL (16 GB)"};
        JComboBox<String> sizeComboBox = new JComboBox<>(collectorSizes);
        sizeComboBox.setBounds(200, 100, 165, 25);
        panel.add(sizeComboBox);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 140, 150, 25);
        panel.add(calculateButton);

        JLabel resultBaseLabel = new JLabel("Rebalancing Threshold (Base):");
        resultBaseLabel.setBounds(10, 170, 200, 25);
        panel.add(resultBaseLabel);

        JTextField resultBaseText = new JTextField(20);
        resultBaseText.setBounds(220, 170, 165, 25);
        resultBaseText.setEditable(false);
        panel.add(resultBaseText);

        // Section 2: Failover Balancing
        JLabel failoverBalancingLabel = new JLabel("Failover Balancing");
        failoverBalancingLabel.setBounds(10, 210, 200, 25);
        panel.add(failoverBalancingLabel);

        JLabel failedCollectorsLabel = new JLabel("Failed Collectors:");
        failedCollectorsLabel.setBounds(10, 240, 150, 25);
        panel.add(failedCollectorsLabel);

        // Default value of 1 for Failed Collectors
        JTextField failedCollectorsText = new JTextField("1", 20);
        failedCollectorsText.setBounds(150, 240, 165, 25);
        panel.add(failedCollectorsText);

        JLabel resultFailoverLabel = new JLabel("Rebalancing Threshold (Failover):");
        resultFailoverLabel.setBounds(10, 300, 220, 25);
        panel.add(resultFailoverLabel);

        JTextField resultFailoverText = new JTextField(20);
        resultFailoverText.setBounds(230, 300, 165, 25);
        resultFailoverText.setEditable(false);
        panel.add(resultFailoverText);

        // Action listener for calculating both Base and Failover Balancing
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get input values
                    double totalInstances = Double.parseDouble(instancesText.getText());
                    double numCollectors = Double.parseDouble(collectorsText.getText());
                    double failedCollectors = Double.parseDouble(failedCollectorsText.getText());

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
                        case "XXL (16 GB)":
                            collectorMemory = 16.0;
                            break;
                        default:
                            collectorMemory = 2.0;  // Default to Medium if unknown
                    }

                    // Base Balancing Calculation
                    double idealSplitBase = totalInstances / numCollectors;
                    double memoryScalingBase = Math.sqrt(collectorMemory / 2.0);
                    double rebalancingThresholdBase = idealSplitBase / memoryScalingBase;
                    long roundedThresholdBase = Math.round(rebalancingThresholdBase);
                    resultBaseText.setText(Long.toString(roundedThresholdBase));

                    // Failover Balancing Calculation
                    double remainingCollectors = numCollectors - failedCollectors;
                    if (remainingCollectors <= 0) {
                        JOptionPane.showMessageDialog(frame, "Number of failed collectors exceeds or equals total collectors.");
                        return;
                    }

                    double idealSplitFailover = totalInstances / remainingCollectors;
                    double memoryScalingFailover = Math.sqrt(collectorMemory / 2.0);
                    double rebalancingThresholdFailover = idealSplitFailover / memoryScalingFailover;
                    long roundedThresholdFailover = Math.round(rebalancingThresholdFailover);
                    resultFailoverText.setText(Long.toString(roundedThresholdFailover));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.");
                }
            }
        });

        // Set the frame to be visible
        frame.setVisible(true);
    }
}