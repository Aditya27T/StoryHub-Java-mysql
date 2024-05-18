/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User.Model;

/**
 *
 * @author Administrator
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int role;
    // add a timestamp for the last time the user logged in
    private int timestamp;


    private User(Integer id, String username, String password, String email, Integer role, Integer timestamp) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public static class Builder {
        private int id;
        private String username;
        private String password;
        private String email;
        private int role;
        private int timestamp;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setRole(int role) {
            this.role = role;
            return this;
        }

        public Builder setTimestamp(int timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public User build() {
            return new User(this.id, this.username, this.password, this.email, this.role, this.timestamp);
        }
    }

    @Override
    public String toString() {
        String [] end = {"User", "Admin"};

        String role = end[this.role];
        String status = "Username: " + this.username + "\n" +
                        "Password: " + this.password + "\n" +
                        "Email: " + this.email + "\n" +
                        "Role: " + role + "\n";
        if (this.role == 0) {
            status += "User";
        } else {
            status += "Admin";
        }
        return status;
    }
}