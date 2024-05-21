/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Story.Dao;

import Story.Models.Story;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface StoryInterface {
    /*
     * Create a new story
     * @param story
     * @return 
     * @throws java.lang.Exception
     */
    public void create(Story story) throws Exception;
    
    /*
     * Update a story
     * @param story
     * @return 
     * @throws java.lang.Exception
     */
    public void update(Story story) throws Exception;

    /*
     * Delete a story
     * @param story
     * @return 
     * @throws java.lang.Exception
     */
    public void delete(Story story) throws Exception;

    /*
     * Get all stories
     * @return 
     * @throws java.lang.Exception
     */
    public List<Story> getAll() throws Exception;

    /*
     * Get all stories if status is 1
     * @return
     * @throws java.lang.Exception
     */
    public List<Story> getAllPosted() throws Exception;

    /*
     * Get a story by id
     * @param id
     * @return 
     * @throws java.lang.Exception
     */
    public Story getById(int id) throws Exception;

    /*
     * Get a story by title
     * @param title
     * @return 
     * @throws java.lang.Exception
     */
    public List<Story> search(String search) throws Exception;

    /*
     * Get a story by user_id
     * @param user_id
     * @return 
     * @throws java.lang.Exception
     */
    public List<Story> getByUserId(int user_id) throws Exception;

    /*
     * Get a story by status
     * @param status
     * @return 
     * @throws java.lang.Exception
     */
    public List<Story> getByStatus(int status) throws Exception;

    /*
     * Update a story status
     * @param story
     * @return
     * @throws java.lang.Exception
     */
    public void updateStatus(int id, int status) throws Exception;
}
