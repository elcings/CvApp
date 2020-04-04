package com.elchinaliyev.test.Model.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Dao.CvDatabase;
import com.elchinaliyev.test.Model.Dao.lanuageDao;
import com.elchinaliyev.test.Model.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LangRepository {

    private lanuageDao langDao;

    public LangRepository(Application app) {
        CvDatabase db=CvDatabase.getInstance(app);
        langDao=db.langDao();
    }


    public List<Language> getAllLangs()
    {
        List<Language> langs=new ArrayList<>();
        try {
            langs= new GetAllLangsAsyncTask(langDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return langs;
    }


    public void insertAll(List<Language> langs)
    {
        new InsertAllAsyncTask(langDao).execute(langs);
    }



    public void delete(int contactId)
    {
        new DeleteAsyncTask(langDao).execute(contactId);
    }
    public void deleteAllLangs()
    {
        new DeleteAllAsyncTask(langDao).execute();
    }

    private static class GetAllLangsAsyncTask extends AsyncTask<Void,Void,List<Language>>
    {
        lanuageDao langDao;
        private GetAllLangsAsyncTask(lanuageDao langDao)
        {
            this.langDao=langDao;
        }


        @Override
        protected List<Language> doInBackground(Void... voids) {
            return langDao.getAllLang();
        }
    }



    private static class InsertAllAsyncTask extends AsyncTask<List<Language>,Void,Void>
    {
        lanuageDao langDao;
        private InsertAllAsyncTask( lanuageDao langDao)
        {
            this.langDao=langDao;
        }


        @Override
        protected Void doInBackground(List<Language>... lists) {
            langDao.insertAll(lists[0]);
            return null;
        }
    }


    private static class DeleteAsyncTask extends AsyncTask<Integer,Void,Void>
    {
        lanuageDao langDao;
        private DeleteAsyncTask( lanuageDao langDao)
        {
            this.langDao=langDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            langDao.delete(integers[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>
    {
        lanuageDao langDao;
        private DeleteAllAsyncTask(lanuageDao langDao)
        {
            this.langDao=langDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            langDao.deleteLangs();
            return null;
        }
    }

}
