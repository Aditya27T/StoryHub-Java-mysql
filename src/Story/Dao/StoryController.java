/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Story.Dao;

import Story.Models.Story;

import java.util.List;
import javax.swing.JOptionPane;
import User.Dao.UserController;

/**
 *
 * @author Administrator
 */
public class StoryController {
    private static final StoryDao storyDao = StoryDao.getInstance();
    /*
     * userFeature
     * Get all posted stories
     * Get a story by id
     * Get a story by title
     * Create a new story
     * Update a story
     */

    public static List<Story> getAllPosted() {
        try {
            return storyDao.getAllPosted();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public static Story getById(int id) {
        try {
            return storyDao.getById(id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public static List<Story> search(String search) {
        try {
            return storyDao.search(search);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public static void create(Story story) {
        try {
            if (UserController.getById(story.getUser_id()) == null) {
                JOptionPane.showMessageDialog(null, "User does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                throw new Exception("User does not exist");
            } else {
                JOptionPane.showMessageDialog(null, "Story created successfully, Waiting for admin approval", "Success", JOptionPane.INFORMATION_MESSAGE);
                storyDao.create(story);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void update(Story story) {
        try {
            storyDao.update(story);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void delete(Story story) {
        try {
            storyDao.delete(story);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static List<Story> getByUserId(int user_id) {
        try {
            return storyDao.getByUserId(user_id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }


    /*
    * AdminFeature
    * Delete a story
    * Get all stories
    * Get a story by id
    * update a story
    * get by status
    * update status
    */

    public static List<Story> getAll() {
        try {
            return storyDao.getAll();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public static void updateStatus(int id, int status) {
        try {
            storyDao.updateStatus(id, status);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static List<Story> getByStatus(int status) {
        try {
            return storyDao.getByStatus(status);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public static void deleteStory(Story story) {
        try {
            storyDao.delete(story);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static Story getStoryById(int id) {
        try {
            return storyDao.getById(id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public static void updateStory(Story story) {
        try {
            storyDao.update(story);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public static void main(String[] args) {
        Story story = new Story.Builder()
                .setTitle("Test")
                .setDescription("Test")
                .setUser_id(1)
                .build();
        create(story);
    }
}