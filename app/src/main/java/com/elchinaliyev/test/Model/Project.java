package com.elchinaliyev.test.Model;

import androidx.annotation.NonNull;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "project",
        foreignKeys = @ForeignKey(
                entity = Contact.class,
                parentColumns = "id",
                childColumns = "contactId",
                onDelete = CASCADE),
        indices = @Index("contactId"))
public class Project {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long contactId;
    private String name;

    public Project() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
