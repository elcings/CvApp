package com.elchinaliyev.test.Model;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity(tableName = "certificate")
public class Certificate {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int contactId;
    private String name;

    public Certificate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
