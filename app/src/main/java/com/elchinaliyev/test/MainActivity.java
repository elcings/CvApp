package com.elchinaliyev.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elchinaliyev.test.Model.Common;
import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.Report.CV;
import com.elchinaliyev.test.ViewModel.CvViewModel;
import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
;

public class MainActivity extends AppCompatActivity {
    EditText txt;
    Button btn, btnSave;
    PDFView pdfView;
    CvViewModel cvViewModel;
    Common common;
    StringBuilder fileName;


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdfView = findViewById(R.id.pdfView);
        btn = findViewById(R.id.showCv);
        btnSave = findViewById(R.id.saveCv);
        common = new Common();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream ims = getAssets().open("el.jpg");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                    Bitmap bmp = common.resize(ims, 100, 130);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    cvViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CvViewModel.class);
                    Contact contact = new Contact();
                    contact.setFirstName("Elchin");
                    contact.setLastName("Aliyev");
                    contact.setEmail("elcinaliyevgs@gmail.com");
                    contact.setBirthDate(dateFormat.format(new Date()));
                    contact.setAddress("Masazir,Qurtulus 93");
                    contact.setNationality("azerbaijanian");
                    contact.setOccupation("Software Developer");
                    contact.setPhone("994773608282");
                    contact.setDescription("Elcin Aliyev.Developer jakdjaldkajdl ajdalkdjalkdj akdajldkajdlkaj dlakjdakld jlkajdladl. tuytutytyutytt. tttttttttttttttttttt.ttttttttttttttttttt.");
                    contact.setSosialMedia("https://www.linkedin.com/feed/");
                    contact.setImage(stream.toByteArray());
                    cvViewModel.insert(contact);
                    cvViewModel.getTopOneId().observe(MainActivity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer integer) {
                            Toast.makeText(MainActivity.this,integer+"",Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* cvViewModel.getContactAll().observe(MainActivity.this, new Observer<List<Contact>>() {
                    @Override
                    public void onChanged(List<Contact> contacts) {
                        if(contacts!=null)
                        Log.d("Contact",contacts.size()+"");
                        else
                        {
                            Log.d("Contact","Yox");
                        }

                    }
                });*/


                cvViewModel.getById(1).observe(MainActivity.this, new Observer<Contact>() {
                    @Override
                    public void onChanged(Contact contact) {
                        fileName = new StringBuilder();
                        Document document = new Document();
                        fileName.append("cv45.pdf");
                        String output = Environment.getExternalStorageDirectory() + "/" + fileName.toString();
                        try {
                            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(permissions, 1);
                                CV myCV = new CV(MainActivity.this, contact);
                                final File file = new File(Environment.getExternalStorageDirectory()
                                        .getAbsolutePath(), fileName.toString());
                                if (file.exists()) {
                                    file.delete();
                                    myCV.GeneratePdf(output);
                                } else {
                                    myCV.GeneratePdf(output);
                                }
                            }

                        } catch (DocumentException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(permissions, 2);
                            final File file = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath(), fileName.toString());
                            if (file.exists()) {
                                pdfView.fromFile(file).load();
                            }


                        }
                    }
                });
            }
        });


    }
}
