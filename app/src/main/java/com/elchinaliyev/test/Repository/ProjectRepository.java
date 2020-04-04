package com.elchinaliyev.test.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.DataBase.CvDatabase;
import com.elchinaliyev.test.Model.Dao.projectDao;
import com.elchinaliyev.test.Model.Project;

public class ProjectRepository {
    private projectDao projectDao;

    public ProjectRepository(Application app) {
        CvDatabase db = CvDatabase.getInstance(app);
        projectDao = db.projectDao();
    }


    public void save(Project pro) {
        if (pro.getId() == 0) {
            new InsertAsyncTask(projectDao).execute(pro);
        } else {
            new UpdateAsyncTask(projectDao).execute(pro);
        }
    }


    public void deleteByConId(int contactId) {
        new DeleteByConIdAsyncTask(projectDao).execute(contactId);
    }


    private static class InsertAsyncTask extends AsyncTask<Project, Void, Void> {
        projectDao projectDao;

        private InsertAsyncTask(projectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(Project... pro) {
            projectDao.insert(pro[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Project, Void, Void> {
        projectDao projectDao;

        private UpdateAsyncTask(projectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(Project... pro) {
            projectDao.insert(pro[0]);
            return null;
        }
    }


    private static class DeleteByConIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        projectDao projectDao;

        private DeleteByConIdAsyncTask(projectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            projectDao.deleteByConId(integers[0]);
            return null;
        }
    }


}
