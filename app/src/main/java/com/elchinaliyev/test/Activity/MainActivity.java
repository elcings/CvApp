package com.elchinaliyev.test.Activity;

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
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.elchinaliyev.test.Adapter.CvAdapter;
import com.elchinaliyev.test.Common.Common;
import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.R;
import com.elchinaliyev.test.ViewModel.CvViewModel;
import com.elchinaliyev.test.ViewModel.CvViewModelList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CvAdapter.OnContactListener {
    CvViewModelList cvViewModelList;
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
        cvViewModelList.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                for(Contact con:contacts) {
                    Log.d("ContactID", con.getId()+""+con.getLastName());
                }
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
                cvViewModel.delete(contact);
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

    @Override
    public void OnContactClick(int position) {
        Intent intent = new Intent(MainActivity.this, CvAddActivity.class);
        intent.putExtra("contactId",contactList.get(position).getId());
        startActivity(intent);
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
        cvViewModelList = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CvViewModelList.class);
        cvViewModel=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CvViewModel.class);
    }

    private void setupRecyclerView() {
        adapter = new CvAdapter(MainActivity.this, contactList,MainActivity.this);
        adapter.notifyDataSetChanged();
        recView.setAdapter(adapter);
    }


}

