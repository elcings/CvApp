package com.elchinaliyev.test.Model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;


public class Common {

    /*public static Bitmap resize(InputStream is, int newWidth, int newHeight) {
        Bitmap bm=BitmapFactory.decodeStream(is);
        int nh = (int) ( bm.getHeight() * (512.0 / bm.getWidth()) );
        Bitmap resize = Bitmap.createScaledBitmap(bm,newWidth,newHeight,true);
        return  resize;
    }*/

    public byte[] ImageToByte(ImageView imageView, int newWidth, int newHeight) {
        Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Bitmap resize = Bitmap.createScaledBitmap(bm, newWidth, newHeight, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        resize.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }
}
