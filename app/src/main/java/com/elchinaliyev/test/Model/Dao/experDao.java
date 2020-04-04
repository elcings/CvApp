package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Experiance;
import com.elchinaliyev.test.Model.Skills;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public abstract class experDao implements BaseDao<Experiance> {

    @Query("DELETE FROM experiance where contactId=:contactId")
    public abstract void deleteByConId(int contactId);
}
