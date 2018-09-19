package com.emirhan.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "todos")
public class Todo {
    @Id
    private ObjectId _id;

    private String name;
    private Date createdAt;
    private Date updatedAt;

    @DBRef
    private List<Item> items;

    public Todo() {
    }

    public Todo(ObjectId _id, String name, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.name = name;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
