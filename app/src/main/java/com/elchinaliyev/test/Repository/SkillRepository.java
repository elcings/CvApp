package com.elchinaliyev.test.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.DataBase.CvDatabase;
import com.elchinaliyev.test.Model.Dao.skillDao;
import com.elchinaliyev.test.Model.Skills;



public class SkillRepository {

    private skillDao skillDao;

    public SkillRepository(Application app) {
        CvDatabase db = CvDatabase.getInstance(app);
        skillDao = db.skillDao();
    }


    public void save(Skills skill) {
        if (skill.getId() == 0) {
            new InsertAsyncTask(skillDao).execute(skill);
        } else {
            new UpdateAsyncTask(skillDao).execute(skill);
        }
    }

    public void deleteByConId(int contactId) {
        new DeleteByConIdAsyncTask(skillDao).execute(contactId);
    }


    private static class InsertAsyncTask extends AsyncTask<Skills, Void, Void> {
        skillDao skillDao;

        private InsertAsyncTask(skillDao skillDao) {
            this.skillDao = skillDao;
        }

        @Override
        protected Void doInBackground(Skills... skill) {
            skillDao.insert(skill[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Skills, Void, Void> {
        skillDao skillDao;

        private UpdateAsyncTask(skillDao skillDao) {
            this.skillDao = skillDao;
        }

        @Override
        protected Void doInBackground(Skills... skill) {
            skillDao.update(skill[0]);
            return null;
        }
    }

    private static class DeleteByConIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        skillDao skillDao;

        private DeleteByConIdAsyncTask(skillDao skillDao) {
            this.skillDao = skillDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            skillDao.deleteByConId(integers[0]);
            return null;
        }
    }

}
