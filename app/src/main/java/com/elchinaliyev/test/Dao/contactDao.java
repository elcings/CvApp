package com.elchinaliyev.test.Dao;

import android.util.Log;

import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Model.Education;
import com.elchinaliyev.test.Model.Experiance;
import com.elchinaliyev.test.Model.Language;
import com.elchinaliyev.test.Model.Project;
import com.elchinaliyev.test.Model.Skills;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class contactDao {

    @Query("SELECT * FROM contact")
    public abstract LiveData<List<Contact>> getAllContacts();

    @Transaction
    @Query("SELECT * FROM contact where id=:contactId")
    public abstract LiveData<ContactWithDetail> getContactWithDetailById(long contactId);

    @Transaction
    @Query("SELECT * FROM contact")
    public abstract LiveData<List<ContactWithDetail>> getContactWithDetails();

    @Insert(onConflict = REPLACE)
    public abstract long insert(Contact entity);

    @Update
    public abstract void update(Contact entity);

    @Delete
    public abstract void delete(Contact entity);

    @Insert
    public abstract void insertCert(Certificate entity);

    @Query("DELETE from certificate where contactId=:contactId")
    public abstract void deleteCert(long contactId);

    @Insert
    public abstract void insertSkill(Skills entity);

    @Query("DELETE from skill where contactId=:contactId")
    public abstract void deleteSkill(long contactId);

    @Insert
    public abstract void insertEdu(Education entity);

    @Query("DELETE from education where contactId=:contactId")
    public abstract void deleteEdu(long contactId);

    @Insert
    public abstract void insertExper(Experiance entity);

    @Query("DELETE from experiance where contactId=:contactId")
    public abstract void deleteExper(long contactId);

    @Insert
    public abstract void insertLang(Language entity);

    @Query("DELETE from language where contactId=:contactId")
    public abstract void deleteLang(long contactId);

    @Insert
    public abstract void insertProject(Project entity);

    @Query("DELETE from project where contactId=:contactId")
    public abstract void deleteProject(long contactId);

    @Transaction
    public void insertOrUpdateContactDetail(ContactWithDetail detail) {
        long contactId = 0;
        if (detail.contact.getId() == 0) {
            contactId = insert(detail.contact);
        } else {
            update(detail.contact);
        }

        if (detail.contact.getId() != 0) {
            deleteSkill(detail.contact.getId());
            deleteProject(detail.contact.getId());
            deleteExper(detail.contact.getId());
            deleteEdu(detail.contact.getId());
            deleteCert(detail.contact.getId());
            deleteLang(detail.contact.getId());
        }
        if (detail.skills != null && detail.skills.size() > 0) {
            for (Skills entity : detail.skills) {
                entity.setContactId(contactId != 0 ? contactId : detail.contact.getId());
                insertSkill(entity);
            }
        }
        if (detail.certs != null && detail.certs.size() > 0) {
            for (Certificate entity : detail.certs) {
                entity.setContactId(contactId != 0 ? contactId : detail.contact.getId());
                insertCert(entity);
            }
        }
        if (detail.educations != null && detail.educations.size() > 0) {
            for (Education entity : detail.educations) {
                entity.setContactId(contactId != 0 ? contactId : detail.contact.getId());
                insertEdu(entity);
            }
        }
        if (detail.experiances != null && detail.experiances.size() > 0) {
            for (Experiance entity : detail.experiances) {
                entity.setContactId(contactId != 0 ? contactId : detail.contact.getId());
                insertExper(entity);
            }
        }
        if (detail.projects != null && detail.projects.size() > 0) {
            for (Project entity : detail.projects) {
                entity.setContactId(contactId != 0 ? contactId : detail.contact.getId());
                insertProject(entity);
            }
        }
        if (detail.languages != null && detail.languages.size() > 0) {
            for (Language entity : detail.languages) {
                entity.setContactId(contactId != 0 ? contactId : detail.contact.getId());
                insertLang(entity);
            }
        }
    }
}
