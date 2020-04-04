package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Model.Project;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
public interface projectDao {
    @Insert
    void  insertAll(List<Project> projects);
    @Query("Select * from project")
    List<Project>getAllProjects();
    @Query("DELETE from project")
    void deleteProjects();
    @Query("DELETE FROM project where contactId=:contactId")
    void delete(int contactId);
}
