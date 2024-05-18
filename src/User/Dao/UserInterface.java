/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package User.Dao;

import User.Model.User;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface UserInterface {
    /*
     * Create a new user
     * @param user
     * @return 
     * @throws java.lang.Exception
     */

    public void create(User user) throws Exception;

    /*
     * Update a user
     * @param user
     * @return 
     * @throws java.lang.Exception
     */

    public void update(User user) throws Exception;

    /*
     * Delete a user
     * @param user
     * @return 
     * @throws java.lang.Exception
     */

    public void delete(User user) throws Exception;

    /*
     * Get all users
     * @return 
     * @throws java.lang.Exception
     */

    public List<User> getAll() throws Exception;

    /*
     * Get a user by id
     * @param id
     * @return 
     * @throws java.lang.Exception
     */
    public User getById(int id) throws Exception;


    /*
     * Get a user by username
     * @param username
     * @return 
     * @throws java.lang.Exception
     */

    public User getByUsername(String username) throws Exception;

    /*
     * Get a user by email
     * @param email
     * @return 
     * @throws java.lang.Exception
     */

    public User getByEmail(String email) throws Exception;

    /*
     * Get a user by username and password
     * @param username
     * @param password
     * @return 
     * @throws java.lang.Exception
     */

    public User getByUsernameAndPassword(String username, String password) throws Exception;

    /*
     * Get a password by id
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public String getPassword(int id) throws Exception;
    
    /*
     * Get a Role by id
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public Boolean getRole(int id) throws Exception;
}
