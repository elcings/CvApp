package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Language;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class lanuageDao implements BaseDao<Language> {


    @Query("DELETE FROM language where contactId=:contactId")
    public abstract void deleteByConId(int contactId);
}
