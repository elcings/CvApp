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
public abstract class contactDao implements BaseDao<Contact> {


    @Query("SELECT * FROM contact WHERE id > :id")
    public abstract Contact getById(int id);

    @Query("SELECT id FROM contact order by id DESC limit 1")
    public abstract int getTopId();

    @Query("SELECT * FROM contact")
    public abstract LiveData<List<Contact>> getAllContacts();

    @Transaction
    @Query("SELECT * FROM contact where id=:contactId")
    public abstract ContactWithDetail getContactWithDetailById(int contactId);

   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);
    @Update
    void update(Contact contact);
    @Delete
    void delete(Contact contact);
    @Query("DELETE FROM contact")
    void deleteAllContact();
    @Query("SELECT * FROM contact WHERE id > :id")
    Contact getById(int id);
    @Query("SELECT id FROM contact order by id DESC limit 1")
    int getTopId();
    @Query("SELECT * FROM contact")
    LiveData<List<Contact>>getAllContacts();
    @Transaction
    @Query("SELECT * FROM contact where id=:contactId")
    ContactWithDetail getContactWithDetailById(int contactId);*/
}
