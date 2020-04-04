package com.elchinaliyev.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfActivity extends AppCompatActivity {

    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdfView=findViewById(R.id.pdfView);
        Log.d("Extras",getIntent().getStringExtra("path"));
      if(getIntent().hasExtra("path")) {
          String path = getIntent().getStringExtra("path");
          String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              requestPermissions(permissions, 2);
              final File file = new File(Environment.getExternalStorageDirectory()
                      .getAbsolutePath(), path);
              if (file.exists()) {
                  pdfView.fromFile(file).load();
              }
          }
      }
    }
}
