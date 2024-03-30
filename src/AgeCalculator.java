import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeCalculator extends JFrame {
    private JPanel mainPanel;
    private JTextField birthDateField;
    private JButton calculateButton, newEntryButton, closeButton;
    private JLabel ageLabel, instructionLabel;
    
    public AgeCalculator() {
        // Setting up the JFrame
        setTitle("Age Calculator");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initializing components
        mainPanel = new JPanel();
        birthDateField = new JTextField(10);
        calculateButton = new JButton("Calculate Age");
        newEntryButton = new JButton("New Entry");
        closeButton = new JButton("Close");
        instructionLabel = new JLabel("Enter your birth date (YYYY-MM-DD): ");
        ageLabel = new JLabel("");
        
        // Initial Panel Setup
        setupInitialPanel();
        
        // Adding the panel to the frame and setting visibility
        add(mainPanel);
        setVisible(true);
    }
    
    private void setupInitialPanel() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        
        mainPanel.add(instructionLabel);
        mainPanel.add(birthDateField);
        mainPanel.add(calculateButton);
        mainPanel.add(closeButton);
        
        // Adding ActionListener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayAge();
            }
        });
        
        // ActionListener for the close button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    private void setupResultPanel() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        
        mainPanel.add(ageLabel);
        mainPanel.add(newEntryButton);
        mainPanel.add(closeButton);
        
        // ActionListener for the new entry button
        newEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                birthDateField.setText("");
                setupInitialPanel();
            }
        });
    }
    
    private void calculateAndDisplayAge() {
        String birthDateString = birthDateField.getText();
        try {
            LocalDate birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(birthDate, currentDate).getYears();
            ageLabel.setText("Your age is: " + age);
            setupResultPanel(); // Update the panel to show the result and options
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            birthDateField.setText("");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgeCalculator());
    }
}
