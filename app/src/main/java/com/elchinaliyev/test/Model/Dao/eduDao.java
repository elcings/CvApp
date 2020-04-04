package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Education;
import com.elchinaliyev.test.Model.Skills;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
@Dao
public interface eduDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Education education);
    @Insert
    void  insertAll(List<Education> educations);
    @Update
    void update(Education education);
    @Query("Select * from education")
    List<Education>getAllEdu();
    @Query("DELETE from education")
    void deleteAllEdu();
    @Query("DELETE FROM education where contactId=:contactId")
    void delete(int contactId);
}
