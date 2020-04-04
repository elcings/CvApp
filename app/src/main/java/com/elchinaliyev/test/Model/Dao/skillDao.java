package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Skills;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface skillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Skills skills);
    @Insert
    void  insertAll(List<Skills>skills);
    @Update
    void update(Skills skills);
    @Query("Select * from skill")
    List<Skills>getAllSkills();
    @Query("DELETE from skill")
    void deleteSkills();
    @Query("DELETE FROM skill where contactId=:contactId")
    void delete(int contactId);
}
