package com.elchinaliyev.test.Model.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Dao.CvDatabase;
import com.elchinaliyev.test.Model.Dao.lanuageDao;
import com.elchinaliyev.test.Model.Dao.projectDao;
import com.elchinaliyev.test.Model.Language;
import com.elchinaliyev.test.Model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProjectRepository {
    private projectDao projectDao;

    public ProjectRepository(Application app) {
        CvDatabase db=CvDatabase.getInstance(app);
        projectDao=db.projectDao();
    }


    public List<Project> getAllprojects()
    {
        List<Project> pros=new ArrayList<>();
        try {
            pros= new GetAllProsAsyncTask(projectDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pros;
    }


    public void insertAll(List<Project> pros)
    {
        new InsertAllAsyncTask(projectDao).execute(pros);
    }



    public void delete(int contactId)
    {
        new DeleteAsyncTask(projectDao).execute(contactId);
    }
    public void deleteAllPros()
    {
        new DeleteAllAsyncTask(projectDao).execute();
    }

    private static class GetAllProsAsyncTask extends AsyncTask<Void,Void,List<Project>>
    {
        projectDao projectDao;
        private GetAllProsAsyncTask(projectDao projectDao)
        {
            this.projectDao=projectDao;
        }


        @Override
        protected List<Project> doInBackground(Void... voids) {
            return projectDao.getAllProjects();
        }
    }



    private static class InsertAllAsyncTask extends AsyncTask<List<Project>,Void,Void>
    {
        projectDao projectDao;
        private InsertAllAsyncTask( projectDao projectDao)
        {
            this.projectDao=projectDao;
        }


        @Override
        protected Void doInBackground(List<Project>... lists) {
            projectDao.insertAll(lists[0]);
            return null;
        }
    }


    private static class DeleteAsyncTask extends AsyncTask<Integer,Void,Void>
    {
        projectDao projectDao;
        private DeleteAsyncTask(  projectDao projectDao)
        {
            this.projectDao=projectDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            projectDao.delete(integers[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>
    {
        projectDao projectDao;
        private DeleteAllAsyncTask(projectDao projectDao)
        {
            this.projectDao=projectDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            projectDao.deleteProjects();
            return null;
        }
    }

}
