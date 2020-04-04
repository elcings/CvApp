package com.elchinaliyev.test.Model.Dao;

import com.elchinaliyev.test.Model.Language;
import com.elchinaliyev.test.Model.Project;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface lanuageDao {
    @Insert
    void  insertAll(List<Language> langs);
    @Query("Select * from language")
    List<Language>getAllLang();
    @Query("DELETE from language")
    void deleteLangs();
    @Query("DELETE FROM language where contactId=:contactId")
    void delete(int contactId);
}
