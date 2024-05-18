/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User.Dao;

import User.Model.User;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class UserController {

    private static final UserDao userDao = UserDao.getInstance();

    public static void create(User user) throws Exception {
        userDao.create(user);
    }

    public static void update(User user) throws Exception {
        userDao.update(user);
    }

    public static void delete(User user) throws Exception {
        userDao.delete(user);
    }

    public static List<User> getAll() throws Exception {
        return userDao.getAll();
    }

    public static User getById(int id) throws Exception {
        return userDao.getById(id);
    }

    public static User getByUsername(String username) throws Exception {
        return userDao.getByUsername(username);
    }

    public static User getByEmail(String email) throws Exception {
        return userDao.getByEmail(email);
    }

    public static Boolean getRoleById(int id) throws Exception {
        return userDao.getRoleById(id);
    }

    public static User getByUsernameAndPassword(String username, String password) throws Exception {
        return userDao.getByUsernameAndPassword(username, password);
    }
    
}
