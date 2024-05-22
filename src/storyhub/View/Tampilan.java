package storyhub.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Story.Models.Story;
import Story.Models.Story.Builder;
import Story.Dao.StoryController;
import User.Dao.UserController;
import java.sql.Timestamp;

public class Tampilan extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JButton mypost;
    private JTextField dateField;
    private JTextField postArea;
    private JButton postButton;
    private JTextArea outputField;
    private JButton refreshButton;
    private JButton logoutButton;

    public Tampilan() {
        setTitle("Simple GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 720);

        setWindowColor(Color.BLUE);

        // Create a panel to hold the input components
        JPanel inputPanel = new JPanel(new FlowLayout());
        searchField = new JTextField("Search..", 20);
        searchButton = new JButton("Search");
        mypost = new JButton("My Post");
        dateField = new JTextField("Today", 10);
        postArea = new JTextField("I like this anime!", 30);
        postButton = new JButton("Post");
        logoutButton = new JButton("Logout");
        refreshButton = new JButton("Refresh");
        inputPanel.add(searchField);
        inputPanel.add(searchButton);
        inputPanel.add(mypost);
        inputPanel.add(dateField);
        inputPanel.add(postArea);
        inputPanel.add(postButton);
        inputPanel.add(refreshButton);
        inputPanel.add(logoutButton);

        outputField = new JTextArea();
        outputField.setEditable(false);
        outputField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        outputField.setLineWrap(true);
        outputField.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(outputField);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        outputField.addMouseWheelListener(e -> {
            if (e.isControlDown()) {
                Font font = outputField.getFont();
                float size = font.getSize() + e.getUnitsToScroll();
                outputField.setFont(font.deriveFont(size));
            }
        });

        searchField.setBounds(10, 10, 490, 25);
        searchButton.setBounds(510, 10, 80, 25);
        mypost.setBounds(600, 10, 80, 25);

        dateField.setBounds(10, 50, 100, 25);
        postArea.setBounds(120, 50, 470, 70);
        postButton.setBounds(600, 50, 80, 25);
        refreshButton.setBounds(600, 80, 80, 25);
        logoutButton.setBounds(600, 110, 80, 25);

        scrollPane.setBounds(20, 150, 660, 500);

        add(searchField);
        add(searchButton);
        add(mypost);
        add(dateField);
        add(postArea);
        add(postButton);
        add(refreshButton);
        add(scrollPane);
        add(logoutButton);

        setLayout(null);
        setVisible(true);

        outputField.addMouseWheelListener(e -> {
            if (e.isControlDown()) {
                Font font = outputField.getFont();
                float size = font.getSize() + e.getUnitsToScroll();
                outputField.setFont(font.deriveFont(size));
            }
        });

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            try {
                List<Story> stories = StoryController.search(searchText);

                StringBuilder output = new StringBuilder();

                for (Story story : stories) {
                    output.append(story.getTitle()).append("\n");
                    output.append(story.getDescription()).append("\n");
                    try {
                        output.append("Posted by: ").append(UserController.getById(story.getUser_id()).getUsername())
                                .append("\n");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    if (story.getPosted_at() != null) {
                        output.append("Posted at: ").append(Timestamp.valueOf(story.getPosted_at().toString()))
                                .append("\n---------------------------------------------\n");
                    } else {
                        output.append("Posted at: ").append("Not posted yet")
                                .append("\n --------------------------------\n");
                    }
                }

                outputField.setText(output.toString());
                outputField.setCaretPosition(outputField.getDocument().getLength());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        mypost.addActionListener(e -> {
            Anu mypost = new Anu();
            mypost.setVisible(true);
            setVisible(false);

        });

        logoutButton.addActionListener(e -> {
            UserController.logout();
            new Login().setVisible(true);
            setVisible(false);
        });

        postButton.addActionListener(e -> {
            String postText = postArea.getText();
            String dateText = dateField.getText();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            int userId = UserController.getIdLoginSession();
            try {
                Story story = new Builder()
                        .setTitle(dateText)
                        .setDescription(postText)
                        .setCreated_at(Timestamp.valueOf(timestamp.toString()))
                        .setStatus(0)
                        .setComment("")
                        .setUser_id(userId)
                        .build();

                if (UserController.getById(story.getUser_id()) == null) {
                    JOptionPane.showMessageDialog(null, "User does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new Exception("User does not exist");
                } else {
                    StoryController.create(story);
                }

                refreshOutput();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        refreshButton.addActionListener(e -> refreshOutput());

        customizeComponents();
    }

    private void customizeComponents() {
        outputField.setFont(new Font("Arial", Font.PLAIN, 14));
        outputField.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        postArea.setFont(new Font("Arial", Font.PLAIN, 14));
        postArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void refreshOutput() {
        List<Story> stories = StoryController.getAllPosted();
        StringBuilder output = new StringBuilder();
    
        for (Story story : stories) {
            output.append(story.getTitle()).append("\n");
            output.append(story.getDescription()).append("\n");
            output.append("Status: ");
            try {
                output.append("Posted by: ").append(UserController.getById(story.getUser_id()).getUsername())
                        .append("\n");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (story.getPosted_at() != null) {
                Timestamp postedAt = Timestamp.valueOf(story.getPosted_at().toString());
                output.append("Posted at: ").append(postedAt).append("\n---------------------------------------------\n");
            } else {
                output.append("Posted at: ").append("Not posted yet").append("\n--------------------------------\n");
            }
        }
    
        outputField.setText(output.toString());
        outputField.setCaretPosition(outputField.getDocument().getLength());
    }

    private void setWindowColor(Color color) {
        getContentPane().setBackground(color = new Color(51, 51, 255));
    }

    public static void main(String[] args) {
        Tampilan tampilan = new Tampilan();
        tampilan.setVisible(true);
        tampilan.refreshOutput();

    }
}