package com.elchinaliyev.test.Model.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Model.Dao.CvDatabase;
import com.elchinaliyev.test.Model.Dao.certificateDao;
import com.elchinaliyev.test.Model.Dao.lanuageDao;
import com.elchinaliyev.test.Model.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CertRepository {
    private certificateDao certificateDao;

    public CertRepository(Application app) {
        CvDatabase db=CvDatabase.getInstance(app);
        certificateDao=db.certDao();
    }


    public List<Certificate> getAllCerts()
    {
        List<Certificate> certs=new ArrayList<>();
        try {
            certs= new GetAllCertsAsyncTask(certificateDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return certs;
    }


    public void insertAll(List<Certificate> certs)
    {
        new InsertAllAsyncTask(certificateDao).execute(certs);
    }



    public void delete(int contactId)
    {
        new DeleteAsyncTask(certificateDao).execute(contactId);
    }
    public void deleteAllLangs()
    {
        new DeleteAllAsyncTask(certificateDao).execute();
    }

    private static class GetAllCertsAsyncTask extends AsyncTask<Void,Void,List<Certificate>>
    {
        certificateDao certificateDao;
        private GetAllCertsAsyncTask(certificateDao certificateDao)
        {
            this.certificateDao=certificateDao;
        }


        @Override
        protected List<Certificate> doInBackground(Void... voids) {
            return certificateDao.getAllCerts();
        }
    }



    private static class InsertAllAsyncTask extends AsyncTask<List<Certificate>,Void,Void>
    {
        certificateDao certificateDao;
        private InsertAllAsyncTask( certificateDao certificateDao)
        {
            this.certificateDao=certificateDao;
        }


        @Override
        protected Void doInBackground(List<Certificate>... lists) {
            certificateDao.insertAll(lists[0]);
            return null;
        }
    }


    private static class DeleteAsyncTask extends AsyncTask<Integer,Void,Void>
    {
        certificateDao certificateDao;
        private DeleteAsyncTask( certificateDao certificateDao)
        {
            this.certificateDao=certificateDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            certificateDao.delete(integers[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>
    {
        certificateDao certificateDao;
        private DeleteAllAsyncTask(certificateDao certificateDao)
        {
            this.certificateDao=certificateDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            certificateDao.deleteCerts();
            return null;
        }
    }

}
