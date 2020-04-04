package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Skills;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class skillDao implements BaseDao<Skills> {
    @Query("DELETE FROM skill where contactId=:contactId")
    public abstract void deleteByConId(int contactId);
}
