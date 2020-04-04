package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Model.Skills;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface certificateDao {
    @Insert
    void  insertAll(List<Certificate> certs);
    @Query("Select * from certificate")
    List<Certificate>getAllCerts();
    @Query("DELETE from certificate")
    void deleteCerts();
    @Query("DELETE FROM certificate where contactId=:contactId")
    void delete(int contactId);
}
