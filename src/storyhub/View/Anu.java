package storyhub.view;

import javax.swing.*;
import java.awt.*;

public class Anu extends JFrame {
    
    private JTextField searchField;
    private JButton searchButton;
    private JTextField outputField;
    private JTable table;
    
    public Anu() {
        // Set the title of the window
        setTitle("Simple GUI");
        
        // Set the size of the window
        setSize(720, 720);
        
        // Set the layout manager to null
        setLayout(null);
        
        // Initialize components
        searchField = new JTextField("Search..");
        searchButton = new JButton("Search");
        
        // Initialize output field
        outputField = new JTextField();
        outputField.setEditable(false); // Make the JTextField read-only
        
        // Initialize table
        String[] columnNames = {"Column 1", "Column 2", "Column 3", "Column 4", "Column 5"};
        Object[][] data = {
            {"JANCOK", "KONTOL", "ASU", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""}
        };
        table = new JTable(data, columnNames);
        
        // Set positions and sizes of components
        searchField.setBounds(10, 10, 490, 25);
        searchButton.setBounds(510, 10, 80, 25);
        
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 50, 660, 143);
        
        outputField.setBounds(20, 270, 660, 400);
        
        // Add components to the frame
        add(searchField);
        add(searchButton);
        add(tableScrollPane);
        add(outputField);
        
        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Additional customization for a social media timeline look
        customizeComponents();
    }
    
    private void customizeComponents() {
        // Customize JTextField for output area
        outputField.setFont(new Font("Arial", Font.PLAIN, 14));
        outputField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Customize JTable
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setGridColor(Color.GRAY);
        table.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public static void main(String[] args) {
        // Create the frame
        Anu frame = new Anu();
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
