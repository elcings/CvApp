package com.elchinaliyev.test.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.DataBase.CvDatabase;
import com.elchinaliyev.test.Model.Dao.lanuageDao;
import com.elchinaliyev.test.Model.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LangRepository {

    private lanuageDao langDao;

    public LangRepository(Application app) {
        CvDatabase db = CvDatabase.getInstance(app);
        langDao = db.langDao();
    }


    public void save(Language lang) {
        if (lang.getId() == 0) {
            new InsertAsyncTask(langDao).execute(lang);
        } else {
            new UpdateAsyncTask(langDao).execute(lang);
        }
    }


    public void deleteByConId(int contactId) {
        new DeleteByConIdAsyncTask(langDao).execute(contactId);
    }


    private static class InsertAsyncTask extends AsyncTask<Language, Void, Void> {
        lanuageDao langDao;

        private InsertAsyncTask(lanuageDao langDao) {
            this.langDao = langDao;
        }


        @Override
        protected Void doInBackground(Language... lang) {
            langDao.insert(lang[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Language, Void, Void> {
        lanuageDao langDao;

        private UpdateAsyncTask(lanuageDao langDao) {
            this.langDao = langDao;
        }


        @Override
        protected Void doInBackground(Language... lang) {
            langDao.update(lang[0]);
            return null;
        }
    }


    private static class DeleteByConIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        lanuageDao langDao;

        private DeleteByConIdAsyncTask(lanuageDao langDao) {
            this.langDao = langDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            langDao.deleteByConId(integers[0]);
            return null;
        }
    }

}
