package com.elchinaliyev.test.Model.Dao;

import android.content.Context;

import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.Education;
import com.elchinaliyev.test.Model.Experiance;
import com.elchinaliyev.test.Model.Language;
import com.elchinaliyev.test.Model.Project;
import com.elchinaliyev.test.Model.Skills;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, Education.class, Experiance.class, Language.class, Project.class, Skills.class, Certificate.class},version = 5,exportSchema = false)
public abstract class CvDatabase extends RoomDatabase {
    public static CvDatabase instance;

    public static synchronized CvDatabase getInstance(final Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),CvDatabase.class,"cv_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract contactDao contactDao();
    public abstract skillDao skillDao();
    public abstract eduDao eduDao();
    public abstract experDao experDao();
    public abstract lanuageDao langDao();
    public abstract projectDao projectDao();
    public abstract certificateDao certDao();
}
