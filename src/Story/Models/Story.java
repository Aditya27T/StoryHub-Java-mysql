/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Story.Models;

import java.sql.Timestamp;

/**
 *
 * @author Administrator
 */
public class Story {
    private int id;
    private String title;
    private String description;
    private int status;
    private String comment;
    private int user_id;
    private String username;

    private Timestamp created_at;
    private Timestamp posted_at;

    public Story(int id, String title, String description, Timestamp created_at, Timestamp posted_at, int status, String comment, int user_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.posted_at = posted_at;
        this.status = status;
        this.comment = comment;
        this.user_id = user_id;
    }

    public Story(String title, String description,int user_id, String username, Timestamp posted_at) {
        this.title = title;
        this.description = description;
        this.user_id = user_id;
        this.posted_at = posted_at;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getPosted_at() {
        return posted_at;
    }

    public static class Builder {
        private int id;
        private String title;
        private String description;
        private int status;
        private String comment;
        private int user_id;
        private Timestamp created_at;
        private Timestamp posted_at;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setUser_id(int user_id) {
            this.user_id = user_id;
            return this;
        }

        public Builder setCreated_at(Timestamp created_at) {
            this.created_at = created_at;
            return this;
        }

        public Builder setPosted_at(Timestamp posted_at) {
            this.posted_at = posted_at;
            return this;
        }

        public Story build() {
            return new Story(id, title, description, created_at, posted_at, status, comment, user_id);
        }
    }

    @Override
    public String toString() {
        String [] end = {"Pending", "Approved", "Rejected"};
        String status = end[this.status];
        String result = "Title: " + this.title + "\n" +
                        "Description: " + this.description + "\n" +
                        "Status: " + status + "\n" +
                        "Comment: " + this.comment + "\n" +
                        "Created at: " + this.created_at + "\n" +
                        "Posted at: " + this.posted_at + "\n" +
                        "User ID: " + this.user_id + "\n";

        if (this.id != 0) {
            result += "ID: " + this.id + "\n";
        } else {
            result += "ID: Not set\n";
        }

        if (this.status == 0) {
            result += "Status: Pending\n";
        } else if (this.status == 1) {
            result += "Status: Approved\n";
        } else if (this.status == 2) {
            result += "Status: Rejected\n";
        } else {
            result += "Status: Not set\n";
        }

        return result;
    }
    
}
