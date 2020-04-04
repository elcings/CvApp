package com.elchinaliyev.test.ViewModel;

import android.app.Application;

import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Model.Education;
import com.elchinaliyev.test.Model.Experiance;
import com.elchinaliyev.test.Model.Language;
import com.elchinaliyev.test.Model.Project;
import com.elchinaliyev.test.Repository.CertRepository;
import com.elchinaliyev.test.Repository.ContactRepository;
import com.elchinaliyev.test.Repository.EduRepository;
import com.elchinaliyev.test.Repository.ExperRepository;
import com.elchinaliyev.test.Repository.LangRepository;
import com.elchinaliyev.test.Repository.ProjectRepository;
import com.elchinaliyev.test.Repository.SkillRepository;
import com.elchinaliyev.test.Model.Skills;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CvViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private SkillRepository skillRepository;
    private EduRepository eduRepository;
    private ExperRepository experRepository;
    private LangRepository langRepository;
    private ProjectRepository projectRepository;
    private CertRepository certRepository;
    private LiveData<List<Contact>> contactAll;

    public CvViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        skillRepository = new SkillRepository(application);
        eduRepository = new EduRepository(application);
        experRepository = new ExperRepository(application);
        langRepository = new LangRepository(application);
        projectRepository = new ProjectRepository(application);
        certRepository = new CertRepository(application);
        contactAll = contactRepository.getAllContacts();
    }


    public ContactWithDetail getContactWithDetail(int contactId) {
        return contactRepository.getContactWithDetail(contactId);
    }

    //contact
    public int getConId() {
        return contactRepository.getId();
    }

    public LiveData<List<Contact>> getContacts() {
        return contactAll;
    }

    /*public Contact getById(int id) {
        return contactRepository.getById(id);
    }*/

    public void saveCon(Contact contact) {
        contactRepository.save(contact);
    }

    public void delete(Contact contact) {
        contactRepository.Delete(contact);
    }

    //skill

    public void saveSkill(List<Skills> skills) {
        for (Skills skill : skills) {
            skillRepository.save(skill);
        }
    }

    public void deleteSkillByConId(int contactId) {
        skillRepository.deleteByConId(contactId);
    }


    //education

    public void saveEdu(List<Education> educations) {
        for (Education edu : educations) {
            eduRepository.save(edu);
        }
    }

    public void deleteEduByConId(int contactId) {
        eduRepository.delete(contactId);
    }

    //experience

    public void saveEper(List<Experiance> expers) {
        for (Experiance exper : expers) {
            experRepository.save(exper);
        }
    }

    public void deleteExper(int contactId) {
        experRepository.deleteById(contactId);
    }


    //language

    public void saveLang(List<Language> langs) {
        for (Language lang : langs) {
            langRepository.save(lang);
        }
    }

    public void deleteLangByConId(int contactId) {
        langRepository.deleteByConId(contactId);
    }


    //project

    public void saveProjects(List<Project> projects) {
        for (Project pro : projects) {
            projectRepository.save(pro);
        }
    }

    public void deleteProjectByConId(int contactId) {
        projectRepository.deleteByConId(contactId);
    }

    //certificate

    public void saveCert(List<Certificate> certs) {
        for (Certificate cert : certs) {
            certRepository.save(cert);
        }
    }

    public void deleteCertConById(int contactId) {
        certRepository.deleteByConId(contactId);
    }

}
