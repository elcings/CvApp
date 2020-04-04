package com.elchinaliyev.test.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.DataBase.CvDatabase;
import com.elchinaliyev.test.Model.Dao.contactDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import androidx.lifecycle.LiveData;

public class ContactRepository {
    private contactDao contactDao;
    private LiveData<List<Contact>> contacts;
    private ExecutorService executorService;

    public ContactRepository(Application app) {
        CvDatabase db = CvDatabase.getInstance(app);
        contactDao = db.contactDao();
        contacts = contactDao.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
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

    /*public Contact getById(int id) {
        Contact contact = null;
        try {
            contact = new GetByIdAsyncTask(contactDao).execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return contact;
    }*/

    public int getId() {
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


    public void save(Contact contact) {
        if (contact.getId() == 0) {
            new InsertAsyncTask(contactDao).execute(contact);
        } else {
            new UpdateAsyncTask(contactDao).execute(contact);
        }
    }

    public void Delete(Contact contact) {
        new DeleteAsyncTask(contactDao).execute(contact);
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
