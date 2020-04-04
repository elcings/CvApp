package com.elchinaliyev.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import com.elchinaliyev.test.Adapter.CvAdapter;
import com.elchinaliyev.test.Model.Common;
import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.ViewModel.CvViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    CvViewModel cvViewModel;
    Common common;
    RecyclerView recView;
    CvAdapter adapter;
    List<Contact> contactList;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        cvViewModel.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactList = contacts;
                setupRecyclerView();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact contact = adapter.getContactAt(viewHolder.getAdapterPosition());
                cvViewModel.deleteSkillByConId(contact.getId());
                cvViewModel.deleteCertConById(contact.getId());
                cvViewModel.deleteEduByConId(contact.getId());
                cvViewModel.deleteLangByConId(contact.getId());
                cvViewModel.deleteProjectByConId(contact.getId());
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, 2);
                    final File file = new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath(), contact.getPath());
                    if (file.exists()) {
                        file.delete();
                    }
                }
                cvViewModel.delete(contact);
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recView);
    }

    public void onAddActivity(View v) {
        Intent intent = new Intent(MainActivity.this, CvAddActivity.class);
        startActivity(intent);
    }

    void init() {
        common = new Common();
        contactList = new ArrayList<>();
        recView = findViewById(R.id.recView);
        floatingActionButton = findViewById(R.id.fltBtn);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setItemAnimator(new DefaultItemAnimator());
        recView.setNestedScrollingEnabled(true);
        cvViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CvViewModel.class);
    }

    private void setupRecyclerView() {
        adapter = new CvAdapter(MainActivity.this, contactList);
        adapter.notifyDataSetChanged();
        recView.setAdapter(adapter);
    }

}

