package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public interface contactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);
    @Update
    void update(Contact contact);
    @Delete
    void delete(Contact contact);
    @Query("DELETE FROM contact")
    void deleteAllContact();
    @Query("SELECT * FROM contact WHERE id > :id")
    LiveData<Contact> getById(int id);
    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAllContact();
    @Query("SELECT id FROM contact order by id DESC limit 1")
    LiveData<Integer> getTopId();
    @Transaction
    @Query("SELECT * FROM contact")
    public LiveData<List<ContactWithDetail>> getContactWithDetail();
}
