package com.elchinaliyev.test.Model.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Dao.CvDatabase;
import com.elchinaliyev.test.Model.Dao.experDao;
import com.elchinaliyev.test.Model.Dao.skillDao;
import com.elchinaliyev.test.Model.Experiance;
import com.elchinaliyev.test.Model.Skills;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExperRepository {
    private experDao experDao;

    public ExperRepository(Application app) {
        CvDatabase db=CvDatabase.getInstance(app);
        experDao=db.experDao();
    }


    public List<Experiance> getAllExpers()
    {
        List<Experiance> expers=new ArrayList<>();
        try {
            expers= new GetAllExpersAsyncTask(experDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return expers;
    }
    public void insert(Experiance expers)
    {
        new InsertAsyncTask(experDao).execute(expers);
    }

    public void insertAll(List<Experiance> expers)
    {
        new InsertAllAsyncTask(experDao).execute(expers);
    }

    public void update(Experiance expers)
    {
        new UpdateAsyncTask(experDao).execute(expers);
    }

    public void delete(int contactId)
    {
        new DeleteAsyncTask(experDao).execute(contactId);
    }
    public void deleteAllExpers()
    {
        new DeleteAllAsyncTask(experDao).execute();
    }

    private static class GetAllExpersAsyncTask extends AsyncTask<Void,Void,List<Experiance>>
    {
        experDao experDao;
        private GetAllExpersAsyncTask(experDao experDao)
        {
            this.experDao=experDao;
        }


        @Override
        protected List<Experiance> doInBackground(Void... voids) {
            return experDao.getAllExpers();
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Experiance,Void,Void>
    {
        experDao experDao;
        private InsertAsyncTask(experDao experDao)
        {
            this.experDao=experDao;
        }

        @Override
        protected Void doInBackground(Experiance... expers) {
            experDao.insert(expers[0]);
            return null;
        }
    }

    private static class InsertAllAsyncTask extends AsyncTask<List<Experiance>,Void,Void>
    {
        experDao experDao;
        private InsertAllAsyncTask(experDao experDao)
        {
            this.experDao=experDao;
        }


        @Override
        protected Void doInBackground(List<Experiance>... lists) {
            experDao.insertAll(lists[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Experiance,Void,Void>
    {
        experDao experDao;
        private UpdateAsyncTask(experDao experDao)
        {
            this.experDao=experDao;
        }

        @Override
        protected Void doInBackground(Experiance... expers) {
            experDao.update(expers[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer,Void,Void>
    {
        experDao experDao;
        private DeleteAsyncTask(experDao experDao)
        {
            this.experDao=experDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            experDao.delete(integers[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>
    {
        experDao experDao;
        private DeleteAllAsyncTask(experDao experDao)
        {
            this.experDao=experDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            experDao.deleteExpers();
            return null;
        }
    }
}
