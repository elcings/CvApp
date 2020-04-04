package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Certificate;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class certificateDao implements BaseDao<Certificate> {
    @Query("DELETE FROM certificate where contactId=:contactId")
    public abstract void deleteByConId(int contactId);
}
