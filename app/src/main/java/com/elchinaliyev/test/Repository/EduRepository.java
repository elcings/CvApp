package com.elchinaliyev.test.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.DataBase.CvDatabase;
import com.elchinaliyev.test.Model.Dao.eduDao;
import com.elchinaliyev.test.Model.Education;


public class EduRepository {
    private eduDao eduDao;

    public EduRepository(Application app) {
        CvDatabase db = CvDatabase.getInstance(app);
        eduDao = db.eduDao();
    }


    public void save(Education education) {
        if (education.getId() == 0) {
            new InsertAsyncTask(eduDao).execute(education);
        } else {
            new UpdateAsyncTask(eduDao).execute(education);
        }
    }


    public void delete(int contactId) {
        new DeleteByConIdAsyncTask(eduDao).execute(contactId);
    }


    private static class InsertAsyncTask extends AsyncTask<Education, Void, Void> {
        eduDao eduDao;

        private InsertAsyncTask(eduDao eduDao) {
            this.eduDao = eduDao;
        }


        @Override
        protected Void doInBackground(Education... edu) {
            eduDao.insert(edu[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Education, Void, Void> {
        eduDao eduDao;

        private UpdateAsyncTask(eduDao eduDao) {
            this.eduDao = eduDao;
        }

        @Override
        protected Void doInBackground(Education... edu) {
            eduDao.update(edu[0]);
            return null;
        }
    }

    private static class DeleteByConIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        eduDao eduDao;

        private DeleteByConIdAsyncTask(eduDao eduDao) {
            this.eduDao = eduDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            eduDao.deleteByConId(integers[0]);
            return null;
        }
    }

}
