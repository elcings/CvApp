package com.elchinaliyev.test.ViewModel;

import android.app.Application;
import android.app.ListActivity;

import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Model.Repository.ContactRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CvViewModel extends AndroidViewModel {
    private ContactRepository repository;
    private LiveData<List<ContactWithDetail>>contactWithDetail;
    private LiveData<List<Contact>>contactAll;
    public CvViewModel(@NonNull Application application) {
        super(application);
        repository=new ContactRepository(application);
        //contactWithDetail=repository.getContactWithDetail();
        //contactAll=repository.getAllContact();
    }


    public LiveData<List<ContactWithDetail>> getContactWithDetail()
    {
        return repository.getContactWithDetail();
    }
    public LiveData<List<Contact>> getContactAll()
    {
        return repository.getAllContact();
    }
    public LiveData<Integer> getTopOneId()
    {
        return repository.getTopId();
    }

    public LiveData<Contact> getById(int id)
    {
        return repository.getById(id);
    }
    public void insert(Contact contact) {
        repository.Insert(contact);
    }
    public void upadte(Contact contact)
    {
        repository.Update(contact);
    }
    public void delete(Contact contact)
    {
        repository.Delete(contact);
    }
}
