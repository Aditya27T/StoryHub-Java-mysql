/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import Models.userModel;
import dao.user.UserDaoImpl;
import java.util.List;
import storyhub.utils.Bcrypt;

import storyhub.View.Login;
/**
 *
 * @author Administrator
 */
public class UserController {
    private UserDaoImpl userDao = new UserDaoImpl();
    
    public List<userModel> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    public userModel getUserById(int id) {
        return userDao.getUserById(id);
    }
    
    public void addUser(userModel user) {
        user.setPassword(Bcrypt.hashPassword(user.getPassword()));
        userDao.addUser(user);
    }
    
    public void updateUser(userModel user) {
        user.setPassword(Bcrypt.hashPassword(user.getPassword()));
        userDao.updateUser(user);
    }
    
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }
    
    public boolean loginHandler(String username, String password) {
        if (userDao.loginHandler(username, password)) {
            return true;
        } else {
            return false;
        }
    }
    
}
