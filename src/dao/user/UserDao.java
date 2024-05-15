/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.user;

import Models.userModel;
import java.util.List; 
/**
 *
 * @author Administrator
 */
public interface UserDao {
    public List<userModel> getAllUsers();
    public userModel getUserById(int id);
    public void addUser(userModel user);
    public void updateUser(userModel user);
    public void deleteUser(int id);
    public boolean loginHandler(String username, String password);
}
