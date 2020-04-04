package com.elchinaliyev.test.Model.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Dao.CvDatabase;
import com.elchinaliyev.test.Model.Dao.eduDao;
import com.elchinaliyev.test.Model.Education;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EduRepository {
    private static EduRepository instance;
    private eduDao eduDao;

    public EduRepository(Application app) {
        CvDatabase db=CvDatabase.getInstance(app);
        eduDao=db.eduDao();
    }


    public List<Education> getAllEdu()
    {
        List<Education> edus=new ArrayList<>();
        try {
            edus= new GetAllEdusAsyncTask(eduDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return edus;
    }
    public void insert(Education education)
    {
        new InsertAsyncTask(eduDao).execute(education);
    }

    public void insertAll(List<Education> educations)
    {
        new InsertAllAsyncTask(eduDao).execute(educations);
    }

    public void update(Education education)
    {
        new UpdateAsyncTask(eduDao).execute(education);
    }

    public void delete(int contactId)
    {
        new DeleteAsyncTask(eduDao).execute(contactId);
    }
    public void deleteAllEdu()
    {
        new DeleteAllAsyncTask(eduDao).execute();
    }

    private static class GetAllEdusAsyncTask extends AsyncTask<Void,Void,List<Education>>
    {
        eduDao eduDao;
        private GetAllEdusAsyncTask(eduDao eduDao)
        {
            this.eduDao=eduDao;
        }


        @Override
        protected List<Education> doInBackground(Void... voids) {
            return eduDao.getAllEdu();
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Education,Void,Void>
    {
        eduDao eduDao;
        private InsertAsyncTask(eduDao eduDao)
        {
            this.eduDao=eduDao;
        }

        @Override
        protected Void doInBackground(Education... educations) {
            eduDao.insert(educations[0]);
            return null;
        }
    }

    private static class InsertAllAsyncTask extends AsyncTask<List<Education>,Void,Void>
    {
        eduDao eduDao;
        private InsertAllAsyncTask(eduDao eduDao)
        {
            this.eduDao=eduDao;
        }


        @Override
        protected Void doInBackground(List<Education>... lists) {
            eduDao.insertAll(lists[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Education,Void,Void>
    {
        eduDao eduDao;
        private UpdateAsyncTask(eduDao eduDao)
        {
            this.eduDao=eduDao;
        }

        @Override
        protected Void doInBackground(Education... edu) {
            eduDao.update(edu[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer,Void,Void>
    {
        eduDao eduDao;
        private DeleteAsyncTask(eduDao eduDao)
        {
            this.eduDao=eduDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            eduDao.delete(integers[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>
    {
        eduDao eduDao;
        private DeleteAllAsyncTask(eduDao eduDao)
        {
            this.eduDao=eduDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eduDao.deleteAllEdu();
            return null;
        }
    }

}
