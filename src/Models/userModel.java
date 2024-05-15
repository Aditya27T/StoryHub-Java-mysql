/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Administrator
 */

public class userModel {
    private int id;
    private String username;
    private String password;
    private enum role {
        Admin,
        User
    }
    private role Role;

    private String queryAddUser = "INSERT INTO users (username, password, role) VALUES ('" + username + "', '" + password + "', '" + Role + "')";
    private String queryGetUserById = "SELECT * FROM users WHERE id = " + id;
    private String queryGetAllUsers = "SELECT * FROM users";
    private String queryUpdateUser = "UPDATE users SET username = '" + username + "', password = '" + password + "', role = '" + Role + "' WHERE id = " + id;
    private String queryDeleteUser = "DELETE FROM users WHERE id = " + id;
    private String queryLoginHandler = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

    public userModel(int id, String username, String password, role Role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.Role = Role;
    }

    public userModel(String username, String password, role Role) {
        this.username = username;
        this.password = password;
        this.Role = Role;
    }

    public userModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public userModel(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public role getRole() {
        return Role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(role Role) {
        this.Role = Role;
    }

    public String getQueryAddUser() {
        return queryAddUser;
    }

    public String getQueryGetUserById() {
        return queryGetUserById;
    }

    public String getQueryGetAllUsers() {
        return queryGetAllUsers;
    }

    public String getQueryUpdateUser() {
        return queryUpdateUser;
    }

    public String getQueryDeleteUser() {
        return queryDeleteUser;
    }

    public String getQueryLoginHandler() {
        return queryLoginHandler;
    }
}