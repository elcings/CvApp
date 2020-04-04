package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Project;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class projectDao implements BaseDao<Project> {


    @Query("DELETE FROM project where contactId=:contactId")
    public abstract void deleteByConId(int contactId);
}
