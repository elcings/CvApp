package com.elchinaliyev.test.ViewModel;

import android.app.Application;

import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Repository.ContactRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CvViewModelList extends AndroidViewModel {
    private ContactRepository contactRepository;
    private LiveData<List<Contact>> contactAll;

    public CvViewModelList(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        contactAll = contactRepository.getContacts();
    }
    public LiveData<List<Contact>> getContacts() {
        return contactAll;
    }
    public LiveData<ContactWithDetail> getContactWithDetailById(long contactId) {
        return contactRepository.getContactWithDetailById(contactId);
    }
}
