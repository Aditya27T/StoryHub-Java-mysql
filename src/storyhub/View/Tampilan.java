package storyhub.view;

import javax.swing.*;
import java.awt.*;

public class Tampilan extends JFrame {
    
    private JTextField searchField;
    private JButton searchButton;
    private JButton profileButton;
    private JTextField dateField;
    private JTextArea postArea;
    private JButton postButton;
    private JTextField outputField; // Changed from JTextArea to JTextField
    private JButton refreshButton;
    
    public Tampilan() {
        // Set the title of the window
        setTitle("Simple GUI");
        
        // Set the size of the window
        setSize(720, 720);
        
        // Set the layout manager to null
        setLayout(null);
        
        // Initialize components
        searchField = new JTextField("Search..");
        searchButton = new JButton("Search");
        profileButton = new JButton("My Post");
        
        dateField = new JTextField("Today");
        postArea = new JTextArea("I like this anime!");
        postButton = new JButton("Post");
        refreshButton = new JButton("Refresh");
        
        // Initialize output field
        outputField = new JTextField();
        outputField.setEditable(false); // Make the JTextField read-only
        
        // Set positions and sizes of components
        searchField.setBounds(10, 10, 490, 25);
        searchButton.setBounds(510, 10, 80, 25);
        profileButton.setBounds(600, 10, 80, 25);
        
        dateField.setBounds(10, 50, 100, 25);
        postArea.setBounds(120, 50, 470, 70);
        postButton.setBounds(600, 50, 80, 25);
        refreshButton.setBounds(600, 80, 80, 25);
        
        outputField.setBounds(20, 150, 660, 500);
        
        // Add components to the frame
        add(searchField);
        add(searchButton);
        add(profileButton);
        add(dateField);
        add(postArea);
        add(postButton);
        add(refreshButton);
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
        
        // Customize post area
        postArea.setFont(new Font("Arial", Font.PLAIN, 14));
        postArea.setLineWrap(true);
        postArea.setWrapStyleWord(true);
        postArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public static void main(String[] args) {
        // Create the frame
        Tampilan frame = new Tampilan();
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
