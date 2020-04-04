package com.elchinaliyev.test.Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.elchinaliyev.test.Model.Contact;
import com.elchinaliyev.test.PdfActivity;
import com.elchinaliyev.test.R;

import java.io.File;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CvAdapter extends RecyclerView.Adapter<CvAdapter.ViewHolder> {

    Context context;
    private List<Contact>list;

    public CvAdapter(Context context,List<Contact> list) {
        this.context=context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view  =inflater.inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
      if(list.size()>0)
      {
          String full=list.get(position).getFirstName()+" "+list.get(position).getLastName();
          holder.fullName.setText(full);
          holder.occupation.setText(list.get(position).getOccupation());
          byte[]image=list.get(position).getImage();
          Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
          holder.imageView.setImageBitmap(bitmap);
          holder.digit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  PopupMenu popupMenu=new PopupMenu(context,holder.digit);
                  popupMenu.inflate(R.menu.mainmenu);
                  popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                      @Override
                      public boolean onMenuItemClick(MenuItem item) {
                          int id=item.getItemId();
                          switch (id)
                          {
                              case R.id.showCv:
                               showCV(position);
                               return true;
                              case R.id.share:
                                  share(position);
                               default:
                                   break;
                          }
                          return false;
                      }
                  });
                  popupMenu.show();
              }
          });
      }
    }
    private void showCV(int position) {
        Intent pdf = new Intent(context, PdfActivity.class);
        pdf.putExtra("path",list.get(position).getPath());
        context.startActivity(pdf);
    }

    private void share(int position) {

        final File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), list.get(position).getPath());
        if (file.exists()) {
            Intent shareIntent = new Intent();
            shareIntent.setPackage("com.whatsapp");
            shareIntent.setType("application/pdf");
            shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
            try {
                Objects.requireNonNull(context).startActivity(shareIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
            }
            context.startActivity(Intent.createChooser(shareIntent, " File send to ...."));
        }
    }


    public Contact getContactAt(int position) {
        return list.get(position);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView fullName,occupation,digit;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName=itemView.findViewById(R.id.fullName);
            occupation=itemView.findViewById(R.id.occup);
            digit=itemView.findViewById(R.id.optionDigit);
            imageView=itemView.findViewById(R.id.personImage);
        }
    }
}
