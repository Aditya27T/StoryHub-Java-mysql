/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.user;

import DB.mysqlConnection;
import Models.userModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class UserDaoImpl implements UserDao {
    @Override 
    public List<userModel> getAllUsers() {
        return null;
    }

    @Override
    public userModel getUserById(int id) {
        return null;
    }

    @Override
    public void addUser(userModel user) {
        mysqlConnection db = new mysqlConnection();
        Connection conn = db.getConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(user.getQueryAddUser());
            System.out.println("User added kontol successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateUser(userModel user) {
        mysqlConnection db = new mysqlConnection();
        Connection conn = db.getConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(user.getQueryUpdateUser());
            System.out.println("User updated successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
        mysqlConnection db = new mysqlConnection();
        userModel user = new userModel(id);
        Connection conn = db.getConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(user.getQueryDeleteUser());
            System.out.println("User deleted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean loginHandler(String username, String password) {
        mysqlConnection db = new mysqlConnection();
        Connection conn = db.getConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM users WHERE username = " + username + " AND password = " + password);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
