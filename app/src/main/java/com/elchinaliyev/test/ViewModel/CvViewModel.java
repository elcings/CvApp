package com.elchinaliyev.test.ViewModel;

import android.app.Application;

import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Model.Education;
import com.elchinaliyev.test.Model.Experiance;
import com.elchinaliyev.test.Model.Language;
import com.elchinaliyev.test.Model.Project;
import com.elchinaliyev.test.Repository.ContactRepository;
import com.elchinaliyev.test.Model.Skills;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CvViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private LiveData<List<Contact>> contactAll;

    public CvViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        contactAll = contactRepository.getAllContacts();
    }

    public ContactWithDetail getContactWithDetail(long contactId) {
        return contactRepository.getContactWithDetail(contactId);
    }

    public LiveData<List<Contact>> getContacts() {
        return contactAll;
    }

    public void insert(ContactWithDetail detail) {
        contactRepository.save(detail);
    }

    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

}
