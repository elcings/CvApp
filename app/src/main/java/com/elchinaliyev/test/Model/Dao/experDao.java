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
public interface experDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Experiance exper);
    @Insert
    void  insertAll(List<Experiance> exper);
    @Update
    void update(Experiance exper);
    @Query("Select * from experiance")
    List<Experiance>getAllExpers();
    @Query("DELETE from experiance")
    void deleteExpers();
    @Query("DELETE FROM experiance where contactId=:contactId")
    void delete(int contactId);
}
