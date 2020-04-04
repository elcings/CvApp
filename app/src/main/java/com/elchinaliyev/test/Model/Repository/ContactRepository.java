package com.elchinaliyev.test.Model.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Model.Dao.CvDatabase;
import com.elchinaliyev.test.Model.Dao.contactDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class ContactRepository {
    private contactDao contactDao;
    private LiveData<List<Contact>> contacts;

    public ContactRepository(Application app) {
        CvDatabase db = CvDatabase.getInstance(app);
        contactDao = db.contactDao();
        contacts=contactDao.getAllContacts();
    }

    public LiveData<List<Contact>>getAllContacts() {
       return contacts;
    }


    public ContactWithDetail getContactWithDetail(int contactId) {
        ContactWithDetail contactWithDetail = null;
        try {
            contactWithDetail = new GetContactWithDetailAsyncTask(contactDao).execute(contactId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return contactWithDetail;
    }

    public Contact getById(int id) {
        Contact contact = null;
        try {
            contact = new GetByIdAsyncTask(contactDao).execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return contact;
    }

    public int getTopId() {
        int contactId = 0;
        try {
            contactId = new GetTopOneAsyncTask(contactDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return contactId;
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
    public void DeleteAllContact() {
        new DeleteAllAsyncTask(contactDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<Contact, Void, Void> {
        private contactDao contactDao;

        private InsertAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }

    private static class GetTopOneAsyncTask extends AsyncTask<Void, Void, Integer> {
        private contactDao contactDao;

        private GetTopOneAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return contactDao.getTopId();
        }
    }

    private static class GetByIdAsyncTask extends AsyncTask<Integer, Void, Contact> {
        private contactDao contactDao;

        private GetByIdAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Contact doInBackground(Integer... integers) {
            return contactDao.getById(integers[0]);
        }
    }

    //getContactWithDetail
    private static class GetContactWithDetailAsyncTask extends AsyncTask<Integer, Void, ContactWithDetail> {
        private contactDao contactDao;

        private GetContactWithDetailAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected ContactWithDetail doInBackground(Integer... integers) {

            return contactDao.getContactWithDetailById(integers[0]);
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Contact, Void, Void> {
        private contactDao contactDao;

        private UpdateAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.update(contacts[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Contact, Void, Void> {
        private contactDao contactDao;

        private DeleteAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.delete(contacts[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private contactDao contactDao;

        private DeleteAllAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.delete();
            return null;
        }
    }
}
