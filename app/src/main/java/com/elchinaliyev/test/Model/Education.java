package com.elchinaliyev.test.Model;

import java.util.Date;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "education",
        foreignKeys = @ForeignKey(
                entity = Contact.class,
                parentColumns = "id",
                childColumns = "contactId",
                onDelete = CASCADE),
        indices = @Index("contactId"))
public class Education {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    private long contactId;
    private String specialty;
    private String university;
    private String location;
    private String endDate;

    public Education() {
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
