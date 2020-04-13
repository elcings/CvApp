package com.elchinaliyev.test.ViewModel;

import android.app.Application;

import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Repository.ContactRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CvViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private MutableLiveData<Boolean> isSave=new MutableLiveData<>();

    public CvViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
    }

    public void save(ContactWithDetail detail) {
        isSave.setValue(true);
        contactRepository.save(detail);
       // isSave.setValue(false);
    }

    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    public LiveData<Boolean>getIsSave() {
        return isSave;
    }

}
