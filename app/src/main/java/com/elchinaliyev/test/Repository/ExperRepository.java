package com.elchinaliyev.test.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.DataBase.CvDatabase;
import com.elchinaliyev.test.Model.Dao.experDao;
import com.elchinaliyev.test.Model.Experiance;

public class ExperRepository {
    private experDao experDao;

    public ExperRepository(Application app) {
        CvDatabase db = CvDatabase.getInstance(app);
        experDao = db.experDao();
    }


    public void save(Experiance exper) {
        if (exper.getId() == 0) {
            new InsertAsyncTask(experDao).execute(exper);
        } else {
            new UpdateAsyncTask(experDao).execute(exper);
        }

    }

    public void deleteById(int contactId) {
        new DeleteByConIdAsyncTask(experDao).execute(contactId);
    }

    private static class InsertAsyncTask extends AsyncTask<Experiance, Void, Void> {
        experDao experDao;

        private InsertAsyncTask(experDao experDao) {
            this.experDao = experDao;
        }

        @Override
        protected Void doInBackground(Experiance... exper) {
            experDao.insert(exper[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Experiance, Void, Void> {
        experDao experDao;

        private UpdateAsyncTask(experDao experDao) {
            this.experDao = experDao;
        }

        @Override
        protected Void doInBackground(Experiance... exper) {
            experDao.update(exper[0]);
            return null;
        }
    }

    private static class DeleteByConIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        experDao experDao;

        private DeleteByConIdAsyncTask(experDao experDao) {
            this.experDao = experDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            experDao.deleteByConId(integers[0]);
            return null;
        }
    }


}
