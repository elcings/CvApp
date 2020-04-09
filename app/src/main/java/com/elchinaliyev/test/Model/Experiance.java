package com.elchinaliyev.test.Model;

import java.util.Date;
import androidx.annotation.NonNull;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "experiance",
        foreignKeys = @ForeignKey(
                entity = Contact.class,
                parentColumns = "id",
                childColumns = "contactId",
                onDelete = CASCADE),
        indices = @Index("contactId"))
public class Experiance {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long contactId;
    private String position;
    private String company;
    private String location;
    private String startDate;
    private String endDate;
    private boolean isWork;



    public Experiance() {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }
}
