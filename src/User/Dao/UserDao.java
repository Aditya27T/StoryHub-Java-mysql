/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User.Dao;

import ConnectionDB.mysql;
import User.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import storyhub.utils.Bcrypt;

/**
 *
 * @author Administrator
 */
public class UserDao implements UserInterface{
    private static Connection connection = null;
    private static UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    @Override
    public void create(User user) throws Exception {
        connection = mysql.getConnection();
        connection.setAutoCommit(false); // Disable autocommit

        String query = "INSERT INTO users (username, password, email, role, timestamp)";
        query += " VALUES (?, ?, ?, ?, NOW())";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, Bcrypt.hashPassword(user.getPassword()));
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole());

            if (ps.executeUpdate() >= 1) {
                System.out.println("User created successfully");
                connection.commit();
            } else {
                System.out.println("User creation failed");
                try {
                    connection.rollback();
                } catch (Exception e) {
                    throw new Exception("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @Override
    public void update(User user) throws Exception {
        connection = mysql.getConnection();
        String query = "UPDATE users SET username = ?, password = ?, email = ?, role = ?, timestamp = NOW()";
        query += " WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole());
            ps.setInt(5, user.getId());
            
            if (ps.executeUpdate() >= 1) {
                System.out.println("User updated successfully");
                connection.commit();


            } else {
                System.out.println("User update failed");
                try {
                    connection.rollback();
    
    
                } catch (Exception e) {
                    throw new Exception("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(User user) throws Exception {
        connection = mysql.getConnection();
        String query = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, user.getId());
            
            if (ps.executeUpdate() >= 1) {
                System.out.println("User deleted successfully");
                connection.commit();


            } else {
                System.out.println("User deletion failed");
                try {
                    connection.rollback();
    
    
                } catch (Exception e) {
                    throw new Exception("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .setTimestamp(rs.getTimestamp("timestamp"))
                        .build();
                users.add(user);
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return users;
    }

    @Override
    public User getById(int id) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .setTimestamp(rs.getTimestamp("timestamp"))
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User getByUsername(String username) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .setTimestamp(rs.getTimestamp("timestamp"))
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User getByEmail(String email) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, Bcrypt.hashPassword(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .build();
            } else {
                throw new Exception("User not found");
            } 
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public Boolean getRole(int id) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT role FROM users WHERE id = ?";
        Boolean role = false;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            // if role 1 is admin, else user 0 and except that is false
            if (rs.next()) {
                role = rs.getInt("role") == 1;
            } else if (rs.getInt("role") == 0) {
                role = false;
            } else {
                throw new Exception("Role not found");
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return role;
    }

    @Override
    public String getPassword(int id) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT password FROM users WHERE id = ?";
        String password = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            } else {
                throw new Exception("Password not found");
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return password;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
