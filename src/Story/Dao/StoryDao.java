/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Story.Dao;

import ConnectionDB.mysql;
import Story.Models.Story;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Administrator
 */
public class StoryDao implements StoryInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static StoryDao INSTANCE = new StoryDao();
    
    public StoryDao() {
        connection = mysql.getConnection();
    }
    
    @Override
    public void create(Story story) throws Exception {
        connection = mysql.getConnection();
        String query = "INSERT INTO stories (title, description, create_at, posted_at, status, Commentar, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, story.getTitle());
            preparedStatement.setString(2, story.getDescription());
            preparedStatement.setTimestamp(3, story.getCreated_at());
            preparedStatement.setTimestamp(4, story.getPosted_at());
            preparedStatement.setInt(5, story.getStatus());
            preparedStatement.setString(6, story.getComment());
            preparedStatement.setInt(7, story.getUser_id());
            if (preparedStatement.executeUpdate() == 0) {
                throw new Exception("Creating story failed, no rows affected."); 
            } else {
                System.out.println("Story created successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Story story) throws Exception {
        try {
            String query = "UPDATE stories SET title = ?, description = ?, create_at = ?, posted_at = ?, status = ?, Commentar = ?, user_id = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, story.getTitle());
            preparedStatement.setString(2, story.getDescription());
            preparedStatement.setTimestamp(3, story.getCreated_at());
            preparedStatement.setTimestamp(4, story.getPosted_at());
            preparedStatement.setInt(5, story.getStatus());
            preparedStatement.setString(6, story.getComment());
            preparedStatement.setInt(7, story.getUser_id());
            preparedStatement.setInt(8, story.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new Exception("Updating story failed, no rows affected.");
            } else {
                System.out.println("Story updated successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Story story) throws Exception {
        String query = "DELETE FROM stories WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, story.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Story> getAll() throws Exception {
        List<Story> stories = new ArrayList<>();
        String query = "SELECT * FROM stories";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Story story = new Story(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getTimestamp("create_at"),
                    resultSet.getTimestamp("posted_at"),
                    resultSet.getInt("status"),
                    resultSet.getString("Commentar"),                  
                    resultSet.getInt("user_id")
            );
            stories.add(story);
        }
        return stories;
    }

    @Override
    public Story getById(int id) throws Exception {
        String query = "SELECT * FROM stories WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Story(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getTimestamp("create_at"),
                    resultSet.getTimestamp("posted_at"),
                    resultSet.getInt("status"),
                    resultSet.getString("Commentar"),
                    resultSet.getInt("user_id")
            );
        }
        return null;
    }

    @Override
    public List<Story> search(String search) throws Exception {
        List<Story> stories = new ArrayList<>();
        String query = "SELECT * FROM stories WHERE title LIKE ? OR description LIKE ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "%" + search + "%");
        preparedStatement.setString(2, "%" + search + "%");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Story story = new Story(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getTimestamp("create_at"),
                    resultSet.getTimestamp("posted_at"),
                    resultSet.getInt("status"),
                    resultSet.getString("Commentar"),
                    resultSet.getInt("user_id")
            );
            stories.add(story);
        }
        return stories;
    }

    @Override
    public List<Story> getByUserId(int user_id) throws Exception {
        List<Story> stories = new ArrayList<>();
        String query = "SELECT * FROM stories WHERE user_id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, user_id);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Story story = new Story(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getTimestamp("create_at"),
                    resultSet.getTimestamp("posted_at"),
                    resultSet.getInt("status"),
                    resultSet.getString("Commentar"),
                    resultSet.getInt("user_id")
            );
            stories.add(story);
        }
        return stories;
    }

    @Override
    public List<Story> getByStatus(int status) throws Exception {
        List<Story> stories = new ArrayList<>();
        String query = "SELECT * FROM stories WHERE status = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, status);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Story story = new Story(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getTimestamp("create_at"),
                    resultSet.getTimestamp("posted_at"),
                    resultSet.getInt("status"),
                    resultSet.getString("Commentar"),
                    resultSet.getInt("user_id")
            );
            stories.add(story);
        }
        return stories;
    }

    @Override
    public void updateStatus(int id, int status) throws Exception {
        String query = "UPDATE stories SET status = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, status);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Story> getAllPosted() throws Exception {
        List<Story> stories = new ArrayList<>();
        String query = "SELECT s.title, s.description, s.user_id, u.username FROM stories s INNER JOIN users u ON s.user_id = u.id WHERE s.status = 1";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Story story = new Story(
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("username")
            );
            stories.add(story);
        }
        return stories;
    }

    public static StoryDao getInstance() {
        return INSTANCE;
    }
}