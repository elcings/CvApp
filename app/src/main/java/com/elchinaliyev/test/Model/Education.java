package com.elchinaliyev.test.Model;

import java.util.Date;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;




@Entity(tableName = "education")
public class Education {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private int contactId;
    private String degree;
    private String university;
    private String location;
    private String endDate;

    public Education() {
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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
