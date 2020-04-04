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
public abstract class eduDao implements BaseDao<Education>{

    @Query("DELETE from education")
    public abstract void deleteAllEdu();

    @Query("DELETE FROM education where contactId=:contactId")
    public abstract void deleteByConId(int contactId);
}
