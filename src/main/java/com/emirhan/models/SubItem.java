package com.emirhan.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "subitems")
public class SubItem {

    @Id
    private ObjectId _id;

    private String name;
    private String description;
    private Date deadline;
    private boolean isCompleted;
    private Date createdAt;
    private Date updatedAt;


    public SubItem() {
    }

    public SubItem(ObjectId _id, String name, String description, Date deadline, boolean isCompleted, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

