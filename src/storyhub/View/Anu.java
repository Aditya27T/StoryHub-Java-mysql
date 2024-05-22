package storyhub.View;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import Story.Models.Story;
import Story.Dao.StoryController;
import java.sql.Timestamp;
import User.Dao.UserController;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;


public class Anu extends JFrame {


    private JTextArea outputField;
    private JTable table;
    private JButton BackButton;

    public Anu() {
        setTitle("My Post");

        setSize(700, 600);

        setLayout(null);

        setWindowColor(Color.BLUE);

        outputField = new JTextArea();
        outputField.setEditable(false); 
        outputField.setLineWrap(true); 
        outputField.setWrapStyleWord(true); 
        BackButton = new JButton("Back");

        BackButton.setBounds(getWidth() - 120, getHeight() - 80, 100, 30);
        outputField.setBounds(20, 240, 640, 200);

        BackButton.addActionListener(e -> {
            this.dispose();
            new Tampilan().setVisible(true);
        });

        add(BackButton);

        int user_id = UserController.getIdLoginSession();

        List<Story> stories = StoryController.getByUserId(user_id);

        table = new JTable();

        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID", "Title", "Create At", "Posted At", "Status", "Comment", "Show", "Edit", "Delete"
            }
        ) {
            Class[] types = new Class[] {
                java.lang.Integer.class, java.lang.String.class, java.sql.Timestamp.class, java.sql.Timestamp.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (Story s : stories) {
            String status = "";
            if (s.getStatus() == 0) {
                status = "PENDING";
            } else if (s.getStatus() == 1) {
                status = "APPROVED";
            } else if (s.getStatus() == 2) {
                status = "REJECTED";
            }
            model.addRow(new Object[]{s.getId(), s.getTitle(), s.getCreated_at(), s.getPosted_at(), status, s.getComment(), "Show", "Edit", "Delete"});
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setDefaultRenderer(Integer.class, centerRenderer);
        table.setDefaultRenderer(Timestamp.class, centerRenderer);
        table.setDefaultRenderer(Double.class, centerRenderer);
        table.setDefaultRenderer(Float.class, centerRenderer);
        table.setDefaultRenderer(Boolean.class, centerRenderer);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.setDefaultRenderer(Long.class, centerRenderer);
        table.setDefaultRenderer(Short.class, centerRenderer);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 6) {
                    int id = (int) table.getValueAt(row, 0);
                    Story story = StoryController.getById(id);
                    outputField.setText(story.getTitle() + "\n" + story.getDescription() + "\n" + story.getPosted_at() + "\n" + story.getComment());
                    if (story.getComment().equals("")) {
                        outputField.append("\nNo comment");
                    }
                    // if status is empty, show "No status"
                    if (story.getStatus() == 0) {
                        outputField.append("\nPENDING");
                    } else if (story.getStatus() == 1) {
                        outputField.append("\nAPPROVED");
                    } else if (story.getStatus() == 2) {
                        outputField.append("\nREJECTED");
                    }
                } else if (row >= 0 && col == 7) {
                    int id = (int) table.getValueAt(row, 0);
                    Story story = StoryController.getById(id);
                    editStory(story);
                    refreshTable(); 
                } else if (row >= 0 && col == 8) {
                    int id = (int) table.getValueAt(row, 0);
                    Story story = StoryController.getById(id);
                    StoryController.delete(story);
                    refreshTable(); 
                }
            }
        });

        table.setDefaultEditor(Object.class, null);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 20, 640, 200);

        JScrollPane outputScrollPane = new JScrollPane(outputField);
        outputScrollPane.setBounds(20, 240, 300, 400);

        add(tableScrollPane);
        add(outputScrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        customizeComponents();
    }

    private void customizeComponents() {
        outputField.setFont(new Font("Arial", Font.PLAIN, 14));
        outputField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setGridColor(Color.GRAY);
        table.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        table.getColumnModel().getColumn(6).setCellRenderer(new CustomTableCellRenderer(Color.GREEN));
        table.getColumnModel().getColumn(7).setCellRenderer(new CustomTableCellRenderer(Color.YELLOW));
        table.getColumnModel().getColumn(8).setCellRenderer(new CustomTableCellRenderer(Color.RED));
    }

    private void refreshTable() {
        int user_id = UserController.getIdLoginSession();

        List<Story> stories = StoryController.getByUserId(user_id);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Story s : stories) {
            String status = "";
            if (s.getStatus() == 0) {
                status = "PENDING";
            } else if (s.getStatus() == 1) {
                status = "APPROVED";
            } else if (s.getStatus() == 2) {
                status = "REJECTED";
            }
        
            if (s.getPosted_at () == null && s.getCreated_at() != null) {
                model.addRow(new Object[]{s.getId(), s.getTitle(), s.getCreated_at(), "Not posted yet", status, s.getComment(), "Show", "Edit", "Delete"});
            } else {
                model.addRow(new Object[]{s.getId(), s.getTitle(), s.getCreated_at(), s.getPosted_at(), status, s.getComment(), "Show", "Edit", "Delete"});
            }
        }
    }

    private void editStory(Story story) {
        String title = JOptionPane.showInputDialog("Enter the title", story.getTitle());
        String description = JOptionPane.showInputDialog("Enter the description", story.getDescription());
        try {
            story = new Story.Builder()
                .setId(story.getId())
                .setTitle(title)
                .setDescription(description)
                .setCreated_at(story.getCreated_at())
                .setPosted_at(story.getPosted_at())
                .setStatus(story.getStatus())
                .setComment(story.getComment())
                .setUser_id(story.getUser_id())
                .build();
            if (UserController.getById(story.getUser_id()) == null) {
                JOptionPane.showMessageDialog(null, "User does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                throw new Exception("User does not exist");
            } else if (StoryController.getById(story.getId()) == null) {
                JOptionPane.showMessageDialog(null, "Story does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Story does not exist");
            } else {
                StoryController.update(story);
                JOptionPane.showMessageDialog(null, "Story updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            refreshTable();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Anu frame = new Anu();
        frame.setVisible(true);
    }
    
    private class CustomTableCellRenderer extends DefaultTableCellRenderer {
        private Color color;
        
        public CustomTableCellRenderer(Color color) {
            this.color = color;
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cell.setBackground(color);
            return cell;
        }
    }
    
    private void setWindowColor(Color color) {
        getContentPane().setBackground(color = new Color(51,51,255));
    }
}