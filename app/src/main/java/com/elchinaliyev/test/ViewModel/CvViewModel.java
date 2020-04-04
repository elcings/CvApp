package com.elchinaliyev.test.ViewModel;

import android.app.Application;
import android.app.ListActivity;

import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Model.Education;
import com.elchinaliyev.test.Model.Experiance;
import com.elchinaliyev.test.Model.Language;
import com.elchinaliyev.test.Model.Project;
import com.elchinaliyev.test.Model.Repository.CertRepository;
import com.elchinaliyev.test.Model.Repository.ContactRepository;
import com.elchinaliyev.test.Model.Repository.EduRepository;
import com.elchinaliyev.test.Model.Repository.ExperRepository;
import com.elchinaliyev.test.Model.Repository.LangRepository;
import com.elchinaliyev.test.Model.Repository.ProjectRepository;
import com.elchinaliyev.test.Model.Repository.SkillRepository;
import com.elchinaliyev.test.Model.Skills;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
        skillRepository=new SkillRepository(application);
        eduRepository=new EduRepository(application);
        experRepository=new ExperRepository(application);
        langRepository=new LangRepository(application);
        projectRepository=new ProjectRepository(application);
        certRepository=new CertRepository(application);
        contactAll=contactRepository.getAllContacts();
    }


    public ContactWithDetail getContactWithDetail(int contactId) {
        return contactRepository.getContactWithDetail(contactId);
    }
   //contact
    public int getTopOneId() {
        return contactRepository.getTopId();
    }
    public LiveData<List<Contact>> getAllContacts() {
        return contactAll;
    }
    public Contact getById(int id){
        return contactRepository.getById(id);
    }
    public void insert(Contact contact) {
        contactRepository.Insert(contact);
    }
    public void upadte(Contact contact) {
        contactRepository.Update(contact);
    }
    public void delete(Contact contact) {
        contactRepository.Delete(contact);
    }
    public void deleteAllContact() {
        contactRepository.DeleteAllContact();
    }

    //skill

    public List<Skills> getAllSkills() { return skillRepository.getAllSkills();}
    public void insertSkill(Skills skills) {  skillRepository.insert(skills);}
    public void insertAllSkill(List<Skills> skills) {
        skillRepository.insertAll(skills);
    }
    public void upadteSkill(Skills skills) {
        skillRepository.update(skills);
    }
    public void deleteSkill(int contactId) {
        skillRepository.delete(contactId);
    }
    public void deleteAllSkill() {
        skillRepository.deleteAllSkill();
    }


    //education


    public List<Education> getAllEdu() { return eduRepository.getAllEdu();}
    public void insertEdu(Education education) {  eduRepository.insert(education);}
    public void insertAllEdu(List<Education> educations) {
        eduRepository.insertAll(educations);
    }
    public void upadteEdu(Education education) {
        eduRepository.update(education);
    }
    public void deleteEdu(int contactId) {
        eduRepository.delete(contactId);
    }
    public void deleteAllEdu() {
        eduRepository.deleteAllEdu();
    }

    //experience

    public List<Experiance> getAllExper() { return experRepository.getAllExpers();}
    public void insertExper(Experiance exper) {  experRepository.insert(exper);}
    public void insertAllEper(List<Experiance> expers) {
        experRepository.insertAll(expers);
    }
    public void upadteExper(Experiance exper) {
        experRepository.update(exper);
    }
    public void deleteExper(int contactId) {
        experRepository.delete(contactId);
    }
    public void deleteAllExper() {
        experRepository.deleteAllExpers();
    }


    //language

    public List<Language> getAllLang() { return langRepository.getAllLangs();}
    public void insertAllLang(List<Language> expers) {
        langRepository.insertAll(expers);
    }
    public void deleteLangs(int contactId) {
        langRepository.delete(contactId);
    }
    public void deleteAlllang() {
        langRepository.deleteAllLangs();
    }



    //project
    public List<Project> getAllProject() { return projectRepository.getAllprojects();}
    public void insertAllProjects(List<Project> expers) {
        projectRepository.insertAll(expers);
    }
    public void deleteProject(int contactId) {
        projectRepository.delete(contactId);
    }
    public void deleteAllProjects() {
        projectRepository.deleteAllPros();
    }

    //certificate

    public List<Certificate> getAllCert() { return certRepository.getAllCerts();}
    public void insertAllCerts(List<Certificate> expers) {
        certRepository.insertAll(expers);
    }
    public void deletecerts(int contactId) {
        certRepository.delete(contactId);
    }
    public void deleteAllCerts() {
        certRepository.deleteAllLangs();
    }

}
