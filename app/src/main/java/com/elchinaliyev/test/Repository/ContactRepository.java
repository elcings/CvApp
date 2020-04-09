package com.elchinaliyev.test.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.DataBase.CvDatabase;
import com.elchinaliyev.test.Dao.contactDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class ContactRepository {
    private contactDao contactDao;
    private LiveData<List<Contact>> contacts;

    public ContactRepository(Application app) {
        CvDatabase db = CvDatabase.getInstance(app);
        contactDao = db.contactDao();
        contacts = contactDao.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contacts;
    }


    public ContactWithDetail getContactWithDetail(long contactId) {
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

    public void save(ContactWithDetail detail) {
            new InsertAsyncTask(contactDao).execute(detail);
    }

    public void delete(Contact contact) {
        new DeleteAsyncTask(contactDao).execute(contact);
    }


    private static class GetContactWithDetailAsyncTask extends AsyncTask<Long, Void, ContactWithDetail> {
        private contactDao contactDao;

        private GetContactWithDetailAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected ContactWithDetail doInBackground(Long... longs) {

            return contactDao.getContactWithDetailById(longs[0]);
        }
    }
    private static class InsertAsyncTask extends AsyncTask<ContactWithDetail, Void, Void> {
        private contactDao contactDao;

        private InsertAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(ContactWithDetail... entity) {
            contactDao.insertOrUpdateContactDetail(entity[0]);
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

    private static class DeleteAllAsyncTask extends AsyncTask<Contact, Void, Void> {
        private contactDao contactDao;

        private DeleteAllAsyncTask(contactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.delete(contacts[0]);
            return null;
        }
    }
}
