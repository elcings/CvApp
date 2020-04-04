package com.elchinaliyev.test.Repository;

import android.app.Application;
import android.os.AsyncTask;
import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.DataBase.CvDatabase;
import com.elchinaliyev.test.Model.Dao.certificateDao;

public class CertRepository {
    private certificateDao certificateDao;

    public CertRepository(Application app) {
        CvDatabase db = CvDatabase.getInstance(app);
        certificateDao = db.certDao();
    }

    public void save(Certificate cert) {
        if (cert.getId() == 0) {
            new InsertAsyncTask(certificateDao).execute(cert);
        } else {
            new UpdateAsyncTask(certificateDao).execute(cert);
        }
    }
    public void deleteByConId(int contactId) {
        new DeleteByConIdAsyncTask(certificateDao).execute(contactId);
    }


    private static class InsertAsyncTask extends AsyncTask<Certificate, Void, Void> {
        certificateDao certificateDao;

        private InsertAsyncTask(certificateDao certificateDao) {
            this.certificateDao = certificateDao;
        }

        @Override
        protected Void doInBackground(Certificate... cert) {
            certificateDao.insert(cert[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Certificate, Void, Void> {
        certificateDao certificateDao;

        private UpdateAsyncTask(certificateDao certificateDao) {
            this.certificateDao = certificateDao;
        }

        @Override
        protected Void doInBackground(Certificate... cert) {
            certificateDao.update(cert[0]);
            return null;
        }
    }


    private static class DeleteByConIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        certificateDao certificateDao;

        private DeleteByConIdAsyncTask(certificateDao certificateDao) {
            this.certificateDao = certificateDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            certificateDao.deleteByConId(integers[0]);
            return null;
        }
    }


}
