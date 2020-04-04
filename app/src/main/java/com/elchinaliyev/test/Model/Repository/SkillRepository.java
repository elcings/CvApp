package com.elchinaliyev.test.Model.Repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Dao.CvDatabase;
import com.elchinaliyev.test.Model.Dao.skillDao;
import com.elchinaliyev.test.Model.Skills;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class SkillRepository {

    private skillDao skillDao;

    public SkillRepository(Application app) {
        CvDatabase db=CvDatabase.getInstance(app);
        skillDao=db.skillDao();
    }


    public List<Skills> getAllSkills()
    {
       List<Skills> skills=new ArrayList<>();
        try {
            skills= new GetAllSkillsAsyncTask(skillDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return skills;
    }
    public void insert(Skills skills)
    {
       new InsertAsyncTask(skillDao).execute(skills);
    }

    public void insertAll(List<Skills> skills)
    {
        new InsertAllAsyncTask(skillDao).execute(skills);
    }

    public void update(Skills skills)
    {
        new UpdateAsyncTask(skillDao).execute(skills);
    }

    public void delete(int contactId)
    {
        new DeleteAsyncTask(skillDao).execute(contactId);
    }
    public void deleteAllSkill()
    {
        new DeleteAllAsyncTask(skillDao).execute();
    }

    private static class GetAllSkillsAsyncTask extends AsyncTask<Void,Void,List<Skills>>
    {
        skillDao skillDao;
        private GetAllSkillsAsyncTask(skillDao skillDao)
        {
            this.skillDao=skillDao;
        }


        @Override
        protected List<Skills> doInBackground(Void... voids) {
            return skillDao.getAllSkills();
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Skills,Void,Void>
    {
        skillDao skillDao;
        private InsertAsyncTask(skillDao skillDao)
        {
            this.skillDao=skillDao;
        }

        @Override
        protected Void doInBackground(Skills... skills) {
            skillDao.insert(skills[0]);
            return null;
        }
    }

    private static class InsertAllAsyncTask extends AsyncTask<List<Skills>,Void,Void>
    {
        skillDao skillDao;
        private InsertAllAsyncTask(skillDao skillDao)
        {
            this.skillDao=skillDao;
        }


        @Override
        protected Void doInBackground(List<Skills>... lists) {
            skillDao.insertAll(lists[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Skills,Void,Void>
    {
        skillDao skillDao;
        private UpdateAsyncTask(skillDao skillDao)
        {
            this.skillDao=skillDao;
        }

        @Override
        protected Void doInBackground(Skills... skills) {
            skillDao.update(skills[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer,Void,Void>
    {
        skillDao skillDao;
        private DeleteAsyncTask(skillDao skillDao)
        {
            this.skillDao=skillDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            skillDao.delete(integers[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>
    {
        skillDao skillDao;
        private DeleteAllAsyncTask(skillDao skillDao)
        {
            this.skillDao=skillDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            skillDao.deleteSkills();
            return null;
        }
    }


}
