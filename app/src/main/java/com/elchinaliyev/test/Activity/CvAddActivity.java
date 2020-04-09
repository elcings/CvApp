package com.elchinaliyev.test.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Common.Common;
import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Model.Education;
import com.elchinaliyev.test.Model.Experiance;
import com.elchinaliyev.test.Model.Language;
import com.elchinaliyev.test.Model.Project;
import com.elchinaliyev.test.Model.Skills;
import com.elchinaliyev.test.R;
import com.elchinaliyev.test.Report.CV;
import com.elchinaliyev.test.ViewModel.CvViewModel;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CvAddActivity extends AppCompatActivity  {

    Button loadImage;
    DatePickerDialog picker;
    private LinearLayout parentSkillLayout, parentEduLayout, parentExperLayout, parentCertLayout, parentLangLayout, parentProjectLayout;
    ImageView image;
    EditText birthDate, firstName, lastName, address, phone, nationality, description, occupation, email, sosialMedia;
    EditText univertsity, location, graduatedYear, speciality;
    EditText skillName;
    EditText position, company, compLocation, startDate, endDate;
    EditText certName, projectName,langName,langLevel;
    Spinner spinner;
    CheckBox isWork;
    private static int RESULT_LOAD_IMAGE = 1;
    CvViewModel cvViewModel;
    Common common;
    Contact contact;
    ContactWithDetail cont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_add);
        init();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear);
        long id = getIntent().getLongExtra("contactId", 0);
        if (id != 0) {
            cont = cvViewModel.getContactWithDetail(id);
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    setContact(cont);
                    setSkills(parentSkillLayout,cont);
                    setCert(parentCertLayout,cont);
                    setEdu(parentEduLayout,cont);
                    setExper(parentExperLayout,cont);
                    setProject(parentProjectLayout,cont);
                    setLangs(parentLangLayout,cont);

                }
            });
        }
    }
    void createCv(ContactWithDetail contact) {
        String fileName = contact.contact.getPath();
        try {
            String output = Environment.getExternalStorageDirectory() + "/" + fileName;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, 1);
                if (contact != null) {
                    CV myCV = new CV(CvAddActivity.this, contact);
                    final File file = new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath(), fileName.toString());
                    if (file.exists()) {
                        file.delete();
                        myCV.GeneratePdf(output);
                    } else {
                        myCV.GeneratePdf(output);
                    }
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void saveData() {
        ContactWithDetail detail=new ContactWithDetail();
        detail.contact=getContact();
        detail.skills=getSkills(parentSkillLayout);
        detail.experiances=getEperience(parentExperLayout);
        detail.educations=getEducation(parentEduLayout);
        detail.projects=getProject(parentProjectLayout);
        detail.certs=getCertificate(parentCertLayout);
        detail.languages=getLang(parentLangLayout);
        cvViewModel.insert(detail);
        createCv(detail);
        Toast.makeText(CvAddActivity.this, "Success", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.savecv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.save:
                saveData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    public void init() {
        contact=new Contact();
        common = new Common();
        cvViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CvViewModel.class);
        loadImage = findViewById(R.id.loadImage);
        image = findViewById(R.id.image);
        birthDate = findViewById(R.id.birthdate);
        birthDate.setInputType(InputType.TYPE_NULL);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        nationality = findViewById(R.id.nationality);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        sosialMedia = findViewById(R.id.sosilaMedia);
        description = findViewById(R.id.description);
        occupation = findViewById(R.id.occupation);

        //Skill
        parentSkillLayout = findViewById(R.id.parent_linear_layout);
        skillName = findViewById(R.id.skillName);


        //education
        parentEduLayout = findViewById(R.id.parent_edu_layout);
        location = findViewById(R.id.location);
        speciality = findViewById(R.id.specialty);
        univertsity = findViewById(R.id.university);
        graduatedYear = findViewById(R.id.graduated);

        //experience
        parentExperLayout = findViewById(R.id.parent_exper_layout);
        position = findViewById(R.id.title_position);
        company = findViewById(R.id.company);
        compLocation = findViewById(R.id.comp_location);
        startDate = findViewById(R.id.workStartDate);
        startDate.setInputType(InputType.TYPE_NULL);
        endDate = findViewById(R.id.workEndDate);
        endDate.setInputType(InputType.TYPE_NULL);
        isWork = findViewById(R.id.isWork);

        //certificate
        parentCertLayout = findViewById(R.id.parent_cert_layout);
        certName = findViewById(R.id.certName);


        //projects
        parentProjectLayout = findViewById(R.id.parent_pro_layout);
        projectName = findViewById(R.id.projectName);

        //language
        parentLangLayout=findViewById(R.id.parent_lang_layout);
        langName=findViewById(R.id.langName);
        langLevel=findViewById(R.id.langLevel);
    }

    public void onBirtdDate(View v) {
        final Calendar cld = Calendar.getInstance();
        int day = cld.get(Calendar.DAY_OF_MONTH);
        int month = cld.get(Calendar.MONTH);
        int year = cld.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(CvAddActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
    }

    //skill clicks
    public void onAddSkill(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.skill, null);
        parentSkillLayout.addView(rowView, parentSkillLayout.getChildCount() - 1);
        skillName = rowView.findViewById(R.id.skillName);
        skillName.requestFocus();
    }

    public void onDelSkill(View v) {
        parentSkillLayout.removeView((View) v.getParent());
    }

    public void expandSkill(View v) {
        if (parentSkillLayout.getVisibility() == View.VISIBLE) {
            parentSkillLayout.setVisibility(View.GONE);
        } else {
            parentSkillLayout.setVisibility(View.VISIBLE);
            skillName.requestFocus();
        }
    }

    //Education clicks
    public void onDelEdu(View v) {
        parentEduLayout.removeView((View) v.getParent());
    }

    public void onAddEdu(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.education, null);
        parentEduLayout.addView(rowView, parentEduLayout.getChildCount() - 1);
        location = rowView.findViewById(R.id.location);
        speciality = rowView.findViewById(R.id.specialty);
        univertsity = rowView.findViewById(R.id.university);
        graduatedYear = rowView.findViewById(R.id.graduated);
        speciality.requestFocus();
    }

    public void expandEdu(View v) {
        if (parentEduLayout.getVisibility() == View.VISIBLE) {
            parentEduLayout.setVisibility(View.GONE);
        } else {
            parentEduLayout.setVisibility(View.VISIBLE);
            speciality.requestFocus();
        }
    }

    //Experience clicks

    public void onDelExper(View v) {
        parentExperLayout.removeView((View) v.getParent());
    }

    public void onAddExper(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.experience, null);
        parentExperLayout.addView(rowView, parentExperLayout.getChildCount() - 1);
        position = rowView.findViewById(R.id.title_position);
        company = rowView.findViewById(R.id.company);
        compLocation = rowView.findViewById(R.id.comp_location);
        startDate = rowView.findViewById(R.id.workStartDate);
        endDate = rowView.findViewById(R.id.workEndDate);
        isWork = rowView.findViewById(R.id.isWork);
        position.requestFocus();
    }

    public void expandExper(View v) {
        if (parentExperLayout.getVisibility() == View.VISIBLE) {
            parentExperLayout.setVisibility(View.GONE);
        } else {
            parentExperLayout.setVisibility(View.VISIBLE);
            position.requestFocus();
        }
    }

    public void onWorkStartDate(View v) {
        final Calendar cld = Calendar.getInstance();
        int day = cld.get(Calendar.DAY_OF_MONTH);
        int month = cld.get(Calendar.MONTH);
        int year = cld.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(CvAddActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        startDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
    }

    public void onWorkEndDate(View v) {
        final Calendar cld = Calendar.getInstance();
        int day = cld.get(Calendar.DAY_OF_MONTH);
        int month = cld.get(Calendar.MONTH);
        int year = cld.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(CvAddActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        endDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
    }


    //certificate

    public void onDelCert(View v) {
        parentCertLayout.removeView((View) v.getParent());
    }

    public void onAddCert(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.certificate, null);
        parentCertLayout.addView(rowView, parentCertLayout.getChildCount() - 1);
        certName = rowView.findViewById(R.id.certName);
        certName.requestFocus();
    }

    public void expandCert(View v) {
        if (parentCertLayout.getVisibility() == View.VISIBLE) {
            parentCertLayout.setVisibility(View.GONE);
        } else {
            parentCertLayout.setVisibility(View.VISIBLE);
            certName.requestFocus();
        }
    }

    //Projects
    public void onDelPro(View v) {
        parentProjectLayout.removeView((View) v.getParent());
    }

    public void onAddPro(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.projects, null);
        parentProjectLayout.addView(rowView, parentProjectLayout.getChildCount() - 1);
        projectName = rowView.findViewById(R.id.projectName);
        projectName.requestFocus();
    }

    public void expandPro(View v) {
        if (parentProjectLayout.getVisibility() == View.VISIBLE) {
            parentProjectLayout.setVisibility(View.GONE);
        } else {
            parentProjectLayout.setVisibility(View.VISIBLE);
            projectName.requestFocus();
        }
    }


    //language

    public void onDelLang(View v) {
        parentLangLayout.removeView((View) v.getParent());
    }

    public void onAddLang(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.language, null);
        parentLangLayout.addView(rowView, parentLangLayout.getChildCount() - 1);
        langName = rowView.findViewById(R.id.langName);
        langLevel = rowView.findViewById(R.id.langLevel);
        langName.requestFocus();
    }

    public void expandLang(View v) {
        if (parentLangLayout.getVisibility() == View.VISIBLE) {
            parentLangLayout.setVisibility(View.GONE);
        } else {
            parentLangLayout.setVisibility(View.VISIBLE);
            langName.requestFocus();

        }
    }


    public void onLoadImage(View v) {
        Intent imgIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(imgIntent, RESULT_LOAD_IMAGE);
    }

    //getAll
    public List<Language> getLang(LinearLayout layout) {
        List<Language> langs = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount() - 1; i++) {
            Language lang = new Language();
            View v = layout.getChildAt(i);
            langName = v.findViewById(R.id.langName);
            langLevel = v.findViewById(R.id.langLevel);
            lang.setName(langName.getText().toString());
            lang.setLevel(langLevel.getText().toString());
            langs.add(lang);
        }
        return langs;
    }
    public void setLangs(LinearLayout layout,ContactWithDetail con) {
        for (int i = 0; i < con.languages.size(); i++) {
            if (i != 0) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.language, null);
                layout.addView(rowView, layout.getChildCount() - 1);
                langName = rowView.findViewById(R.id.langName);
                langLevel = rowView.findViewById(R.id.langLevel);
                langName.setText(con.languages.get(i).getName());
                langLevel.setText(con.languages.get(i).getLevel());
            } else {
                View rowView = layout.getChildAt(i);
                langName = rowView.findViewById(R.id.langName);
                langLevel = rowView.findViewById(R.id.langLevel);
                langName.setText(con.languages.get(i).getName());
                langLevel.setText(con.languages.get(i).getLevel());;
            }
        }
        layout.setVisibility(View.VISIBLE);
    }


    public List<Skills> getSkills(LinearLayout layout) {
        List<Skills> skills = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount() - 1; i++) {
            Skills skillNam = new Skills();
            View v = layout.getChildAt(i);
            skillName = v.findViewById(R.id.skillName);
            skillNam.setName(skillName.getText().toString());
            skills.add(skillNam);
        }
        return skills;
    }

    public void setSkills(LinearLayout layout,ContactWithDetail con) {
        for (int i = 0; i < con.skills.size(); i++) {
            if (i != 0) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.skill, null);
                layout.addView(rowView, layout.getChildCount() - 1);
                skillName = rowView.findViewById(R.id.skillName);
                skillName.setText(con.skills.get(i).getName());
            } else {
                View v = layout.getChildAt(i);
                skillName = v.findViewById(R.id.skillName);
                skillName.setText(con.skills.get(i).getName());
            }
        }
        layout.setVisibility(View.VISIBLE);
    }

    public List<Education> getEducation(LinearLayout layout) {
        List<Education> edu = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount() - 1; i++) {
            Education education = new Education();
            View v = layout.getChildAt(i);
            location = v.findViewById(R.id.location);
            speciality = v.findViewById(R.id.specialty);
            univertsity = v.findViewById(R.id.university);
            graduatedYear = v.findViewById(R.id.graduated);
            education.setSpecialty(speciality.getText().toString());
            education.setLocation(location.getText().toString());
            education.setUniversity(univertsity.getText().toString());
            education.setEndDate(graduatedYear.getText().toString());
            edu.add(education);
        }
        return edu;
    }

    public void setEdu(LinearLayout layout,ContactWithDetail con) {

        for (int i = 0; i < con.educations.size(); i++) {
            if (i != 0) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.education, null);
                layout.addView(rowView, layout.getChildCount() - 1);
                location = rowView.findViewById(R.id.location);
                speciality = rowView.findViewById(R.id.specialty);
                univertsity = rowView.findViewById(R.id.university);
                graduatedYear = rowView.findViewById(R.id.graduated);
                location.setText(con.educations.get(i).getLocation());
                speciality.setText(con.educations.get(i).getSpecialty());
                univertsity.setText(con.educations.get(i).getUniversity());
                graduatedYear.setText(con.educations.get(i).getEndDate());
            } else {
                View rowView = layout.getChildAt(i);
                location = rowView.findViewById(R.id.location);
                speciality = rowView.findViewById(R.id.specialty);
                univertsity = rowView.findViewById(R.id.university);
                graduatedYear = rowView.findViewById(R.id.graduated);
                location.setText(con.educations.get(i).getLocation());
                speciality.setText(con.educations.get(i).getSpecialty());
                univertsity.setText(con.educations.get(i).getUniversity());
                graduatedYear.setText(con.educations.get(i).getEndDate());
            }
        }
        layout.setVisibility(View.VISIBLE);


    }

    public List<Experiance> getEperience(LinearLayout layout) {
        List<Experiance> eper = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount() - 1; i++) {
            Experiance experiance = new Experiance();
            View v = layout.getChildAt(i);
            position = v.findViewById(R.id.title_position);
            company = v.findViewById(R.id.company);
            compLocation = v.findViewById(R.id.comp_location);
            startDate = v.findViewById(R.id.workStartDate);
            endDate = v.findViewById(R.id.workEndDate);
            isWork = v.findViewById(R.id.isWork);
            experiance.setPosition(position.getText().toString());
            experiance.setCompany(company.getText().toString());
            experiance.setLocation(compLocation.getText().toString());
            experiance.setStartDate(startDate.getText().toString());
            experiance.setEndDate(endDate.getText().toString());
            experiance.setWork(isWork.isChecked());
            eper.add(experiance);
        }
        return eper;
    }

    public void setExper(LinearLayout layout,ContactWithDetail con) {
        for (int i = 0; i < con.experiances.size(); i++) {
            if (i != 0) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.experience, null);
                layout.addView(rowView, layout.getChildCount() - 1);
                position = rowView.findViewById(R.id.title_position);
                company = rowView.findViewById(R.id.company);
                compLocation =rowView.findViewById(R.id.comp_location);
                startDate = rowView.findViewById(R.id.workStartDate);
                endDate = rowView.findViewById(R.id.workEndDate);
                isWork = rowView.findViewById(R.id.isWork);

                position.setText(con.experiances.get(i).getPosition());
                company.setText(con.experiances.get(i).getCompany());
                compLocation.setText(con.experiances.get(i).getLocation());
                startDate.setText(con.experiances.get(i).getStartDate());
                endDate.setText(con.experiances.get(i).getEndDate());
                isWork.setChecked(con.experiances.get(i).isWork());
            } else {
                View rowView= layout.getChildAt(i);
                position = rowView.findViewById(R.id.title_position);
                company = rowView.findViewById(R.id.company);
                compLocation =rowView.findViewById(R.id.comp_location);
                startDate = rowView.findViewById(R.id.workStartDate);
                endDate = rowView.findViewById(R.id.workEndDate);
                isWork = rowView.findViewById(R.id.isWork);

                position.setText(con.experiances.get(i).getPosition());
                company.setText(con.experiances.get(i).getCompany());
                compLocation.setText(con.experiances.get(i).getLocation());
                startDate.setText(con.experiances.get(i).getStartDate());
                endDate.setText(con.experiances.get(i).getEndDate());
                isWork.setChecked(con.experiances.get(i).isWork());
            }
        }
        layout.setVisibility(View.VISIBLE);
    }

    public List<Project> getProject(LinearLayout layout) {
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount() - 1; i++) {
            Project pro = new Project();
            View v = layout.getChildAt(i);
            projectName = v.findViewById(R.id.projectName);
            pro.setName(projectName.getText().toString());
            projects.add(pro);
        }
        return projects;
    }

    public void setProject(LinearLayout layout,ContactWithDetail con) {
        for (int i = 0; i < con.projects.size(); i++) {
            if (i != 0) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.projects, null);
                layout.addView(rowView, layout.getChildCount() - 1);
                projectName = rowView.findViewById(R.id.projectName);
                projectName.setText(con.projects.get(i).getName());
            } else {
                View v = layout.getChildAt(i);
                projectName = v.findViewById(R.id.projectName);
                projectName.setText(con.skills.get(i).getName());
            }
        }
        layout.setVisibility(View.VISIBLE);
    }

    public List<Certificate> getCertificate(LinearLayout layout) {
        List<Certificate> certs = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount() - 1; i++) {
            Certificate cert = new Certificate();
            View v = layout.getChildAt(i);
            certName = v.findViewById(R.id.certName);
            cert.setName(certName.getText().toString());
            certs.add(cert);
        }
        return certs;
    }

    public void setCert(LinearLayout layout,ContactWithDetail con) {
        for (int i = 0; i < con.certs.size(); i++) {
            if (i != 0) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.certificate, null);
                layout.addView(rowView, layout.getChildCount() - 1);
                certName = rowView.findViewById(R.id.certName);
                certName.setText(con.certs.get(i).getName());
            } else {
                View v = layout.getChildAt(i);
                certName = v.findViewById(R.id.certName);
                certName.setText(con.certs.get(i).getName());
            }
        }
        layout.setVisibility(View.VISIBLE);
    }

    public Contact getContact() {
        contact.setFirstName(firstName.getText().toString());
        contact.setLastName(lastName.getText().toString());
        contact.setAddress(address.getText().toString());
        contact.setPhone(phone.getText().toString());
        contact.setEmail(email.getText().toString());
        contact.setDescription(description.getText().toString());
        contact.setBirthDate(birthDate.getText().toString());
        contact.setSosialMedia(sosialMedia.getText().toString());
        contact.setNationality(nationality.getText().toString());
        contact.setOccupation(occupation.getText().toString());
        contact.setPath(email.getText().toString() + ".pdf");
        contact.setImage(common.ImageToByte(image, 120, 140));
        return contact;
    }

    public void setContact(ContactWithDetail con) {
        contact.setId(cont.contact.getId());
        firstName.setText(cont.contact.getFirstName());
        lastName.setText(cont.contact.getLastName());
        address.setText(cont.contact.getAddress());
        phone.setText(cont.contact.getPhone());
        email.setText(cont.contact.getEmail());
        description.setText(cont.contact.getDescription());
        birthDate.setText(cont.contact.getBirthDate());
        sosialMedia.setText(cont.contact.getSosialMedia());
        nationality.setText(cont.contact.getNationality());
        occupation.setText(cont.contact.getOccupation());
        image.setImageBitmap(common.ConvertByteToBitmap(cont.contact.getImage()));
    }



}
