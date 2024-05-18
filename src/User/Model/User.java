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


    private User(Integer id, String username, String password, String email, Integer role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public static class Builder {
        private int id;
        private String username;
        private String password;
        private String email;
        private int role;

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

        public User build() {
            return new User(this.id, this.username, this.password, this.email, this.role);
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