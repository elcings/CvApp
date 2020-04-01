package com.elchinaliyev.test.Model.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Model.Dao.CvDatabase;
import com.elchinaliyev.test.Model.Dao.contactDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ContactRepository {
    private contactDao contactDao;
    private LiveData<List<ContactWithDetail>>contactWithDetail;
    public ContactRepository(Application app)
    {
        CvDatabase db=CvDatabase.getInstance(app);
        contactDao=db.contactDao();
        contactWithDetail=contactDao.getContactWithDetail();
    }

    public LiveData<List<ContactWithDetail>>getContactWithDetail()
    {
        return contactWithDetail;
    }

    public LiveData<Contact>getById(int id)
    {
         return contactDao.getById(id);
    }
    public LiveData<Integer>getTopId()
    {
         return contactDao.getTopId();
    }
    public LiveData<List<Contact>>getAllContact()
    {
        return contactDao.getAllContact();
    }

    public void Insert(Contact contact) {
        new InsertAsyncTask(contactDao).execute(contact);
    }
    public void Update(Contact contact) {
        new UpdateAsyncTask(contactDao).execute(contact);
    }
    public void Delete(Contact contact) {
        new DeleteAsyncTask(contactDao).execute(contact);
    }


    private static class InsertAsyncTask extends AsyncTask<Contact,Void,Void>
    {
      private contactDao contactDao;
      private InsertAsyncTask(contactDao contactDao)
      {
          this.contactDao=contactDao;
      }

        @Override
        protected Void doInBackground(Contact... contacts) {
         contactDao.insert(contacts[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Contact,Void,Void>
    {
      private contactDao contactDao;
      private UpdateAsyncTask(contactDao contactDao)
      {
          this.contactDao=contactDao;
      }
        @Override
        protected Void doInBackground(Contact... contacts) {
          contactDao.update(contacts[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Contact,Void,Void>
    {
        private contactDao contactDao;
        private DeleteAsyncTask(contactDao contactDao)
        {
            this.contactDao=contactDao;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.delete(contacts[0]);
            return null;
        }
    }
}
